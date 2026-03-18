package problems.lc2493;

import java.util.*;

class Solution {

    private boolean isBipartite(
        Map<Integer, List<Integer>> adjList, int node, int[] nodeGroup
    ) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{node, 0});
        nodeGroup[1] = 0;
        while(!queue.isEmpty()) {
            int[] top = queue.poll();
            for (Integer i : adjList.get(top[0])) {
                if(nodeGroup[i] == -1) {
                    queue.add(new int[]{i, (top[1] + 1) % 2});
                    nodeGroup[i] = (top[1] + 1) % 2;
                } else if(nodeGroup[i] == nodeGroup[top[0]]) {
                    return false;
                }
            }
        }
        return true;
    }

    public int magnificentSets(int n, int[][] edges) {
        int[] nodeGroup = new int[n+1];
        Arrays.fill(nodeGroup, -1);
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            adjList.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        for (int i = 1; i <= n; i++) {
            if(nodeGroup[i] == -1) {
                if(!isBipartite(adjList, i, nodeGroup)) {
                    return  -1;
                }
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.magnificentSets(4, new int[][]{{1, 2}, {1, 3}, {2, 4}, {3, 4}, {2, 3}}));
        System.out.println(obj.magnificentSets(4, new int[][]{{1, 2}, {1, 3}, {2, 4}, {3, 4}, {2, 3}}));
    }
}
