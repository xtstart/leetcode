package part01_nums;

import org.junit.Test;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-09-25  11:31
 * @Version: 1.0
 * @Description: TODO\
 * [No.88]
 */

public class MergeNums {

    @Test
    public void test01(){
        int[] nums1 = {1,2,3,0,0,0};
        int[] nums2 = {2,5,6};
        merge(nums1,3,nums2,3);
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int len = nums1.length;

        int index = 0;
        for (int i = 0; i < n; i++) {
            while (index < len && nums1[index] <= nums2[i]) {
                index++;
            }
            for (int j = index; j < len - 1; j++) {
                nums1[j + 1] = nums1[j];
            }
            nums1[index] = nums2[i];
        }
    }

    public void merge01(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1; // nums1的最后一个有效元素
        int j = n - 1; // nums2的最后一个元素
        int k = m + n - 1; // 合并后数组的最后位置

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }

        // 如果nums2还有剩余元素，直接复制到nums1
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }


    // 插入元素
    public void merge02(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1; // nums1的有效元素最后一个索引
        int j = n - 1; // nums2的最后一个元素索引
        int k = m + n - 1; // 合并后数组的最后位置

        // 从后往前插入元素
        while (j >= 0) {
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }
    }

}
