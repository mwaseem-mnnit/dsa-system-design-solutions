package problems.lc981;
import java.util.*;

class TimeMap {
    private Map<String, TreeMap<Integer, String>> timeMap;
    public TimeMap() {
        this.timeMap = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        TreeMap<Integer, String> timestampVsValue = this.timeMap.computeIfAbsent(key, k -> new TreeMap<>());
        timestampVsValue.put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        TreeMap<Integer, String> timestampVsValue = this.timeMap.getOrDefault(key, null);
        if(timestampVsValue == null) {
            return "";
        }
        Map.Entry<Integer, String> entry = timestampVsValue.floorEntry(timestamp);
        if(entry == null) {
            return "";
        }
        return entry.getValue();
    }

    public static void main(String[] args) {
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{1, 2});
        Collections.sort(list, Comparator.comparingInt(a -> a[0]));
        TimeMap timeMap = new TimeMap();
        timeMap.set("foo", "bar", 2);
        System.out.println(timeMap.get("foo", 1));
        System.out.println(timeMap.get("foo", 3));
        timeMap.set("foo", "bar2", 4);
        timeMap.set("foo", "bar3", 5);
        timeMap.set("foo", "bar2", 8);
        System.out.println(timeMap.get("foo", 4));
        System.out.println(timeMap.get("foo", 5));
        System.out.println(timeMap.get("foo", 9));
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */