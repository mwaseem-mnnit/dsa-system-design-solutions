class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n+1][n];
        for (int i = 0; i < n; i++) {
            dp[0][i] = true;
            dp[1][i] = true;
        }
        int l = 1, ind = 0;
        for (int i = 2; i <= s.length(); i++) {
            for (int j = 0; j < s.length() - i + 1; j++) {
                dp[i][j] = dp[i-2][j+1] && (s.charAt(j) == s.charAt(i+j-1));
                if(dp[i][j]) {
                    l = i;
                    ind = j;
                }
            }
        }
        return s.substring(ind, ind+l);
    }

    static void main() {
        Solution obj = new Solution();
        System.out.println(obj.longestPalindrome("babab"));
        System.out.println(obj.longestPalindrome("a"));
        System.out.println(obj.longestPalindrome("aaaa"));
    }
}