package june.june03;

import org.junit.Test;

import java.util.*;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-03  10:12
 * @Version: 1.0
 * @Description: No.219 [Easy]
 * 【存在重复元素】
 * Q:
 * 给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，满足 nums[i] == nums[j] 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3,1], k = 3
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：nums = [1,0,1,1], k = 1
 * 输出：true
 * 示例 3：
 * <p>
 * 输入：nums = [1,2,3,1,2,3], k = 2
 * 输出：false
 */

public class ContainsNearbyDuplicate {

    @Test
    public void test() {
        int[] arr = {2, 2};

        containsNearbyDuplicate(arr, 3);

    }


    @Test
    public void test01() {
        int[] arr = {99, 99};

        containsNearbyDuplicate01(arr, 2);
    }

    /**
     * 暴力破解
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            for (int j = k; j > 0; j--) {
                if (i - j > 0 && nums[i] == nums[i - j]) {
                    return true;
                }

                if (j + i < len && nums[i] == nums[j + i]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 自己的写法，使用哈希表，记录一个元素最近一次出现的位置和现在的位置进行比较
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate01(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            if (map.containsKey(num) && i - map.get(num) <= k) {
                return true;
            }
            map.put(num, i);
        }
        return false;
    }


    /**
     * 官方哈希表写法，比我的更简介
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate02(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            if (map.containsKey(num) && i - map.get(num) <= k) {
                return true;
            }
            map.put(num, i);
        }
        return false;
    }


    /**
     * 官方使用滑动窗口
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate03(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }

}
