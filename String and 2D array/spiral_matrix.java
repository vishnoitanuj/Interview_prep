import java.util.Scanner;
class spiral_matrix{

    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int m=sc.nextInt();
        int n=sc.nextInt();
        int a[][]=new int[m][n];
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[0].length;j++)
                a[i][j]=sc.nextInt();;
        }
        spiralPrint(a);

    }
    public static void spiralPrint(int a[][]){
        
        int l1=a.length,l2=a[0].length,r=0,c=-1;
		while(l1>0 && l2>0){
            for(int j=0;j<l2;j++)
                System.out.print(a[r][++c]+" ");
            for(int j=1;j<l1;j++)
                System.out.print(a[++r][c]+" ");
            for(int j=1;j<l2;j++)
                System.out.print(a[r][--c]+" ");
            for(int j=1;j<l1-1;j++)
                System.out.print(a[--r][c]+" ");
            l1-=2;
            l2-=2;
        }

    }
}