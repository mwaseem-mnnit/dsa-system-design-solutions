package problems.lc727;

public class Solution {

    public String minWindow(String s1, String s2) {
        int[][] dp = new int[s2.length()][s1.length()];

        for (int i = 0; i < s1.length(); i++) {
            if(s2.charAt(0) == s1.charAt(i)) {
                dp[0][i] = 1;
            } else {
                dp[0][i] = ((i - 1 >= 0) && dp[0][i-1] > -1) ? 1 + dp[0][i-1] : -1;
            }
        }

        for (int i = 1; i < s2.length(); i++) {
            for (int j = 0; j < s1.length(); j++) {
                if(s2.charAt(i) == s1.charAt(j)) {
                    dp[i][j] = ((j - 1 >=0) && dp[i-1][j-1] > -1) ? dp[i-1][j-1] + 1 : -1;
                } else {
                    dp[i][j] = ((j - 1 >=0) && dp[i][j-1] > -1) ? dp[i][j-1] + 1 : -1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int index = -1, min = Integer.MAX_VALUE;
        for (int i = 0; i < s1.length(); i++) {
            if(dp[s2.length()-1][i] > -1 && dp[s2.length()-1][i] < min) {
                min = dp[s2.length()-1][i];
                index = i;
            }
        }
        if(index == -1) return "";
        else {
            int j = s2.length() - 1;
            while(j >= 0) {
                if(s1.charAt(index) == s2.charAt(j)) {
                    j--;
                }
                sb.append(s1.charAt(index));
                index--;
            }
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(obj.minWindow("abdcdbacef", "bce"));
        System.out.println(obj.minWindow("abdcdbacef", "abdcdbacef"));
        System.out.println(obj.minWindow("a", "b"));
        System.out.println(obj.minWindow("a", "a"));
    }
}
