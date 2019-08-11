package GFG;

import java.util.Arrays;

public class MakeTriplets {

	public static void main(String[] args) {
		triplets(4);

	}
	
	public static void triplets(int n){
		int a[]=new int[3];
		a[0]=1;
		a[1]=2;
		a[2]=2;
		int c=2;
		while(a[0]<n) {
			print(a);
			if(c==2) {
				a[c--]+=1;
			}
			else if(c>=0)
				a[c--]+=1;
			else{
				if(a[0]==n-1)
					break;
				c=2;
			}
		}
		
		
	}
	public static void print(int a[]) {
		for(int i:a)
			System.out.print(i+" ");
		System.out.println();
	}

}
