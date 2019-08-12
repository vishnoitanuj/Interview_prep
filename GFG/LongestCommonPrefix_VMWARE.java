package GFG;

import java.util.Arrays;

public class LongestCommonPrefix_VMWARE {

	public static void main(String[] args) {
		String arr[] = {"apple", "ape", "april"}; 
	        int n = arr.length; 
	  
	        commonPrefix(arr, n); 

	}

	private static void commonPrefix(String[] arr, int n) {
		Arrays.sort(arr);
		String result="";
		String a=arr[0];
		String b=arr[arr.length-1];
		for(int i=0,j=0;i<a.length() && j<b.length();i++,j++) {
			if(a.charAt(i)!=b.charAt(j))
				break;
			result+=a.charAt(i);
		}
		System.out.println(result);
		
	}

}
