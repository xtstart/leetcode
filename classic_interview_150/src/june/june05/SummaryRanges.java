package june.june05;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-05  16:07
 * @Version: 1.0
 * @Description: No.228 [Easy]
 * 【汇总区间】
 * Q:
 * 给定一个  无重复元素 的 有序 整数数组 nums 。
 * <p>
 * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表 。也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
 * <p>
 * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
 * <p>
 * "a->b" ，如果 a != b
 * "a" ，如果 a == b
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [0,1,2,4,5,7]
 * 输出：["0->2","4->5","7"]
 * 解释：区间范围是：
 * [0,2] --> "0->2"
 * [4,5] --> "4->5"
 * [7,7] --> "7"
 * 示例 2：
 * <p>
 * 输入：nums = [0,2,3,4,6,8,9]
 * 输出：["0","2->4","6","8->9"]
 * 解释：区间范围是：
 * [0,0] --> "0"
 * [2,4] --> "2->4"
 * [6,6] --> "6"
 * [8,9] --> "8->9"
 */

public class SummaryRanges {

    @Test
    public void test() {
        int[] arr = {0, 1, 2, 4, 5, 7};
        summaryRanges(arr);
    }


    public List<String> summaryRanges(int[] nums) {
        List<String> results = new ArrayList<String>();
        int left = 0;
        int right = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i < nums.length - 1 && nums[i] + 1 == nums[i + 1]) {
                right++;
                continue;
            }
            if (i == nums.length - 1 && right != nums.length - 1) {
                right++;
            }
            results.add(left == right ? nums[left] + "" : nums[left] + "->" + nums[right]);
            left = i + 1;
            right = i + 1;
        }
        return results;
    }

    public List<String> summaryRanges01(int[] nums) {
        List<String> results = new ArrayList<>();
        if (nums.length == 0) return results;

        int left = 0;

        for (int i = 1; i <= nums.length; i++) {
            if (i == nums.length || nums[i] != nums[i - 1] + 1) {
                if (left == i - 1) {
                    results.add(String.valueOf(nums[left]));
                } else {
                    results.add(nums[left] + "->" + nums[i - 1]);
                }
                left = i;
            }
        }

        return results;
    }


    /**
     * 官方题解di
     * @param nums
     * @return
     */
    public List<String> summaryRanges02(int[] nums) {
        List<String> ret = new ArrayList<String>();
        int i = 0;
        int n = nums.length;
        while (i < n) {
            int low = i;
            i++;
            while (i < n && nums[i] == nums[i - 1] + 1) {
                i++;
            }
            int high = i - 1;
            StringBuffer temp = new StringBuffer(Integer.toString(nums[low]));
            if (low < high) {
                temp.append("->");
                temp.append(Integer.toString(nums[high]));
            }
            ret.add(temp.toString());
        }
        return ret;
    }


}
