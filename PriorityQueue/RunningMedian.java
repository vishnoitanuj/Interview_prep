package PriorityQueue;

import java.util.*;
public class RunningMedian {

	public static void runningMedian(int a[]) {
		PriorityQueue<Integer> min=new PriorityQueue<>();
        PriorityQueue<Integer> max=new PriorityQueue<>(Collections.reverseOrder());
        max.add(a[0]);
        int median=a[0];
        System.out.println(median);
        for(int i=1;i<a.length;i++){
            int max_temp=max.peek();
            if(a[i]<max_temp){
                max.add(a[i]);
                if((max.size()-min.size())>=2)
                    min.add(max.remove());
            }
            else{
                min.add(a[i]);
                if((min.size()-max.size())>=2)
                    max.add(min.remove());
            }
            if(i%2==0){
                median=(min.size()>max.size())?min.peek():max.peek();
            }
            else
                median=(max.peek()+min.peek())/2;
            System.out.println(median);
        }
	}
}
