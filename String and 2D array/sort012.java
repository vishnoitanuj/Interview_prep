import java.util.Scanner;
class sort012{

    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int a[]=new int[n];
        for(int i=0;i<a.length;i++){
            a[i]=sc.nextInt();
        }
        sort(a);
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]+" ");
        }

    }
    public static void sort(int arr[]){
        int nz=0,nt=arr.length-1,t,l=arr.length;
		for(int i=0;i<l;i++){
            if(arr[i]==0){
                t=arr[nz];
                arr[nz]=arr[i];
                arr[i]=t;
                nz++;
            }
            else if(arr[i]==2){
                t=arr[nt];
                arr[nt]=arr[i];
                arr[i]=t;
                nt--;
                i--;
                l--;
            }
        }

    }
}