package Strings2DArray;

import java.util.Scanner;
class palindrome_substring{
    public static void main(String args[]){
        Scanner d=new Scanner(System.in);
        String na=d.nextLine();
        System.out.println(countPalindromeSubstring(na));

    }

    public static int countPalindromeSubstring(String na){
        int c=1;
        StringBuffer t;
        for(int l=1;l<na.length();l++){
            for(int i=0;i<na.length()-l;i++){
                int mid=i,left=mid-l,right=mid+l;
                String temp = na.substring(mid, mid+l);
                t=new StringBuffer(temp);
                t.reverse();
                if(temp.equals(t.toString())){
                    c++;
                    System.out.println(temp);
                    while(left>=0 && right<na.length()){
                        if(na.charAt(left)==na.charAt(right)){
                            System.out.println(na.substring(left, right+1));
                            c++;
                            left--;
                            right++;
                        }
                        else
                            break;
                    }
                }
            }
        }
        return c;
	}
}