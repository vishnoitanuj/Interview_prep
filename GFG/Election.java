package GFG;


import java.util.Set;
import java.util.HashMap;

public class Election {
	
	public static String countMax(String na[]) {
		HashMap<String,Integer> map=new HashMap<>();
		for(String i:na) {
			if(map.containsKey(i))
				map.put(i,map.get(i)+1);
			else
				map.put(i,1);
		}
		Set<String> keys=map.keySet();
		int max=0;
		String out="Tanuj";
		for(String i:keys) {
			if(map.get(i)>=max) {
				if(i.compareTo(out)>0) {
					max=map.get(i);
					out=i;
				}
			}
		}
		return out;
	}
	
	public static void main(String args[]) {
		String na[]= {"Tanuj","Ankush","Tanuj","Tanay","Ankita","Mini","Tanuj","Mini","Mini"};
		System.out.println(countMax(na));
	}

}
