package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class CombinationalSum {
	
	public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int arr[] = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = s.nextInt();
        }
        int sum = s.nextInt();
        ArrayList<ArrayList<Integer>> res = combinationSum(arr, sum);
        for(int i = 0; i < res.size(); i++) {
        	ArrayList<Integer> temp = res.get(i);
            for(int j = 0; j < temp.size(); j++) {
                System.out.print(temp.get(j) + " ");
            }
            System.out.println();
        }
    }
	
	public static ArrayList<ArrayList<Integer>> combinationSum(int a[],int sum) {
		HashMap<Integer, Boolean> map=new HashMap<>();
		for(int i:a) {
			map.put(i,true);
		}
		Set<Integer> keys=map.keySet();
		a=new int[keys.size()];
		int k=0;
		for(int i:keys)
			a[k++]=i;
		Arrays.sort(a);
		ArrayList<ArrayList<Integer>> out=new ArrayList<>();
		ArrayList<Integer> list=new ArrayList<Integer>();
		combinationSum(a,sum,0,list,out);
		return out;
			
	}

	private static void combinationSum(int[] a, int sum, int si, ArrayList<Integer> list,ArrayList<ArrayList<Integer>> out) {
		
		if(sum<0)
			return;
		if(sum==0 && (!out.contains(list))) {
			out.add(new ArrayList<>(list));
			return;
		}
		
		for(int i=si;i<a.length;i++) {
			list.add(a[si]);
			combinationSum(a,sum-a[si], i, list, out);
			list.remove(list.size()-1);
			combinationSum(a, sum, i+1, list, out);
		}
	}

}
