package part23_dynamic_programming2level;

import org.junit.Test;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-08-21  14:43
 * @Version: 1.0
 * @Description: TODO
 */

public class IsInterleave {


    @Test
    public void test(){
        String s1= "aabcc";
        String s2= "dbbca";
        String s3= "aadbbcbcac";
        isInterleave(s1, s2, s3);
    }


    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length();
        if (n + m != t) {
            return false;
        }
        boolean[][] dp = new boolean[n + 1][m + 1];

        dp[0][0] = true;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                int p = i + j - 1;
                if (i > 0) {
                    // TODO 需要debug理解一下为什么是charAt(i-1)
                    dp[i][j] = dp[i][j] || (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(p));
                }
                if (j > 0) {
                    dp[i][j] = dp[i][j] || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }

        return dp[n][m];
    }
}
