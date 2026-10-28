package problems.lc42;

import java.util.Stack;

public class Solution1 {

    public int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int sum = 0;
        for (int i = 1; i < height.length; i++) {
            int leftOver = 0;
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int top = stack.pop();
                int currentWater = (i - top) * height[top];
                sum = sum + (currentWater - leftOver);
                leftOver = currentWater + height[top];
            }
            stack.push(i);
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println((new Solution1()).trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println((new Solution1()).trap(new int[]{4,2,0,3,2,5}));
        System.out.println((new Solution1()).trap(new int[]{2,0,3,0,6,0,4,0,15,6,4,9}));
    }
}
