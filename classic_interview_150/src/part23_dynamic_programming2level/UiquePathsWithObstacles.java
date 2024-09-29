package part23_dynamic_programming2level;

import org.junit.Test;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-08-20  16:45
 * @Version: 1.0
 * @Description: TODO
 */

public class UiquePathsWithObstacles {
    @Test
    public void test01() {
        int[][] arr = {{0, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 0}};
        uniquePathsWithObstacles(arr);
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int rows = obstacleGrid.length;
        int columns = obstacleGrid[0].length;
        int[][] dp = new int[rows + 1][columns + 1];
        dp[1][1] = obstacleGrid[0][0] == 1 ? 0 : 1;

        for (int i = 1; i <= rows; i++) {

            for (int j = 1; j <= columns; j++) {
                if (i != 1 || j != 1) {
                    if (obstacleGrid[i - 1][j - 1] != 1) {
                        if (dp[i - 1][j] != 0 && dp[i][j - 1] != 0) {
                            dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                        } else {
                            dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                        }
                    }
                }
            }
        }
        return dp[rows][columns];
    }
}
