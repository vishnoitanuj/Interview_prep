package DP;

import java.util.ArrayList;
import java.util.Scanner;

public class AlphaCode {
	
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
        String ch=s.next();
        while(!ch.equals("0")){
        	System.out.println(count(ch));
            ch=s.next();
        }
//        System.out.println(Integer.MAX_VALUE);
//        int n=(int)Math.pow(10,9)+7;
//        System.out.println(count());
        	
	}
    
    private static int count(String na){
        int a[]=new int[na.length()+1];
        a[0]=1;
        String t=na.substring(0,2);
        a[1]=(Integer.parseInt(t)<=26)?2:1;
        for(int i=2;i<na.length();i++){
            t=na.substring(i-1,i+1);
            int k=Integer.parseInt(t);
            a[i]=a[i-1];
            if(k<=26)
                a[i]+=a[i-2];
        }
        return a[na.length()-1];
    }

}
