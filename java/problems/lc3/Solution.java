package problems.lc3;

import java.util.Arrays;

public class Solution {

    public int lengthOfLongestSubstring(String s) {
        int l = 0, r = 0, max = 0;
        int[] map = new int[256];
        Arrays.fill(map, -1);
        while (r < s.length()) {
            char ch = s.charAt(r);
            if (map[ch] > -1) {
                max = Math.max(max, r - l);
                for (; l < map[ch] + 1; l++) {
                    map[s.charAt(l)] = -1;
                }
            }
            map[ch] = r;
            r++;
        }
        return Math.max(max, r - l);
    }

    public static void main(String[] args) {
        System.out.println((new Solution()).lengthOfLongestSubstring("ababb"));
        System.out.println((new Solution()).lengthOfLongestSubstring("a"));
        System.out.println((new Solution()).lengthOfLongestSubstring("aa"));
        System.out.println((new Solution()).lengthOfLongestSubstring("aa0)-*&^1sdwe"));
        System.out.println((new Solution()).lengthOfLongestSubstring("ccbbcc"));
    }
}
