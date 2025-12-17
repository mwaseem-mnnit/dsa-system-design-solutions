package problems.lc76;
import java.util.HashMap;
import java.util.Map;

class Solution {

    public String minWindow(String s, String t) {
        Map<Character, Integer> tMap = new HashMap<>();
        Map<Character, Integer> sMap = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
        }

        int l = 0, r = 0, minLen = Integer.MAX_VALUE, start = -1, end = -1;
        while(r < s.length()) {
            Character ch = s.charAt(r);
            sMap.put(ch, sMap.getOrDefault(ch, 0) + 1);
            boolean isComplete = this.compareMap(tMap, sMap);
            if(isComplete) {
                while(l < r && sMap.get(s.charAt(l)) > tMap.getOrDefault(s.charAt(l), 0)) {
                    sMap.put(s.charAt(l), sMap.get(s.charAt(l)) - 1);
                    l++;
                }
                if(minLen > r - l + 1) {
                    start = l; end = r; minLen = r - l + 1;
                }
            }
            r++;
        }
        if(minLen == Integer.MAX_VALUE) {
            return "";
        }
        return s.substring(start, end + 1);
    }

    private boolean compareMap(Map<Character, Integer> tMap, Map<Character, Integer> sMap) {
        for (Map.Entry<Character, Integer> entry : tMap.entrySet()) {
            if(sMap.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(obj.minWindow("aaaaa", "a"));
        System.out.println(obj.minWindow("aaaaa", "aaaaaaa"));
        System.out.println(obj.minWindow("aaaaa", "aaaaa"));
        System.out.println(obj.minWindow("baab", "bab"));
    }
}