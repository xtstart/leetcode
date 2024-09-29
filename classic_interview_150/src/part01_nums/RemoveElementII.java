package part01_nums;

import org.junit.Test;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-09-27  17:58
 * @Version: 1.0
 * @Description: [No.80]
 */

public class RemoveElementII {

    @Test
    public void test() {

        int[] arr = {1,1,1,2,2,3};
        removeDuplicates(arr);

    }

    public int removeDuplicates(int[] nums) {
        int left = 0;
        int flag = 0;
        int curNo = nums[0];
        for (int right = 0; right < nums.length; right++) {
            if (curNo == nums[right]) {
                flag++;
                if(flag <= 2 ){
                    nums[left++] = curNo;
                }
            } else {
                curNo = nums[right];
                nums[left++] = curNo;
                flag = 1;
            }
        }
        return left;
    }
}
