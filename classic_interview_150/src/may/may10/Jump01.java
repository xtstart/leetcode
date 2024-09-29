package may.may10;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-05-11  10:00
 * @Version: 1.0
 * @Description: [Easy]
 *
 *
 * Q:
 * 跳跃游戏(一)
 * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
 * 示例 1：
 * <p>
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 */

public class Jump01 {


    /**
     *
     * @param nums
     * @return
     */
    public boolean canJump01(int[] nums) {
        int maxLength = 0;
        for (int i = 0; i <= maxLength; i++) {
            maxLength = Math.max(maxLength, i + nums[i]);
            if (maxLength >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }

    public boolean canJump(int[] nums) {
        int maxLength = 0;
        for (int i = 0; i <= maxLength; i++) {
            maxLength = Math.max(maxLength, i + nums[i]);
            if (maxLength >= nums.length) {
                return true;
            }
        }
        return false;
    }
}
