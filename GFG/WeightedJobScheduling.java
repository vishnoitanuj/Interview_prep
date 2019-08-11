package GFG;

import java.util.Arrays;
import java.util.Comparator;

public class WeightedJobScheduling {
	
	static class Job{
		int start,finish,profit;
		Job(int s,int f,int p){
			start=s;
			finish=f;
			profit=p;
		}
	}

	static class JobComparator implements Comparator<Job>{

		@Override
		public int compare(Job a, Job b) {
			return a.finish<b.finish?-1:a.finish==b.finish?0:1;
		}
	}
	
	private static int findMaxProfit(Job a[]) {
		Arrays.sort(a,new JobComparator());
		int dp[]=new int[a.length];
		dp[0]=a[0].profit;
		for(int i=1;i<a.length;i++) {
			int inc=a[i].profit;
			int index=binaryNonConflict(a,i);
			if(index!=-1)
				inc+=dp[index];
			dp[i]=Math.max(inc, dp[i-1]);
		}
		return dp[a.length-1];
	}
	
	private static int binaryNonConflict(Job a[],int i) {
		int l=0,h=i-1;
		while(l<=h) {
			int mid=l+(h-l)/2;
			if(a[mid].finish<=a[i].start) {
				if(a[mid+1].finish<=a[i].start)
					l=mid+1;
				else
					return mid;
			}
			else
				h=mid-1;
		}
		return -1;
	}
	public static void main(String[] args) {
		Job jobs[] = {new Job(1, 2, 50), new Job(3, 5, 20), 
                new Job(6, 19, 100), new Job(2, 100, 200)}; 

		System.out.println("Optimal profit is " + findMaxProfit(jobs));  
	}

}
