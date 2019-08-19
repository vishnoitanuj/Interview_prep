package Hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Scanner;

public class longest_increasing_subsequence{

    public static void main(String args[]) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int a[]=new int[n];
        for(int i=0;i<n;i++){
            a[i]=sc.nextInt();
        }
        longestSubsequence(a);
    }

	public static ArrayList<Integer> longestSubsequence(int[] arr){
		// Write your code here
        ArrayList<Integer> out=new ArrayList<>();
        HashMap<Integer, Boolean> map=new HashMap<>();
        for(int i:arr){
            map.put(i,true);
        }
        int max=0,start=arr[0];
        Set<Integer> keys=map.keySet();
        for(int i:keys){
            if(map.get(i)){
                map.put(i,false);
                //right
            int j=i+1,left=0,right=0;
            while(map.containsKey(j) && map.get(j)){
                right++;
                map.put(j,false);
                j++;
            }
            j=i-1;
            while(map.containsKey(j) && map.get(j)){
                left++;
                map.put(j,false);
                j--;
            }
            if(left>max){
                max=left;
                start=i-left;
            }
            else if(right>max){
                max=right;
                start=i;
            }
            else if(right==max || left==max){
                int min=(i<(i-left))?i:i-left;
                max=(right==max)?right:left;    
                for(int k=0;k<arr.length;k++){
                    if(arr[k]==min){
                        start=min;
                        break;
                    }
                    if(arr[k]==start){
                        break;
                    }
                }
            }
            }
        }
        for(int i=start;i<=start+max;i++)
            out.add(i);
        return out;
	}
}
