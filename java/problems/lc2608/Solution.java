package problems.lc2608;

import java.util.*;

class Solution {

    public int findShortestCycle(int n, int[][] edges) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int i = 0; i < n; i++) {
            adjList.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, bfs(i, adjList, n));
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int bfs(int node, Map<Integer, List<Integer>> adjList, int n) {
        int[] parent = new int[n];
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(node);
        distance[node] = 0;
        int ans = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (Integer child : adjList.get(curr)) {
                if (distance[child] == Integer.MAX_VALUE) {
                    distance[child] = distance[curr] + 1;
                    parent[child] = curr;
                    queue.offer(child);
                } else if (parent[child] != curr && parent[curr] != child) {
                    ans = Math.min(ans, distance[child] + distance[curr] + 1);
                }
            }
        }
        return ans;
    }

    private int dfs(int i, Map<Integer, List<Integer>> adjList, boolean[] visited, int[] distance) {
        visited[i] = true;

        int tempAns = Integer.MAX_VALUE;
        for (Integer child : adjList.get(i)) {
            if (!visited[child]) {
                int res = dfs(child, adjList, visited, distance);
                if (res != -1) {
                    tempAns = Math.min(tempAns, res);
                }
            } else if (distance[child] != -1) {
                tempAns = Math.min(tempAns, distance[i] - distance[child] + 1);
            }
        }
        distance[i] = -1;
        return tempAns == Integer.MAX_VALUE ? -1 : tempAns;
    }
}
