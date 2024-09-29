package part01_nums;

import org.junit.Test;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-09-26  10:05
 * @Version: 1.0
 * @Description: [No.27]
 */

public class RemoveElementI {

    @Test
    public void test(){
        int[] arr = {0,1,2,2,3,0,4,2};
        removeElement(arr,2);
    }

    public int removeElement(int[] nums, int val) {
        int result = nums.length;
        int pre = 0;
        int tail = 0;
        for (int i : nums) {
            if (i == val)
                result--;
        }
        // 使用双指针法
        while (true) {
            while (pre < nums.length && nums[pre] != val) {
                pre++;
                tail = Math.max(pre, tail);
            }
            while (tail < nums.length && nums[tail] == val) {
                tail++;
            }
            if (pre >= nums.length || tail >= nums.length) {
                break;
            }
            nums[pre] = nums[tail];
            nums[tail] = val;
            pre++;
            tail++;
        }
        return result;
    }

    @Test
    public void test02(){
        int[] arr1 = {3,2,2,3};
        int val1 = 3;
        int[] arr2 = {0,1,2,2,3,0,4,2};
        int val2 = 2;
        removeElement01(arr1, val1);
    }

    public int removeElement01(int[] nums, int val) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        while (left <= right) {
            if (nums[left] == val) {
                nums[left] = nums[right];
                right--;
            } else {
                left++;
            }
        }
        return left;
    }
}
