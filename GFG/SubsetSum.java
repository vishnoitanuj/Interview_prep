package GFG;

import java.util.HashMap;

public class SubsetSum {
	
	public static int dp(int a[],int total,int i,HashMap<Integer,Integer> map){
		if(map.containsKey(total))
			return map.get(total);
		if(total==0)
			return 1;
		else if(total<0)
			return 0;
		else if(i<0)
			return 0;
		int sa;
		if(total<a[i])
			sa = dp(a,total,i-1,map);
		else
			sa=dp(a,total-a[i],i-1,map)+dp(a,total,i-1,map);
		return sa;
	}
	public static void main(String args[]) {
		int a[]= {2,4,6,10};
		HashMap<Integer,Integer> map=new HashMap<>();
		System.out.println(dp(a,16,3,map));
	}

}
