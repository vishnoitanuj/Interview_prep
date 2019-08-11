package PriorityQueue;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

class MaxHeapComparator implements Comparator<Integer>{

	@Override
	public int compare(Integer o1, Integer o2) {
		if(o1<o2)
			return 1;
		else if(o1>o2)
			return -1;
		else
			return 0;
	}
	
	
}

public class MaxPriorityQueueInBuilt {
	
	public static void main(String args[]) {
		MaxHeapComparator ob=new MaxHeapComparator();
		PriorityQueue<Integer> pq=new PriorityQueue<Integer>(ob);
//		PriorityQueue<Integer> pq=new PriorityQueue<Integer>(Collections.reverseOrder());
		int a[]= {9,1,10,2,3};
		for(int i:a)
			pq.add(i);
		while(!pq.isEmpty())
			System.out.print(pq.remove()+" ");
	}

}
