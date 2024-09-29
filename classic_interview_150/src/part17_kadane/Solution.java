package part17_kadane;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-07-26  20:36
 * @Version: 1.0
 * @Description: TODO
 */

public class Solution {

    public class Status {
        /**
         * 区间[l,r]
         * lSum: 区间内以l为左端点的最大子段和
         * rSum: 区间内以r为右端点的最大子段和
         * mSum: 区间内最大的字段和
         * iSum: 区间和
         */
        int lSum, rSum, mSum, iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }

    public int maxSubArray(int[] nums) {
        return getInfo(nums, 0, nums.length - 1).mSum;
    }

    public Status getInfo(int[] nums, int left, int right) {
        if (left == right) {
            return new Status(nums[left], nums[left], nums[left], nums[left]);
        }
        int mid = (left + right) >> 1;
        Status lState = getInfo(nums, left, mid);
        Status rState = getInfo(nums, mid + 1, right);
        return pushUp(lState, rState);
    }

    public Status pushUp(Status l, Status r) {
        int iSum = l.iSum + r.iSum;
        int lSum = Math.max(l.lSum, l.iSum + r.lSum);
        int rSum = Math.max(r.rSum, r.iSum + l.rSum);
        int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
        return new Status(lSum, rSum, mSum, iSum);
    }

}
