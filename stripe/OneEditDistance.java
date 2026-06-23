package stripe;
import java.util.*;
public class OneEditDistance {
   
    public static void main(String[] args) {
        Solution ob = new Solution();
        System.out.println(ob.isOneEditDistance("ab", "acb"));
    }
}

class Solution {
    public boolean isOneEditDistance(String s, String t) {
        if(s.length()>t.length()){
            return isOneEditDistance(t,s);
        }

        if(t.length()-s.length()>1){
            return false;
        }

        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!=t.charAt(i)){

                if(s.length()==t.length()){
                    return s.substring(i+1).equals(t.substring(i+1));
                }

                return t.substring(i+1).equals(s.substring(i));
            }
        }
        return false;
    }
}