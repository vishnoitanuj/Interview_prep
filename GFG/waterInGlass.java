package GFG;

import java.text.DecimalFormat;

public class waterInGlass {
	
	private static double find(double k,int r,int c) {
		double a[]=new double[r*(r+1)];
		a[0]=k;
		int index=0;
		for(int i=1;i<=r;i++) {
			for(int j=1;j<=i;j++,index++) {
				k=a[index];
				a[index]=(k>1)?1.0:k;
				k=(k>1)?k-1.0:0;
				a[index+i]=k/2;
				a[index+i+1]=k/2;
			}
		}
		double ans= a[r*(r-1)/2 + c-1];
		ans=Math.round(ans*100.000000)/100.000000;
		return ans;
	}

	public static void main(String[] args) {
		double ans=find(2.0,2,2);
		DecimalFormat df=new DecimalFormat("#.#####");
		System.out.println(ans);

	}

}
