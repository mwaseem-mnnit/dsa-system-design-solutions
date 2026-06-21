package problems.lc45;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().jump(new int[]{2,3,1,1,4}));
        System.out.println(new Solution().jump(new int[]{1,1,1,1,1}));
        System.out.println(new Solution().jump(new int[]{1,1}));
        System.out.println(new Solution().jump(new int[]{1}));
    }
    public int jump(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }

        int[] result = new int[nums.length];
        result[0] = 0;
        for (int i = 1; i < result.length; i++) {
            result[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < nums.length; i++) {
            int currentStep = result[i];
            int lowerBound = lowerBound(result, i+1, nums.length - 1, currentStep + 2);
            for (int j = lowerBound; j <= Math.min(nums.length - 1, i + nums[i]); j++) {
                result[j] = Math.min(result[j], result[i] + 1);
            }
        }
        return result[nums.length - 1];
    }

    public static int lowerBound(int[] arr, int l, int r, int target) {
        int ans = r + 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (arr[mid] >= target) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return ans;
    }
}
