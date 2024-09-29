package may.may14;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-05-14  10:10
 * @Version: 1.0
 * @Description: No.42 [hard]
 * 【接雨水】
 * Q:
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 */

public class Trap {


    /**
     * 【官方题解：动态规划】
     *
     * i能接到得雨量取决于 hightMax[i] - hight[i]
     * hightMax[i] 下雨后水能到达的最大高度等于下标 i 两边的最大高度的最小值
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int n = height.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        int result = 0;
        for (int i = 1; i < n - 1; i++) {
            result += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return result;
    }
}
