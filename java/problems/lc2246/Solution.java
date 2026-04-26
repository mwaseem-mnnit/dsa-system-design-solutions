package problems.lc2246;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    int max = 1;
    public int longestPath(int[] parent, String s) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int i = 1; i <parent.length; i++) {
            List<Integer> children = adjList.computeIfAbsent(parent[i], k -> new ArrayList<>());
            children.add(i);
            adjList.putIfAbsent(parent[i], children);
        }
        findPath(0, adjList, s);
        return max;
    }

    private int findPath(int node, Map<Integer, List<Integer>> adjList, String s) {
        List<Integer> children = adjList.get(node);
        if(children == null || children.isEmpty()) {
            return 1;
        }
        char[] chars = new char[children.size()];
        int[] subTreeLength = new int[children.size()];

        for (int i = 0; i < children.size(); i++) {
            int childTreeLength = findPath(children.get(i), adjList, s);
            subTreeLength[i] = childTreeLength;
            chars[i] = s.charAt(children.get(i));
        }

        int tempMax1 = 0, tempMax2 = 0;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] != s.charAt(node)) {
                if(tempMax1 < subTreeLength[i]) {
                    tempMax2 = tempMax1;
                    tempMax1 = subTreeLength[i];
                }
                if(tempMax2 < subTreeLength[i]) {
                    tempMax2 = subTreeLength[i];
                }
            }
        }
        max = Math.max(max, tempMax1 + tempMax2 + 1);
        return 1 + tempMax1;
    }
}
