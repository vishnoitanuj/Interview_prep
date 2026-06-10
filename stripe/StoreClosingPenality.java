package stripe;

import java.util.*;

public class StoreClosingPenality {
    
    private int computePenality(List<String> log, int closingTime){
        int penality=0;
        for(int i=0;i<log.size();i++){
            if(i<closingTime && "N".equals(log.get(i))) {
                penality++;
            } else if(i>=closingTime && "Y".equals(log.get(i))){
                penality++;
            }
        }
        return penality;
    }

    private int bestClosingTime(List<String> log){
        int[] penality=new int[log.size()+1];
        for(int i=0;i<=log.size();i++){
            penality[i]=computePenality(log, i);
        }
        int min=penality[0];
        int min_index=0;
        for(int i=1;i<=log.size();i++){
            if(penality[i]<min){
                min = penality[i];
                min_index = i;
            }
        }
        return min_index;
    }

    private int bestClosingTimeOptimised(String log){
        int penality = 0;
        for(char c: log.toCharArray()){
            if(c=='Y'){
                penality++;
            }
        }

        int minPenality = penality;
        int bestTime = 0;
        for(int i=0;i<log.length();i++){
            if(log.charAt(i) == 'Y'){
                penality--;
            } else{
                penality++;
            }

            if (penality < minPenality) {
                minPenality = penality;
                bestTime = i + 1;
            }
        }
        return bestTime;
    }

    private List<Integer> getBestClosingTimes(String aggregateLog){
        List<Integer> bestClosingTimes = new ArrayList<>();
        Scanner sc = new Scanner(aggregateLog);
        boolean insideLog = false, invalidLog = false;

        StringBuilder sb = new StringBuilder();
        while(sc.hasNext()){
            String token = sc.next();

            switch (token) {
                case "BEGIN":
                    if(!insideLog){
                        insideLog = true;
                        invalidLog = false;
                        sb.setLength(0);
                    } else {
                        invalidLog = true;
                    }
                    break;
                case "END":
                    if(insideLog){
                        if(!invalidLog){
                            bestClosingTimes.add(bestClosingTimeOptimised(sb.toString()));
                        }
                        insideLog = false;
                        invalidLog = false;
                        sb.setLength(0);
                    }
                    break;
                case "Y":
                case "N":
                    if(insideLog && !invalidLog){
                        sb.append(token);
                    }
                    break;
                default:
                    break;
                    
            }

        }
        sc.close();
        return bestClosingTimes;
    }
}
