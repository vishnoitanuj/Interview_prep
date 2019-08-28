package Graphs;

import java.util.Scanner;

public class ConnectingDots {
	
	boolean visited[][];
    int[] dx= {1,-1,0,0};
    int[] dy= {0,0,1,-1};
    int findCycle = 0;
	
	int solve(String[] board , int n, int m)
	{
		visited=new boolean[n][m];
		// Write your code here.
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!visited[i][j]){
                    dfs(board,i,j,-1,-1,board[i].charAt(j),n,m);
                }
            }
        }
        return findCycle;
	}	
    
    private void dfs(String[] board,int x,int y,int fromX,int fromY,char needColor, int n,int m) {
    	if(x<0 || x>=n || y<0 || y>=m)
    		return;
    	if(board[x].charAt(y)!=needColor)
    		return;
    	if(visited[x][y]) {
    		findCycle=1;
    		return;
    	}
    	visited[x][y]=true;
    	for(int f=0;f<4;f++) {
    		int nextX=x+dx[f];
    		int nextY=y+dy[f];
    		if(nextX==fromX && nextY==fromY)
    			continue;
    		dfs(board,nextX,nextY,x,y,needColor,n,m);
    	}
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
