package problems.mongotest;
//
//import java.util.*;
//import java.util.concurrent.locks.ReentrantLock;
//
//class Node {
//    String value;
//    Node left;
//    Node right;
//}
//
////@Service()
//public class LRUCache {
//
//    // Repository Pattern (Redis, Dynamodb)
//    private final int capacity;
//
//    private final Map<String, Node> cache = new HashMap<>();
//
//    //private final Map<String, problems.mongodb.Node> nodeAddress = new HashMap<>();
//
//
//    private final List<Node> orderedList = new LinkedList<Node>();
//
//    private final Map<String, Long> expiry = new TreeMap<>(); // Sorted Set, or Map, TreeMap
//
//    private final ReentrantLock lock = new ReentrantLock();
//
//    public LRUCache(int capacity) {
//        this.capacity = capacity;
//    }
//
////    @TelemetryProvide()
//    public String get(String key) {
//        if (cache.containsKey(key)) {
//
////            checkExpiryForKey
//            if (expiry.containsKey(key) && expiry.get(key) < System.currentTimeMillis()) {
//                System.out.println("cache expired for " + key);
//
//                // critical section
//                cache.remove(key);
////                order.remove(key); // O(N)
//                expiry.remove(key);
//                throw new Exception("Key not found");
//            }
//
//            // critical section
////            order.remove(key); //O (N)
////            order.add(key);
//
//            System.out.println("cache hit " + key);
//            return cache.get(key);
//        }
//
//        System.out.println("cache miss " + key);
//        return key;//throw new Exception("Cache Miss");
//    }
//
////    @TelemetryProvide()
//    public void put(String key, String value, Long ttlMillis) {
//        if (capacity == 0) {
////            throw CapacityOverflow; // throw exception
//        }
//
//        // T1 V1 and T2 V2
//        // critical section
//        if (cache.containsKey(key)) {
//
//            // T1 Sync
//            cache.put(key, value);
////            order.remove(key); // O(N)
////            order.add(key);
//
//        } else {
//            // T1 and T2
//            if (cache.size() >= capacity) {
////                String oldest = order.get(0);
////                cache.remove(oldest);
////                order.remove(0);
//                // should be removed from expiry
//            }
////            cache.put(key, value);
////            order.add(key);
//
//        }
//        // critical section
//        if (ttlMillis != null && ttlMillis > 0) {
//            expiry.put(key, System.currentTimeMillis() + ttlMillis);
//        }
//    }
//
////    @TelemetryProvide()
//    public void remove(String key) {
//        try {
//            // critical section
//            cache.remove(key);
////            order.remove(key);
//            expiry.remove(key);
//            System.out.println("removed " + key);
//        } catch (Exception e) {
//            // Logg with templates
//        }
//    }
//
//    public int size() {
//        return cache.size();
//    }
//
//    // O(N)
//    public void clearExpired() {
//        // complexity
////        for (Long value : expiry.valueList()) {
////            if(value < now ) {
////                break;
////            }
////        }
//
//        for (String key : cache.keySet()) {
//            if (expiry.containsKey(key) && expiry.get(key) < System.currentTimeMillis()) {
//                // critical section
//                cache.remove(key);
////                order.remove(key);
//                expiry.remove(key);
//            }
//        }
//    }
//}
//
