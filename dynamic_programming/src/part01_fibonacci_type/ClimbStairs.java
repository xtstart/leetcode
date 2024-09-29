package part01_fibonacci_type;

import org.junit.Test;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-26  14:35
 * @Version: 1.0
 * @Description:
 * No.70 [easy]
 * [memoization][math][dynamic-programming]
 * Q:
 *  假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 */

public class ClimbStairs {

    @Test
    public void test(){
        int i = climbStairs(44);
        System.out.println(i);
    }


    /**
     * 自己得解法： 运行超时
     * 好理解
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        return climb(0, n);
    }

    public int climb(int n, int target) {
        if (n > target) {
            return 0;
        }
        if (n == target) {
            return 1;
        }
        return climb(n + 1, target) + climb(n + 2, target);
    }


    /**
     * 官方解法一: 动态规划
     *
     * 我们用 f(x) 表示爬到第 x 级台阶的方案数，考虑最后一步可能跨了一级台阶，也可能跨了两级台阶，所以我们可以列出如下式子：
     * f(0) = 0;
     * f(1) = 1;
     * f(2) = f(0) + f(1);
     * f(3) = f(1) + f(2);
     *
     * ...
     * f(n) = f(n-1) + f(n-2);
     * @param n
     * @return
     */
    public int climbStairs02(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }
}
