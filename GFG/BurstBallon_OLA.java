package GFG;

public class BurstBallon_OLA {
	
	public static int calc(int a[]) {
		int n=a.length;
		int dp[][]=new int[n][n];
		for(int len=1;len<=n;len++) {
			for(int i=0;i<=n-len;i++) {
				int j=i+len-1;
				for(int k=i;k<=j;k++) {
					int left=1,right=1;
					if(i!=0)
						left=a[i-1];
					if(j!=n-1)
						right=a[j+1];
					
					int before=0,after=0;
					if(i!=k)
						before=dp[i][k-1];
					if(j!=k)
						after=dp[k+1][j];
					dp[i][j]=Math.max(dp[i][j], before+(left*a[k]*right)+after);
						
				}
			}
		}
		return dp[0][n-1];
	}

	public static void main(String[] args) {
		int a[]= new int[]{1,2,3,4,5};
		System.out.println(calc(a));

	}

}
