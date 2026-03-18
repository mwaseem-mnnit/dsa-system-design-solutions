package problems.lc2360;

import java.util.Arrays;

class Solution {

    private int dfs(int node, int[] edges, boolean[] visited, int[] distance) {
        visited[node] = true;
        int child = edges[node];
        if(child == -1) {
            distance[node] = -1;
            return -1;
        }
        int ans = -1;
        if(!visited[child]) {
            distance[child] = distance[node] + 1;
            ans = dfs(child, edges, visited, distance);
        } else if(distance[child] != -1) {
            ans = distance[node] - distance[child] + 1;
        }
        distance[node] = -1;
        return ans;
    }

    public int longestCycle(int[] edges) {

        boolean[] visited = new boolean[edges.length];
        int[] distance = new int[edges.length];
        Arrays.fill(distance, -1);
        int ans = -1;
        for (int i = 0; i < edges.length; i++) {
            if(!visited[i]) {
                distance[i] = 0;
                ans = Math.max(ans, dfs(i, edges, visited, distance));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().longestCycle(new int[]{3,3,4,2,3}));
        System.out.println(new Solution().longestCycle(new int[]{2,-1,3,1}));
        System.out.println(new Solution().longestCycle(new int[]{5,0,4,1,6,6,3}));
        System.out.println(new Solution().longestCycle(new int[]{1,2,0,4,5,6,3,5,4}));
        System.out.println(new Solution().longestCycle(new int[]{-1,4,-1,2,0,4}));
    }
}
