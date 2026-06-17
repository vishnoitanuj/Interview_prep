package rippling;

import java.util.*;

public class MaxBusyDashers {

    private static class Event {
        int time;
        int type; // 0 = END, 1 = START
        long dasherId;

        Event(int time, int type, long dasherId) {
            this.time = time;
            this.type = type;
            this.dasherId = dasherId;
        }
    }

    public static int maxBusyDashers(int[][] logs) {
        List<Event> events = new ArrayList<>();

        for (int[] log : logs) {
            long dasherId = log[0];
            int start = log[1];
            int end = log[2];

            events.add(new Event(start, 1, dasherId)); // START
            events.add(new Event(end, 0, dasherId));   // END
        }

        events.sort((a, b) -> {
            if (a.time != b.time) {
                return Integer.compare(a.time, b.time);
            }
            // END before START when times tie
            return Integer.compare(a.type, b.type);
        });

        Map<Long, Integer> activeOrders = new HashMap<>();

        int busyDashers = 0;
        int maxBusy = 0;

        for (Event e : events) {
            int currentCount = activeOrders.getOrDefault(e.dasherId, 0);

            if (e.type == 1) { // START
                if (currentCount == 0) {
                    busyDashers++;
                }

                activeOrders.put(e.dasherId, currentCount + 1);

            } else { // END
                if (currentCount == 1) {
                    busyDashers--;
                    activeOrders.remove(e.dasherId);
                } else {
                    activeOrders.put(e.dasherId, currentCount - 1);
                }
            }

            maxBusy = Math.max(maxBusy, busyDashers);
        }

        return maxBusy;
    }

    public static void main(String[] args) {
        int[][] logs1 = {
                {1, 1, 4},
                {1, 1, 3},
                {2, 2, 5}
        };

        int[][] logs2 = {
                {1, 1, 3},
                {1, 3, 5},
                {2, 3, 4}
        };

        System.out.println(maxBusyDashers(logs1)); // 2
        System.out.println(maxBusyDashers(logs2)); // 2
    }
}
