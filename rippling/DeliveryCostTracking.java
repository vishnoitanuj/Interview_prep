package rippling;
import java.util.*;

import rippling.DeliveryCostTracking.Delivery;

public class DeliveryCostTracking {
    
    record Delivery(
        String driverId,
        int startTime,
        int endTime
    ) {
        public long totalCost(){
            return (long)endTime - startTime;
        }
    }

    private Set<String> driverIds = new HashSet<>();
    private final List<Delivery> deliveries = new ArrayList<>();
    private final Queue<Delivery> unPaidDeliveries = new PriorityQueue<>((a,b) -> a.endTime() - b.endTime());
    private long totalCost = 0L;
    private long unPaidCost = 0L;

    public void add_driver(String driverId){
        driverIds.add(driverId);
    }

    public void add_delivery(String driverId, int startTime, int endTime){
        if(!driverIds.contains(driverId)){
            throw new IllegalArgumentException("Unknown Driver: "+driverId);
        }
        Delivery delivery = new Delivery(driverId, startTime, endTime);
        deliveries.add(delivery);
        unPaidDeliveries.add(delivery);
        totalCost+= (long) (endTime-startTime);
        unPaidCost+=(long) (endTime-startTime);
    }

    public long get_total_cost(){
        return totalCost;
    }

    public void pay_upto_time(int uptoTime){
        while (!unPaidDeliveries.isEmpty() && unPaidDeliveries.peek().endTime()<=uptoTime) {
            unPaidCost-=unPaidDeliveries.poll().totalCost();
        }
    }

    public long get_cost_to_be_paid(){
        return unPaidCost;
    }

    // Assuming analytical queries are less. If frequent i would index by time or maintain a sweep line structure.
    public int get_max_active_drivers_in_last_24_hours(int currentTime){
        int windowStart = currentTime - 1440;
        Set<String> activeDrivers = new HashSet<>();
        for(Delivery d: deliveries){
            if(d.endTime()>=windowStart && d.startTime()<=currentTime){
                activeDrivers.add(d.driverId());
            }
        }
        return activeDrivers.size();
    }
}
