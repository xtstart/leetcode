package part15_back_tracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-07-22  09:39
 * @Version: 1.0
 * @Description: TODO
 */

public class GenerateParenthesis {

    @Test
    public void test01(){
        generateParenthesis(3);
    }


    /**
     * 自己的解法
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> results = new ArrayList<>();
        dfs(n, 0, new StringBuilder(), results, 0, 0);
        return results;
    }

    public void dfs(int n, int index, StringBuilder str, List<String> result, int left, int right) {
        if (left == right && index == 2 * n) {
            result.add(str.toString());
            return;
        }
        if (left < n) {
            str.append("(");
            dfs(n, index + 1, str, result, left + 1, right);
            str.deleteCharAt(index);
        }
        if (left > right) {
            str.append(")");
            dfs(n, index + 1, str, result, left, right + 1);
            str.deleteCharAt(index);
        }
    }


}
