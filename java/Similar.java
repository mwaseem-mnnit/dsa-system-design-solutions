import java.util.HashMap;
import java.util.Map;

public class Similar {

    public static long computeFactorial(int n) {
        long val = 1;
        for (int i = 1; i <= n; i++) {
            val = (val * i);
        }
        return val;
    }
    public static long findFactorial(Map<Integer, Integer> map, int size) {
        long val = computeFactorial(size);
        for (Map.Entry<Integer, Integer> integerIntegerEntry : map.entrySet()) {
            int divide = integerIntegerEntry.getValue();
            if (divide == 0) continue;
            long divideFact = computeFactorial(divide);
            val = val / divideFact;
        }
        return val;
    }

    public static long doFindFactorial(Map<Integer, Integer> map, int size) {
        Integer zeroes = map.get(0);
        if (zeroes == null) {
            return findFactorial(map, size);
        }
        long value = 0;
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            if (e.getKey() == 0) continue;
            int count = e.getValue();
            int key = e.getKey();
            map.put(key, count - 1);
            value += findFactorial(map, size - 1);
            map.put(key, count);
        }
        return value;
    }

    public static Map<Integer, Integer> createMap(String number) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < number.toCharArray().length; i++) {
            int n = number.charAt(i) - '0';
            if (!map.containsKey(n)) {
                map.put(n, 1);
            } else {
                map.put(n, map.get(n) + 1);
            }
        }
        return map;
    }

    public static long findSimilar(String a, String b) {
        Map<Integer, Integer> aMap = createMap(a);
        Map<Integer, Integer> bMap = createMap(b);

        if (a.startsWith("0") || b.startsWith("0")) {
            if (b.startsWith("0")) {
                int count = bMap.get(0);
                if (count == 1) {
                    bMap.remove(0);
                } else {
                    bMap.put(0, bMap.get(0) - 1);
                }
                b = b.substring(1);
            }
            return doFindFactorial(bMap, b.length());
        }
        boolean isMatched = true;
        for (Map.Entry<Integer, Integer> entry : aMap.entrySet()) {
            Integer bValue = bMap.get(entry.getKey());
            if (bValue == null || !bValue.equals(entry.getValue())) {
                isMatched = false;
                break;
            }
        }
        return isMatched ? doFindFactorial(aMap, a.length()) : doFindFactorial(bMap, b.length());
    }

    public static void main(String[] args) {
        System.out.println(findSimilar("7343101008", "7303008114"));
    }
}