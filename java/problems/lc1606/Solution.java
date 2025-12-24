package problems.lc1606;

import java.util.*;

public class Solution {

    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        Map<Integer, Integer> serverVsRequestCount = new HashMap<>();
        int maxRequestHandled = 1;
        Queue<int[]> serverOccupancy = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        TreeSet<Integer> availableServers = new TreeSet<>();
        for (int i = 0; i < arrival.length && i < k; i++) {
            serverVsRequestCount.put(i, 1);
            serverOccupancy.add(new int[]{i, arrival[i] + load[i]});
        }

        int i = k;
        while(i < arrival.length) {
            while(!serverOccupancy.isEmpty() && serverOccupancy.peek()[1] <= arrival[i]) {
                int []top = serverOccupancy.poll();
                availableServers.add(top[0]);
            }

            if(availableServers.isEmpty()) {
                i++;
                continue;
            }

            Integer server = availableServers.ceiling(i % k);
            if(server == null) {
                server = availableServers.first();
            }
            availableServers.remove(server);
            serverVsRequestCount.put(server, serverVsRequestCount.getOrDefault(server, 0) + 1);
            maxRequestHandled = Math.max(maxRequestHandled, serverVsRequestCount.get(server));
            serverOccupancy.add(new int[]{server, arrival[i] + load[i]});
            i++;
        }

        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : serverVsRequestCount.entrySet()) {
            if(entry.getValue() == maxRequestHandled) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.busiestServers(3, new int[]{1,2,3,4}, new int[]{1,3,1,3}));
    }
}
