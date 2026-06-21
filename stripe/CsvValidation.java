package stripe;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

// Check this: https://chatgpt.com/share/6a3677fd-19a0-83e8-b351-ec83e12261df

interface FieldValidator {
    Optional<String> validate(String value);
}

class RequiredValidator implements FieldValidator {

    @Override
    public Optional<String> validate(String value) {
        if(value.isEmpty()){
            return Optional.of("Field is required");
        }
        return Optional.empty();
    }
}

class EmailValidator implements FieldValidator {

    @Override
    public Optional<String> validate(String value) {
        if(value.isEmpty() || value.contains("@") && value.contains(".")){
            return Optional.empty();
        }
        return Optional.of("Email is invalid");
    }
}

class PhoneValidator implements FieldValidator {
    @Override
    public Optional<String> validate(String value) {
        try{
            BigInteger phone = new BigInteger(value);
            if(value.length()==10){
                return Optional.empty();
            }
            return Optional.of("Phone number should be of 10 digiits");
        } catch(NumberFormatException e){
            return Optional.of("Phone number should be digits");
        }
    }
}

class NumbericValidator implements FieldValidator {
    @Override
    public Optional<String> validate(String value) {
        try{
            BigInteger phone = new BigInteger(value);
            return Optional.empty();
        } catch(NumberFormatException e){
            return Optional.of("Should be numeric");
        }
    }
}

record ValidationError(int row, String field, String message){};

record Row(int rowNumber, Map<String, String> values){};
record RowValidation(int row, List<String> errors){};

public class CsvValidation {
    
    private final Map<String, List<FieldValidator>> fieldValidators;

    public CsvValidation(Map<String, List<FieldValidator>> fieldValidators){
        this.fieldValidators = fieldValidators;
    }

    public List<ValidationError> validate(BufferedReader reader) throws IOException{
        List<ValidationError> result = new ArrayList<>();
        String header = reader.readLine();
        if(header==null || header.isEmpty()){
            return result;
        }

        String[] columns = header.split(",");
        String line;
        int rowNumber = 1;
        while((line=reader.readLine())!=null){
            rowNumber++;

            String[] values =
                    line.split(",", -1);
            if(values.length!=columns.length){
                result.add(new ValidationError(rowNumber, "ROW", "Columns count mismatch"));
                continue;
            }
            for(int i=0;i<columns.length;i++){
                List<FieldValidator> validators = fieldValidators.getOrDefault(columns[i].trim(), Collections.emptyList());
                for(FieldValidator val: validators){
                    Optional<String> optionalError = val.validate(values[i].trim());
                    if(!optionalError.isEmpty()){
                        result.add(new ValidationError(rowNumber, columns[i].trim(), optionalError.get()));
                    }
                }
            }
        }

        return result;
    }

    public List<RowValidation> validateOptimised(List<String> rows){
        Map<Integer, List<FieldValidator>> validations = new HashMap<>();
        validations.put(1, List.of(new RequiredValidator(), new NumbericValidator()));
        validations.put(2, List.of(new RequiredValidator(), new EmailValidator()));
        validations.put(3, List.of(new RequiredValidator(), new NumbericValidator(), new PhoneValidator()));
        Map<Integer, List<String>> validationMap = new HashMap<>();
        List<RowValidation> result = new ArrayList<>();
        for(int i=0;i<rows.size();i++){
            String r = rows.get(i);
            String[] parts = r.split(",", 3);
            for(int j=0;j<parts.length;j++){
                List<FieldValidator> toValidate = validations.get(j+1);
                List<String> violations = new ArrayList<>();
                for(FieldValidator ob: toValidate){
                    Optional<String> ans = ob.validate(parts[j]);
                    if(!ans.isEmpty()){
                        violations.add(ans.get());
                    }
                }
                validationMap.computeIfAbsent(i+1, k -> new ArrayList<>()).addAll(violations);
            }
        }
        for(Map.Entry<Integer, List<String>> entry: validationMap.entrySet()){
            if(!entry.getValue().isEmpty())
                result.add(new RowValidation(entry.getKey(), entry.getValue()));
        }
        return result;
    }
    public List<RowValidation> validate(List<String> rows){
        List<RowValidation> result = new ArrayList<>();
        for(int i=0;i<rows.size();i++){
            String r = rows.get(i);
            String parts[] = r.split(",", 3);
            List<String> valFail = new ArrayList<>();
            if(parts.length<3){
                valFail.add("Invalid row");
                RowValidation val = new RowValidation(i+1, valFail);
                result.add(val);
                continue;
            }
            if(parts[0].isEmpty()){
                valFail.add("UserId is required");
            } else{
                try{
                    BigInteger userId = new BigInteger(parts[0].trim());
                } catch(NumberFormatException e){
                    valFail.add("UserId should be integer");
                }
            }

            if(parts[1].isEmpty()){
                valFail.add("Email is required");
            } else if(!(parts[1].contains("@") && parts[1].contains(".com"))) {
                valFail.add("Must be a valid Email");
            }

            if(parts[2].isEmpty()){
                valFail.add("Phone is required");
            } else{
                try{
                    BigInteger bigInteger = new BigInteger(parts[2]);
                    if(parts[2].length()!=10){
                        valFail.add("Phone number should be 10 digits");
                    }
                } catch(NumberFormatException e){
                    valFail.add("Phone number must be digits");
                }
            }
            if(valFail.isEmpty()){
                continue;
            }
            RowValidation val = new RowValidation(i+1, valFail);
            result.add(val);
        }
        return result;
    }

    public static void main(String[] args) {
        CsvValidation obj = new CsvValidation();
        List<String> input = Arrays.asList(
            "1,john@gmail.com,9876543210", 
            "2,invalid-email,123", 
            "3,,9999999999", 
            "4,jane@gmail.com,"
        );
        List<RowValidation> result = obj.validateOptimised(input);
        for(RowValidation v: result){
            System.out.println(v.row() + " , " + v.errors());
        }
    }
}
