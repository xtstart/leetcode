package may.may28;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-05-28  10:55
 * @Version: 1.0
 * @Description: No.242 [Easy]
 * 【有效字母的异位词】
 * Q:
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 *
 *
 *
 * 示例 1:
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 *
 *
 * 提示:
 *
 * 1 <= s.length, t.length <= 5 * 104
 * s 和 t 仅包含小写字母
 */

public class IsAnagram {

    public static void main(String[] args) {
        int[] arr = {1,2};
        arr[1]++;
        System.out.println(arr[1]);
    }

    /**
     * 【自己的解法】
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (sLen != tLen) {
            return false;
        }
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        int[] arr = new int[26];
        for (int i = 0; i < sLen; i++) {
            arr[sChar[i] - 'a']++;
            arr[tChar[i] - 'a']--;
        }
        for (int j : arr) {
            if (j != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * ChatGPT优化代码
     * 主要是减少临时变量的使用
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram02(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] charCounts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            charCounts[s.charAt(i) - 'a']++;
            charCounts[t.charAt(i) - 'a']--;
        }

        for (int count : charCounts) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

}
