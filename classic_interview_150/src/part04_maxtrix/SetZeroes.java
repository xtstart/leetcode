package part04_maxtrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-14  14:48
 * @Version: 1.0
 * @Description: No.73 [medium]
 * 【矩阵置零】
 * Q:
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 */

public class SetZeroes {

    /**
     * 自己的解法：
     * 需要使用一个额外的数组
     * 1.遍历整个二位数组将所有的为0的元素的坐标记录下来
     * 2.再遍历记录下的数组，将数组中所有元素所在的二维数组所在的行列置为零
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        List<int[]> zeros = new ArrayList<int[]>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    zeros.add(new int[]{i, j});
                }
            }
        }
        for (int[] cur : zeros) {
            for (int j = 0; j < columns; j++) {
                matrix[cur[0]][j] = 0;
            }
            for (int i = 0; i < rows; i++) {
                matrix[i][cur[1]] = 0;
            }
        }
    }
}
