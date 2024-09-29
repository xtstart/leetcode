package part15_back_tracking;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-07-22  10:49
 * @Version: 1.0
 * @Description: TODO
 */

public class Exist {

    /**
     * 自己的解法
     */
    class Solution01{

        boolean flag = false;
        int[][] arr = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        public boolean exist(char[][] board, String word) {

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    visit(board, word, 0, i, j, new boolean[board.length][board[0].length]);
                }
            }
            return flag;
        }

        public void  visit(char[][] board, String word, int index, int i, int j,boolean[][] visited) {
            if (index == word.length()) {
                flag = true;
                return;
            }

            if(i <0 || i>=board.length || j < 0 || j >= board[0].length || visited[i][j]){
                return;
            }
            visited[i][j] = true;
            for(int k =0 ;k < arr.length;k++){
                if(word.charAt(index) == board[i][j]){
                    visit(board,word,index+1,i+arr[k][0],j+arr[k][1],visited);
                }
            }
            visited[i][j] =false;

        }
    }


    /**
     * chatGpt优化代码
     */
    class Solution02 {

        int[][] directions = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };

        public boolean exist(char[][] board, String word) {
            int rows = board.length;
            int cols = board[0].length;
            boolean[][] visited = new boolean[rows][cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (dfs(board, word, 0, i, j, visited)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean dfs(char[][] board, String word, int index, int i, int j, boolean[][] visited) {
            if (index == word.length()) {
                return true;
            }

            if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j] || board[i][j] != word.charAt(index)) {
                return false;
            }

            visited[i][j] = true;
            for (int[] dir : directions) {
                int newRow = i + dir[0];
                int newCol = j + dir[1];
                // TODO
                if (dfs(board, word, index + 1, newRow, newCol, visited)) {
                    return true;
                }
            }
            visited[i][j] = false;
            return false;
        }
    }


    /**
     * 官方解法：回溯
     */
    class Solution03 {
        public boolean exist(char[][] board, String word) {
            int h = board.length, w = board[0].length;
            boolean[][] visited = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    boolean flag = check(board, visited, i, j, word, 0);
                    if (flag) {
                        return true;
                    }
                }
            }
            return false;
        }

        public boolean check(char[][] board, boolean[][] visited, int i, int j, String s, int k) {
            if (board[i][j] != s.charAt(k)) {
                return false;
            } else if (k == s.length() - 1) {
                return true;
            }
            visited[i][j] = true;
            int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            boolean result = false;
            for (int[] dir : directions) {
                int newi = i + dir[0], newj = j + dir[1];
                if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                    if (!visited[newi][newj]) {
                        boolean flag = check(board, visited, newi, newj, s, k + 1);
                        if (flag) {
                            result = true;
                            break;
                        }
                    }
                }
            }
            visited[i][j] = false;
            return result;
        }
    }

}
