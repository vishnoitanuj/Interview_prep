import java.util.Scanner;

public class interleaving {

	public static void main(String args[]){
        Scanner d=new Scanner(System.in);
        String a=d.nextLine();
        String b=d.nextLine();
        interleavings(a, b);
    }
	public static void interleavings(String first, String second){
        String out=new String();
		interleavings(first,second,out);
		
	}
    
    public static void interleavings(String a, String b, String c){
        if(a.length()==0 && b.length()==0){
            System.out.println(c);
        }
        if(a.length()!=0){
            interleavings(a.substring(1),b,c+a.charAt(0));
        }
        
        if(b.length()!=0){
            interleavings(a,b.substring(1),c+b.charAt(0));
        }
	}
	
}