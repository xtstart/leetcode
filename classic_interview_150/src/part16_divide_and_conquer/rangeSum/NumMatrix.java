package part16_divide_and_conquer.rangeSum;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-07-24  21:03
 * @Version: 1.0
 * @Description: TODO
 */

public class NumMatrix {

    public static void main(String[] args) {
        int[][] matrix = {{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}};
        NumMatrix numMatrix = new NumMatrix(matrix);
        numMatrix.sumRegion(2, 1, 4, 3);
    }


    int[][] sums;

    public NumMatrix(int[][] matrix) {
        int rows = matrix.length;
        int colums = matrix[0].length;

        sums = new int[rows][colums];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colums; j++) {
                int before = (i - 1 < 0 || j - 1 < 0) ? 0 : sums[i - 1][j - 1];
                int top = i - 1 < 0 ? 0 : sums[i - 1][j];
                int right = j - 1 < 0 ? 0 : sums[i][j - 1];
                sums[i][j] = matrix[i][j] + top + right - before;
            }

        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {

        int before = (row1 - 1 < 0 | col1 - 1 < 0) ? 0 : sums[row1 - 1][col1 - 1];
        int top = row1 - 1 < 0 ? 0 : sums[row1 - 1][col2];
        int right = col1 - 1 < 0 ? 0 : sums[row2][col1 - 1];
        return sums[row2][col2] + before - top - right;
    }
}



