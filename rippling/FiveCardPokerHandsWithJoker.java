package rippling;
// Q: https://prachub.com/coding-questions/implement-logger-and-card-ranking (part 3)
import java.util.*;
public class FiveCardPokerHandsWithJoker {
    
    private static final Map<Character, Integer> RANKS = Map.ofEntries(
        Map.entry('2', 0),
        Map.entry('3', 1),
        Map.entry('4', 2),
        Map.entry('5', 3),
        Map.entry('6', 4),
        Map.entry('7', 5),
        Map.entry('8', 6),
        Map.entry('9', 7),
        Map.entry('T', 8),
        Map.entry('Q', 9),
        Map.entry('K', 10),
        Map.entry('A', 11),
        Map.entry('J', -1)
    );

    public record Hand(String hand, long bid, int type) {
    }

    public long totalScore(List<String> records){
        List<Hand> hands = new ArrayList<>();
        for(String rec: records){
            String[] parts = rec.split("\\s", 2);
            hands.add(new Hand(parts[0], Long.parseLong(parts[1]), getHandType(parts[0])));
        }
        hands.sort(this::compareHands);
        long ans = 0L;
        for(int i=0;i<hands.size();i++){
            ans+= (long)(i+1)*hands.get(i).bid();
        }

        return ans;
    }

    private int compareHands(Hand h1, Hand h2){

        if(h1.type()!=h2.type()){
            return Integer.compare(h1.type(), h2.type());
        }

        for(int i=0;i<5;i++){
            int r1 = RANKS.get(h1.hand().charAt(i));
            int r2 = RANKS.get(h2.hand().charAt(i));

            if(r1!=r2){
                return Integer.compare(r1, r2);
            }
        }
        return 0;
    }

    private int getHandType(String hand){
        Map<Character, Integer> freq = new HashMap<>();
        int jokerCount=0;
        for(char ch: hand.toCharArray()){
            if(ch=='J'){
                jokerCount++;
                continue;
            }
            freq.merge(ch, 1, Integer::sum);
        }
        if(freq.isEmpty()){
            return 6; // Five J
        }
        List<Integer> counts = new ArrayList<>(freq.values());
        counts.sort(Comparator.reverseOrder());
        counts.set(0, counts.get(0)+jokerCount);
        if(counts.get(0)==5){
            return 6;
        }

        if(counts.get(0)==4){
            return 5;
        }

        if(counts.get(0)==3 && counts.get(1)==2){
            return 4;
        }

        if(counts.get(0)==3){
            return 3;
        }

        if(counts.get(0)==2 && counts.get(1)==2){
            return 2;
        }

        if(counts.get(0)==2){
            return 1;
        }

        return 0;
    }

    public static void main(String[] args) {

        FiveCardPokerHandsWithJoker solution = new FiveCardPokerHandsWithJoker();

        List<String> input = List.of(
                "32T3K 765", "T55J5 684", "KK677 28", "KTJJT 220", "QQQJA 483"
        );

        System.out.println(
                solution.totalScore(input)
        );
    }
}
