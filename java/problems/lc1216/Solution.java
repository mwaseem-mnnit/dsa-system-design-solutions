package problems.lc1216;

public class Solution {

    public boolean isValidPalindrome(String s, int k) {
        int[][] dp = new int[s.length() + 1][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[0][i] = 0;
            dp[1][i] = 0;
        }

        for (int i = 0; i < s.length() - 1; i++) {
            dp[2][i] = s.charAt(i) == s.charAt(i+1) ? 0 : 1;
        }

        for (int i = 3; i <= s.length(); i++) {
            for (int j = 0; j + i <= s.length(); j++) {
                dp[i][j] = s.length() - 1;
            }
        }

        for (int i = 3; i <= s.length(); i++) {
            for (int j = 0; j + i <= s.length(); j++) {
                dp[i][j] = 1 + Math.min(dp[i-1][j], dp[i-1][j+1]);
                if(s.charAt(j) == s.charAt(j+i-1)) {
                    dp[i][j] = Math.min(dp[i][j], dp[i-2][j+1]);
                }
            }
        }
        return dp[s.length()][0] <= k;
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.isValidPalindrome("abcdeca", 2));
        System.out.println(obj.isValidPalindrome("abcdeca", 1));
        System.out.println(obj.isValidPalindrome("abcdefa", 3));
        System.out.println(obj.isValidPalindrome("abcdefa", 4));
        System.out.println(obj.isValidPalindrome("abcdegh", 3));
        System.out.println(obj.isValidPalindrome("abcdegh", 4));
    }

    /*
    * Input: s = "abcdeca", k = 2
    * Output: true
    * Explanation: Remove 'b' and 'e' characters.
    *   acdca
    *
    *     a  b  c  d  e  c  a
    *     0  1  2  3  4  5  6
    * 0   1  0  0  0  0  0  0
    * 1   1  1  0  0  0  0  0
    * 2   0  1  1  0  0  0
    *
    * */
}
