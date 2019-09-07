package Strings;

public class PalindromicSubstrings {
	
	public static int count(String na) {
		int c=0;
		for(int i=0;i<na.length();i++) {
			int left=i-1;
			int right=i+1;
			c++;
			while(left>=0 && right<na.length()) {
				if(na.charAt(left)==na.charAt(right)) {
					c++;
					left--;
					right++;
				}
				else
					break;
			}
			if(i<na.length()-1 && na.charAt(i)==na.charAt(i+1)) {
				left=i-1;
				right=i+2;
				c++;
				while(left>=0 && right<na.length()) {
					if(na.charAt(left)==na.charAt(right)) {
						c++;
						left--;
						right++;
					}
					else
						break;
				}	
				
			}
		}
		return c;
	}
	
	public static void main(String[] args) {
		String na="ababa"; 
  
        System.out.println(count(na)); 
	}

}
