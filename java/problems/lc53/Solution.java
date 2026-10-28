package problems.lc53;

public class Solution {

    public int maxSubArray(int[] nums) {
        int candidate = nums[0], max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if(candidate < 0) {
                candidate = 0;
            }
            candidate += nums[i];
            max = Math.max(max, candidate);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println((new Solution()).maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println((new Solution()).maxSubArray(new int[]{-2}));
        System.out.println((new Solution()).maxSubArray(new int[]{-2, -9, 0}));
        System.out.println((new Solution()).maxSubArray(new int[]{-1, 2, 3}));
    }
}
