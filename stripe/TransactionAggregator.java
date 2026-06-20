package stripe;

import java.util.*;;

public class TransactionAggregator {
    
    public record Transaction(String txnId, String userId, Double amount){

        public boolean isValid(){
            return !txnId.isEmpty() && !userId.isEmpty() && !amount.isNaN();
        }
    };

    public Map<String, Double> amountPerUser(List<Transaction> transactions){
        Map<String, Double> result = new HashMap<>();
        for(Transaction transaction: transactions){
            if(!transaction.isValid()){

            }
            result.put(transaction.userId(), result.getOrDefault(transaction.userId(), 0.0)+transaction.amount());
        }
        return result;
    }

    public void print(Map<String, Double> result){
        for(Map.Entry<String, Double> entry: result.entrySet()){
            System.out.println(entry.getKey()+ " : "+ String.format("%.2f", entry.getValue()));
        }
    }

    public static void main(String[] args) {
        TransactionAggregator obj = new TransactionAggregator();
        Transaction t1 = new Transaction("txn1","user1",0.1);
        Transaction t2 = new Transaction("txn2","user1",0.2);
        Map<String, Double> res = obj.amountPerUser(List.of(t1, t2));
        obj.print(res);
    }
}

// Ref: https://chatgpt.com/share/6a364dc6-b804-83ee-a735-c6b4b62ccdcc

// avoid duplicate txns --> Maintain set of txnIds

// Precision: 

/*
I would not use double for monetary values because floating-point arithmetic introduces precision errors. I'd either:
Store money in the smallest currency unit (cents) using long
record Transaction(String txnId, String userId, long amountInCents)
or
Use BigDecimal if multiple currencies and fractional units are required.
For a payments company, storing integer cents is typically preferred because it's faster, exact, and avoids rounding issues.
*/
// Duplicate txn with different userId/amount --> Throw error --> If the same transaction ID appears with different payloads, that indicates data corruption, replay bugs, or an upstream consistency issue. I would not silently choose one version. I'd raise an error, log the conflicting records, and stop processing (or quarantine the record depending on requirements).

// Scale 1 million record ---> 
/*

Initially I'd rely on row-level locking because it guarantees correctness and keeps the design simple.
If contention becomes a problem, I'd consider sharding/partitioning by user hash so that different users are spread across database partitions.
For extremely hot users, I might move to event sourcing:
store immutable transactions
aggregate asynchronously
maintain materialized totals
This avoids every write hitting the same row.

Normally we don't create:
500 million partitions
We create something like:
1024 partitions
using:
partition = hash(userId) % 1024;
or:
shard = hash(userId) % N;
Many users share each partition.

*/