package part04_maxtrix;

import org.junit.Test;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-14  16:14
 * @Version: 1.0
 * @Description: No.289 [medium]
 * 【生命游戏】
 * Q:
 * 根据 百度百科 ， 生命游戏 ，简称为 生命 ，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
 * <p>
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态： 1 即为 活细胞 （live），或 0 即为 死细胞 （dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 * <p>
 * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 * 下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。给你 m x n 网格面板 board 的当前状态，返回下一个状态。
 */

public class GameOfLife {

    @Test
    public void test01() {
        int[][] arr = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        gameOfLife(arr);

    }

    /**
     * 自己得解法：
     * 1.循环遍历记录每个细胞周围八个细胞得情况并记录总数，根据规则，将该细胞的下一个状态记录在一个临时数组中
     * 2.将临时数组中的内容拷贝回原数组
     *
     * @param board
     */
    public void gameOfLife(int[][] board) {
        int rows = board.length;
        int columns = board[0].length;
        int[][] temp = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                temp[i][j] = getStatus(board[i][j], getCount(i, j, rows, columns, board));
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = temp[i][j];
            }
        }
    }

    /**
     * 计算出细胞周围8个细胞中存活细胞的数量
     *
     * @param row
     * @param column
     * @param rows
     * @param columns
     * @param board
     * @return
     */
    public int getCount(int row, int column, int rows, int columns, int[][] board) {
        int count = 0;
        int top = row - 1;
        int left = column - 1;
        for (int i = top; i < top + 3; i++) {
            for (int j = left; j < left + 3; j++) {
                if (i == row && j == column) {
                    continue;
                } else if ((i >= 0 && i < rows) && (j >= 0 && j < columns) && board[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 根据周围8个细胞存活的数量，根据规则，得到细胞下一个阶段的状态
     *
     * @param status
     * @param count
     * @return
     */
    public int getStatus(int status, int count) {
        if (status == 1 && (count == 2 || count == 3)) {
            return 1;
        } else if (status == 0 && count == 3) {
            return 1;
        }
        return 0;
    }


}
