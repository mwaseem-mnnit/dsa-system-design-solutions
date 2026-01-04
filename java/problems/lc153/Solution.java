package problems.lc153;

public class Solution {

    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1, pivot = 0;
        while (l < r) {
            int mid = (l + r) / 2;
            if(nums[l] <= nums[mid] && nums[mid] <= nums[r]) {
                break;
            }

            if(nums[l] > nums[mid]) {
                r = mid;
            } else if(nums[mid] > nums[r]) {
                l = mid;
            }

            if(l == mid && l + 1 < nums.length && nums[l + 1] < nums[l]) {
                pivot = l + 1;
                break;
            }
            if(r == mid && r -1 >= 0 && nums[r-1] > nums[r]) {
                pivot = r;
                break;
            }
        }
        if(pivot == -1) {
            return nums[0];
        }
        return nums[pivot];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findMin(new int[]{3,4,5,1,2}));
        System.out.println(new Solution().findMin(new int[]{4,5,6,1,2,3}));
        System.out.println(new Solution().findMin(new int[]{6,1,2,3,4,5}));
        System.out.println(new Solution().findMin(new int[]{1,2,3,4,5}));
        System.out.println(new Solution().findMin(new int[]{1,2,3,4}));
        System.out.println(new Solution().findMin(new int[]{1}));
    }
}
