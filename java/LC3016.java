import java.lang.reflect.Array;
import java.util.*;

class Solution {
    public int minimumPushes(String word) {
        Integer[] arr = new Integer[26];
        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = 0;
        }

        for(int i=0;i<word.length();i++) {
            arr[word.charAt(i)-'a']++;
        }
        Arrays.sort(arr, Collections.reverseOrder());

        int result = 0, multiplier = 0;
        for (int i = 0; i < arr.length; i++) {
            if(i % 8 == 0 ) {
                multiplier++;
            }
            result += arr[i] * multiplier;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.minimumPushes("aaaaaaaaaabbbbbbbbbccccccccdddddddeeeeeefffffgggghhhllzz"));
        System.out.println(obj.minimumPushes("aabbccddeeffgghhiiiiii"));

    }
}