package problems.lc2509;

import java.util.HashMap;
import java.util.Map;

class Solution {

    public int[] cycleLengthQueries(int n, int[][] queries) {
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            result[i] = returnPath(queries[i][0], queries[i][1]);
        }
        return result;
    }

    int returnPath(int x, int y) {
        Map<Integer, Integer> visited = new HashMap<>();
        visited.put(x, 0);
        while(x > 1) {
            int dist = visited.get(x);
            x = x / 2;
            visited.put(x, dist + 1);
        }

        int dist = 0;
        while(!visited.containsKey(y) && y > 1) {
            dist++;
            y = y / 2;
        }

        return dist + visited.get(y) + 1;
    }
}
