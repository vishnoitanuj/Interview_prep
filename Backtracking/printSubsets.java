package Backtracking;

import java.util.ArrayList;

public class printSubsets {
	public static void main(String args[]) {
		int a[]= {5,4,1,2};
		printSubsetsSumTok(a, 6);
	}
	public static void printSubsetsSumTok(int input[], int k) {
		// Write your code here
        ArrayList<Integer> out=new ArrayList<>();
        subsetsSumK(input,k,input.length,out);
		
	}
    
    private static void subsetsSumK(int a[],int k,int n,ArrayList<Integer> out){
            if(k==0){
                // out.add(a[si]);
                for(int i=0;i<out.size();i++)
                    System.out.print(out.get(i)+" ");
                System.out.println();
                return;
            }
        if(n==0)
            return;
        subsetsSumK(a,k,n-1,out);
        ArrayList<Integer> out1=new ArrayList<Integer>(out);
        out1.add(0,a[n-1]);
        subsetsSumK(a,k-a[n-1],n-1,out1);
    }

}
