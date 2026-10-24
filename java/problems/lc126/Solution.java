package problems.lc126;

import java.util.*;

class Node {
    String val;
    List<Node> connectedList;

    Node(String val) {
        this.val = val;
        this.connectedList = new ArrayList<>();
    }
}

public class Solution {
    Map<String, Node> nodeMap;
    Set<Node> visited;
    Map<Node, List<Node>> parentMap;
    Map<Node, Integer> minLevel;

    public static void main(String[] args) {
        System.out.println((new Solution()).findLadders(
                "hit",
                "cog",
                List.of("hot","dot","dog","lot","log","cog")
        ));

        System.out.println((new Solution()).findLadders(
                "hit",
                "cog",
                List.of("hil","dot","dog","lot","log","cog")
        ));

        System.out.println((new Solution()).findLadders(
                "a",
                "c",
                List.of("a", "b", "c")
        ));

        System.out.println((new Solution()).findLadders(
                "leet",
                "code",
                List.of("lest","leet","lose","code","lode","robe","lost")
        ));
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        if(!this.findWord(endWord, wordList)) {
            return new ArrayList<>();
        }

        this.nodeMap = new HashMap<>();
        this.visited = new HashSet<>();
        this.parentMap = new HashMap<>();
        this.minLevel = new HashMap<>();

        boolean isBegin = false;
        for (String word : wordList) {
            if(word.equals(beginWord)) {
                isBegin = true;
            }
            this.connectWithKey(word, this.findConnected(word, wordList));
        }
        if(!isBegin) {
            this.connectWithKey(beginWord, this.findConnected(beginWord, wordList));
        }
        Node root = this.nodeMap.get(beginWord);
        Node endWordNode = this.runBFS(root, endWord);

        List<List<String>> result = new ArrayList<>();
        this.callRecursion(endWordNode, new ArrayList<>(), result);
        return result;
    }

    private void callRecursion(Node node, List<String> pathList, List<List<String>> result) {
        if(node == null) {
            return;
        }
        pathList.add(node.val);
        List<Node> parentList = parentMap.get(node);
        if(parentList.isEmpty()) {
            List<String> candidate = new ArrayList<>(pathList);
            Collections.reverse(candidate);
            result.add(candidate);
        } else {
            for (Node parentNode : parentList) {
                this.callRecursion(parentNode, pathList, result);
            }
        }
        pathList.removeLast();
    }

    Node runBFS(Node root, String endWord) {
        Node endWordNode = null;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        minLevel.put(root, 1);
        visited.add(root);
        parentMap.put(root, new ArrayList<>());

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if(node.val.equals(endWord)) {
                endWordNode = node;
            }

            int parentLevel = minLevel.get(node);

            for (Node childNode : node.connectedList) {

                int currLevel = this.minLevel.getOrDefault(childNode, Integer.MAX_VALUE);
                if(currLevel > parentLevel + 1) {
                    this.minLevel.put(childNode, parentLevel + 1);
                    this.parentMap.put(childNode, new ArrayList<>(List.of(node)));
                } else if(currLevel ==  parentLevel + 1) {
                    parentMap.get(childNode).add(node);
                }

                if(!visited.contains(childNode)) {
                    visited.add(childNode);
                    queue.add(childNode);
                }
            }
        }
        return endWordNode;
    }

    List<String> findConnected(String anchor, List<String> wordList) {
        List<String> result = new ArrayList<>();
        for (String word : wordList) {
            int diff = this.getDiff(anchor, word);
            if(diff == 1) {
                result.add(word);
            }
        }
        return result;
    }

    void connectWithKey(String anchor, List<String> connectedList) {
        Node node = this.nodeMap.getOrDefault(anchor, new Node(anchor));
        this.nodeMap.put(anchor, node);
        for (String word : connectedList) {
            Node connectedNode = this.nodeMap.getOrDefault(word, new Node(word));
            node.connectedList.add(connectedNode);
            this.nodeMap.put(word, connectedNode);
        }
    }

    int getDiff(String word1, String word2) {
        int diff = 0;
        for (int i = 0; i < word1.length(); i++) {
            if(word1.charAt(i) != word2.charAt(i)) {
                diff += 1;
            }
        }
        return diff;
    }

    boolean findWord(String word, List<String> wordList) {
        return wordList.contains(word);
    }
}