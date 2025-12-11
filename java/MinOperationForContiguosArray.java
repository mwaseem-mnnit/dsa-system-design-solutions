import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public static int lower_bound(int[] array, int key) {
        int low = 0, high = array.length;
        int mid;
        while (low < high) {
            mid = low + (high - low) / 2;
            if (key <= array[mid]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        if (low < array.length && array[low] < key) {
            low++;
        }
        return low;
    }

    public static int minOperations(int[] nums) {
        if(nums.length == 1) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int[] nums2 = new int[set.size()];
        int j=0;
        for (Integer integer : set) {
            nums2[j++] = integer;
        }
        Arrays.sort(nums2);
        int min = nums.length;
        for(int i=0; i<nums2.length; i++) {
            int lastElement = nums2[i] + nums.length - 1;
            int idx = lower_bound(nums2, lastElement);
            if(idx >= nums2.length) {
                idx=nums2.length-1;
            }
            if( lastElement < nums2[idx]) {
                idx-=1;
            }
            min = Math.min(min, nums.length - (idx - i + 1));
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(minOperations(new int[]{4,2,5,3}));
        System.out.println(minOperations(new int[]{1,2,3,5,6}));
        System.out.println(minOperations(new int[]{2,3,1,1,2,2,7}));
        System.out.println(minOperations(new int[]{1,10,100,1000}));
    }
}