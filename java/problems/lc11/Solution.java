package problems.lc11;

public class Solution {

    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1, max = 0;
        while (l < r) {
            max = Math.max(max, (r - l) * Math.min(height[r], height[l]));
            if (height[r] > height[l]) {
                l++;
            } else r--;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println((new Solution()).maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        System.out.println((new Solution()).maxArea(new int[]{2, 0}));
        System.out.println((new Solution()).maxArea(new int[]{1, 2, 4, 3}));
        System.out.println((new Solution()).maxArea(new int[]{1, 2, 3, 1000, 9}));
        System.out.println((new Solution()).maxArea(new int[]{2, 3, 4, 5, 18, 17, 6}));
    }
}
