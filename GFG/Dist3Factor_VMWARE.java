package GFG;

public class Dist3Factor_VMWARE {
	
	public static boolean check(long n) {
		int sq=(int)Math.sqrt(n);
		if(1L*sq*sq !=n)
			return false;
		return isPrime(sq)?true:false;
	}
	
	public static boolean isPrime(long n) {
		if(n<=1)
			return false;
		if(n<=3)
			return true;
		if(n%2==0 || n%3==0)
			return false;
		for(int i=5;i*i<n;i+=6)
			if(n%i==0 || n%(i+2)==0)
				return false;
		return true;
	}

	public static void main(String[] args) {
		System.out.println(check(100L));

	}

}
