import java.util.Scanner;
class PrintKeypad{
    
    public static void main(String[] args) {
        Scanner d= new Scanner(System.in);
        int n=d.nextInt();
        printKeypad(n);
    }

    public static void printKeypad(int input){
		printKeypad(input,"");
	}
    
    public static void printKeypad(int input,String output){
		if(input==0 || input==1){
            System.out.println(output);
            return;
        }
        char ch[]=helper(input%10);
        for(int i=0;i<ch.length;i++)
            printKeypad(input/10,ch[i]+output);
		
	}
    private static char[] helper(int n){
        int l=(n==7 || n==9)?4:3;
        char ch[]=new char[l];
        switch(n){
            case 2:
                ch= new char[]{'a','b','c'};
                break;
            case 3:
                ch= new char[]{'d','e','f'};
                break;
            case 4:
                ch= new char[]{'g','h','i'};
                break;
            case 5:
                ch= new char[]{'j','k','l'};
                break;
            case 6:
                ch= new char[]{'m','n','o'};
                break;
            case 7:
                ch= new char[]{'p','q','r','s'};
                break;
            case 8:
                ch= new char[]{'t','u','v'};
                break;
            case 9:
                ch= new char[]{'w','x','y','z'};
                break;
        }
        return ch;
    }
}