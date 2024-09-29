package may.may20;

import java.util.*;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-05-20  22:33
 * @Version: 1.0
 * @Description: No.2 [Medium]
 * 【无重复字符的最长子串】
 * Q:
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长
 * 子串
 *  的长度。
 *
 *
 *
 * 示例 1:
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */

public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        String str = "abba";
        LengthOfLongestSubstring lengthOfLongestSubstring = new LengthOfLongestSubstring();
        int i = lengthOfLongestSubstring.lengthOfLongestSubstring(str);
        System.out.println(i);
    }

    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        int left = 0, right = 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        while (right < len) {
            if (!map.containsKey(s.charAt(right))) {
                map.put(s.charAt(right), right);
                max = Math.max(max, right - left + 1);
                right++;
            } else {
                int temp = map.get(s.charAt(right));
                for (int i = left; i <= temp; i++) {
                    map.remove(s.charAt(i));
                }
                map.put(s.charAt(right), right);
                left = temp + 1;
                right++;
            }
        }
        return max;
    }


    /**
     * 用chatGpt优化的代码
     * 不用每次remove元素，而是根据left =Math.max(left, map.get(currentChar) + 1);判断left的位置
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring01(String s) {
        int len = s.length();
        if (len == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;

        while (right < len) {
            char currentChar = s.charAt(right);
            if (map.containsKey(currentChar)) {
                // 更新左指针到重复字符的下一个位置
                left =Math.max(left, map.get(currentChar) + 1);
            }
            // 将当前字符及其索引添加到map中
            map.put(currentChar, right);
            // 更新最大长度
            max = Math.max(max, right - left + 1);
            // 右指针向右移动
            right++;
        }

        return max;
    }

    /**
     * 【官方解法】
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring02(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

}
