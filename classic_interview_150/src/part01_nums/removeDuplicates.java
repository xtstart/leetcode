package part01_nums;

import org.junit.Test;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-09-27  10:00
 * @Version: 1.0
 * @Description: [No.26]
 */

public class removeDuplicates {

    @Test
    public void test(){
        int[]  nums = {1,1,2};
        removeDuplicates(nums);
    }

    public int removeDuplicates(int[] nums) {
        // 双指针法
        int right = 0, left = 0;
        while (right < nums.length) {
            if (nums[left] != nums[right]) {
                nums[++left] = nums[right];
            }
            right++;
        }
        return left+1;
    }


}
