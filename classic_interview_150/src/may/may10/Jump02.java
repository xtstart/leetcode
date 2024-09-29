package may.may10;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-05-10  11:54
 * @Version: 1.0
 * @Description: TODO
 */

public class Jump02 {
    public static void main(String[] args) {
        int[] arrs = {2, 3, 0, 1, 4};
        Jump02 jump02 = new Jump02();
        jump02.jump02(arrs);
    }


    // 硬解，递归暴力寻找，会运行超时
    public int jump01(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }
        int length = 0;
        find(0, 1, nums);
        return min;
    }

    int min = Integer.MAX_VALUE;

    public void find(int i, int times, int[] arrs) {

        if (i >= arrs.length) {
            return;
        }

        if (i + arrs[i] >= arrs.length - 1) {
            min = Math.min(times, min);
            return;
        }
        for (int j = 1; j <= arrs[i]; j++) {
            find(i + j, times + 1, arrs);
        }
    }


    //

    public int jump02(int[] nums) {
        int start = 0;
        int end = 0;
        int length = 0;

        while (end < nums.length - 1) {
            length++;
            int max = 0;
            int flag = 0;
            for (int i = start; i <= end; i++) {
                if (max < i + nums[i]) {
                    max = i + nums[i];
                    flag = i;
                }
            }
            start = flag + 1;
            end = max;
        }

        return length;
    }
}
