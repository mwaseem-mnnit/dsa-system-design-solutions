import java.util.PriorityQueue;

class Solution {
    public String reorganizeString(String s) {
        PriorityQueue<Node> queue = getNodes(s);

        StringBuilder sb = new StringBuilder();
        int timestamp = 1;
        while(!queue.isEmpty()) {
            timestamp += 1;
            Node node = queue.poll();
            if(
                    sb.length() > 0 &&
                    node.ch == sb.charAt(sb.length() - 1)
            ) {
                return "";
            }
            sb.append(node.ch);
            node.count -=1;
            node.lastUsed = timestamp;
            Node nextNode = queue.peek();

            if(nextNode != null && nextNode.count < node.count) {
                timestamp += 1;
                nextNode = queue.poll();
                sb.append(nextNode.ch);
                nextNode.count -= 1;
                nextNode.lastUsed = timestamp;
                if(nextNode.count > 0) {
                    queue.add(nextNode);
                }
            }

            if(node.count > 0) {
                queue.add(node);
            }
        }
        return sb.toString();
    }

    private PriorityQueue<Node> getNodes(String s) {
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> (o2.count == o1.count) ? o1.lastUsed - o2.lastUsed : o2.count - o1.count);
        Node[] nodeList = new Node[26];
        for(Character ch: s.toCharArray()) {
            int ascii = ch - 'a';
            Node node = nodeList[ascii];
            if(node == null) {
                node = new Node(ch, 0, 1);
            }
            node.count += 1;
            nodeList[ascii] = node;
        }
        for (Node node : nodeList) {
            if(node != null) {
                queue.add(node);
            }
        }
        return queue;
    }


    public static void main(String[] args) {
        System.out.println(new Solution().reorganizeString("aab"));
        System.out.println(new Solution().reorganizeString("abaabcbcdef"));
        System.out.println(new Solution().reorganizeString("abaabacabacadaeaf"));
        System.out.println(new Solution().reorganizeString("abcd"));
        System.out.println(new Solution().reorganizeString("aabbcc"));
        System.out.println(new Solution().reorganizeString("aaaa"));
        System.out.println(new Solution().reorganizeString("vvvlo"));

    }
}

class Node {
    Character ch;
    int count;
    int lastUsed;

    Node(Character ch, int count, int lastUsed) {
        this.ch = ch;
        this.count = count;
        this.lastUsed = lastUsed;
    }
}