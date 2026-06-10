package stripe;

/*
Problem 3: Catch Me If You Can - Fraud Detection
Background

Stripe processes billions of dollars worth of transactions every day. Our job is to protect customers and legitimate merchants by detecting and blocking fraudulent transactions. We will build a simplified fraud detection model that marks merchants as fraudulent if too many of their transactions are suspicious. The problem is split into three parts.

Part 1: Count-Based Fraud Detection

Each merchant has a Merchant Consumer Code (MCC) that represents their industry (e.g., retail, airline). Each MCC has an associated fraud threshold (integer > 1) that indicates the maximum allowed number of fraudulent transactions before the merchant is marked as fraudulent.

We are given

A comma-separated list of non-fraudulent codes (e.g., "approved,invalid_pin,expired_card").

A comma-separated list of fraudulent codes (e.g., "do_not_honor,stolen_card,lost_card").

A table of MCCs with their fraud thresholds: MCC,threshold (one per line).

A table of merchants with their MCCs: account_id,MCC.

The minimum number of total transactions we must observe before evaluating a merchant (integer ≥ 0).

A table of charges: CHARGE,charge_id,account_id,amount,code

Output

Return a lexicographically sorted, comma-separated list of fraudulent merchants (by account_id).

Part 2: Percentage-Based Fraud Detection

Count-based thresholds can unfairly mark high-volume merchants as fraudulent. Instead, use a percentage threshold:

Each MCC now has a fraction between 0 and 1 indicating the maximum allowed fraction of fraudulent transactions.

If a merchant’s fraud percentage ≥ threshold, mark them as fraudulent.

Merchants stay fraudulent even if their fraud percentage later decreases.

Only evaluate merchants after seeing at least the minimum number of total transactions.

Inputs remain the same as Part 1, except the MCC table now contains fractions.

Part 3: Dispute Resolution

Sometimes transactions are incorrectly marked as fraudulent. We now support disputes which overturn the fraudulent status of a specific transaction.

Input now may include lines like: DISPUTE,charge_id

When a dispute is present, that transaction is treated as not fraudulent for all calculations. If a merchant was marked fraudulent solely due to disputed transactions, they may return to non-fraudulent status until they cross the threshold again with new transactions.
*/
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
