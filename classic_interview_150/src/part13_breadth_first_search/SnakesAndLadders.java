package part13_breadth_first_search;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-07-10  10:52
 * @Version: 1.0
 * @Description: No.909 [medium]
 * 【蛇梯棋】
 * Q:
 * 给你一个大小为 n x n 的整数矩阵 board ，方格按从 1 到 n2 编号，编号遵循 转行交替方式 ，从左下角开始 （即，从 board[n - 1][0] 开始）每一行交替方向。
 * <p>
 * 玩家从棋盘上的方格 1 （总是在最后一行、第一列）开始出发。
 * <p>
 * 每一回合，玩家需要从当前方格 curr 开始出发，按下述要求前进：
 * <p>
 * 选定目标方格 next ，目标方格的编号符合范围 [curr + 1, min(curr + 6, n2)] 。
 * 该选择模拟了掷 六面体骰子 的情景，无论棋盘大小如何，玩家最多只能有 6 个目的地。
 * 传送玩家：如果目标方格 next 处存在蛇或梯子，那么玩家会传送到蛇或梯子的目的地。否则，玩家传送到目标方格 next 。
 * 当玩家到达编号 n2 的方格时，游戏结束。
 * r 行 c 列的棋盘，按前述方法编号，棋盘格中可能存在 “蛇” 或 “梯子”；如果 board[r][c] != -1，那个蛇或梯子的目的地将会是 board[r][c]。编号为 1 和 n2 的方格上没有蛇或梯子。
 * <p>
 * 注意，玩家在每回合的前进过程中最多只能爬过蛇或梯子一次：就算目的地是另一条蛇或梯子的起点，玩家也 不能 继续移动。
 * <p>
 * 举个例子，假设棋盘是 [[-1,4],[-1,3]] ，第一次移动，玩家的目标方格是 2 。那么这个玩家将会顺着梯子到达方格 3 ，但 不能 顺着方格 3 上的梯子前往方格 4 。
 * 返回达到编号为 n2 的方格所需的最少移动次数，如果不可能，则返回 -1。
 */

public class SnakesAndLadders {


    @Test
    public void test01() {
        int[][] board = {
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 35, -1, -1, 13, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 15, -1, -1, -1, -1}};

        int i = snakesAndLadders(board);
        System.out.println(i);
    }

    @Test
    public void test02() {
        int[][] board = {
                {-1, -1},
                {-1, 3}};

        int i = snakesAndLadders(board);
        System.out.println(i);
    }

    @Test
    public void test03() {
        int[][] board = {{-1, 1, 2, -1}, {2, 13, 15, -1}, {-1, 10, -1, -1}, {-1, 6, 2, 8}};

        int i = snakesAndLadders(board);
        System.out.println(i);
    }


    public int snakesAndLadders(int[][] board) {
        int n = board[0].length;
        int end = n * n;
        boolean[] visited = new boolean[end];
        Queue<Integer> queue = new ArrayDeque<Integer>();
        queue.offer(1);
        visited[0] = true;
        int minStep = Integer.MAX_VALUE;
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                if (cur == end) {
                    minStep = Math.min(step, minStep);
                }
                for (int j = 1; j <= 6; j++) {
                    if (cur + j <= end) {
                        int next = getNext(cur + j, board);
                        if (!visited[next - 1]) {
                            queue.offer(next);
                            visited[next - 1] = true;
                        }
                    }
                }
            }
            step++;
        }
        return minStep == Integer.MAX_VALUE ? -1 : minStep;
    }

    public int getNext(int id, int[][] board) {

        int n = board[0].length;
        int r = (id - 1) / n, c = (id - 1) % n;
        if (r % 2 == 1) {
            c = n - 1 - c;
        }
        int x = n - 1 - r;
        int y = c;
        return board[x][y] > -1 ? board[x][y] : id;
    }


    /**
     * 官方解法
     * @param board
     * @return
     */
    public int snakesAndLadders02(int[][] board) {
        int n = board.length;
        boolean[] vis = new boolean[n * n + 1];
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{1, 0});
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            for (int i = 1; i <= 6; ++i) {
                int nxt = p[0] + i;
                if (nxt > n * n) { // 超出边界
                    break;
                }
                int[] rc = id2rc(nxt, n); // 得到下一步的行列
                if (board[rc[0]][rc[1]] > 0) { // 存在蛇或梯子
                    nxt = board[rc[0]][rc[1]];
                }
                if (nxt == n * n) { // 到达终点
                    return p[1] + 1;
                }
                if (!vis[nxt]) {
                    vis[nxt] = true;
                    queue.offer(new int[]{nxt, p[1] + 1}); // 扩展新状态
                }
            }
        }
        return -1;
    }

    public int[] id2rc(int id, int n) {
        int r = (id - 1) / n, c = (id - 1) % n;
        if (r % 2 == 1) {
            c = n - 1 - c;
        }
        return new int[]{n - 1 - r, c};
    }
}
