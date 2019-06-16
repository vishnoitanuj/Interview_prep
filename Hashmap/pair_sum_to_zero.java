import java.util.*;

public class pair_sum_to_zero {
	public static void PairSum(int[] input, int size) {

        public static void main(String args[]) {
            Scanner sc=new Scanner(System.in);
            int n=sc.nextInt();
            int a[]=new int[n];
            for(int i=0;i<n;i++){
                a[i]=sc.nextInt();
            }
            PairSum(a);
        }
		public static void PairSum(int[] input) {
            HashMap<Integer,Integer> map=new HashMap<>();
            for(int i:input){
                if(map.containsKey(i))
                    map.put(i,map.get(i)+1);
                else
                    map.put(i,1);
            }
            for(int i:input){
                if(map.containsKey(i*-1)){
                    int neg=(i<0)?i:-1*i;
                    int l=map.get(neg)*map.get(neg*-1);
                    for(int j=0;j<l;j++)
                        System.out.println(neg+" "+(-1*neg));
                    map.remove(i);
                    map.remove(-1*i);
                }
            }
        }
}