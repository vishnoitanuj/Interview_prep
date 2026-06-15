package rippling;

import java.util.*;

public class ArticleVotingTracker {
    
    public enum VoteType {
        UP,
        DOWN
    }

    public record VoteEvent(String articleId, VoteType voteType, int eventOrder){

        @Override
        public String toString(){
            return "(" + articleId + ", " + voteType + ", " + eventOrder + ")";
        }
    }

    public record UserArticle(String userId, String articleId) {}

    private final Map<UserArticle, VoteType> currentVotes;
    private final Map<String, Deque<VoteEvent>> userHistory;
    private int eventCounter;

    public ArticleVotingTracker(){
        currentVotes = new HashMap<>();
        userHistory = new HashMap<>();
        eventCounter = 0;
    }

    public void vote(String userId, String articleId, VoteType voteType){
        UserArticle key = new UserArticle(userId, articleId);

        VoteType existingVote = currentVotes.get(key);

        if(existingVote== voteType){
            return;
        }

        eventCounter++;

        currentVotes.put(key, voteType);

        VoteEvent voteEvent = new VoteEvent(articleId, voteType, eventCounter);
        userHistory.computeIfAbsent(userId, k -> new LinkedList<>()).addFirst(voteEvent);
    }

    public List<VoteEvent> getLastVotes(String userId){
        Deque<VoteEvent> history = userHistory.get(userId);

        if(history == null){
            return Collections.emptyList();
        }

        List<VoteEvent> result = new ArrayList<>(3);
        int count = 0;

        for(VoteEvent event: history){
            result.add(event);
            count++;

            if(count==3){
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {

        ArticleVotingTracker tracker = new ArticleVotingTracker();

        tracker.vote("u1", "a1", VoteType.UP);
        tracker.vote("u1", "a1", VoteType.UP);     // ignored
        tracker.vote("u1", "a2", VoteType.DOWN);
        tracker.vote("u1", "a1", VoteType.DOWN);

        System.out.println(
                tracker.getLastVotes("u1"));
    }
}
