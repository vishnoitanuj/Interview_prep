package GoldmanSachs;

//LeetCode42 - https://leetcode.com/problems/trapping-rain-water/submissions/
public class TrappingRainWater {

    public int trap(int[] water){
        int n = water.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = water[0];
        for(int i=1;i<n;i++)
            left[i]=Math.max(left[i-1], water[i]);
        right[n-1]=water[n-1];
        for(int i=n-2;i>=0;i--)
            right[i] = Math.max(right[i+1],water[i]);
        int sum = 0;
        for(int i=0;i<n;i++)
            sum += Math.min(left[i],right[i])-water[i];
        return sum;
    }

    public static void main(String[] args) {
        int[] water = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.print(new TrappingRainWater().trap(water));
    }
}
