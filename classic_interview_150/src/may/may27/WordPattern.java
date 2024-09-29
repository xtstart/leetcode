package may.may27;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-05-27  16:09
 * @Version: 1.0
 * @Description: No.290 [Easy]
 * 【单词规律】
 * Q:
 * 给定一种规律 pattern 和一个字符串 s ，判断 s 是否遵循相同的规律。
 *
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 s 中的每个非空单词之间存在着双向连接的对应规律。
 *
 *
 *
 * 示例1:
 *
 * 输入: pattern = "abba", s = "dog cat cat dog"
 * 输出: true
 * 示例 2:
 *
 * 输入:pattern = "abba", s = "dog cat cat fish"
 * 输出: false
 * 示例 3:
 *
 * 输入: pattern = "aaaa", s = "dog cat cat dog"
 * 输出: false
 */

public class WordPattern {


    /**
     * 自己的解法
     * @param pattern
     * @param s
     * @return
     */
    public boolean wordPattern(String pattern, String s) {
        String[] strs = s.split(" ");
        char[] pattenChar = pattern.toCharArray();
        if (strs.length != pattenChar.length) {
            return false;
        }
        Map<Character, String> map = new HashMap<Character, String>();
        for (int i = 0; i < pattenChar.length; i++) {
            char ch = pattenChar[i];
            if (map.containsKey(ch)) {
                if (!map.get(ch).equals(strs[i])) {
                    return false;
                }
                continue;
            } else {
                if(map.containsValue(strs[i])){
                    return false;
                }
                map.put(ch, strs[i]);
            }
        }
        return true;
    }


    /**
     * 官方解法
     * @param pattern
     * @param str
     * @return
     */
    public boolean wordPattern02(String pattern, String str) {
        Map<String, Character> str2ch = new HashMap<String, Character>();
        Map<Character, String> ch2str = new HashMap<Character, String>();
        int m = str.length();
        int i = 0;
        for (int p = 0; p < pattern.length(); ++p) {
            char ch = pattern.charAt(p);
            if (i >= m) {
                return false;
            }
            int j = i;
            while (j < m && str.charAt(j) != ' ') {
                j++;
            }
            String tmp = str.substring(i, j);
            if (str2ch.containsKey(tmp) && str2ch.get(tmp) != ch) {
                return false;
            }
            if (ch2str.containsKey(ch) && !tmp.equals(ch2str.get(ch))) {
                return false;
            }
            str2ch.put(tmp, ch);
            ch2str.put(ch, tmp);
            i = j + 1;
        }
        return i >= m;
    }

    /**
     * 判断第一次出现的位置
     * @param pattern
     * @param s
     * @return
     */
    public boolean wordPattern03(String pattern, String s) {
        List<String> ls = Arrays.asList(s.split(" "));
        int n = pattern.length();
        if (n != ls.size()) return false;
        for (int i = 0; i < n; i++) {
            if (pattern.indexOf(pattern.charAt(i)) != ls.indexOf(ls.get(i)))
                return false;
        }
        return true;
    }
}
