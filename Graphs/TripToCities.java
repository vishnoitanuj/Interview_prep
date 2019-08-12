package Graphs;

import java.util.Scanner;

public class TripToCities {

	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int c = in.nextInt();
        int k = in.nextInt();
        int a[][]=new int[n][n];
        
        for(int i = 0; i < m; i++) {
        	int x = in.nextInt();
        	int y = in.nextInt();
        	int z = in.nextInt();
        	a[x-1][y-1]=z;
            a[y-1][x-1]=z;
        }
        System.out.println(count(a,n,m,c-1,k));
     in.close();   
    }

	public static int count(int[][] a, int n, int m, int c, int k) {
		boolean visited[]=new boolean[n];
		int count=1;
		visited[c]=true;
		int swap=0;
		do {
			swap=0;
			int v=c;
			int d=0;
			for(int j=0;j<n;j++) {
				if(!visited[j] && a[v][j]!=0) {
					if((d+a[v][j])<=k) {
						d+=a[v][j];
						visited[j]=true;
						v=j;
						swap=1;
						count++;
					}
					else 
						break;
					}
			}
		}while(swap!=0);
		
		return count;
	}
	
	

}
