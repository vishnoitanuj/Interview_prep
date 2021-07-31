package GoldmanSachs;

//Leetcode: 53 https://leetcode.com/problems/maximum-subarray/
public class MaximumContinousSumSubArray {

    public int maxSum(int[] a){
        int[] dp = new int[a.length];
        int max = dp[0] = a[0];
        for(int i=1;i<a.length;i++){
            dp[i] = a[i] + Math.max(dp[i-1],0);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] a = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.print(new MaximumContinousSumSubArray().maxSum(a));
    }
}
