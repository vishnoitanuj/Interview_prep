package stripe;
import java.util.*;


public class MoneyMovement {
    
    record Output(String from, String to, int amount){};
    class Amount {
        String country; 
        int amount;

        Amount(String country, int amount){
            this.country = country;
            this.amount = amount;
        }
    };
    public List<Output> settleMoney(Map<String, Integer> input){
        List<Amount> extraAmounts = new ArrayList<>();
        List<Amount> devoidAmounts = new ArrayList<>();
        int totalExtra = 0, totalDevoid = 0;

        for(Map.Entry<String, Integer> entry: input.entrySet()){
            if(entry.getValue()>100){
                extraAmounts.add(new Amount(entry.getKey(), entry.getValue()-100));
                totalExtra+=entry.getValue()-100;
            } else if(entry.getValue()<100){
                devoidAmounts.add(new Amount(entry.getKey(), 100 - entry.getValue()));
                totalDevoid+=100-entry.getValue();
            }
        }

        if(totalDevoid>totalExtra){
            throw new RuntimeException("Cannot be settled");
        }

        // extraAmounts.sort((a,b) -> b.amount - a.amount);
        // devoidAmounts.sort((a,b) -> b.amount - a.amount);
        int extraIdx = 0, devoidIdx = 0;
        List<Output> outputs = new ArrayList<>();
        while(devoidIdx<devoidAmounts.size()){
            Amount extra = extraAmounts.get(extraIdx);
            Amount devoid = devoidAmounts.get(devoidIdx);
            
            int donorAmount = Math.min(extra.amount, devoid.amount);
            Output output = new Output(extra.country, devoid.country, donorAmount);
            outputs.add(output);
            extra.amount -= donorAmount;
            devoid.amount -= donorAmount;
            if(extra.amount==0){
                extraIdx++;
            } 
            if(devoid.amount==0){
                devoidIdx++;
            }
        }
        return outputs;
    }
    public static void main(String[] args) {
        Map<String, Integer> input = new HashMap<>();
        input.put("AU", 80);
        input.put("US", 140);
        input.put("MX", 110);
        input.put("SG", 120);
        input.put("FR", 70);
        MoneyMovement obj = new MoneyMovement();
        List<Output> outputs = obj.settleMoney(input);
        System.out.println(outputs.toString());
    }
}
