package Backtracking;

public class n_queen {
	
	public static void main(String args[]) {
		placeNQueens(4);
	}
	
	public static void placeNQueens(int n){
	    int a[][]=new int[n][n];
	    SolveNQ(a,0);
	    return;

		}
	
	private static void print(int a[][]) {
		for(int i=0;i<a.length;i++) {
    		for(int j=0;j<a.length;j++)
    			System.out.print(a[i][j]+" ");
    	}
    	System.out.println();
	}
	    
    private static boolean SolveNQ(int a[][],int col){
        if(col>=a.length){
        	print(a);
            return true;
        }
        boolean res=false;
        for(int i=0;i<a.length;i++){
            if(isSafe(i,col,a)){
                a[i][col]=1;
                res=SolveNQ(a,col+1) || res;
                a[i][col]=0;
            }
        }
        return res;
    }
    
    private static boolean isSafe(int i,int j,int a[][]){
        for(int c=0;c<a.length;c++){
            if(a[i][c]==1)
                return false;
        }
        
        for(int r=i,c=j;r>=0 && c>=0;r--,c--){
            if(a[r][c]==1)
                return false;
        }
        for(int r=i,c=j;r<a.length && c>=0;r++,c--){
            if(a[r][c]==1)
                return false;
        }
        return true;
        
    }
    

}
