package part04_maxtrix;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-13  10:58
 * @Version: 1.0
 * @Description:
 * No.48 [medium]
 *【旋转图像】
 * Q:
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * <p>
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 * <p>
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 * <p>
 * - 不要使用辅助矩阵
 */

public class Rotate {

    // 没有解出

    /**
     * 官方解法一：使用辅助数组
     * 使用一个辅助数组进行解题
     * int len = matrix.length;
     * matrix[i][j] = matrix[j][len - i - 1];
     * <p>
     * 思路：
     * 1.根据上图得公式，将生成一个新得数组，将数据拷贝到新数组中
     * 2.将新数组中得元素复制到原来得数组中
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int len = matrix.length;
        int[][] newArr = new int[len][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                newArr[j][len - i - 1] = matrix[i][j];
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(newArr[i], 0, matrix[i], 0, matrix[0].length);
        }
    }


    /**
     * 官方解法二：原地旋转
     * <p>
     * 根据上面的公式：
     * matrixNew[col][len - row -1] = matrix[row][col];
     * <p>
     * 第一次交换:
     * matrix[col][len - row -1] = matrix[row][col];
     * <p>
     * 第二次交换:
     * matrix[len - row -1][ len -col -1 ] = matrix[col][len - row -1]
     * <p>
     * 第三次交换:
     * matrix[len - col -1][row] = matrix[len - row -1][ len - col -1 ]
     * <p>
     * 第四次交换:
     * matrix[row][ col ] = matrix[len - col -1][row]
     *
     * @param matrix
     */
    public void rotate02(int[][] matrix) {
        int n = matrix.length;

        int temp = 0;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                // 根据上面推导出的公式，暂存出matrix[i][j]的值，然后移动
                temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }

    /**
     * 官方解法三: 用翻转代替旋转
     * 先进行水平翻转：                                        matrix[i][j] = matrix[n - i -1][j];
     * 再进行主要对角线翻转(以[top,left] -> [bottom,right]为轴): matrix[i][j] = matrix[j][i];
     * <p>
     * 结合上述的两个式子得出：  matrix[i][j] = matrix[j][n - i -1]; 于方法一二得出的式子以知
     *
     * @param matrix
     */
    public void rotate03(int[][] matrix) {
        int n = matrix.length;
        int temp = 0;
        // 先进行水平翻转
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }
        // 再进行主对角线翻转
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

}
