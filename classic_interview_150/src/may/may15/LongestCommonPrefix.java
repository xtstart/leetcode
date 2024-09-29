package may.may15;

import sun.applet.Main;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-05-15  17:07
 * @Version: 1.0
 * @Description: No.14 [Easy]
 * 【最长公共前缀】
 */

public class LongestCommonPrefix {

    public static void main(String[] args) {
        String a = "01234";
        System.out.println(a.substring(0,2));
    }


    public String longestCommonPrefix(String[] strs) {
        int n = strs.length;
        int minLength = Integer.MAX_VALUE;
        for (String str : strs) {
            minLength = Math.min(str.length(), minLength);
        }
        char cur = ' ';
        StringBuilder common = new StringBuilder();
        for (int i = 0; i < minLength; i++) {
            cur = strs[0].charAt(i);
            for (int j = 1; j < n; j++) {
                if (cur != strs[j].charAt(i)) {
                    return common.toString();
                }
            }
            common.append(cur);
        }
        return common.toString();
    }
}
