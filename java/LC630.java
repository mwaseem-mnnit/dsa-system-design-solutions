/*
 * https://leetcode.com/problems/course-schedule-iii/
 * */
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {

    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, Comparator.comparingInt(a -> a[1]));
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        int totalTime = 0;
        for (int i = 0; i < courses.length; i++) {
            int[] course = courses[i];
            if(course[0] > course[1]) {
                continue;
            }
            if(course[1] >= totalTime + course[0]) {
                queue.add(course[0]);
                totalTime += course[0];
            } else {
                int top = queue.peek();
                if(top > course[0]) {
                    queue.poll();
                    queue.add(course[0]);
                    totalTime -= top;
                    totalTime += course[0];
                }
            }
        }
        return queue.size();
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.scheduleCourse(new int[][]{{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}}));
    }
}