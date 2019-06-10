/*
N=4
4 4 4 4 4 4 4 
4 3 3 3 3 3 4 
4 3 2 2 2 3 4 
4 3 2 1 2 3 4 
4 3 2 2 2 3 4 
4 3 3 3 3 3 4 
4 4 4 4 4 4 4 
*/

import java.util.Scanner;

public class pattern5 {

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int N=sc.nextInt();
        print(N);
    }
	public static void print(int N) {
        for(int i=N;i>0;i--){
            for(int j=N;j>i;j--)
                System.out.print(j+" ");
            for(int j=i;j>0;j--)
                System.out.print(i+" ");
            for(int j=1;j<i;j++)
                System.out.print(i+" ");
            for(int j=i;j<N;j++)
                System.out.print((j+1)+" ");
            System.out.println();
        }

        for(int i=2;i<=N;i++){
            for(int j=N;j>i;j--)
                System.out.print(j+" ");
            for(int j=i;j>0;j--)
                System.out.print(i+" ");
            for(int j=1;j<i;j++)
                System.out.print(i+" ");
            for(int j=i;j<N;j++)
                System.out.print((j+1)+" ");
            System.out.println();
        }
    }
}