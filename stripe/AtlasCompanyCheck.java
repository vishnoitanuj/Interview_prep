package stripe;

/*
Problem 1: Atlas Company Name Check
Background

Stripe Atlas allows founders to register a US company remotely from anywhere in the world. A key step in the registration process is verifying the availability of the proposed company name. To avoid confusion, the check doesn’t just look for exact matches, names that are too similar (per specific rules) are also considered unavailable.

Part 1: Basic Name Availability Check

We need to determine if a proposed company name is available by normalizing it and comparing it against previously registered names (after applying the same normalization rules).

We are given:

A set of name normalization rules (to determine if two names are considered identical):

Ignore case (e.g., "Llama, Inc." and "LLAMA, Inc." are the same)

Treat & and , as spaces

Collapse consecutive spaces into a single space

Ignore standard company suffixes (case-insensitive), with suffixes including: ["Inc.", "Corp.", "LLC", "L.L.C.", "LLC."]

Ignore leading "The", "An", or "A" (e.g., "Llama, Inc." and "The Llama, Inc." are the same)

Ignore "And" unless it appears at the start of the name (e.g., "Llama And Friend, Inc." and "Llama Friend, Inc." are the same; "And Llama Friend, Inc." is distinct)

If the normalized name is empty or only contains spaces, it is considered unavailable

A table of name check requests: each line includes account_id and proposed name, separated by |

Output

For each request, return a line with account_id followed by | and either "Name Available" or "Name Not Available". Once a name is marked as available, it is registered and becomes unavailable for all subsequent requests.

Part 2: Persistent Registration Tracking

Extend Part 1 to maintain a permanent record of all registered names. If a merchant re-submits a name (after normalization) that they or another merchant previously registered, it should be marked as unavailable.

Inputs remain the same as Part 1, but the system must retain the state of registered names across all requests.

Part 3: Name Reclamation Requests

Companies may dissolve, freeing up their names for reuse. We now support reclamation requests to remove a previously registered name from the unavailable list.

Input may now include lines like: RECLAIM,account_id,original_proposed_name (where "original_proposed_name" is the exact name the account used when successfully registering)

When processing a reclamation request, the normalized version of the original name is removed from the unavailable list. Only the account that originally registered the name is authorized to reclaim it.
*/

import java.util.*;

public class AtlasCompanyCheck {

    Map<String, String> registeredCompanies = new HashMap<>();

    private static final Set<String> PREFIXES = Set.of("THE", "AN", "A");
    private static final Set<String> SUFFIXES = Set.of("INC.", "CORP.", "LLC", "L.L.C.", "LLC.");

    public String normalize(String name){
        name = name.toUpperCase();
        name = name.replaceAll(",", "\s").replaceAll("&", "\s");
        name = name.replaceAll(
            "\s+", "\s");
        name=name.trim();
        if(name.isEmpty()){
            return "";
        }
        
        LinkedList<String> tokens = new LinkedList<>(Arrays.asList(name.split("\\s+")));
        if(!tokens.isEmpty() && PREFIXES.contains(tokens.get(0))){
            tokens.remove(0);
        }

        while (!tokens.isEmpty() && SUFFIXES.contains(tokens.getLast())) {
            tokens.removeLast();
        }

        if(tokens.isEmpty()){
            return "";
        }

        List<String> out = new ArrayList<>();
        out.add(tokens.getFirst());
        for(int i=1;i<tokens.size();i++){
            if(tokens.get(i).equals("AND")){
                continue;
            }
            out.add(tokens.get(i));
        }

        return String.join(" ", out);
    }

    private boolean available(String name){
        return !registeredCompanies.containsKey(name);
    }

    private void reclaimRequest(String request){
        String[] na = request.split(",", 3);
        String id = na[1];
        String name = na[2];

        String normalizedName = normalize(name);
        if(!registeredCompanies.containsKey(normalizedName)){
            System.out.println("Company not present");
            return;
        }
        if(!registeredCompanies.get(normalizedName).equals(id)){
            System.out.println("Not allowed to remove company not owned by you");
            return;
        }
        registeredCompanies.remove(normalizedName);

        System.out.println(name+ " available again");
    }

    public void result(String account){
        String[] na = account.split("\\|", 2);
        String id = na[0].trim();
        String name = na[1].trim();
        String normalizedName = normalize(name);
        if(!normalizedName.isEmpty() && available(normalizedName)){
            registeredCompanies.put(normalizedName, id);
            System.out.println(id +" | Name Available");
        } else{
            System.out.println(id + " | Name Not Available");
        }
    }
    public static void main(String[] args){
        String account = "1 | Llama, Inc.";
        AtlasCompanyCheck obj = new AtlasCompanyCheck();
        obj.result(account);
        account = "2 | The LLAMA, Inc.";
        obj.result(account);

        account = "3 | Llama And Friend, Inc.";
        obj.result(account);

        account = "4 | Llama Friend, Inc.";
        obj.result(account);

        String req = "RECLAIM,1,Llama, Inc.";
        obj.reclaimRequest(req);

        account = "2 | The LLAMA, Inc.";
        obj.result(account);
    }

}