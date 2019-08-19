package Patterns;
/*
N=4
   1
  23
 345
4567
*/
import java.util.Scanner;

public class pattern2 {

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int N=sc.nextInt();
        print(N);
    }
	public static void print(int N) {
        for(int i=1;i<=N;i++){
            for(int sp=N-1;sp>=i;sp--){
                System.out.print(" ");
            }
            int n=i;
            for(int j=i;j>0;j--){
                System.out.print(n);
                n++;
            }
            System.out.println();
        }
    }

}