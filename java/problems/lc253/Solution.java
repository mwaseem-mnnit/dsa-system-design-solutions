package problems.lc253;

import java.util.Arrays;

public class Solution {

    public int minMeetingRooms(int[][] intervals) {
        int[] meetingStartTime = new int[intervals.length];
        int[] meetingEndTime = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            meetingStartTime[i] = intervals[i][0];
            meetingEndTime[i] = intervals[i][1];
        }
        Arrays.sort(meetingStartTime);
        Arrays.sort(meetingEndTime);
        int l = 0, r = 0;
        int count = 0, roomRequired = 0;
        while(l < meetingStartTime.length) {
            while ( l < meetingStartTime.length && meetingStartTime[l] < meetingEndTime[r]) {
                count++;
                roomRequired = Math.max(roomRequired, count);
                l++;
            }

            while(r < meetingEndTime.length && l < meetingStartTime.length && meetingEndTime[r] <= meetingStartTime[l]) {
                count--;
                r++;
            }
        }
        return roomRequired;
    }
}
