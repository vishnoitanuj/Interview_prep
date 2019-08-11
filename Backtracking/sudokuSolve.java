package Backtracking;

import java.util.Scanner;

public class sudokuSolve {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		int board[][] = new int[9][9];
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				board[i][j] = s.nextInt();
			}
		}
		System.out.println(sudokuSolver(board));	
	}
	
    public static boolean sudokuSolver(int a[][]){
    	int row=-1,col=-1;
    	boolean f=false;
    	for(int i=0;i<9;i++) {
    		for(int j=0;j<9;j++) {
    			if(a[i][j]==0) {
    				row=i;
    				col=j;
    				f=true;
    				break;
    			}
    		}
    		if(f)
    			break;
    	}
    	if(row==-1)
    		return true;
    	for(int i=1;i<=9;i++) {
    		if(isSafe(a, row, col, i)) {
    			a[row][col]=i;
    			if(sudokuSolver(a))
    				return true;
    			else
    				a[row][col]=0;
    		}
    	}
    	return false;
        
    }
    private static boolean isSafe(int a[][],int row,int col,int n){
        //col
        for(int i=0;i<9;i++){
            if(a[i][col]==n)
                return false;
        }
        //row
        for(int i=0;i<9;i++){
            if(a[row][i]==n)
                return false;
        }
        //box
        int t=row/3,s=col/3;
        int sr=t*3,sc=s*3;
        for(int i=sr;i<sr+3;i++){
            for(int j=sc;j<sc+3;j++){
                if(a[i][j]==n)
                    return false;
            }
        }
        return true;
    }

}
