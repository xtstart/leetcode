package june.june04;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-04  09:54
 * @Version: 1.0
 * @Description: No.128 [Medium]
 * 【最长连续序列】
 * Q:
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * <p>
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 * <p>
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 */

public class LongestConsecutive {


    /**
     * 暴力破解
     * 会超时
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<Integer>(nums.length);
        for (int item : nums) {
            set.add(item);
        }
        int max = 1;
        for (int item : nums) {
            int temp = 1;
            while (set.contains(item + 1)) {
                temp++;
                max = Math.max(temp, max);
                item++;
            }
        }
        return max;
    }


    /**
     * 官方题解
     * 多了一步 if (!num_set.contains(num - 1)) {的判断，减少了比较的次数
     *
     * 优化点主要有两点：
     * 1. 第二次遍历直接遍历的哈希集合而不是原来的数组，去重一部分元素
     * 2. 使用 if (!num_set.contains(num - 1)) { 判断修剪枝叶
     * @param nums
     * @return
     */
    public int longestConsecutive02(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}
