package problems.lc1665;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {

    public int minimumEffort(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> ( (b[1] - b[0]) - (a[1] - a[0])) );
        int[] maxPair = Arrays.stream(tasks).max(Comparator.comparingInt(a -> a[1])).orElse(tasks[0]);
        int[] minPair = Arrays.stream(tasks).min(Comparator.comparingInt(a -> a[1])).orElse(tasks[0]);
        int sum = Arrays.stream(tasks).mapToInt(a -> a[0]).sum();
        int r = sum + maxPair[1];
        int l = minPair[1];
        int min = Integer.MAX_VALUE;
        while (l < r) {
            int mid = (l+r)/2;
            boolean early = false;
            for (int[] task : tasks) {
                if(mid < task[1]) {
                    l = (l+r)/2 + 1;
                    early = true;
                    break;
                }
                mid = mid - task[0];
            }
            if(!early) {
                min = Math.min(min, (l+r)/2);
                r = (l+r)/2;
            }
        }
        return min;
    }

    public static void main(String[] args) {
//        System.out.println((new Solution()).minimumEffort(new int[][]{{1, 2}, {2, 4}, {4, 8}}));
        System.out.println((new Solution()).minimumEffort(new int[][]{{1, 3}, {2, 4}, {10, 11}, {10, 12}, {8,9}}));
//        System.out.println((new Solution()).minimumEffort(new int[][]{{1, 7}, {2, 8}, {3, 9}, {4, 10}, {5, 11}, {6, 12}}));
    }
}
