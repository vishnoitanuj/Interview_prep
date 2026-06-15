package rippling;

// Question: https://prachub.com/coding-questions/implement-logger-and-card-ranking
import java.util.*;
public class ConfigurableLogger {

    private String removeString = "";
    private boolean removedEnabled = false;

    private int truncateLength = 0;
    private boolean truncatedEnabled = false;

    private boolean uppercaseEnabled = false;
    private boolean storeEnabled = false;

    private final List<String> storedMessages = new ArrayList<>();

    public List<List<String>> logger(List<String> commands){

        List<List<String>> result = new ArrayList<>();

        for(String c: commands){
            
            if(c.startsWith("SET_REMOVE")){
                String[] parts = c.split("\\s", 2);
                removeString = parts.length==2 ? parts[1] : "";
                removedEnabled = true;
            } else if(c.startsWith("CLEAR_REMOVE")){
                removedEnabled = false;
            } else if(c.startsWith("SET_TRUNCATE")){
                String[] parts = c.split("\\s", 2);
                int k = Integer.valueOf(parts[1]);
                truncateLength = Math.max(0, k);
                truncatedEnabled = true;
            } else if(c.startsWith("CLEAR_TRUNCATE")){
                truncatedEnabled = false;
            } else if(c.startsWith("SET_UPPERCASE")){
                String[] parts = c.split("\\s",2);
                uppercaseEnabled = parts[1].equals("1");
            } else if(c.startsWith("SET_STORE")){
                String[] parts = c.split("\\s", 2);
                storeEnabled = parts[1].equals("1");
            } else if(c.startsWith("LOG")){
                String message = "";
                if(c.length()>3){
                    message = c.substring(4);
                }

                if("<NULL>".equals(message)){
                    message = "";
                }
                String transformedString = transform(message);

                if(storeEnabled){
                    storedMessages.add(transformedString);
                }
                result.add(storedMessages);
            } else if(c.startsWith("SEARCH")){
                String query = "";

                if(c.length()>6){
                    query = c.substring(7);
                }

                List<String> matches = new ArrayList<>();
                for(String stored:storedMessages){
                    if(stored.contains(query)){
                        matches.add(stored);
                    }
                }
                result.add(matches);
            }
            
        }
        return result;
    }

    private String transform(String message){
        String result = message;

        if(removedEnabled && !removeString.isEmpty()){
            result = result.replaceAll(removeString, "");
        }

        if(truncatedEnabled){
            result = result.substring(0, Math.min(truncateLength, result.length()));
        }

        if(uppercaseEnabled){
            result = result.toUpperCase();
        }
        return result;
    }

    public static void main(String[] args) {

        ConfigurableLogger logger = new ConfigurableLogger();

        List<String> commands = List.of(
                "SET_REMOVE ab",
                "SET_TRUNCATE 5",
                "SET_UPPERCASE 1",
                "SET_STORE 1",
                "LOG xxababy",
                "SEARCH Y"
        );

        System.out.println(logger.logger(commands));
    }
}
