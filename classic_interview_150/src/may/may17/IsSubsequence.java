package may.may17;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-05-17  14:34
 * @Version: 1.0
 * @Description: No.392 [Easy]
 * 【判断子序列】
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 *
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 *
 * 进阶：
 *
 * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 */

public class IsSubsequence {


    public boolean isSubsequence(String s, String t) {
        int n = s.length();
        int m = t.length();
        int index = 0;

        for (int i = 0; i < m; i++) {
            if (index < n && s.charAt(index) == t.charAt(i)) {
                index++;
            }
        }
        if (index == n) {
            return true;
        }

        return false;
    }

}
