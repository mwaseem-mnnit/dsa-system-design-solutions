package problems.lc552;

class Solution {

    public static final int MOD = 1000000007;
    public int checkRecord(int n) {
        int[][][] dp = new int[n+1][2][3];
        dp[0][0][0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 3; k++) {
                    /** adding P **/
                    dp[i+1][j][0] = (dp[i+1][j][0] + dp[i][j][k]) % MOD;
                    /** adding A **/
                    if(j < 1) {
                        dp[i+1][j+1][0] = ( dp[i+1][j+1][0] + dp[i][j][k] ) % MOD;
                    }
                    /** adding L **/
                    if(k < 2 ) {
                        dp[i+1][j][k+1] = (dp[i+1][j][k+1] + dp[i][j][k]) % MOD;
                    }
                }
            }
        }
        int result = 0;
        for (int j = 0; j < 2; j++) {
            for (int k = 0; k < 3; k++) {
                result = (result + dp[n][j][k]) % MOD;
            }
        }
        return  result;
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.checkRecord(1));
        System.out.println(obj.checkRecord(2));
        System.out.println(obj.checkRecord(3));
        System.out.println(obj.checkRecord(4));
        System.out.println(obj.checkRecord(10101));
    }
}