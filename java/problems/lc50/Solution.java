package problems.lc50;

import java.util.ArrayList;
import java.util.List;

class Solution {

    public double myPow(double x, int n) {
        if(n == 0 ) {
            return 1.0;
        }
        long nn = n;
        List<Pair> list = new ArrayList<>();
        list.add(new Pair(1.0, 0));
        list.add(new Pair(x, 1));

        while(list.getLast().value < Math.abs(nn)) {
            Pair last = list.getLast();
            list.add(new Pair(last.key * last.key, 2 * last.value));
        }
        boolean isNegative = nn < 0;
        nn = Math.abs(nn);
        double result = 1.0;
        int index = list.size() - 1;
        while(nn > 0) {
            Pair last = list.get(index);
            if(last.value > nn) {
                index--;
                continue;
            }
            result *= last.key;
            nn = nn - last.value;
            index--;
        }
        return isNegative ? 1.0 / result : result;
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.myPow(-1.00000, -2147483648));
    }
}

class Pair {
    double key;
    long value;

    public Pair(double key, long value) {
        this.key = key;
        this.value = value;
    }
}
