package Patterns;
/*
N=5
1        1
12      21
123    321
1234  4321
1234554321
*/
import java.util.Scanner;

public class pattern3 {

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int N=sc.nextInt();
        print(N);
    }
	public static void print(int N) {
        for(int i=1;i<=N;i++){
            for(int j=1;j<=i;j++)
                System.out.print(j);
            for(int sp=(N-i)*2;sp>0;sp--)
                System.out.print(" ");
            for(int j=i;j>0;j--)
                System.out.print(j);
            System.out.println();
        }
    }

}