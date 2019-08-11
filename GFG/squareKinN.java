package GFG;

public class squareKinN {
	
	private static void print(int a[][],int k) {
		int n=a.length;
		if(k>n)
			return;
		int b[][]=new int[k][n];
		
		for(int j=0;j<n;j++) {
			int sum=0;
			for(int i=0;i<k;i++)
				sum+=a[i][j];
			b[0][j]=sum;
			for(int i=1;i<n-k+1;i++) {
				sum+=a[i+k-1][j]-a[i-1][j];
				b[i][j]=sum;
			}
		}
		for(int i=0;i<n-k+1;i++) {
			int sum=0;
			for(int j=0;j<k;j++) {
				sum+=b[i][j];
			}
			System.out.print(sum+" ");
			for(int j=1;j<n-k+1;j++) {
				sum+=b[i][j+k-1]-b[i][j-1];
				System.out.print(sum+" ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int mat[][] = {{1, 1, 1, 1, 1}, 
                {2, 2, 2, 2, 2}, 
                {3, 3, 3, 3, 3}, 
                {4, 4, 4, 4, 4},  
                {5, 5, 5, 5, 5}, 
               }; 
		 int k = 3; 
		 print(mat, k); 

	}

}
