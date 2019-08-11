package PriorityQueue;

public class checkMaxHeap {
	
	public static boolean checkmaxheap(int a[]) {
		int n=a.length;
		for(int i=0;i<n;i++) {
			if((2*i+1)<n && a[i]<a[2*i+1])
				return false;
			if((2*i+2)<n && a[i]<a[2*i+2])
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
