package Graphs;

import java.util.Scanner;

public class CodingNinja {
	
	int a[][]= {{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1}};
	String na="CODINGNINJA";
	boolean visited[][];
	
	public int solve(String[] Graph , int n, int m)
	{
		int i,j,f=0;
		visited=new boolean[n][m];
		for(i=0;i<n;i++) {
			for(j=0;j<m;j++) {
				if(Graph[i].charAt(j)=='C') {
					f=DFS(Graph,i,j,1,n,m);
					if(f==1)
						return 1;
					}
			}
		}
		return f;

	}	
	
	private int DFS(String g[],int x,int y,int index,int n,int m) {
		if(index>=na.length())
			return 1;
		visited[x][y]=true;
		int i,newx,newy,f=0;
		for(i=0;i<8;i++) {
			newx=x+a[i][0];
			newy=y+a[i][1];
			if(validPoint(newx,newy,n,m) && g[newx].charAt(newy)==na.charAt(index) && !visited[newx][newy]) {
				f=f|DFS(g,newx,newy,index+1,n,m);
			}
		}
		visited[x][y]=false;
		return f;
		
	}
	
	private boolean validPoint(int x,int y,int n,int m) {
		if(x>=0 && y>=0 && x<n && y<m)
			return true;
		return false;
	}
    
    
    public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int N,M,i;
		
		N = scan.nextInt();
		M = scan.nextInt();
		
		String[] Graph = new String[N];
		
		for(i = 0; i < N; i++){
			
			Graph[i] = scan.next();
			
		}
		System.out.println(new CodingNinja().solve(Graph,N,M));

	}
}
