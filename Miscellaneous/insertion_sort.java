/*
Complexity: O(n**2)
Best case: O(n)
*/
class insertion_sort{
    public static void main(String args[]){
        int[] arr = new int[5];
        arr = new int[6];
        // int arr[] = {5,4,3,1,2};
        // arr = sort(arr);
        // for(int i=0;i<arr.length;i++)
        //     System.out.print(arr[i]+" ");
    }
    public static int[] sort(int a[]){
        for(int i=1;i<a.length;i++){
            int key=a[i];
            int j=i-1;
            while(j>=0 && a[j]>key){
                a[j+1]=a[j];
                j--;
            }
            a[j+1]=key;
        }
        return a;
    }
}