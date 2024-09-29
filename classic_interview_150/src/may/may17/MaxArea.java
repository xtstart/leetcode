package may.may17;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-05-17  15:55
 * @Version: 1.0
 * @Description: No.11 [Medium]
 * 【盛最多水容器】
 * 11. 盛最多水的容器
 * 中等
 * 相关标签
 * 相关企业
 * 提示
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 *
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 返回容器可以储存的最大水量。
 *
 * 说明：你不能倾斜容器。
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * 示例 2：
 *
 * 输入：height = [1,1]
 * 输出：1
 */

public class MaxArea {


    /**
     * 【自己的解法】
     * 暴力遍历
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            int j = i + 1;
            while (j < height.length) {
                max = Math.max(getArea(i, j, height), max);
                j++;
            }
        }
        return max;
    }

    public int getArea(int i, int j, int[] height) {
        int h = Math.min(height[i], height[j]);
        return (j - i) * h;
    }

}
