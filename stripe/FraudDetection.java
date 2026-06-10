package stripe;

import java.util.*;

public class FraudDetection {

    record Charge(String type, String chargeId, String accountId, int amount, String code){};
    
    private static final Set<String> nonFradulentCodes = Set.of("approved","invalid_pin","expired_card");
    private static final Set<String> fradulentCodes = Set.of("do_not_honor","stolen_card","lost_card");

    private List<String> getFraudMerchants(Map<String, Double> mccToFraudThresholdMap, 
        Map<String, String> accountToMccMap, int n, List<Charge> charges){
        
        Map<String, Integer> fraudCount = new HashMap<>();
        Map<String, Integer> totalCount = new HashMap<>();
        Map<String, String> chargeIdToAccountMap = new HashMap<>();

        Set<String> fraudChargeIds = new HashSet<>();
        Set<String> fraudMerchants = new HashSet<>();

        for(Charge charge: charges){
            String accountId = charge.accountId();
            String chargeType = charge.type();
            String chargeId = charge.chargeId();

            if("DISPUTE".equals(chargeType) && fraudChargeIds.contains(chargeId)){
                String disputeAcc = chargeIdToAccountMap.get(chargeId);
                accountId = disputeAcc;
                fraudCount.merge(disputeAcc, -1, Integer::sum);
                fraudChargeIds.remove(chargeId);
            } else if("CHARGE".equals(chargeType)){
                totalCount.merge(accountId, 1, Integer::sum);
                if(fradulentCodes.contains(charge.code())){
                    fraudCount.merge(accountId, 1, Integer::sum);
                    fraudChargeIds.add(chargeId);
                }
                chargeIdToAccountMap.put(chargeId, accountId);
            }

            if(!totalCount.containsKey(accountId)){
                continue;
            }

            int total = totalCount.get(accountId);
            
            if(total<n){
                continue;
            }
            String mccCode = accountToMccMap.get(accountId);
            if(mccCode == null || !mccToFraudThresholdMap.containsKey(mccCode)){
                continue;
            }

            int frauds = fraudCount.getOrDefault(accountId, 0);

            double ratio = (double) frauds/total;
            if(ratio>=mccToFraudThresholdMap.get(mccCode)){
                fraudMerchants.add(accountId);
            } else{
                fraudMerchants.remove(accountId);
            }
        }
        List<String> resuList = new ArrayList<>(fraudMerchants);
        Collections.sort(resuList);
        return resuList;
    }
}
