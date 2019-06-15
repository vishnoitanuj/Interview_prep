import java.util.ArrayList;
import java.util.HashMap;;
class remove_duplicates{
    public static void main(String args[]){
        int a[]={1,2,5,1,6,4,5};
        HashMap<Integer, Boolean> seen =new HashMap<>();
        ArrayList<Integer> out = new ArrayList<>();
        for(int i=0;i<a.length;i++){
            if(seen.containsKey(a[i]))
                continue;
            else{
                out.add(a[i]);
                seen.put(a[i], true);
            }
        }
        for(int i: out)
            System.out.print(i+" "); 
    }
}