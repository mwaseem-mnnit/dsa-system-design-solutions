
/*
 * https://leetcode.com/problems/parallel-courses-iii/
 * */

package problems.lc2050;

import java.util.*;

class Solution {
    public int minimumTime(int n, int[][] relations, int[] time) {
        Map<Integer, List<Integer>> preRquisite = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, Integer> timeTaken = new HashMap<>();

        /* initialize inDegree*/
        for (int i = 0; i < n; i++) {
            inDegree.putIfAbsent(i+1, 0);
        }

        /* now set inDegree and preRquisite */
        for (int i = 0; i < relations.length; i++) {
            int[] relation = relations[i];
            inDegree.put(relation[1], inDegree.get(relation[1]) + 1);
            preRquisite.putIfAbsent(relation[0], new ArrayList<>());
            preRquisite.get(relation[0]).add(relation[1]);
        }

        int max = Integer.MIN_VALUE;
        Queue<Integer> queue = new LinkedList<>();

        /* add all courses with inDegree 0 to queue */
        inDegree.entrySet().stream().filter(entry -> entry.getValue() == 0).forEach(entry -> queue.add(entry.getKey()));

        /* while queue is not empty, pop a course and update timeTaken */
        while (!queue.isEmpty()) {
            int top = queue.poll();
            int currentTime = timeTaken.getOrDefault(top, 0);
            timeTaken.put(top, currentTime + time[top-1]);
            if(max < currentTime + time[top-1]) {
                max = currentTime + time[top-1];
            }
            preRquisite.getOrDefault(top, new ArrayList<>()).forEach(parentCourse -> {
                inDegree.put(parentCourse, inDegree.get(parentCourse) - 1);
                if(inDegree.get(parentCourse) == 0) {
                    queue.add(parentCourse);
                }
                int previousTime = timeTaken.getOrDefault(parentCourse, 0);
                timeTaken.put(
                        parentCourse,
                        Math.max(previousTime, timeTaken.get(top))
                );
            });
        }
        return max;
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.minimumTime(4, new int[][]{{1,2},{2,3},{3,4}}, new int[]{1,2,4,3}));
    }
}