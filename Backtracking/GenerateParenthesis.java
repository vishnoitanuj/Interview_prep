package Backtracking;

import java.util.Scanner;

public class GenerateParenthesis {
	static Scanner s = new Scanner(System.in);
	public static void main(String[] args) {
		 
		int n = s.nextInt();
		printWellFormedParanthesis(n);

	}
	
	public static void printWellFormedParanthesis( int n){
		if(n > 0) 
        printWellFormedParanthesis("", n, 0, 0); 
        return; 

	}
    
    private static void printWellFormedParanthesis(String na, int n, int open, int close) {
        
        if(close == n)  
        { 
            System.out.println(na); 
            return; 
        } 
        else
        { 
            if(open < n) { 
                na += '('; 
                printWellFormedParanthesis(na, n, open+1, close); 
            } 
            if(open > close) { 
                na += ')'; 
                printWellFormedParanthesis(na, n, open, close+1); 
            } 
        } 
    } 

}
