import java.util.*;

/*
* Problem: https://leetcode.com/problems/course-schedule-ii/description/
* */
class Solution {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            List<Integer> outDegreeList = map.getOrDefault(prerequisite[1], new ArrayList<>());
            outDegreeList.add(prerequisite[0]);
            map.put(prerequisite[1], outDegreeList);
            inDegree.put(prerequisite[0], inDegree.getOrDefault(prerequisite[0], 0) + 1);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (!inDegree.containsKey(i)) {
                queue.add(i);
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int top = queue.poll();
            result.add(top);
            List<Integer> outDegreeList = map.getOrDefault(top, new ArrayList<>());
            for (Integer course : outDegreeList) {
                inDegree.put(course, inDegree.get(course) - 1);
                if(inDegree.get(course) == 0) {
                    queue.add(course);
                }
            }
        }
        return result.size() == numCourses ? result.stream().mapToInt(i -> i).toArray() : new int[0];
    }
}