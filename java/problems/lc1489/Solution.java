package problems.lc1489;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution {

    public static void main(String[] args) {
        Solution o=new Solution();
        System.out.println(o.findCriticalAndPseudoCriticalEdges(5, new int[][]{{0, 1, 1}, {1, 2, 1}, {2, 3, 2}, {0, 3, 2}, {0, 4, 3}, {4, 4, 3}, {1, 4, 6}}));
    }

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        Arrays.sort(edges, Comparator.comparingInt(a -> a[2]));

        List<Integer> mstPath = this.findMST(n, edges, -1);
        int mstCost = mstPath.getLast();

        List<Integer> criticalEdge = new ArrayList<>();
        List<Integer> pCriticalEdge = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            List<Integer> newPath = this.findMST(n, edges, i);
            if(newPath.getLast() > mstCost) {
                criticalEdge.add(i);
            } else {
                pCriticalEdge.add(i);
            }
        }
        return List.of(criticalEdge, pCriticalEdge);
    }

    public List<Integer> findMST(int n, int[][] edges, int excludeIdx) {
        int[] parent = new int[n];
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
        List<Integer> mstPath = new ArrayList<>();
        int mstCost = 0, edgeCount = 0;
        for (int i = 0; i < edges.length; i++) {
            if(i == excludeIdx) {
                continue;
            }
            int[] edge = edges[i];
            boolean addCost = this.union(edge[0], edge[1], parent, rank);
            if(!addCost) {
                continue;
            }
            mstCost += edge[2];
            edgeCount += 1;
            mstPath.add(i);
            if(edgeCount == n) {
                break;
            }
        }
        mstPath.add(mstCost);
        return mstPath;
    }

    private int findParent(int i, int[] parent) {
        if(parent[i] != i) {
            parent[i] = findParent(parent[i], parent);
        }
        return parent[i];
    }

    private boolean union(int x, int y, int[] parent, int[] rank) {
        int px = findParent(x, parent);
        int py = findParent(y, parent);
        if(px == py) {
            return false;
        }
        if(rank[px] > rank[py]) {
            parent[py] = px;
        } else {
            parent[px] = py;
        }
        if(rank[px] == rank[py]) {
            rank[py] += 1;
        }
        return true;
    }
}
