package part15_back_tracking;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-18  09:49
 * @Version: 1.0
 * @Description: No.46 [medium]
 * [array][backtracking]
 * 【全排列】
 * Q:
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */

public class Permute {

    @Test
    public void test01() {
        int[] arr = {1, 2, 3};
        permute(arr);
    }


    // TODO 还未掌握
    /**
     * 使用回溯的方法
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        List<Integer> output = new ArrayList<Integer>();
        for (int num : nums) {
            output.add(num);
        }

        int n = nums.length;
        backtrack(n, output, res, 0);
        return res;
    }

    public void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
        // 所有数都填完了
        if (first == n) {
            res.add(new ArrayList<Integer>(output));
        }
        for (int i = first; i < n; i++) {
            // 动态维护数组
            Collections.swap(output, first, i);
            // 继续递归填下一个数
            backtrack(n, output, res, first + 1);
            // 撤销操作
            Collections.swap(output, first, i);
        }
    }


    @Test
    public void test02(){
        int[] arr = {1, 2, 3};
        permute02(arr);
    }

    /**
     * 使用数组来记录哪些元素被访问过
     */
    private int[] flag;
    private List<List<Integer>> res = new ArrayList<List<Integer>>();

    public List<List<Integer>> permute02(int[] nums) {
        if (nums == null) {
            return null;
        }
        flag = new int[nums.length];
        domain(nums, new ArrayList<Integer>());
        return res;
    }

    private void domain(int[] nums, List<Integer> cur) {
        if (cur.size() == nums.length) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (flag[i] == 0) {
                cur.add(nums[i]);
                flag[i] = 1;
                domain(nums, cur);
                flag[i] = 0;
                cur.remove(cur.size() - 1);
            }
        }
    }


    /**
     * 深度优先算法求解
     * @param nums
     * @return
     */


    public List<List<Integer>> permute03(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        int len = nums.length;
        if (len == 0) {
            return res;
        }
        ArrayDeque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[len];
        dfs(nums, len, 0, path, used, res);
        return res;
    }

    private void dfs(int[] nums, int len, int depth, ArrayDeque<Integer> path, boolean[] used, List<List<Integer>> res) {

        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < len; i++) {
            if (used[i]) {
                continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums, len, depth + 1, path, used, res);

            // 回溯
            path.removeLast();
            used[i] = false;

        }
    }


    /**
     * 自己仿照人家的算法求解
     * @param nums
     * @return
     */

    public List<List<Integer>> permute05(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList();
        }
        List<List<Integer>> res = new ArrayList<>();
        int[] flags = new int[nums.length];
        dfs(flags, new ArrayList<Integer>(), nums, res);
        return res;
    }

    public void dfs(int[] flags, List<Integer> list, int[] nums, List<List<Integer>> res) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (flags[i] == 0) {
                flags[i] = 1;
                list.add(nums[i]);
                dfs(flags, list, nums, res);
                flags[i] = 0;
                list.remove(list.size() - 1);
            }
        }

    }
}
