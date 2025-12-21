package problems.lc778;

import java.util.LinkedList;
import java.util.Queue;

/*
* https://leetcode.com/problems/swim-in-rising-water/
* */
class Solution {

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int[][] maxValueInPath = new int[n][n];
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maxValueInPath[i][j] = Integer.MAX_VALUE;
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        maxValueInPath[0][0] = grid[0][0];
        while(!queue.isEmpty()) {
            int[] top = queue.poll();
            int x = top[0];
            int y = top[1];
            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if(
                    nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length
                ) {
                    int currentMax = Math.max(grid[nx][ny], maxValueInPath[x][y]);
                    if(maxValueInPath[nx][ny] > currentMax) {
                        maxValueInPath[nx][ny] = currentMax;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
        }
        return maxValueInPath[n-1][n-1];
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.swimInWater(new int[][]{{0,2},{1,3}}));
        System.out.println(obj.swimInWater(new int[][]{{0,1,2,3,4},{24,23,22,21,5},{12,13,14,15,16},{11,17,18,19,20},{10,9,8,7,6}}));
    }
}