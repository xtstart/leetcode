package y2014.m06_june.d27;

import java.util.Stack;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-27  11:14
 * @Version: 1.0
 * @Description: TODO
 */

public class SmallestString {

    // TODO 该题未解出

    /**
     * 官方解法：贪心算法
     * @param s
     * @return
     */
    public String smallestString(String s) {
        int indexOfFirstNonA = findFirstNonA(s);
        if (indexOfFirstNonA == s.length()) {
            StringBuilder sb = new StringBuilder(s);
            sb.setCharAt(s.length() - 1, 'z');
            return sb.toString();
        }
        int indexOfFirstA_AfterFirstNonA = findFirstA_AfterFirstNonA(s, indexOfFirstNonA);
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (i >= indexOfFirstNonA && i < indexOfFirstA_AfterFirstNonA) {
                res.append((char) (c - 1));
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

    public int findFirstNonA(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != 'a') {
                return i;
            }
        }
        return s.length();
    }

    public int findFirstA_AfterFirstNonA(String s, int firstNonA) {
        for (int i = firstNonA; i < s.length(); i++) {
            if (s.charAt(i) == 'a') {
                return i;
            }
        }
        return s.length();
    }

}
