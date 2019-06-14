import java.util.Scanner;

class break_string{
    public static void main(String args[]){
        Scanner d=new Scanner(System.in);
        String na=d.nextLine();
        System.out.println(breakWords(na));

    }
    public static String breakWords(String input){
		String na[]=input.split(" ");
        String t=new String();
        for(int i=0;i<na.length;i++){
            t+=" ";
            if(na[i].length()>=4 && na[i].length()%2==0){
                t+=na[i].substring(0,na[i].length()/2)+" "+na[i].substring(na[i].length()/2);
            }
            else
                t+=na[i];
        }
        t=t.trim();
        return t;
		
	}
}