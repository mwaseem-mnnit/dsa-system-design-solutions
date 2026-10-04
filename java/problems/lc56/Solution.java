package problems.lc56;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class Solution {

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        Stack<int[]> stack = new Stack<>();
        stack.push(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] top = stack.peek();
            int[] cur = intervals[i];
            if(top[1] < cur[0]) {
                stack.push(cur);
            } else {
                stack.pop();
                stack.push(new int[]{top[0], Math.max(top[1], cur[1])});
            }
        }
        int[][] result = new int[stack.size()][];
        int i = 0;
        while (!stack.isEmpty()) {
            result[i] = stack.pop();
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString((new Solution()).merge(new int[][]{{1, 2}, {3, 4}})));
    }
}