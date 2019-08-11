/*
Complexity: O(n**2)
*/
class selection_sort{
    public static void main(String args[]){
        int arr[] = {5,4,3,1,2};
        arr = sort(arr);
        for(int i=0;i<arr.length;i++)
            System.out.print(arr[i]+" ");
    }
    public static int[] sort(int a[]){
        int min;
        for(int i=0;i<a.length-1;i++){
            min=i;
            for(int j=i+1;j<a.length;j++){
                if(a[j]<a[min]){
                    min=j;
                }
            }
            int t = a[min];
            a[min]=a[i];
            a[i]=t;
        }
        return a;
    }
}