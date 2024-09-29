package part17_kadane;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-07-29  14:29
 * @Version: 1.0
 * @Description: TODO
 */

public class MaxSubarraySumCircular {

    @Test
    public void test01(){
        int[] arr = {1,-2,3,-2};
        maxSubarraySumCircular(arr);
    }

    /**
     * 前缀和+滑动窗口+单调队列
     *
     * 滑动窗口保证长度不超过 n （没有重复使用），
     * 前缀和取差可以快速计算子数组和，
     * 单调队列保留可能的最优解
     *
     * @param nums
     * @return
     */
    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        // 队列 0: 数组下标 1: 前缀和
        Deque<int[]> queue = new ArrayDeque<int[]>();
        int pre = nums[0], res = nums[0];
        //
        queue.offerLast(new int[]{0, pre});
        for (int i = 1; i < 2 * n; i++) {
            // 保证队列长度不超过n
            while (!queue.isEmpty() && queue.peekFirst()[0] < i - n) {
                queue.pollFirst();
            }
            pre += nums[i % n];
            // 两个前缀和的差值就是中间子数组的和
            res = Math.max(res, pre - queue.peekFirst()[1]);
            // 作为被减项越小越好
            while (!queue.isEmpty() && queue.peekLast()[1] >= pre) {
                queue.pollLast();
            }
            queue.offerLast(new int[]{i, pre});
        }
        return res;
    }


    @Test
    public void test02(){
        Deque<int[]> queue = new ArrayDeque<int[]>();
        System.out.println(queue.peekFirst()[0]);
    }

}
