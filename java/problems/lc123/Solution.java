package problems.lc123;

import java.util.Arrays;

public class Solution {

    public int maxProfit(int[] prices) {
        int[] result = new int[2];
        Arrays.fill(result, 0);
        int l = 0, r = 1;
        while (r < prices.length) {
            if (prices[r] < prices[r - 1]) {
                int currentProfit = prices[r - 1] - prices[l];
                this.computeMaxProfits(result, currentProfit);
                l = r;
            }
            r++;
        }
        this.computeMaxProfits(result, prices[r - 1] - prices[l]);
        return result[0] + result[1];
    }

    private void computeMaxProfits(int[] result, int currentProfit) {
        int max1 = result[0];
        int max2 = result[1];
        if (currentProfit < max1) {
            max2 = Math.max(max2, currentProfit);
        }
        if (currentProfit >= max1) {
            max2 = max1;
            max1 = currentProfit;
        }
        result[0] = max1;
        result[1] = max2;
    }

    public static void main(String[] args) {
        System.out.println((new Solution()).maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
        System.out.println((new Solution()).maxProfit(new int[]{1, 2, 3, 4, 5}));
        System.out.println((new Solution()).maxProfit(new int[]{7, 6, 4, 3, 1}));
        System.out.println((new Solution()).maxProfit(new int[]{1, 2, 4, 2, 5, 7, 2, 4, 9, 0}));
    }
}