package part04_maxtrix;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-12  09:15
 * @Version: 1.0
 * @Description:
 * No.36 [medium]
 *【有效数独】
 * Q:
 * 请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
 * 1.数字 1-9 在每一行只能出现一次。
 * 2.数字 1-9 在每一列只能出现一次。
 * 3.数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 */

public class IsValidSudoku {

    @Test
    public void test01() {
        char[][] arr = {{'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        isValidSudoku(arr);
    }

    @Test
    public void test02() {
        char[][] arr = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        isValidSudoku(arr);
    }

    @Test
    public void test03() {
        char[][] arr = {{'.', '.', '4', '.', '.', '.', '6', '3', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'5', '.', '.', '.', '.', '.', '.', '9', '.'},
                {'.', '.', '.', '5', '6', '.', '.', '.', '.'},
                {'4', '.', '3', '.', '.', '.', '.', '.', '1'},
                {'.', '.', '.', '7', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '5', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'}};
        isValidSudoku(arr);

    }

    /**
     * 自己的解法：
     * 判断是否是数独需要满足三个条件:
     * 1.两个节点的i是否相同
     * 2.两个节点的j是否相同
     * 3.两个节点i/3,j/3是否都相同
     * 上面三个有一个满足则不满足是数独的条件
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        Map<Character, List<int[]>> map = new HashMap<Character, List<int[]>>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                List<int[]> list = map.getOrDefault(c, new ArrayList<>());
                int[] newNode = {i, j};
                if (!list.isEmpty()) {
                    for (int[] node : list) {
                        if (node[0] == i || node[1] == j || is_points_in_same_3x3_grid(node, newNode)) {
                            return false;
                        }
                    }
                } else {
                    map.put(c, list);
                }
                list.add(newNode);
            }
        }
        return true;
    }

    /**
     * 判断是否在一个3x3的小格子里
     *
     * @param node1
     * @param node2
     * @return
     */
    public boolean is_points_in_same_3x3_grid(int[] node1, int[] node2) {
        if ((node1[0] / 3 == node2[0] / 3) && (node1[1] / 3 == node2[1] / 3)) {
            return true;
        }
        return false;
    }


    /**
     * 官方解法：
     * 使用3个数组，分别将同一行的数字出现的次数
     * 同一列的数字出现的次数
     * 一个3X3格子出现的次数
     * 记录下来，然后遍历一遍，如果大于1则不符合数独的条件
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku02(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] columns = new int[9][9];
        int[][][] subboxes = new int[3][3][9];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int num = board[i][j] - '0' - 1;
                rows[i][num]++;
                columns[j][num]++;
                subboxes[i / 3][j / 3][num]++;
                if (rows[i][num] > 1 || columns[j][num] > 1 || subboxes[i / 3][j / 3][num] > 1) {
                    return false;
                }
            }
        }
        return true;
    }

}

