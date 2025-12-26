package problems.lc2402;

import java.util.*;

public class Solution {
    public int mostBooked(int n, int[][] meetings) {

        Queue<int[]> occupiedQueue = new PriorityQueue<>((a, b) -> a[1] ==  b[1] ? a[0] - b[0] : a[1] - b[1]);
        Queue<int[]> freeQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        Map<Integer, Integer> serverVsMeetingCount = new HashMap<>();

        /** Sort meetings by start time **/
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));

        /** add all servers to free queue and initialize meeting count **/
        int maxMeetings = 0;
        for (int i = 0; i < n; i++) {
            freeQueue.add(new int[]{i, 0});
            serverVsMeetingCount.put(i, 0);
        }

        int i = 0;
        /** iterate over remaining meetings **/
        while( i < meetings.length) {

            /** find servers which are free at the start of ith meeting and add it to free queue **/
            while(!occupiedQueue.isEmpty() && occupiedQueue.peek()[1] <= meetings[i][0]) {
                int[] freeServer = occupiedQueue.poll();
                freeQueue.add(new int[]{freeServer[0], freeServer[1]});
            }

            /** if no server found, wait till the first server becomes available **/
            if(freeQueue.isEmpty() && !occupiedQueue.isEmpty()) {
                int[] nextFreeServer = occupiedQueue.poll();
                freeQueue.add(new int[]{nextFreeServer[0], nextFreeServer[1]});
            }

            /**  assign the ith meeting to the server and update meeting count **/
            int[] assignedServer = freeQueue.poll();
            serverVsMeetingCount.put(assignedServer[0], serverVsMeetingCount.get(assignedServer[0]) + 1);

            /** keep track of max meeting count, minor optimisation to find the resul quickly **/
            maxMeetings = Math.max(maxMeetings, serverVsMeetingCount.get(assignedServer[0]));

            long start = meetings[i][0];
            long end = meetings[i][1];
            long duration = end - start;

            long actualStart = Math.max(start, assignedServer[1]);
            long actualEnd = actualStart + duration;

            occupiedQueue.add(new int[]{assignedServer[0], (int) actualEnd});
            i++;
        }

        /** find the lowest number server which has max meetings **/
        int resultServer = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : serverVsMeetingCount.entrySet()) {
            if(entry.getValue() == maxMeetings) {
                resultServer = Math.min(resultServer, entry.getKey());
            }
        }
        return resultServer;
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.mostBooked(4, new int[][]{{19, 20}, {14, 15}, {13, 14}, {11, 20}}));
    }
}