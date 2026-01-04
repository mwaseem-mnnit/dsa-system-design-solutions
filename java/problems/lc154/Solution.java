package problems.lc154;

import java.util.Stack;

class Solution {

    public int findMin(int[] nums) {
        Stack<int[]> stack = new Stack<>();
        int pivot = -1;
        stack.push(new int[]{0, nums.length - 1});
        while (!stack.isEmpty()) {
            int[] top = stack.pop();
            int l = top[0];
            int r = top[1];
            int mid = (r + l) / 2;

            if (nums[l] == nums[mid] && nums[mid] == nums[r]) {
                pushToStack(stack, l, r, mid);
                continue;
            }
            pivot = computePivotIfExist(nums, l, r);
            if (pivot != -1) {
                break;
            }
        }
        if(pivot != -1) {
            return nums[pivot];
        }
        return nums[0];
    }

    int computePivotIfExist(int[] nums, int l, int r) {
        int pivot = -1;
        while (l < r) {
            int mid = (r + l) / 2;
            if(nums[mid] >= nums[l] && nums[mid] <= nums[r]) {
                return -1;
            }
            if(nums[mid] == nums[l] && nums[mid] == nums[r]) {
                return -1;
            }
            if (nums[mid] < nums[l]) {
                r = mid;
            }
            if (nums[mid] > nums[r]) {
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
        if(l + 1 < nums.length && nums[l + 1] < nums[l]) {
            pivot = l + 1;
        }
        if(r - 1 >= 0 && nums[r-1] > nums[r]) {
            pivot = r;
        }
        return pivot;
    }

    void pushToStack(Stack<int[]> stack, int l, int r, int mid) {
        if (mid - l > 1) {
            stack.push(new int[]{l, mid});
        }
        if( r - mid > 1) {
            stack.push(new int[]{mid, r});
        }
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.findMin(new int[]{3, 4, 5, 1, 2}));
        System.out.println(obj.findMin(new int[]{4, 4, 4, 4, 2, 3, 4}));
        System.out.println(obj.findMin(new int[]{4, 1, 3, 4, 4, 4, 4, 4}));
        System.out.println(obj.findMin(new int[]{2, 3, 4, 5, 6, 7, 8, 1}));
        System.out.println(obj.findMin(new int[]{4,4,5,4,4}));
        System.out.println(obj.findMin(new int[]{4,5,6,1,2,3}));
        System.out.println(obj.findMin(new int[]{1,2,3,4,5}));
        System.out.println(obj.findMin(new int[]{1}));
        System.out.println(obj.findMin(new int[]{1,1}));
    }
}
