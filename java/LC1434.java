import java.util.*;
import java.util.stream.Collectors;

class Solution {
    static int maxValue = -1;
    static int maxHat = -1;

    static long countWays(List<Integer>[] hatPersonMapping, long[][] dp, int mask, int hat) {
        if(maxValue == mask) {
            return 1;
        }
        if(maxHat < hat) return 0;
        if(dp[mask][hat] != -1) return dp[mask][hat];
        long answer = countWays(hatPersonMapping, dp, mask, hat+1);
        for (int i = 0; i < hatPersonMapping[hat].size(); i++) {
            if((mask & (1 << hatPersonMapping[hat].get(i))) == 0) {
                answer =  (answer + countWays(hatPersonMapping, dp, (mask | (1 << hatPersonMapping[hat].get(i))), hat+1)) % 1000000007;
            }
        }
        dp[mask][hat] = answer % 1000000007;
        return dp[mask][hat];
    }

    public static int numberWays(List<List<Integer>> hats) {
        List<Integer>[] hatPersonMapping = new List[41];
        for (int i = 0; i < hatPersonMapping.length; i++) {
            hatPersonMapping[i] = new ArrayList<>();
        }
        for (int i = 0; i < hats.size(); i++) {
            for (Integer it : hats.get(i)) {
                hatPersonMapping[it].add(i);
                maxHat = Math.max(maxHat, it);
            }
        }
        int n = hats.size();
        maxValue = (int)Math.pow(2, n)-1;
        long[][] dp = new long[maxValue+1][maxHat+1];
        for (long[] longs : dp) {
            Arrays.fill(longs, -1L);
        }
        return (int) countWays(hatPersonMapping, dp, 0, 1);
    }

    public static void main(String[] args) {
        System.out.println(numberWays(List.of(List.of(3,4), List.of(4,5),List.of(5))));
        HashMap<Integer, String> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        List<Integer> ii = list.stream().map(it -> it*2).collect(Collectors.toList());
        for (Map.Entry<Integer, String> integerStringEntry : map.entrySet()) {

        }
    }
}