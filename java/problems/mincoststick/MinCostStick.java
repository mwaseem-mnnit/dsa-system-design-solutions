package problems.mincoststick;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {

    Map<String, Integer> map;
    int computeCost(int n, int [] cuts, int l, int r) {
        if(l>=r) {
            return 0;
        }
        if(map.containsKey("" + n + ":" + l + ":" + r)) {
            return map.get("" + n + ":" + l + ":" + r);
        }
        int min = Integer.MAX_VALUE;
        for(int i=l; i<r; i++) {
            int at = cuts[i];
            int left = computeCost(at, cuts, l, i);
            for(int j=i+1; j<r; j++) {
                cuts[j] = cuts[j]-at;
            }
            int right = computeCost(n-at, cuts, i+1, r);
            for(int j=i+1; j<r; j++) {
                cuts[j] = cuts[j]+at;
            }
            min = Math.min(min, left + right + n);
        }
        map.put("" + n + ":" + l + ":" + r, min);
        return min;
    }

    public int minCost(int n, int[] cuts) {
        Arrays.sort(cuts);
        map = new HashMap<>();
        return computeCost(n, cuts, 0, cuts.length);
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.minCost(7, new int[]{1,3,4,5}));
        System.out.println(obj.minCost(9, new int[]{5,6,1,4,2}));
    }
}