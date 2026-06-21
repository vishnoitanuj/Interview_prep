package stripe;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
public class MinPenality {

    public List<Integer> calcPenality(String na){
        int penality=0;
        for(char ch: na.toCharArray()){
            if(ch=='Y'){
                penality++;
            }
        }

        // int[] penalities = new int[na.length()+1];
        int minPenality = penality;
        // penalities[0] = penality;
        for(int i=0;i<na.length();i++){
            char ch = na.charAt(i);
            if(ch=='Y'){
                penality--;
            } else{
                penality++;
            }
            // penalities[i+1]=penality;

            minPenality = Math.min(penality, minPenality);

        }

        List<Integer> result = new ArrayList<>();
        for(int i=0;i<na.length();i++){
            char ch = na.charAt(i);
            if(ch=='Y'){
                penality--;
            } else{
                penality++;
            }
            if(penality==minPenality){
                result.add(i+1);
            }
        }
        
        // for(int i=0;i<penalities.length;i++){
        //     if(penalities[i]==minPenality){
        //         result.add(i);
        //     }
        // }
        return result;
    }

    private final LinkedList<Integer> penalities = new LinkedList<>();
    public int bestTime=0;
    public void streamingBestClosingTime(BufferedReader reader) throws IOException{
        int charValue;
        int penality = (penalities.isEmpty())?0:penalities.getLast();
        int minPenality = penality;
        int idx=bestTime;
        while((charValue = reader.read()) != -1){
            if(charValue == 'Y'){
                penality++;
                
            } else{
                penality--;
            }
            penality = Math.min(penality, 0);
            if(penality<minPenality){
                minPenality = Math.min(penality, minPenality);
                bestTime = ++idx;
            }
            penalities.add(minPenality);
        }
    }

    public static void main(String[] args) throws Exception {
        MinPenality ob = new MinPenality();
        System.out.println(ob.calcPenality("YYNN"));

        BufferedReader reader = new BufferedReader(new java.io.StringReader("YYNN"));
        ob.streamingBestClosingTime(reader);
        System.out.println(ob.bestTime);
    }
    
}
