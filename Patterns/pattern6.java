package Patterns;

/*
N=5
 1    2   3    4   5
 11   12  13   14  15
 21   22  23   24  25
 16   17  18   19  20
 6    7    8   9   10
*/

import java.util.Scanner;

public class pattern6 {

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int N=sc.nextInt();
        print(N);
    }
	public static void print(int n) {
        int c=0, f=(int)Math.ceil(n/2.0);
        for(int i=1;i<=n;i++){
            int s=c*n+1;
            for(int j=1;j<=n;j++){
                System.out.print(s+" ");
                s++;
            }
            if(i<f){
                c+=2;
            }
            else if(i==f){
                c= (n%2==0)?c+1:c-1;
            }
            else{
                c-=2;
            }
            System.out.println();

        }
    }
}