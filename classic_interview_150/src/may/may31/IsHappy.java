package may.may31;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-05-31  10:29
 * @Version: 1.0
 * @Description: No.202 [Easy]
 * 【快乐数】
 * Q:
 * 编写一个算法来判断一个数 n 是不是快乐数。
 *
 * 「快乐数」 定义为：
 *
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果这个过程 结果为 1，那么这个数就是快乐数。
 * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 19
 * 输出：true
 * 解释：
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 * 示例 2：
 *
 * 输入：n = 2
 * 输出：false
 */

public class IsHappy {

    public static void main(String[] args) {
        int a = 123;
        System.out.println(a % 10);
    }


    /**
     * 自己的解法
     * 注意退出循环的另一个条件是出现了循环
     * @param n
     * @return
     */
    public boolean isHappy01(int n) {
        Set<Integer> set =new HashSet<>();
        while (n != 1) {
            String strn = String.valueOf(n);
            int temp = 0;
            for (int i = 0; i < strn.length(); i++) {
                int j = Integer.valueOf(strn.substring(i, i + 1));
                temp += j * j;
            }
            n = temp;
            if(set.contains(n)){
                return false;
            }
            set.add(n);
        }
        return true;
    }


    /**
     * 官方解法
     * @param n
     * @return
     */
    public boolean isHappy02(int n) {
        Set<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getNext(n);
        }
        return n == 1;
    }

    private int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            /**
             * 进行10 的取模能得到个位
             */
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }


    /**
     * 快慢指针
     * 我们不是只跟踪链表中的一个值，而是跟踪两个值，称为快跑者和慢跑者。在算法的每一步中，慢速在链表中前进 1 个节点，快跑者前进 2 个节点（对 getNext(n) 函数的嵌套调用）。
     *
     * 如果 n 是一个快乐数，即没有循环，那么快跑者最终会比慢跑者先到达数字 1。
     *
     * 如果 n 不是一个快乐的数字，那么最终快跑者和慢跑者将在同一个数字上相遇。
     *
     * @param n
     * @return
     */
    public boolean isHappy03(int n) {
        int slowRunner = n;
        int fastRunner = getNext(n);
        while (fastRunner != 1 && slowRunner != fastRunner) {
            slowRunner = getNext(slowRunner);
            fastRunner = getNext(getNext(fastRunner));
        }
        return fastRunner == 1;
    }
}
