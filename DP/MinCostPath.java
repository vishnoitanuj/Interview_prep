package DP;

import java.util.Scanner;

public class MinCostPath {
	
	public static int minCostPath(int a[][]) {
		int n=a.length;
		int m=a[0].length;
		int dp[][]=new int[n][m];
		dp[n-1][m-1]=a[n-1][m-1];
		for(int i=n-2;i>=0;i--)
			dp[i][m-1]=dp[i+1][m-1]+a[i][m-1];
		for(int j=m-2;j>=0;j--)
			dp[n-1][j]=dp[n-1][j+1]+a[n-1][j];
		for(int i=n-2;i>=0;i--) {
			for(int j=m-2;j>=0;j--)
				dp[i][j]=a[i][j]+Math.min(dp[i+1][j], Math.min(dp[i][j+1], dp[i+1][j+1]));
		}
		return dp[0][0];
	}
	
	static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {
		int m = s.nextInt();
		int n = s.nextInt();
		int input[][] = new int[m][n];
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				input[i][j] = s.nextInt();
			}
		}
		System.out.println(minCostPath(input));
	}
}
