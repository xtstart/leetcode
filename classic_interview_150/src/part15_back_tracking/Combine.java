package part15_back_tracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-07-19  11:26
 * @Version: 1.0
 * @Description: TODO
 */

public class Combine {


    @Test
    public void test01() {
        Combine combine = new Combine();
        combine.combine(4, 2);
        StringBuilder stringBuilder = new StringBuilder();

    }


    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> combines = new ArrayList<>();
        dfs(1, n, k, combines, new ArrayList<Integer>());
        return combines;
    }

    public void dfs(int index, int n, int k, List<List<Integer>> combines, List<Integer> list) {
        if (list.size() == k) {
            combines.add(new ArrayList<Integer>(list));
            return;
        }
        int cur = list.size();

        for (int i = index; i <= n; i++) {
            list.add(i);
            dfs(i + 1, n, k, combines, list);
            list.remove(cur);
        }
    }
}
