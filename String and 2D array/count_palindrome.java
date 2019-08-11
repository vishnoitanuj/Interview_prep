import java.util.Scanner;

class count_palindrome{
    public static void main(String args[]){
        Scanner d=new Scanner(System.in);
        String na=d.nextLine();
        System.out.println(countPalindromeSubstrings(na));

    }
    public static int countPalindromeSubstrings(String na) {
        int c=0;
		for(int i=0;i<na.length();i++){
            String t=new String();
            for(int j=i;j<na.length();j++){
                t+=na.charAt(j);
                if(checkPalindrome(t))
                    c++;
                
            }
        }
        return c;

	}
    
    public static boolean checkPalindrome(String na){
        String t=new String();
        for(int i=na.length()-1;i>=0;i--)
        t+=na.charAt(i);
        return (t.equals(na));
    }
}