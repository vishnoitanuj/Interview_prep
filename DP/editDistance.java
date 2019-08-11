package DP;

import java.util.Scanner;

public class editDistance {
	
	public static int editDistanceDP(String s1, String s2){
		
	    int n=s1.length();
        int m=s2.length();
        int dp[][]=new int[n][m];
        dp[n-1][m-1]=(s1.charAt(n-1)==s2.charAt(m-1))?0:1;
        for(int i=n-2;i>=0;i--)
            dp[i][m-1]=(s1.charAt(i)==s2.charAt(m-1))?dp[i+1][m-1]:dp[i+1][m-1]+1;
        for(int j=m-2;j>=0;j--)
            dp[n-1][j]=(s1.charAt(n-1)==s2.charAt(j))?dp[n-1][j+1]:dp[n-1][j+1]+1;
        for(int i=n-2;i>=0;i--){
            for(int j=m-2;j>=0;j--){
                if(s1.charAt(i)==s2.charAt(j))
                    dp[i][j]=dp[i+1][j+1];
                else
                    dp[i][j]=1+Math.min(dp[i+1][j],Math.min(dp[i][j+1],dp[i+1][j+1]));
            }
        }
        for(int i=0;i<dp.length;i++){
        	for(int j=0;j<m;j++)
        		System.out.print(dp[i][j]);
        	System.out.println();
        }
        return dp[0][0];

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
	
		String str1 = s.nextLine();
		String str2 = s.nextLine();
//		System.out.println(editDistanceDP(str1, str2));
		System.out.println(Integer.MAX_VALUE);
	}



}
