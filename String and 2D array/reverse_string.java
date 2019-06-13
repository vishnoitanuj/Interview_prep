import java.util.Scanner;

class reverse_string{
    public static void main(String args[]){
        Scanner d=new Scanner(System.in);
        String na=d.nextLine();
        System.out.println(reverseWordWise(na));

    }
    public static String reverseWordWise(String input) {
        String na[]=input.split(" ");
        String output = new String();
        for(int i=na.length-1;i>=0;i--)
            output+=na[i]+" ";
        output.trim();
        return output;
    }
}