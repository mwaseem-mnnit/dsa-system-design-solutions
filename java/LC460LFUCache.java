import java.util.*;


class LFUCache {

    private int capacity;
    private Map<Integer, Node> cache;
    private Map<Integer, LinkedHashSet<Integer>> frequencyVsKeys;
    private int minFrequency;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.frequencyVsKeys = new HashMap<>();
        this.minFrequency = 1;
    }

    public int get(int key) {
        Node node = this.cache.get(key);
        if(node == null) {
            return -1;
        }
        this.rebalance(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = this.cache.get(key);
        if(node == null) {
            if(this.cache.size() >= this.capacity) {
                this.invalidateCache();
            }
            node = new Node(key, value, 0, 0);
            this.minFrequency = 0;
        }
        node.value = value;
        this.cache.put(key, node);
        this.rebalance(node);
    }

    public void invalidateCache() {
        LinkedHashSet<Integer> keySet = this.frequencyVsKeys.get(this.minFrequency);
        Integer removeKey = keySet.iterator().next();
        keySet.remove(removeKey);
        this.cache.remove(removeKey);
        if(keySet.isEmpty()) {
            this.frequencyVsKeys.remove(this.minFrequency);
            this.minFrequency = 1;
        }
    }

    public void rebalance(Node node) {
        LinkedHashSet<Integer> keySet = this.frequencyVsKeys.computeIfAbsent(node.counter, k -> new LinkedHashSet<>());
        keySet.remove(node.key);
        if(keySet.isEmpty()) {
            this.frequencyVsKeys.remove(node.counter);
            if(this.minFrequency == node.counter) {
                this.minFrequency += 1;
            }
        }
        node.counter += 1;
        LinkedHashSet<Integer> nextKeySet = this.frequencyVsKeys.computeIfAbsent(node.counter, k -> new LinkedHashSet<>());
        nextKeySet.add(node.key);
        this.frequencyVsKeys.put(node.counter, nextKeySet);
    }

    public static void main(String[] args) {
        LFUCache obj = new LFUCache(2);
        obj.put(1, 1);
        obj.put(2, 2);
        System.out.println(obj.get(1));
        System.out.println(obj.get(2));
        obj.put(3, 3);
        System.out.println(obj.get(2));
        System.out.println(obj.get(3));
        obj.put(2, 22);
        obj.put(4, 4);
        System.out.println(obj.get(3));
        System.out.println(obj.get(2));
        System.out.println(obj.get(4));
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

class LFUCache_1 {

    private int capacity;
    private int timestamp;
    private Map<Integer, Node> cache;
    private PriorityQueue<Node> minHeap;

    public LFUCache_1(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.minHeap = new PriorityQueue<>((o1, o2) -> (o1.counter == o2.counter ? (o1.timestamp - o2.timestamp) : o1.counter - o2.counter));
        this.timestamp = 0;
    }

    public int get(int key) {
        Node node = this.cache.get(key);
        if (node == null) {
            return -1;
        }
        this.timestamp += 1;
        node.counter += 1;
        node.timestamp = this.timestamp;
        return node.value;
    }

    public void put(int key, int value) {
        boolean isPresent = true;
        this.timestamp += 1;
        Node node = this.cache.get(key);
        if(node == null) {
            node = new Node();
            isPresent = false;
        }
        node.key = key;
        node.value = value;
        node.counter += 1;
        node.timestamp = this.timestamp;

        if(!isPresent && this.cache.size() >= this.capacity) {
            this.invalidateCache();
        }
        this.cache.put(key, node);
        if(!isPresent) {
            this.minHeap.add(new Node(node.key, node.value, node.counter, node.timestamp));
        }
    }

    private void invalidateCache() {
        while (this.cache.size() >= this.capacity) {
            Node heapNode = this.minHeap.poll();
            Node cacheNode = this.cache.get(heapNode.key);
            if(
                    heapNode.counter == cacheNode.counter &&
                    heapNode.timestamp == cacheNode.timestamp
            ) {
                this.cache.remove(heapNode.key);
                return;
            }
            heapNode.counter = cacheNode.counter;
            heapNode.timestamp = cacheNode.timestamp;
            this.minHeap.add(heapNode);
        }
    }

    public static void main(String[] args) {
        LFUCache_1 obj = new LFUCache_1(2);
        obj.put(1, 1);
        obj.put(2, 2);
        System.out.println(obj.get(1));
        obj.put(3, 3);
        System.out.println(obj.get(2));
        System.out.println(obj.get(3));
        obj.put(4, 4);
        System.out.println(obj.get(1));
        System.out.println(obj.get(3));
        System.out.println(obj.get(4));
    }
}

class Node {
    int key;
    int value;
    int counter;
    int timestamp;

    Node() {
        this.counter = 0;
    }

    public Node(int key, int value, int counter, int timestamp) {
        this.key = key;
        this.value = value;
        this.counter = counter;
        this.timestamp = timestamp;
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */