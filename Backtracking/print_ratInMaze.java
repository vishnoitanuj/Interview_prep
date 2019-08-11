package Backtracking;

public class print_ratInMaze {
	
	public static void main(String args[]) {
		int m[][]= {{1,0,1},{1,1,1},{1,1,1}};
		ratInAMaze(m);
	}
	
	public static void ratInAMaze(int maze[][]){
		int visited[][]=new int[maze.length][maze.length];
		ratInAMaze(maze,0,0,visited);
			
		}
	    
	    private static void ratInAMaze(int a[][],int i,int j,int v[][]){
	        int n=a.length;
	        if(i<0 || i>=n || j<0 || j>=n || a[i][j]==0 || v[i][j]==1)
	            return;
            v[i][j]=1;
	        if(i==n-1 && j==n-1){
	            for(int r=0;r<n;r++){
	                for(int c=0;c<n;c++)
	                    System.out.print(v[i][j]+" ");
	            }
	            System.out.println();
	            v[i][j]=0;
	            return;
	        }
	        ratInAMaze(a,i-1,j,v);
	        ratInAMaze(a,i,j+1,v);
	        ratInAMaze(a,i+1,j,v);
	        ratInAMaze(a,i,j-1,v);
	        v[i][j]=0;
	    }
}
