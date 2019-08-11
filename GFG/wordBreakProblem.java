package GFG;

public class wordBreakProblem {
	private static void print(String str,String na[],int n,String result) {
		
		for(int i=1;i<=n;i++) {
			String prefix=str.substring(0,i);
			if(dictContains(na,prefix)) {
				if(i==n) {
					result+=prefix;
					System.out.println(result);
					return;
				}
				print(str.substring(i),na,n-i,result+prefix+" ");
			}
		}
	}
	
	private static boolean dictContains(String na[],String str) {
		for(String i:na) {
			if(i.equals(str))
				return true;
		}
		return false;
	}
	

	public static void main(String[] args) {
		String dict[]= {"i","like","sam","samsung","mobile","sung"};
		String na="ilikesamsungmobile";
		print(na,dict,na.length(),"");

	}

}
