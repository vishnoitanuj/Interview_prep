import java.util.HashMap;
import java.util.Scanner;
public class max_frequency {

    public static void main(String args[]) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int a[]=new int[n];
        for(int i=0;i<a.length;i++){
            a[i]=sc.nextInt();
        }
        System.out.println(maxFrequencyNumber(a));
    }
	public static int maxFrequencyNumber(int[] arr){
		HashMap<Integer, Integer> map=new HashMap<>();
        for(int i=0;i<arr.length;i++){
            if(map.containsKey(arr[i]))
                map.put(arr[i], map.get(arr[i])+1);
            else
                map.put(arr[i], 1);
        }
        int max=0,out=arr[0];
        
        for(int i:arr){
            if(map.get(i)>max){
                max=map.get(i);
                out=i;
            }
        }
        return out;
		
	}
}