

import java.io.*;
import java.util.*;

class Solution {
    public static int maxSubarray(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0, max=0;
        for(int i=0; i<arr.length; i++) {
            sum += arr[i];
            if(map.containsKey(sum - k)) {
                max = Math.max(max, i - map.get(sum - k));
            }
            if(!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return max;
    }

    public static void main(String args[]) {
        System.out.println(maxSubarray(new int[]{10, 5, 2, 7, 1, 9} , 15));
        System.out.println(maxSubarray(new int[]{-5, 8, -14, 2, 4, 12} , -5));
    }
}