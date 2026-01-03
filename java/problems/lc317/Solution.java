package problems.lc317;

import java.util.LinkedList;
import java.util.Queue;

class Solution {

    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int result = -1;

    public int shortestDistance(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 0) {
                    bfs(new int[]{i, j, 0}, grid);
                }
            }
        }
        return result;
    }

    public void bfs(int[] src, int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        int[][] distance = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                distance[i][j] = -1;
            }
        }

        queue.offer(src);
        while (!queue.isEmpty()) {
            int[] top = queue.poll();
            int i = top[0];
            int j = top[1];
            int dist = top[2];
            for (int[] dir : dirs) {
                int nx = i + dir[0];
                int ny = j + dir[1];
                if(nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if(grid[nx][ny] == 1) {
                        distance[nx][ny] = distance[nx][ny] == -1 ? dist + 1 : Math.min(distance[nx][ny], dist + 1);
                    }
                    if(!visited[nx][ny] && grid[nx][ny] == 0) {
                        queue.offer(new int[]{nx, ny, dist + 1});
                        visited[nx][ny] = true;
                    }
                }
            }
        }

        int temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(grid[i][j] == 1) {
                    if(distance[i][j] == -1) {
                        return;
                    } else {
                        temp += distance[i][j];
                    }
                }
            }
        }
        result = result == -1 ? temp : Math.min(result, temp);
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.shortestDistance(new int[][]{{1,1, 2,0,1},{0,0,0,0, 0},{0,0,1,0,0}}));
    }
}