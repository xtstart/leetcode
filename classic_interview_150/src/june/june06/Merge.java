package june.june06;

import java.util.*;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-06  10:45
 * @Version: 1.0
 * @Description: No.56 [Medium]
 * 【合并区间】
 * Q:
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2：
 * <p>
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */

public class Merge {

    public int[][] merge(int[][] intervals) {
        int[][] arr = new int[1][];
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });
        List<int[]> list = new ArrayList<int[]>();
        int len = intervals.length;
        int i = 0;
        while (i < len) {
            int left = i;
            int R = intervals[left][1];
            i++;
            while(i< len && R >= intervals[i][0]){
                R = Math.max(intervals[i][1],R);
                i++;
            }
            int right = i-1;
            if(left == right){
                list.add(intervals[left]);
            }else{
                list.add(new int[]{intervals[left][0],R});
            }

        }
        return  list.toArray(new int[list.size()][]);
    }


    /**
     * 官方解答
     * @param intervals
     * @return
     */
    public int[][] merge02(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; ++i) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

}
