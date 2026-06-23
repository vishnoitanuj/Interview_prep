package stripe;

import java.util.*;

public class SubscriptionNotifierScheduler {


    static class UserAccount{
        String name;
        String plan;
        Long accountDate;
        long duration;

        public UserAccount(String name, String plan, Long accountDate, long duration) {
            this.name = name;
            this.plan = plan;
            this.accountDate = accountDate;
            this.duration = duration;
        }

        public Long subscriptionEndDate(){
            return accountDate + duration;
        }

        public String getName() {
            return name;
        }

        public String getPlan() {
            return plan;
        }

        public Long getAccountDate() {
            return accountDate;
        }

        public long getDuration() {
            return duration;
        }
    }

    record Message(String user, String type, String plan, Long timestamp){}
    private final Map<Long, List<Message>> scheduledMessages = new TreeMap<>();

    public Map<Long, List<Message>> send_schedule(Map<String, String> emailMap, List<UserAccount> userAccounts){
        PriorityQueue<Message> queue = new PriorityQueue<>((a,b) -> a.timestamp().compareTo(b.timestamp()));
        for(UserAccount account: userAccounts){
            for(Map.Entry<String, String> entry: emailMap.entrySet()){
                if(entry.getKey().equalsIgnoreCase("start")){
                    queue.add(
                            new Message(account.getName(), entry.getValue(), account.getPlan(), account.getAccountDate()));
                }
                else if(entry.getKey().startsWith("T-")){
                    String[] parts = entry.getKey().split("-", 2);
                    int beforeTime = Integer.parseInt(parts[1]);
                    queue.add(
                            new Message(account.getName(), entry.getValue(), account.getPlan(), account.subscriptionEndDate()-beforeTime));
                } else if (entry.getKey().equalsIgnoreCase("end")) {
                    queue.add(
                            new Message(account.getName(), entry.getValue(), account.getPlan(), account.subscriptionEndDate()));
                }
            }
        }


        while (!queue.isEmpty()){
            Message message = queue.poll();
            scheduledMessages.computeIfAbsent(message.timestamp(), k -> new ArrayList<>()).add(message);
        }
        return scheduledMessages;
    }

    record PlanChanges(String name, String newPlanName, Long changeDate){};

    public void planChange(Map<String, String> emailMap, List<UserAccount> userAccounts, List<PlanChanges> planChanges){
        Map<String, UserAccount> map = new HashMap<>();
        for(UserAccount userAccount: userAccounts){
            map.put(userAccount.getName(), userAccount);
        }

        for(PlanChanges change: planChanges){
            String user = change.name();
            UserAccount oldAccount = map.get(user);

            long changeDate = change.changeDate();
            if(changeDate>oldAccount.subscriptionEndDate()){
                continue;
            }

            recomputeOldNotifications(emailMap, oldAccount, change);
        }
    }

    class ChangeWithRenewal {
        String type;
        String name;
        String newPlan;
        long extension;
        long changeDate;

        public ChangeWithRenewal(String type, String name, String newPlan, long changeDate) {
            this.type = type;
            this.name = name;
            this.newPlan = newPlan;
            this.changeDate = changeDate;
        }

        public ChangeWithRenewal(String type, String name, long extension, long changeDate) {
            this.type = type;
            this.name = name;
            this.extension = extension;
            this.changeDate = changeDate;
        }
    }

    public void planChangeWithRenewal(Map<String, String> emailMap, List<UserAccount> userAccounts, List<ChangeWithRenewal> changeWithRenewals){
        Map<String, UserAccount> map = new HashMap<>();
        for(UserAccount userAccount: userAccounts){
            map.put(userAccount.getName(), userAccount);
        }
        for(ChangeWithRenewal changeWithRenewal: changeWithRenewals){
            String user = changeWithRenewal.name;
            UserAccount oldAccount = map.get(user);
            long changeDate = changeWithRenewal.changeDate;

            if(changeDate>oldAccount.subscriptionEndDate() && !changeWithRenewal.type.equalsIgnoreCase("Renewal")){
                continue;
            }

            recomputeOldNotifications(emailMap, oldAccount, changeWithRenewal);
        }
    }


    private void recomputeOldNotifications(Map<String, String> emailMap, UserAccount userAccount, PlanChanges change) {
        for(String type: emailMap.keySet()){
            long newTimeOfSend = 0;
            long oldTimeOfSend = 0;
            if(type.startsWith("T-")){
                oldTimeOfSend = userAccount.subscriptionEndDate() - Integer.parseInt(type.split("-", 2)[1]);
                newTimeOfSend = change.changeDate() + userAccount.getDuration() - Integer.parseInt(type.split("-", 2)[1]);
            } else if(type.equalsIgnoreCase("start")){
                oldTimeOfSend = userAccount.getAccountDate();
                newTimeOfSend = change.changeDate();
            }
            else if(type.equalsIgnoreCase("end")){
                oldTimeOfSend = userAccount.subscriptionEndDate();
                newTimeOfSend = change.changeDate() + userAccount.getDuration();
            }

            if(oldTimeOfSend>change.changeDate()){
                scheduledMessages.get(oldTimeOfSend).remove(new Message(userAccount.getName(), emailMap.get(type) ,userAccount.getPlan(), oldTimeOfSend));
            }
            scheduledMessages.computeIfAbsent(newTimeOfSend, k -> new ArrayList<>()).add(new Message(userAccount.getName(), type, change.newPlanName(), newTimeOfSend));
            userAccount.plan = change.newPlanName();
            userAccount.accountDate = change.changeDate();
        }
    }

    private void recomputeOldNotifications(Map<String, String> emailMap, UserAccount userAccount, ChangeWithRenewal change) {
        for(String type: emailMap.keySet()){
            long newTimeOfSend = 0;
            long oldTimeOfSend = 0;
            if(type.startsWith("T-")){
                oldTimeOfSend = userAccount.subscriptionEndDate() - Integer.parseInt(type.split("-", 2)[1]);
                newTimeOfSend = change.changeDate + userAccount.getDuration() - Integer.parseInt(type.split("-", 2)[1]);
            } else if(!change.type.equalsIgnoreCase("Renewal") && type.equalsIgnoreCase("start")){
                oldTimeOfSend = userAccount.getAccountDate();
                newTimeOfSend = change.changeDate;
            }
            else if(type.equalsIgnoreCase("end")){
                oldTimeOfSend = userAccount.subscriptionEndDate();
                newTimeOfSend = change.changeDate + userAccount.getDuration();
            }

            if(change.type.equalsIgnoreCase("Renewal")){
                newTimeOfSend += change.extension;
            }

            if(oldTimeOfSend>change.changeDate){
                scheduledMessages.get(oldTimeOfSend).remove(new Message(userAccount.getName(), emailMap.get(type) ,userAccount.getPlan(), oldTimeOfSend));
            }
            scheduledMessages.computeIfAbsent(newTimeOfSend, k -> new ArrayList<>()).add(new Message(userAccount.getName(), type, change.newPlan, newTimeOfSend)); // Should have renewal
            userAccount.plan = change.newPlan;
            userAccount.accountDate = change.changeDate;
        }
    }
}
