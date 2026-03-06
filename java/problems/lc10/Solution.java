package problems.lc10;

class Solution {

    public boolean isMatch(String s, String p) {
        Boolean[][] dp = new Boolean[s.length()+1][p.length()+1];
        return findMatch(s, p, 0, 0, dp);
    }

    boolean findMatch(String s, String p, int i, int j, Boolean[][] dp) {
        if(dp[i][j] != null) {
            return dp[i][j];
        }
        if(j == p.length()) {
            dp[i][j] = (i == s.length());
            return dp[i][j];
        }
        boolean firstMatch = (i < s.length() && ((s.charAt(i) == p.charAt(j)) || p.charAt(j) == '.'));
        if(j+1 < p.length() && p.charAt(j+1) == '*') {
            dp[i][j] = findMatch(s, p, i, j+2, dp) || (firstMatch && findMatch(s, p, i+1, j, dp));
        } else {
            dp[i][j] = firstMatch && findMatch(s, p, i+1, j+1, dp);
        }
        return dp[i][j];
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.isMatch("aa", "a"));
        System.out.println(obj.isMatch("aa", ".*"));
        System.out.println(obj.isMatch("abcdef", "ab*cd.*e*"));
    }
}
