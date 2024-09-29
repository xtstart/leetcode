package may.may16;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-05-16  10:58
 * @Version: 1.0
 * @Description: No.28 [Easy]
 * 【找出字符串中第一个匹配项的小标】
 *
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。如果 needle 不是 haystack 的一部分，则返回  -1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：haystack = "sadbutsad", needle = "sad"
 * 输出：0
 * 解释："sad" 在下标 0 和 6 处匹配。
 * 第一个匹配项的下标是 0 ，所以返回 0 。
 * 示例 2：
 *
 * 输入：haystack = "leetcode", needle = "leeto"
 * 输出：-1
 * 解释："leeto" 没有在 "leetcode" 中出现，所以返回 -1 。
 */

public class StrStr {

    /**
     * 自己实现
     * 【暴力遍历】
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        int n = haystack.length();
        int left = 0;
        int flag = 0;

        while (left < n) {
            int index = 0;
            flag = left;
            while (left < n && index < needle.length() && haystack.charAt(left) == needle.charAt(index)) {
                if (index == needle.length() - 1) {
                    return flag;
                }
                left++;
                index++;
            }
            left=flag+1;
        }
        return -1;
    }
}
