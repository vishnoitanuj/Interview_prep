package Hashmap;

import java.util.HashMap;
import java.util.Set;
public class annagram{
    
	public static int makeAnagram(String s1,String s2){
        HashMap<Character, Integer> map=new HashMap<>();
        for(int i=0;i<s1.length();i++){
            char ch=s1.charAt(i);
            if(map.containsKey(ch))
                map.put(ch, map.get(ch)+1);
            else
                map.put(ch,1);
        }
        int c=0;
        for(int i=0;i<s2.length();i++){
            char ch=s2.charAt(i);
            if(!map.containsKey(ch))
                c++;
            else{
                map.put(ch, map.get(ch)-1);
                if(map.get(ch)==0)
                    map.remove(ch);
            }
        }
        Set<Character> keys=map.keySet();
        for(char i:keys){
            c+=map.get(i);
        }
        return c;        
	}

}
