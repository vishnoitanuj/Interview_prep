package Patterns;

/*
N=3
  1
 232
34543
*/
import java.util.Scanner;

public class pattern1 {

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int N=sc.nextInt();
        print(N);
    }
	public static void print(int N) {
        int c=0;
		for(int i=1;i<=N;i++){
            for(int sp=N-1;sp>=i;sp--)
                System.out.print(" ");
            for(int j=1;j<=i;j++){
                c++;
                System.out.print(c);
            }
            for(int k=i-1;k>0;k--){
                c--;
                System.out.print(c);
            }
            System.out.println();
        }
	}
}