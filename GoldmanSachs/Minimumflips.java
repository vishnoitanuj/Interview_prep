package GoldmanSachs;

//Leetcode 1888: https://leetcode.com/problems/minimum-number-of-flips-to-make-the-binary-string-alternating/
public class Minimumflips {

    public int minFlips(String s){
        int min = Integer.MAX_VALUE;
        int n = s.length();
        int count=0;
        for(int i=0;i<2*n;i++){
            int r = i%n;
            if(s.charAt(r)!=(i%2==0?'1':'0'))
                count++;
            if(i>=n && s.charAt(r)!=(r%2==0?'1':'0'))
                count--;
            if(i>=n-1)
               min = Math.min(min,Math.min(count, n-count));
        }
        return min;
    }

    public static void main(String[] args) {
        String s = "111000";
        System.out.print(new Minimumflips().minFlips(s));
    }
}
