package part16_divide_and_conquer;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-07-24  11:29
 * @Version: 1.0
 * @Description: TODO
 */

public class Construct {

   /* public Node construct(int[][] grid) {
        int n = grid.length;
        Node head = new Node();


    }*/

    public void method(int[] grid, int size) {


    }


    /**
     * 判断该区域内的元素时候相同
     *
     * @param grid 数组
     * @param size 范围长度
     *             一下两个参数为起点横纵坐标
     * @param x    纵坐标
     * @param y    横坐标
     * @return 相同返回true，不同返回false
     */
    public boolean hasUniformValues(int[][] grid, int size, int x, int y) {
        int first = grid[x][y];
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < j + size; j++) {
                if (grid[i][j] != first) {
                    return false;
                }
            }
        }
        return true;
    }

    class Solution {
        public Node construct(int[][] grid) {
            return dfs(grid, 0, 0, grid.length, grid.length);
        }

        /**
         * 处理解决 r0行到r1 - 1 行，从c0到c1 - 1列的部分
         * @param grid 数组
         * @param r0
         * @param c0
         * @param r1
         * @param c1
         * @return
         */
        public Node dfs(int[][] grid, int r0, int c0, int r1, int c1) {
            boolean same = true;
            for (int i = r0; i < r1; ++i) {
                for (int j = c0; j < c1; ++j) {
                    if (grid[i][j] != grid[r0][c0]) {
                        same = false;
                        break;
                    }
                }
                if (!same) {
                    break;
                }
            }

            if (same) {
                return new Node(grid[r0][c0] == 1, true);
            }

            Node ret = new Node(
                    true,
                    false,
                    dfs(grid, r0, c0, (r0 + r1) / 2, (c0 + c1) / 2),
                    dfs(grid, r0, (c0 + c1) / 2, (r0 + r1) / 2, c1),
                    dfs(grid, (r0 + r1) / 2, c0, r1, (c0 + c1) / 2),
                    dfs(grid, (r0 + r1) / 2, (c0 + c1) / 2, r1, c1)
            );
            return ret;
        }
    }

    class Solution01 {
        public Node construct(int[][] grid) {
            int n = grid.length;
            int[][] pre = new int[n + 1][n + 1];
            for (int i = 1; i <= n; ++i) {
                for (int j = 1; j <= n; ++j) {
                    pre[i][j] = pre[i - 1][j] + pre[i][j - 1] - pre[i - 1][j - 1] + grid[i - 1][j - 1];
                }
            }
            return dfs(grid, pre, 0, 0, n, n);
        }

        public Node dfs(int[][] grid, int[][] pre, int r0, int c0, int r1, int c1) {
            int total = getSum(pre, r0, c0, r1, c1);
            if (total == 0) {
                return new Node(false, true);
            } else if (total == (r1 - r0) * (c1 - c0)) {
                return new Node(true, true);
            }

            Node ret = new Node(
                    true,
                    false,
                    dfs(grid, pre, r0, c0, (r0 + r1) / 2, (c0 + c1) / 2),
                    dfs(grid, pre, r0, (c0 + c1) / 2, (r0 + r1) / 2, c1),
                    dfs(grid, pre, (r0 + r1) / 2, c0, r1, (c0 + c1) / 2),
                    dfs(grid, pre, (r0 + r1) / 2, (c0 + c1) / 2, r1, c1)
            );
            return ret;
        }

        public int getSum(int[][] pre, int r0, int c0, int r1, int c1) {
            return pre[r1][c1] - pre[r1][c0] - pre[r0][c1] + pre[r0][c0];
        }
    }


    public Node construct(int[][] grid) {
        int n = grid.length;
        int[][] pre = new int[n + 1][n + 1];
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                pre[i][j] = pre[i - 1][j] + pre[i][j - 1] - pre[i - 1][j - 1] + grid[i - 1][j - 1];
            }
        }
        return dfs(grid, pre, 0, 0, n, n);
    }

    public Node dfs(int[][] grid, int[][] pre, int r0, int c0, int r1, int c1) {
        int total = getSum(pre, r0, c0, r1, c1);
        if (total == 0) {
            return new Node(false, true);
        } else if (total == (r1 - r0) * (c1 - c0)) {
            return new Node(true, true);
        }

        Node ret = new Node(
                true,
                false,
                dfs(grid, pre, r0, c0, (r0 + r1) / 2, (c0 + c1) / 2),
                dfs(grid, pre, r0, (c0 + c1) / 2, (r0 + r1) / 2, c1),
                dfs(grid, pre, (r0 + r1) / 2, c0, r1, (c0 + c1) / 2),
                dfs(grid, pre, (r0 + r1) / 2, (c0 + c1) / 2, r1, c1)
        );
        return ret;
    }

    public int getSum(int[][] pre, int r0, int c0, int r1, int c1) {
        return pre[r1][c1] - pre[r1][c0] - pre[r0][c1] + pre[r0][c0];
    }



    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }

}
