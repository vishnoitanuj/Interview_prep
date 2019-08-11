package Graphs;

import java.util.Scanner;

public class ConnectingDots {
	
	boolean visited[][];
    int a[][]={{-1,0},{1,0},{0,1},{0,-1}};
	
	int solve(String[] board , int n, int m)
	{
		// Write your code here.
        int f=0;
        visited=new boolean[n][m];
//        f=DFS(board,0,1,0,0,board[0].charAt(1),n,m,0);
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!visited[i][j]){
                    f=DFS(board,i,j,board[i].charAt(j),n,m,0);
                    if(f==1)
                        return f;
                }
            }
        }
        return f;
	}	
    
    private int DFS(String g[],int x2,int y2,char s,int n,int m,int c) {
        if(c==4)
            return 1;
		visited[x2][y2]=true;
		int i,newx,newy,f=0;
		for(i=0;i<4;i++) {
			newx=x2+a[i][0];
			newy=y2+a[i][1];
			if(validPoint(newx,newy,n,m) && g[newx].charAt(newy)==s && !visited[newx][newy]) {
				f=f|DFS(g,newx,newy,s,n,m,c+1);
			}
		}
		visited[x2][y2]=false;
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
		
		String[] board = new String[N];
		
		for(i = 0; i < N; i++){
			
			board[i] = scan.next();
			
		}
		System.out.println(new ConnectingDots().solve(board,N,M));

	}

}
