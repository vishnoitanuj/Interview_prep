package stripe;

import java.util.*;
public class PaymentCardValidation {
    
    private boolean luhnAlgorithm(String n){
        int sum = 0;
        for(int i=0;i<n.length();i++){
            int digit = n.charAt(i) - '0';
            int posRight = n.length()-i;
            if(posRight%2==0){
                digit*=2;
                digit = (digit>9) ? digit-9 : digit;
            }
            sum+=digit;
        }
        return sum%10==0;
    }

    public String visaValidation(String n){
        if(n.length()==16 && n.charAt(0)=='4' && luhnAlgorithm(n)){
            return "VISA";
        } else{
            return "INVALID_CHECKSUM";
        }
    }

    public String multiNetworkValidation(String n){
        String network = null;
        if(n.length()==16){
            if(n.charAt(0)=='4'){
                network = "VISA";
            } else if(n.charAt(0)=='5' && n.charAt(1)>='1' && n.charAt(1)<='5'){
                network= "MASTERCARD";
            }
        } else if(n.length()==15){
            if(n.charAt(0)=='3' && (n.charAt(1)=='4' || n.charAt(1)=='7')){
                network = "AMEX";
            }
        }

        if(network==null){
            return "UNKNOWN_NETWORK";
        }

        return luhnAlgorithm(n) ? network : "INVALID_CHECKSUM";

    }
    
    private Map<String, Integer> count(String s){
        Map<String, Integer> result = new HashMap<>();
        char[] arr = s.toCharArray();
        StringBuffer sb = new StringBuffer();
        dfs(arr, 0, 0, 0, sb, result);
        return result;
    }

    private void dfs(char[] arr, // character array
        int idx,    // current index
        int sum,
        int parity,
        StringBuffer sb,
        Map<String, Integer> result){

        if(idx==arr.length){
            if(sum%10==0){
                String card = sb.toString();
                classifyCard(card, result);
            }
            return;
        }

        if(arr[idx]!='*'){
            int digit = arr[idx] - '0';
            int val = luhnTransform(digit, parity);
            sb.append(arr[idx]);
            dfs(arr, idx+1, sum+val, parity+1, sb, result);
            sb.deleteCharAt(sb.length()-1);
            return;
        }
        for(char i='0';i<='9';i++){
            int digit = i - '0';
            int val = luhnTransform(digit, parity);
            sb.append(i);
            dfs(arr, idx+1, sum+val, parity+1, sb, result);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    private int luhnTransform(int digit, int parity){
        int val = digit;
        if(parity%2==1){
            val *=2;
        }
        if(val>9){
            return val-9;
        }
        return val;
    }

    private void classifyCard(String card, Map<String, Integer> result){
        int n = card.length();
        if(n==16){
            if(card.charAt(0)=='4'){
                result.merge("VISA", 1, Integer::sum);
            } else {
                int prefix = Integer.parseInt(card.substring(0, 2));
                if(prefix>=51 && prefix<=55){
                    result.merge("MASTERCARD", 1, Integer::sum);
                }
            }
        } else if(n==15){
            int prefix = Integer.parseInt(card.substring(0, 2));
            if(prefix==34 || prefix==37){
                result.merge("AMEX", 1, Integer::sum);
            }
        }
    }
    public static void main(String[] args){
        PaymentCardValidation ob = new PaymentCardValidation();
        // System.out.println(ob.visaValidation("4532015112830366"));
        // System.out.println(ob.multiNetworkValidation("5482334509943"));
        // System.out.println(ob.multiNetworkValidation("4425233430109994"));
        // System.out.println(ob.multiNetworkValidation("562523343010901"));
        // System.out.println(ob.multiNetworkValidation("4532015112830366"));
        Map<String, Integer> map = ob.count("**2424242424242");
        for(Map.Entry<String, Integer> entry: map.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
