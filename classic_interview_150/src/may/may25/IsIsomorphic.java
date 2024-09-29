package may.may25;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-05-25  18:26
 * @Version: 1.0
 * @Description: No.205 [Easy]
 * 【同构字符串】
 * Q:
 * 给定两个字符串 s 和 t ，判断它们是否是同构的。
 * <p>
 * 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
 * <p>
 * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入：s = "egg", t = "add"
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：s = "foo", t = "bar"
 * 输出：false
 * 示例 3：
 * <p>
 * 输入：s = "paper", t = "title"
 * 输出：true
 */

public class IsIsomorphic {

    @Test
    public void test() {
        String s = "egg";
        String t = "add";
        isIsomorphic(s, t);

    }


    /**
     * 自己的实现
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        int slen = s.length();
        int tlen = t.length();
        if (slen != tlen) {
            return false;
        }

        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        int[] sCorrespondings = new int[128];
        int[] tCorrespondings = new int[128];

        for (int i = 0; i < slen; i++) {

            if (tCorrespondings[tChar[i]] == 0 && sCorrespondings[sChar[i]] == 0) {
                sCorrespondings[sChar[i]] = tChar[i] + 1;
                tCorrespondings[tChar[i]] = sChar[i] + 1;
            } else {
                int sCorresponding = sCorrespondings[sChar[i]] - 1;
                int tCorresponding = tCorrespondings[tChar[i]] - 1;
                if (sCorresponding != tChar[i] || tCorresponding != sChar[i]) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 官方题解
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic01(String s, String t) {
        Map<Character, Character> s2t = new HashMap<Character, Character>();
        Map<Character, Character> t2s = new HashMap<Character, Character>();
        int len = s.length();
        for (int i = 0; i < len; ++i) {
            char x = s.charAt(i), y = t.charAt(i);
            if ((s2t.containsKey(x) && s2t.get(x) != y) || (t2s.containsKey(y) && t2s.get(y) != x)) {
                return false;
            }
            s2t.put(x, y);
            t2s.put(y, x);
        }
        return true;
    }

}
