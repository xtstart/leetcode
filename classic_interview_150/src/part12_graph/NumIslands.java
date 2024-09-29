package part12_graph;

import org.junit.Test;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-28  11:41
 * @Version: 1.0
 * @Description: [No.200] [medium]
 * [][]
 * 【岛屿问题】
 * Q:
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * <p>
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该网格的四条边均被水包围。
 */

public class NumIslands {

    @Test
    public void test01() {
        char[][] grids = {{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 1, 1}};

        int i = numIslands(grids);
        System.out.println(i);
    }


    boolean[][] visited = null;

    int rows, columns;

    public int numIslands(char[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;
        this.rows = rows;
        this.columns = columns;
        visited = new boolean[rows][columns];
        int result = 0;

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < columns; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    visit(grid, i, j);
                    result++;
                }
            }
        }
        return result;
    }

    /**
     * 左下右上
     *
     * @param grid
     * @param row
     * @param column
     */
    public void visit(char[][] grid, int row, int column) {
        if (row < 0 || row >= rows || column < 0 || column >= columns || visited[row][column]
                || grid[row][column] == '0') {
            return;
        }
        visited[row][column] = true;
        visit(grid, row, column + 1);
        visit(grid, row + 1, column);
        visit(grid, row, column - 1);
        visit(grid, row - 1, column);
    }

    /**
     * chatgpt优化
     */
    class Solution {

        boolean[][] visited;
        int rows, columns;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 右、下、左、上

        public int numIslands(char[][] grid) {
            rows = grid.length;
            columns = grid[0].length;
            visited = new boolean[rows][columns];
            int result = 0;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (grid[i][j] == '1' && !visited[i][j]) {
                        visit(grid, i, j);
                        result++;
                    }
                }
            }
            return result;
        }

        public void visit(char[][] grid, int row, int column) {
            if (row < 0 || row >= rows || column < 0 || column >= columns || visited[row][column] || grid[row][column] == '0') {
                return;
            }
            visited[row][column] = true;
            for (int[] direction : directions) {
                visit(grid, row + direction[0], column + direction[1]);
            }
        }
    }

}
