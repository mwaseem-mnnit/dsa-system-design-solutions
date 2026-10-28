package problems.lc42;

import java.util.Arrays;
import java.util.Stack;

public class Solution {

    public int trap(int[] height) {
        int[] largestRight = new int[height.length];
        int[] largestLeft = new int[height.length];
        Arrays.fill(largestLeft, -1);
        Arrays.fill(largestRight, -1);
        this.computeLargestLeft(height, largestLeft);
        this.computeLargestRight(height, largestRight);
        int sum = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int topWater = Math.min(largestLeft[i], largestRight[i]);
            if(topWater > height[i]) {
                sum += Math.max(0, topWater - height[i]);
            }

        }
        return sum;
    }

    private void computeLargestRight(int[] height, int[] largestRight) {
        int max = height[height.length - 1];
        for (int i = height.length - 2; i > 0; i--) {
            largestRight[i] = max;
            max = Math.max(max, height[i]);
        }
    }

    private void computeLargestLeft(int[] height, int[] largestLeft) {
        int max = height[0];
        for (int i = 1; i < height.length; i++) {
            largestLeft[i] = max;
            max = Math.max(max, height[i]);
        }
    }

    public static void main(String[] args) {
        System.out.println((new Solution()).trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println((new Solution()).trap(new int[]{4,2,0,3,2,5}));
        System.out.println((new Solution()).trap(new int[]{2,0,3,0,6,0,4,0,15,6,4,9}));
    }

}
