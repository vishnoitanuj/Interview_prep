package GFG;

public class MagnetArrayProblem {
	
	public static void calculate(int a[]) {
		for(int i=0 ; i<a.length-1 ; i++){
            double mid=0.0, l=a[i], r=a[i+1];
            while(Math.abs(l-r) > 0.000001){
                mid=(l+r)/2;
                double tf = 0;
                for(int j=0 ; j<=i ; j++){
                    tf += 1/(mid-a[j]);
                }
                for(int j=i+1; j<a.length ; j++){
                    tf -= 1/(a[j]-mid);
                }
                if(tf > 0){
                    l = mid;
                }
                else if(tf < 0 ){
                    r = mid;
                }
                else{
                    break;
                }
            }
            System.out.printf("%.2f ", mid);
        }
	}

	public static void main(String[] args) {
		int a[]= {0,10,20,30};
		calculate(a);
	}

}
