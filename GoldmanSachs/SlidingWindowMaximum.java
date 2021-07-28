package GoldmanSachs;

import java.util.ArrayDeque;
import java.util.Deque;

// Goldman
public class SlidingWindowMaximum {

    /*  poll -> remove head
        offer -> add at last
        peek -> retrieve at head
    */
    @SuppressWarnings("unchecked")
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums==null || k==1)
            return nums;
        int[] result = new int[nums.length - k + 1];
        int counter = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        for(int i=0;i<nums.length;i++){
            while (!deque.isEmpty() && deque.peek() < i-k+1)
                deque.poll();
            while (!deque.isEmpty() && nums[deque.peekLast()]<nums[i])
                deque.pollLast();
            deque.offer(i);
            if(i>=k-1)
                result[counter++] = nums[deque.peek()];
        }
        return result;

    }

    public static void main(String[] args) {
        SlidingWindowMaximum obj = new SlidingWindowMaximum();
        int nums[] = {1,3,-1,-3,5,3,6,7};
        int k=3;
        int[] result = obj.maxSlidingWindow(nums,k);
        for(int i:result)
            System.out.print(i+" ");
        System.out.println();
    }
}
