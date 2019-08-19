package Hashmap;

import java.util.*;

public class longest_subset_with_max_10{

    public static void main(String args[]) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int a[]=new int[n];
        for(int i=0;i<n;i++){
            a[i]=sc.nextInt();
        }
        System.out.println(max(a));
    }
    
	public static int max(int[] arr){
        int max=0;
        HashMap<Integer,Integer> map=new HashMap<>();
        map.put(1,0);
        map.put(0,0);
        for(int i:arr){
            map.put(i,map.get(i)+1);
        }
        for(int i:arr){
            if(map.get(1)==0 || map.get(0)==0)
            break;
            if(map.get(1)==map.get(0)){
                    max=map.get(1);
                    break;
                }
            map.put(i,map.get(i)-1);

            
        }
        return max*2;
    
	}

}
