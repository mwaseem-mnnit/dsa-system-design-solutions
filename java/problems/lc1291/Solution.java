package problems.lc1291;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        int i = Integer.valueOf(low).toString().length();
        int maxLength = Math.min(9, Integer.valueOf(high).toString().length());
        List<Integer> result = new ArrayList<>();
        while (i <= maxLength) {
            this.computeSequence(result, i, low, high);
            i++;
        }
        return result;
    }

    private void computeSequence(List<Integer> result, int i, int low, int high) {
        char[] nums = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        int l = 0;
        while(l + i <= nums.length) {
            StringBuilder s = new StringBuilder();
            for(int j = l; j < l + i; j++ ) {
                s.append(nums[j]);
            }
            int candidate = Integer.parseInt(s.toString());
            if(candidate >= low && candidate <= high) {
                result.add(candidate);
            }
            l++;
        }
    }

    public static void main(String[] args) {
        System.out.println((new Solution()).sequentialDigits(10, 123847));
    }

}
