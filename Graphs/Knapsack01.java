package Graphs;

import java.util.Scanner;

public class Knapsack01 {
	
	public static int knapsack(int weight[],int value[],int max) {
		int prev[]=new int[max+1];
		int curr[]=new int[max+1];
		for(int i=1;i<=value.length;i++) {
			for(int w=1;w<=max;w++) {
				if(weight[i-1]>w)
					curr[w]=prev[w];
				else
					curr[w]=Math.max(prev[w-weight[i-1]]+value[i-1], prev[w]);
			}
			prev=curr;
			curr=new int[max+1];
		}
		return prev[max];
	}

	public static void main(String[] args) {
			Scanner s = new Scanner(System.in);
			int n = s.nextInt();
			int weight[] = new int[n];
			for(int i = 0 ; i < n; i++){
				weight[i] = s.nextInt();
			}
			int value[] = new int[n];
			for(int i = 0 ; i < n; i++){
				value[i] = s.nextInt();
			}
			int maxWeight = s.nextInt();
			System.out.println(knapsack(weight, value, maxWeight));
			
		}

}
