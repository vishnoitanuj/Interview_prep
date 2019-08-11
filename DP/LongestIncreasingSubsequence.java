package DP;

import java.util.Scanner;

public class LongestIncreasingSubsequence {
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int arr[] = new int[n];
		for(int i=0; i<n; i++){
			arr[i] = s.nextInt();
		}
		
		int c[]=lis(arr);
		for(int i:c)
			System.out.print(i+" ");

	}
	
	public static int[] lis(int arr[]) {
		
		int count[]=new int[arr.length];
        count[0]=1;
        for(int i=1;i<arr.length;i++){
            count[i]=1;
            for(int j=i-1;j>=0;j--){
                if(arr[i]>arr[j])
                    count[i]=Math.max(count[i],1+count[j]);
            }
        }
        return count;

	}	

}
