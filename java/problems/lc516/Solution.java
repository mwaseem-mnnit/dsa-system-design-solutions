package problems.lc516;

public class Solution {

    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length() + 1][s.length()];
        int max = 1;
        for (int i = 0; i < s.length(); i++) {
            dp[1][i] = 1;
        }
        for (int i = 2; i <= s.length(); i++) {
            for (int j = 0; j + i <= s.length(); j++) {
                dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j+1]);
                dp[i][j] = Math.max(dp[i][j], s.charAt(j) == s.charAt(j+i-1) ? dp[i-2][j+1] + 2 : 0 );
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.longestPalindromeSubseq("a"));
        System.out.println(obj.longestPalindromeSubseq("abcdefgh"));
        System.out.println(obj.longestPalindromeSubseq("abcdcba"));
        System.out.println(obj.longestPalindromeSubseq("bbbab"));
        System.out.println(obj.longestPalindromeSubseq("azxbasxcasxbca"));
        System.out.println(obj.longestPalindromeSubseq("euazbipzncptldueeuechubrcourfpftcebikrxhybkymimgvldiwqvkszfycvqyvtiwfckexmowcxztkfyzqovbtmzpxojfofbvwnncajvrvdbvjhcrameamcfmcoxryjukhpljwszknhiypvyskmsujkuggpztltpgoczafmfelahqwjbhxtjmebnymdyxoeodqmvkxittxjnlltmoobsgzdfhismogqfpfhvqnxeuosjqqalvwhsidgiavcatjjgeztrjuoixxxoznklcxolgpuktirmduxdywwlbikaqkqajzbsjvdgjcnbtfksqhquiwnwflkldgdrqrnwmshdpykicozfowmumzeuznolmgjlltypyufpzjpuvucmesnnrwppheizkapovoloneaxpfinaontwtdqsdvzmqlgkdxlbeguackbdkftzbnynmcejtwudocemcfnuzbttcoew"));
    }
}
