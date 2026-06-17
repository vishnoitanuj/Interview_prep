package rippling;

import java.util.*;
public class AlertingSystem {
    
    enum Severity {
        LOW,
        MEDIUM,
        HIGH
    }

    enum AlertType {
        PAYMENT,
        DATABASE
    }

    enum Status {
        OPEN,
        ACKNOWLEDGED
    }

    record Alert(String id, AlertType alertType, Severity severity, long timestamp, Status status){

        void setStatus(Status status){
            this.status = status;
        }
    };

    private final Map<String, Alert> alertsById = new HashMap<>();
    private final Map<AlertType, List<Alert>> alertByType = new HashMap<>();
    private final TreeMap<Long, List<Alert>> alertByTime = new TreeMap<>();
    private final TreeSet<Alert> alertsBySeverity;

    public AlertingSystem() {
        alertsBySeverity = new TreeSet<>((a,b) -> {
            int cnt = Integer.compare(b.severity.ordinal(),
        a.severity.ordinal());
        if(cnt!=0){
            return cnt;
        }
        return a.id().compareTo(b.id());
        });
    }

    public void addAlert(Alert alert){
        alertsById.put(alert.id(), alert);

        alertByType.computeIfAbsent(alert.alertType(), k-> new ArrayList<>()).add(alert);
        alertByTime.computeIfAbsent(alert.timestamp(),  k-> new ArrayList<>()).add(alert);
        alertsBySeverity.add(alert);
    }

    public void acknowledge(String alertId){
        Alert alert = alertsById.get(alertId);

        if (alert == null) {
            return;
        }

        alert. = Status.ACKNOWLEDGED;
    }

    public List<Alert> getAlertsInRange(long start,
                     long end) {

        List<Alert> result = new ArrayList<>();

        NavigableMap<Long, List<Alert>>
                range =
                alertByTime.subMap(
                        start,
                        true,
                        end,
                        true);

        for (List<Alert> alerts : range.values()) {
            result.addAll(alerts);
        }

        return result;
    }
}
