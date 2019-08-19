package Hashmap;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
public class array_intersection {

    public static void main(String args[]) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int a[]=new int[n];
        for(int i=0;i<n;i++){
            a[i]=sc.nextInt();
        }
        int m=sc.nextInt();
        int b[]=new int[m];
        for(int i=0;i<m;i++){
            b[i]=sc.nextInt();
        }
        intersection(a,b);
    }
	public static void intersection(int[] a, int[] b){
		HashMap<Integer,Integer> map1 = new HashMap<>();
        HashMap<Integer,Integer> map2 = new HashMap<>();
		for(int i:a) {
			if(map1.containsKey(i))
				map1.put(i,map1.get(i)+1);
            else
			    map1.put(i, 1);
		}
        for(int i:b) {
			if(map2.containsKey(i))
				map2.put(i,map2.get(i)+1);
            else
			    map2.put(i, 1);
		}
		
		Set<Integer> keys = map1.keySet();
		for(int i:keys) {
			if(map2.containsKey(i)){
                int l=Math.min(map1.get(i),map2.get(i));
                for(int j=0;j<l;j++)
                    System.out.println(i);
            }
				
		}
	}
}