package part12_graph;

import org.junit.Test;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-29  23:08
 * @Version: 1.0
 * @Description: TODO
 */

public class SurroundedRegions {


    @Test
    public void test(){
        char[][] chars = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};

        solve(chars);
    }

    int rows;
    int columns;
    boolean[][] visited;

    public void solve(char[][] board) {
        rows = board.length;
        columns = board[0].length;
        visited = new boolean[rows][columns];
        // 首先沿着边缘遍历，将边缘能访问得地方标记
        for (int i = 0; i < columns; i++) {
            visit(board, 0, i);
        }

        for (int i = 0; i < columns; i++) {
            visit(board, rows - 1, i);
        }

        for (int i = 0; i < rows; i++) {
            visit(board, i, 0);
        }

        for (int i = 0; i < rows; i++) {
            visit(board, i, columns - 1);
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j] == 'O' && !visited[i][j]) {
                    board[i][j] = 'X';
                }
            }

            StringBuilder stringBuilder = new StringBuilder();
        }

    }

    public void visit(char[][] board, int i, int j) {
        if (i < 0 || i >= rows | j < 0 || j >= columns || board[i][j] == 'X' || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        visit(board, i, j + 1);
        visit(board, i + 1, j);
        visit(board, i, j - 1);
        visit(board, i - 1, j);
    }
}
