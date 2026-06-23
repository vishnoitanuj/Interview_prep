package stripe;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class WebhookDelivery {

    enum Status {
        INITIATED,
        IN_PROGRESS,
        FAILED,
        SUCCESS
    }
    static record WebhookEvent(String eventId, String type, String payload) {}
    private static class StoredWebhookEvent {
        String eventId;
        String type;
        String payload;
        Status status;

        public StoredWebhookEvent(String eventId, String type, String payload, Status status){
            this.eventId = eventId;
            this.type = type;
            this.payload = payload;
            this.status = status;
        }
    }

    private final ProcessEventRepository eventRepository = new InMemoryProcess();

    public void handle(WebhookEvent event) throws IllegalAccessException {

        if(!eventRepository.createIfAbsent(event)){
            System.out.println("Duplicate events");
            return;
        }
        switch(event.type()){
            case "payment_success":
                eventRepository.updateStatus(event, Status.IN_PROGRESS);
                processPaymentSuccess(event);
                eventRepository.updateStatus(event, Status.SUCCESS);
                break;
            case "payment_failed":
                eventRepository.updateStatus(event, Status.IN_PROGRESS);
                processPaymentFailure(event);
                eventRepository.updateStatus(event, Status.SUCCESS);
                break;
            default:
                throw new IllegalAccessException("Unknown payment type");
        }
        
    }

    private void processPaymentSuccess(WebhookEvent event){
        System.out.println("Successfully processed payment for id = "+event.eventId());
    }

    private void processPaymentFailure(WebhookEvent event){
        System.out.println("Failed to process payment for id = "+event.eventId());
    }


    interface ProcessEventRepository {
        boolean createIfAbsent(WebhookEvent event);
        void updateStatus(WebhookEvent event, Status status);
    }

    class InMemoryProcess implements  ProcessEventRepository{

        private final Map<String, StoredWebhookEvent> eventMap = new HashMap<>();

        @Override
        public boolean createIfAbsent(WebhookEvent event) {
            if(eventMap.containsKey(event.eventId())){
                return false;
            }

            eventMap.put(event.eventId(), new StoredWebhookEvent(event.eventId(), event.type(), event.payload(), Status.INITIATED));
            return true;
        }


        @Override
        public void updateStatus(WebhookEvent event, Status status) {
            if(!eventMap.containsKey(event.eventId())){
                throw new RuntimeException("Unknown call");
            }
            StoredWebhookEvent storedWebhookEvent = eventMap.get(event.eventId());
            storedWebhookEvent.status = status;
        }
    }

}

