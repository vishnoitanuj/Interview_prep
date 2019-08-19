package Strings2DArray;

/*
Largest Row or Column
Send Feedback
Given an NxM 2D array, you need to find out which row or column has largest sum (sum of its elements) overall amongst all rows and columns.
Input Format :
 Line 1 : 2 integers N and M respectively, separated by space 
 Line 2: Single line having N*M elements entered in row wise manner, each separated by space.
Output Format :
 If row sum is maximum then - "row" row_num max_sum
 If column sum is maximum then - "column" col_num max_sum
Note : If there are more than one rows/columns with maximum sum consider the row/column that comes first. And if ith row and jth column has same sum (which is largest), consider the ith row as answer.
*/

import java.util.Scanner;

class largest_row_or_column{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int m=sc.nextInt();
        int n=sc.nextInt();
        int a[][]=new int[m][n];
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[0].length;j++)
                a[i][j]=sc.nextInt();;
        }
        getAns(a);

    }
    public static void getAns(int a[][]) {
        int b[]=new int[a.length+a[0].length];
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[0].length;j++)
                b[i]+=a[i][j];
        }
        for(int i=0;i<a[0].length;i++){
            for(int j=0;j<a.length;j++)
                b[a.length+i]+=a[j][i];
        }
        int max=b[0],p=0;
        for(int i=1;i<b.length;i++){
            if(b[i]>max){
                max=b[i];
                p=i;
            }
        }
        if(p<a.length)
        System.out.println("row "+p+" "+max);
        else
        System.out.println("column "+(p-a.length)+" "+max);
    }
}