package stripe;

import java.math.BigInteger;
import java.util.*;

public class CsvValidation {
    
    public static record RowValidation(int row, List<String> errors){}

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
        List<RowValidation> result = obj.validate(input);
        for(RowValidation v: result){
            System.out.println(v.row() + " , " + v.errors());
        }
    }
}
