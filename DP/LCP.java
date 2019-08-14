package DP;

public class LCP {

	public static int LCS(String a) {
		StringBuffer bf=new StringBuffer(a);
		int n=a.length();
		StringBuffer b=bf.reverse();
		int m=b.length();
		int dp[][]=new int[n+1][m+1];
		for(int i=0;i<=n;i++)
			dp[i][0]=0;
		for(int j=0;j<=m;j++)
			dp[0][j]=0;
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=m;j++) {
				if(a.charAt(n-i)==b.charAt(m-j))
					dp[i][j]=1+dp[i-1][j-1];
				else
					dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
			}
		}
		return dp[n][m];
	}
	public static void main(String[] args) {
		System.out.println(LCS("GEEKSFORGEEKS"));

	}

}
