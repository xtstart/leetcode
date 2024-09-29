package may.may30;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-05-30  10:15
 * @Version: 1.0
 * @Description: No.1 [Easy]
 * 【两数之和】
 * Q:
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * <p>
 * 你可以按任意顺序返回答案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 * <p>
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 * <p>
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 */

public class TwoSum {

    @Test
    public void test01() {
        int[] arr = {3, 2, 4};
        int target = 6;
        twoSum(arr, target);
    }


    /**
     * 自己的解法
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> subMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {

            int flag = subMap.getOrDefault(nums[i], -1);
            if (flag != -1) {
                return new int[]{i, flag};
            }
            subMap.put(target - nums[i], i);
        }
        return new int[2];
    }

    /**
     * 暴力破解
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum02(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }
}
