package may.may18;

import java.util.Arrays;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-05-18  12:58
 * @Version: 1.0
 * @Description: No.209 [Medium]
 * 【长度最小的子数组】
 * Q:
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 *
 * 找出该数组中满足其总和大于等于 target 的长度最小的 连续
 * 子数组
 *  [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 示例 2：
 *
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 *
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 */

public class MinSubArrayLen {

    public static void main(String[] args) {
        int[] arr = {2,3,1,2,4,3};
        MinSubArrayLen minSubArrayLen = new MinSubArrayLen();
        minSubArrayLen.minSubArrayLen03(7, arr);
    }


    /**
     * 暴力破解
     * for循环遍历整个数组然后计算以每个数为起点的最小值
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen01(int target, int[] nums) {
        int len = nums.length;
        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            int count = 0;
            for (int j = i; j < len; j++) {
                count += nums[j];
                if (count >= target) {
                    minLength = Math.min(minLength, j - i + 1);
                    break;
                }
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }


    /**
     * 【官方解法】
     * 前缀和加二分查找
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        int len = nums.length;
        int[] sum = new int[len+1];
        int min = Integer.MAX_VALUE;
        // sum 的下标表示前几个元素之后，如num[2] 表示前面 0 个元素的和
        for(int i = 1;i <= len;i++){
            sum[i] = sum[i-1] +  nums[i-1];
        }
        for(int i = 1; i < sum.length ;i++){
            int target = sum[i-1] + s;
            int bound  = binarySearch(sum,i,sum.length,target);
            if(bound  < 0){
                // 将bound 从num中的下标 转换为 nums 中的下标，如num[4]
                // 表示前四个数之后，则在nums中表示下标 0 ~ 3元素的下标之和，则在nums中的下标为3
                bound = - bound - 1;
            }
            if(bound <= len){
                min = Math.min(min,bound - (i - 1));
            }
        }
        return min ==  Integer.MAX_VALUE ? 0 : min;
    }


    /**
     * 二分查找，参考Arrays.binarySearch()
     * @param arr
     * @param fromIndex
     * @param toIndex
     * @param key
     * @return
     * - 如果返回值大于 0 , 则找到该值，并返回了下标。
     *  如果返回值小于0 ,则表示目标值在数组中的插入位置的下标。
     */
    public int binarySearch(int[] arr, int fromIndex, int toIndex, int key) {
        int low = fromIndex;
        int hight = toIndex - 1;

        while (low <= hight) {
            int mid = (low + hight) >>> 1;
            int midVal = arr[mid];
            if (key == midVal) {
                return mid;
            } else if (key > midVal) {
                low = mid + 1;
            } else {
                hight = mid - 1;
            }
        }
        return -(low + 1);
    }

    public int minSubArrayLen03(int s, int[] nums) {

        int len = nums.length;
        int start = 0;
        int end = 0;
        int count = 0;
        int min = Integer.MAX_VALUE;
        while (end < len) {
            count += nums[end];

            while (count >= s) {
                min = Math.min(min, end - start);
                count -= nums[start];
                start = start + 1;
            }
            end++;
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }


}
