import java.util.Scanner;
class largest_unique_substring{
    public static void main(String args[]){
        Scanner d=new Scanner(System.in);
        String na=d.nextLine();
        System.out.println(findLargestUniqueSubstring(na));

    }

    public static String findLargestUniqueSubstring(String str){
		int max=0,index=0;
        String output=new String();
        String t=""+str.charAt(0);
        for(int j=1;j<str.length();j++){
            char ch=str.charAt(j);
            index=t.indexOf(ch);
            if(index<0)
                t+=ch;
            else{
                int l=t.length();
                if(l>max){
                    max=l;
                    output=t;
                }
                t=t.substring(index+1)+ch;
            }
        }
        return output;
	}
}