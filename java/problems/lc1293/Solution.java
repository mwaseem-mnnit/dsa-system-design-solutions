package problems.lc1293;

import java.util.LinkedList;
import java.util.Queue;

/*
* https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/description/
* */
class Solution {
    public int shortestPath(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][][] visited = new boolean[n][m][k + 1];
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int minSteps = Integer.MAX_VALUE;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, k, 0});
        visited[0][0][k] = true;
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0];
            int y = cell[1];
            if (x == n - 1 && y == m - 1) {
                minSteps = Math.min(minSteps, cell[3]);
            }
            int currentSteps = cell[3];
            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    int obstacleRemaining = cell[2] - (grid[nx][ny] == 1 ? 1 : 0);
                    if (
                            obstacleRemaining >= 0 &&
                            !visited[nx][ny][obstacleRemaining]
                    ) {
                        queue.offer(new int[]{nx, ny, obstacleRemaining, currentSteps + 1});
                        visited[nx][ny][obstacleRemaining] = true;
                    }
                }
            }
        }
        return minSteps == Integer.MAX_VALUE ? -1 : minSteps;
    }


    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.shortestPath(new int[][]{{0, 0, 0}, {1, 1, 0}, {0, 0, 0}, {1, 1, 0}}, 1));
        System.out.println(obj.shortestPath(new int[][]{{0, 1, 1, 1}, {0,0,0,1}, {0, 0, 0, 0}, {1, 1, 1, 0}, {1,1,1,0}}, 1));
        System.out.println(obj.shortestPath(new int[][]{{0, 1, 0,0,0,1}, {0,1,0,1,0,1}, {0, 0, 0,1, 0,1}, {1, 1, 1, 1, 0, 1}, {1,1,1,1, 0,0}}, 1));
    }
}