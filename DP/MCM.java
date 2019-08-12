package DP;

public class MCM {
	public static int mcm(int a[]) {
		int n=a.length;
		int dp[][]=new int[n][n];
		for(int i=n-2;i>0;i--) {
			for(int j=1;j<n;j++) {
				if(i<j) {
					int min=Integer.MAX_VALUE;
					for(int k=i;k<j;k++) {
						int cost=dp[i][k]+dp[k+1][j]+a[i-1]*a[k]*a[j];
						if(cost<min)
							min=cost;
					}
					dp[i][j]=min;
				}
			}
		}
		return dp[1][n-1];
	}
	public static void main(String[] args) {
		int arr[] = new int[] {1, 2, 3, 4}; 
  
        System.out.println("Minimum number of multiplications is "+ 
                           mcm(arr)); 
	}

}
