package stripe;
import java.util.*;

public class KycValidation {
    
    private static final List<String> blockList = List.of("ONLINE STORE", "ECOMMERCE, RETAIL", "SHOP", "GENERAL MERCHANDISE");
    static class Company {
        String business_name;
        String business_profile_name;
        String full_statement_descriptor;
        String short_statement_descriptor;
        String url;
        String product_description;
        Boolean verified;
    }

    public boolean verifyBasic(Company company){
        if(hasBlankFields(company)){
            return false;
        }
        return true;
        // System.out.println(String.format("%s: %s", company.verified, company.business_name));
    }

    public boolean descriptorValidation(Company c){
        return c.full_statement_descriptor.length()>=5 && c.full_statement_descriptor.length()<=31;
    }

    public boolean hasBlankFields(Company c){
        return c.business_name.isBlank() || c.business_profile_name.isBlank() || c.full_statement_descriptor.isBlank()
            || c.short_statement_descriptor.isBlank() || c.url.isBlank() || c.product_description.isBlank();
    }

    public boolean validateBlockList(Company c){
        return blockList.stream().anyMatch(e -> e.equalsIgnoreCase(c.full_statement_descriptor));
    }

    public static void main(String[] args) {
        KycValidation obj = new KycValidation();
        List<Company> companies = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(Company c: companies){
            if(obj.verifyBasic(c) && obj.descriptorValidation(c) && obj.validateBlockList(c)){
                sb.append(String.format("VERIFIED: %s ", c.business_name));
            } else{
                sb.append(String.format("NOT VERIFIED VERIFIED: %s ", c.business_name));
            }
        }
        System.out.println(sb.toString());
    }
}
