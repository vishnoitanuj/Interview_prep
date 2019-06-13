import java.util.Scanner;
class wave_array{

    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int m=sc.nextInt();
        int n=sc.nextInt();
        int a[][]=new int[m][n];
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[0].length;j++)
                a[i][j]=sc.nextInt();;
        }
        wavePrint(a);

    }
    public static void wavePrint(int a[][]){
		
		for(int i=0;i<a[0].length;i++){
            for(int j=0;j<a.length;j++)
                if(i%2==0)
                    System.out.print(a[j][i]+" ");
            else
                System.out.print(a[a.length-j-1][i]+" ");
        }

    }
}