package problems.lc3413;

import java.util.Arrays;
import java.util.Comparator;
/*
    k = 4
*   1  2  3  4  5  6  7  8  9  10
*   4  4  4     2  2     1  1
*  */
public class Solution {
    public long maximumCoins(int[][] coins, int k) {
        Arrays.sort(coins, Comparator.comparingInt(a -> a[0]));
        int l = Math.max(coins[0][1] - k + 1, coins[0][0]), li = 0, i = 1;
        long result = (long) coins[0][2] * (coins[0][1] - l + 1);
        long maxResult = result;
        for (; i < coins.length; i++) {
            int pivot = (coins[i][1] - k);
            if(pivot < l) {
                result = (result + (long) (coins[i][1] - coins[i][0]) * coins[i][2]);
                maxResult = Math.max(result, maxResult);
            } else if(pivot < coins[li][1]) {
                maxResult = Math.max(maxResult, result + (long) (Math.max(0, l + k - 1) - coins[i][0] + 1) * coins[i][2]);
                result = result - ((long) coins[li][2] * (pivot - l + 1));
                l = pivot + 1;
            } else {
                while(l <= pivot) {
                    if(pivot >= coins[li][1]) {
                        result = result - ((long) coins[li][2] * (coins[li][1] - l + 1));
                        li++;
                        l = coins[li][0];
                    } else {
                        result = result - ((long) coins[li][2] * (pivot - l + 1));
                        l = pivot + 1;
                        break;
                    }
                }
            }
            result = (result + (long) (coins[i][1] - coins[i][0] + 1) * coins[i][2]);
            maxResult = Math.max(result, maxResult);
        }
        return maxResult;
    }

    public static void main(String[] args) {
        System.out.println((new Solution()).maximumCoins(
                new int[][] {{8,10,1},{1,3,4},{5,6,2}},
                5
        ));
    }
}
