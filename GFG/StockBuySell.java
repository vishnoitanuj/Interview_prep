package GFG;

import java.util.ArrayList;

public class StockBuySell {
	static class Interval{
		int buy,sell;
	}
	
	
	public static void calculate(int a[]) {
		int n=a.length;
		if(n==1)
			return;
		ArrayList<Interval> sol=new ArrayList<>();
		int c=0,i=0;
		while(i<n-1) {
			while((i<n-1) && a[i+1]<=a[i])
				i++;
			if(i==n-1)
				break;
			Interval e=new Interval();
			e.buy=i++;
			while((i<n) && (a[i]>=a[i-1]))
				i++;
			e.sell=i-1;
			sol.add(e);
			c++;
		}
		if(c==0)
			return;
		int buy=0,sell=0;
		for(Interval j:sol) {
			if(a[j.buy]<a[buy])
				buy=j.buy;
			if(a[j.sell]>=a[sell])
				sell=j.sell;
		}
		System.out.println(buy+" "+sell);
		
	}
	public static void main(String args[]) {
		int price[] = { 100, 180, 260, 310, 40, 535, 695 }; 
        int n = price.length; 
  
        // fucntion call 
        calculate(price); 
	}
}
