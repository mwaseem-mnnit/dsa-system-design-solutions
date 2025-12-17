import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class Test {
    static void test() {
        try {
            int i, sum;
            sum = 10;
            for (i = -1; i < 3; i++) {
                sum = sum / i;
            }
            System.out.println(sum);
        } catch (ArithmeticException ex) {
            System.out.println("0");
        }
    }

    public static int getMinMoves(List<Integer> plates) {
        int n = plates.size();
        int min = 1000000001, max = 0;
        int l = -1, r = -1;
        for (int i = 0; i < plates.size(); i++) {
            if (min > plates.get(i)) {
                min = plates.get(i);
                l = i;
            }
            if (max < plates.get(i)) {
                max = plates.get(i);
                r = i;
            }
        }
        if (l < r) {
            return (l) + (n - 1 - r);
        }
        return (l) + (n - 1 - r) - 1;
    }

    private long optimalScoresOfSubArrays(int[] arr) {
        int n = arr.length;
        Stack<Integer> stack = new Stack();
        long ans = 0;
        long[] sum = new long[n + 1], sum1 = new long[n + 1];//prefix sum and suffix sum
        long[] prefix = new long[n + 1], suffix = new long[n + 1];//prefix sum of prefixsum and suffix sum of suffixsum
        int mod = 1_000_000_007;
        for (int i = 0; i < n; i++) {
            sum[i + 1] = (sum[i] + arr[i]) % mod;
            prefix[i + 1] = (prefix[i] + sum[i + 1]) % mod;
        }
        for (int i = n - 1; i >= 0; i--) {
            sum1[i] = (sum1[i + 1] + arr[i]) % mod;
            suffix[i] = (suffix[i + 1] + sum1[i]) % mod;
        }

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int cur = stack.pop();
                int prev = stack.isEmpty() ? -1 : stack.peek();
                int next = i;
                //the commented lines shows how the left sum and right sum are calculated
                //Maybe a little hard to understand but the idea is similar
                //long lsum = suffix[prev+1]-suffix[cur]-sum1[cur]*(cur-prev-1)%mod;
                //long rsum = prefix[next]-prefix[cur+1]-sum[cur+1]*(next-cur-1)%mod;
                //below I takes modulo everytime there is multiplication and addition to avoid overflow in java
                long lsum = (mod + suffix[prev + 1] - (suffix[cur] + sum1[cur] * (cur - prev - 1) % mod) % mod) % mod;
                long rsum = (mod + prefix[next] - (prefix[cur + 1] + sum[cur + 1] * (next - cur - 1) % mod) % mod) % mod;
                long self = ((long) arr[cur] * (next - cur) % mod) * (cur - prev) % mod;
                long curres = (long) arr[cur] * ((rsum * (cur - prev) % mod + lsum * (next - cur) % mod + self) % mod) % mod;
                ans = (ans + curres) % mod;
            }
            stack.push(i);
        }
        while (!stack.isEmpty())//do the same thing for the remaining numbers
        {
            int cur = stack.pop();
            int prev = stack.isEmpty() ? -1 : stack.peek();
            int next = n;
            //comments are the same as previously
            long lsum = (mod + suffix[prev + 1] - (suffix[cur] + sum1[cur] * (cur - prev - 1)) % mod) % mod;
            long rsum = (mod + prefix[next] - (prefix[cur + 1] + sum[cur + 1] * (next - cur - 1)) % mod) % mod;
            long self = ((long) arr[cur] * (next - cur) % mod) * (cur - prev) % mod;
            long curres = (long) arr[cur] * ((rsum * (cur - prev) % mod + lsum * (next - cur) % mod + self) % mod) % mod;
            ans = (ans + curres) % mod;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println("" + getMinMoves(Arrays.asList(1, 2)));
        System.out.println("" + getMinMoves(Arrays.asList(3, 2, 1)));
        System.out.println("" + getMinMoves(Arrays.asList(2, 4, 3, 1, 6)));
        System.out.println("" + getMinMoves(Arrays.asList(5, 4, 3, 2, 1)));
//        List<Integer>[] adj = new List[100];
//        for(int i=0; i<100; i++) {
//            adj[i] = new ArrayList<>();
//            adj[i].add(1);
//            adj[i].add(2);
//        }
//        for (List<Integer> integers : adj) {
//            for (Integer integer : integers) {
//                System.out.println("output: " + integer);
//            }
//        }
//
//        Map<Integer, Integer> map = new HashMap<>();
//        map.put(1,1);
//        map.put(2,1);
//        map.put(3,1);
//        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
//            System.out.println(entry.getKey() + " " + entry.getValue());
//        }
//        Queue<Integer> pq = new PriorityQueue<>((o1,o2)->o2-o1);
//        pq.add(3);
//        pq.add(-1);
//        pq.add(4);
//        pq.add(-2);
//        pq.add(0);
//        while (!pq.isEmpty()) {
//            System.out.println(pq.poll());
//        }
//        Integer[] arr = new Integer[]{9,-1,0,7,23,3,100};
//        Arrays.sort(arr, (o1,o2) -> o2-o1);
//        System.out.println(Arrays.toString(arr));
    }
}

