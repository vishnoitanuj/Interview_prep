package Strings2DArray;

import java.util.Arrays;
import java.util.Scanner;
public class count_platform {

    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int arr[]=new int[n];
        int dep[]=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        for(int i=0;i<n;i++){
            dep[i]=sc.nextInt();
        }
        System.out.println(platformsNeeded(arr,dep));

    }

	public static int platformsNeeded(int arrival[], int departure[]){
        Arrays.sort(arrival);
        Arrays.sort(departure);
        int i=1,j=0,plt=1,result=1,n=arrival.length;
        while(i<n && j<n){
            if(arrival[i]<departure[j]){
                i++;
                plt++;
                if(plt>result)
                    result=plt;
            }
            else{
                j++;
                plt--;
            }
        }
        return result;		
	}
	
}
