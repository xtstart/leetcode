package part04_maxtrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-12  11:03
 * @Version: 1.0
 * @Description:
 * No.54 [medium]
 *【螺旋矩阵】
 * Q:
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * <p>
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 */

public class SpiralOrder {

    // 自己没有解出


    /**
     * 官方解法一：模拟
     * 模拟旋转的路径，初始位置是左上角，初始方向是向右，当路径超出边界或者进入之前访问过的位置是，顺时针旋转，进入下个方向
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length, columns = matrix[0].length;
        boolean[][] visited = new boolean[rows][columns];
        int total = rows * columns;
        int row = 0, column = 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0;
        for (int i = 0; i < total; i++) {
            order.add(matrix[row][column]);
            visited[row][column] = true;
            int nextRow = row + directions[directionIndex][0], nextColumn = column + directions[directionIndex][1];
            if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns || visited[nextRow][nextColumn]) {
                directionIndex = (directionIndex + 1) % 4;
            }
            row += directions[directionIndex][0];
            column += directions[directionIndex][1];
        }
        return order;
    }

    /**
     * 官方解法二：按层模拟
     *
     * 对于每层，从左上方开始以顺时针的顺序遍历所有元素。假设当前层的左上角位于 (top,left) (top,left)，右下角位于 (bottom,right)(bottom,right)，按照如下顺序遍历当前层的元素。
     *
     * 从左到右遍历上侧元素，依次为 (top,left) (top,left) 到 (top,right)(top,right)。
     *
     * 从上到下遍历右侧元素，依次为 (top+1,right) (top+1,right) 到 (bottom,right)。
     *
     * 如果 left < right 且 top<bottom，则从右到左遍历下侧元素，依次为 (bottom,right−1) 到 (bottom,left+1)，以及从下到上遍历左侧元素，依次为 (bottom,left) 到 (top+1,left)。
     *
     * 遍历完当前层的元素之后，将 left 和 top 分别增加 111，将 right 和 bottom 分别减少 111，进入下一层继续遍历，直到遍历完所有元素为止。
     *
     *
     * [top,left]   [1 , 1 , 1 , 1][top,right]
     *               1           1
     * [bottom,left][1 , 1 , 1 , 1][bottom,right]
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder02(int[][] matrix) {
        List<Integer> orders = new ArrayList<>();
        if (null == matrix || matrix.length == 0 || matrix[0].length == 0) {
            return orders;
        }
        int rows = matrix.length;
        int columns = matrix[0].length;
        int top = 0, left = 0, right = columns - 1, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) {
                orders.add(matrix[top][i]);
            }
            for (int i = top+1; i <= bottom; i++) {
                orders.add(matrix[i][right]);
            }
            if (left< right && top <  bottom ){
                for (int i = right-1; i >= left+1; i--) {
                    orders.add(matrix[bottom][i]);
                }
                for (int i = bottom; i >= top+1; i--) {
                    orders.add(matrix[i][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return orders;
    }

}
