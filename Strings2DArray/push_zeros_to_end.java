package Strings2DArray;

import java.util.Scanner;
class push_zeros_to_end{

    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int a[]=new int[n];
        for(int i=0;i<a.length;i++){
            a[i]=sc.nextInt();
        }
        pushZerosAtEnd(a);
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]+" ");
        }

    }
    public static void pushZerosAtEnd(int[] a){
		int k=0,i=0,t;
        while(i<a.length){
            if(a[i]!=0){
                t=a[i];
                a[i]=a[k];
                a[k]=t;
                i++;
                k++;
            }
            else{
                i++;
            }
        }
	}
}