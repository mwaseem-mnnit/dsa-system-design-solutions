import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {


    // Complete the minimumPouringWaterPenalty function below.
    static int min = -1;

    static int minimumPouringWaterPenalty(List<Integer> parent, List<Integer> waterLevel, int overhydratedPenalty, int underhydratedPenalty) {
        Map<Integer, Node> tree = new HashMap<Integer, Node>();
        for (int i = 0; i < parent.size(); i++) {
            tree.put(i, new Node(waterLevel.get(i)));
        }
        for (int i = 1; i < parent.size(); i++) {
            Node node = tree.get(parent.get(i));
            if (node.ch == null) {
                node.ch = new ArrayList<>();
            }
            node.ch.add(tree.get(i));
        }
        int ret = dfs(tree.get(0), overhydratedPenalty, underhydratedPenalty);
        if (min == -1) min = Math.min(ret[0], ret[1]);
        else min = Math.min(min, Math.min(ret[0], ret[1]));
        return min;
    }

    static int[] dfs(Node node, int p, int n) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int psum = 0;
        int nsum = 0;
        if (node.water > 0) psum = p;
        if (node.water < 0) nsum = n;
        if (node.ch != null) {
            for (int i = 0; i < node.ch.size(); i++) {
                int[] ret = dfs(node.ch.get(i), p, n);
                psum += ret[0];
                nsum += ret[1];
            }
        }
        if (min == -1) min = Math.min(psum, nsum);
        else min = Math.min(min, Math.min(psum, nsum));
        return new int[]{psum, nsum};
    }

    class Node {
        List<Node> ch;
        int water;

        Node(int water) {
            this.water = water;
        }
    }
}