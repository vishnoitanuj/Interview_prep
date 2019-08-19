package Patterns;

/*
N=7
*
 * *
   * * *
     * * * *
   * * *
 * *
*

*/
import java.util.Scanner;

public class pattern4 {

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int N=sc.nextInt();
        print(N);
    }
	public static void print(int N) {
        for(int i=1;i<=N;i++){
            if(i<=(N/2+1)){
                for(int sp=1;sp<i;sp++)
                    System.out.print(" ");
                for(int j=1;j<=i;j++)
                    System.out.print("* ");
                System.out.println();
            }
            else{
                for(int sp=N;sp>i;sp--)
                    System.out.print(" ");
                for(int j=N;j>=i;j--)
                    System.out.print("* ");
                System.out.println();
            }
        }
    }
}