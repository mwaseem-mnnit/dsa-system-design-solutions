package problems.lc1458;

import java.util.Arrays;

class Solution {

    /*
     *    2  1  -2   5
     * 3  6  6   6   15
     * 0  6  6   6   15
     * -6 6  6   18  18
     * */

    public int maxDotProduct(int[] nums1, int[] nums2) {
        Integer[][] dp = new Integer[nums1.length][nums2.length];
        int max1 = Arrays.stream(nums1).max().getAsInt();
        int max2 = Arrays.stream(nums2).max().getAsInt();
        int min1 = Arrays.stream(nums1).min().getAsInt();
        int min2 = Arrays.stream(nums2).min().getAsInt();
        if(max1 < 0 && min2 > 0) {
            return max1 * min2;
        }
        if(max2 < 0 && min1 > 0 ) {
            return max2 * min1;
        }
        return findMax(dp, nums1, nums2, 0, 0);
    }

    private int findMax(Integer[][] dp, int[] num1, int[] num2, int i, int j) {
        if(i >= num1.length || j >= num2.length) {
            return 0;
        }
        if(dp[i][j] != null) {
            return dp[i][j];
        }
        dp[i][j] = Math.max(
                num1[i] * num2[j] + findMax(dp, num1, num2, i + 1, j + 1),
                Math.max(
                        findMax(dp, num1, num2, i+1, j),
                        findMax(dp, num1, num2, i, j + 1)
                )
        );
        return dp[i][j];
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.maxDotProduct(new int[]{3, 0, -6}, new int[]{2, 1, -2, 5}));
    }
}

