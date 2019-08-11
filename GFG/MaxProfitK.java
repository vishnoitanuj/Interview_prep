package GFG;

public class MaxProfitK {

	public static int maxProfit(int a[],int k) {
		int n=a.length;
		int dp[][]=new int[k+1][n];
		for(int i=1;i<=k;i++) {
			int prevD=Integer.MIN_VALUE;
			for(int j=1;j<n;j++) {
				prevD=Math.max(prevD, dp[i-1][j-1]-a[j-1]);
				dp[i][j]=Math.max(dp[i][j-1], a[j]+prevD);
			}
		}
		return dp[k][n-1];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int k = 3; 
        int price[] = {12, 14, 17, 10, 14, 13, 12, 15}; 
  
        int n = price.length; 
  
        System.out.println("Maximum profit is: " +  
                            maxProfit(price, k)); 
        System.out.println(Integer.MAX_VALUE);

	}

}
