package Recursion;

class return_all_codes{
    
    public static  String[] getCode(String input){
		if(input.length()==0){
            String na[]=new String[1];
            na[0]="";
            return na;
        }
           
        if(input.length()==1){
            String na[]=new String[1];
            na[0]="" + (char)(input.charAt(0)-'0'+'a'-1);
            return na;
        }
        
        String small1[] = getCode(input.substring(1));
        String small2[] = getCode(input.substring(2));
        int a=Integer.parseInt(input.substring(0,1));
        int b=0;
        if(small2.length>0)
            b=Integer.parseInt(input.substring(0,2));
        int k=0,f=-1,l=0;
        if(b>=10 && b<=26){
            l=small1.length+small2.length;
            f=0;
        }
        else
            l=small1.length;
        String ans[]=new String[l];
        if(f==0){
            char ch2=(char)('a'+b-1);
            for(int i=0;i<small2.length;i++){
                ans[k++]=ch2+small2[i];
            }
        }
        char ch1=(char)('a'+a-1);
        for(int i=0;i<small1.length;i++){
            ans[k++]=ch1+small1[i];
        }  
        return ans;
		
	}
}