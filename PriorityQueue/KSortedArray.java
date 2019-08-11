package PriorityQueue;

import java.util.PriorityQueue;

public class KSortedArray {
	
	public static void ksort(int a[],int k) {
		PriorityQueue<Integer> pq=new  PriorityQueue<Integer>();
		int i=0;
		for(;i<k;i++)
			pq.add(a[i]);
		
		for(;i<a.length;i++) {
			a[i-k]=pq.remove();
			pq.add(a[i]);
		}
		
		for(int j=a.length-k;j<a.length;j++) {
			a[j]=pq.remove();
		}
	}
	
	public static void main(String args[]) {
		int a[]= {2,4,1,9,6,8};
		ksort(a,3);
		for(int i:a)
			System.out.print(i+" ");
	}

}
