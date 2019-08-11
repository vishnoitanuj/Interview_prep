package DP;

import java.util.Scanner;

public class Max_Sum_Subarray {
	
	public static int findSum(int a[],int n){
	    
	    int max=Integer.MIN_VALUE,sum=0;
	    for(int i=0;i<n;i++){
	        sum+=a[i];
	        if(sum>max)
	            max=sum;
	        if(sum<0){
	            sum=0;
	        }
	    }
	    
	    return max;
	    
			
		}
	
	static Scanner s = new Scanner(System.in);
	
	public static int[] takeInput(int size) {
		int arr[] = new int[size];
		for (int i = 0; i < size; i++) {
			arr[i] = s.nextInt();
		}
		return arr;
	}
	
	public static void main(String[] args) {
		int n = s.nextInt();
		int arr[] = takeInput(n);
		System.out.println(findSum(arr,n));
	}

}
