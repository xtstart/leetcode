package may.may22;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-05-22  11:37
 * @Version: 1.0
 * @Description: TODO
 */

public class MinWindow {

    public static void main(String[] args) {
        MinWindow minWindow = new MinWindow();
        String s = "b";
        String t = "a";
        minWindow.minWindow(s, t);
    }
    public String minWindow(String s, String t) {
        if (s.length() < t.length()){
            return "";
        }
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int count = t.length();
        int len = s.length();
        for (int i = 0; i < count; i++) {
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        }
        int min = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        boolean flag=false;

        for (int i = 0; i < s.length(); i++) {

            if (map.containsKey(s.charAt(i))) {
                Map<Character, Integer> temp = new HashMap<Character, Integer>(map);
                int sum = 0;
                int index = i;
                while (index < len) {
                    if (sum < count ) {
                        if (temp.getOrDefault(s.charAt(index),0) >0) {
                            sum++;
                            if (sum == count) {
                                if (min > index - i + 1) {
                                    flag =true;
                                    min = index - i + 1;
                                    left = i;
                                    right = index;
                                    break;
                                }
                            }
                            temp.put(s.charAt(index), temp.get(s.charAt(index)) - 1);
                        }
                    }
                    index++;
                }
            }
        }
        return flag ? s.substring(left,right+1) : "";
    }

    /**
     * 【AI】 优化
     *
     * 优化的部分：
     * 1.使用左右指针进行寻找，在找到右边的数之后，通过移动左指针来缩小窗口
     * @param s
     * @param t
     * @return
     */
    public String minWindow01(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }

        Map<Character, Integer> tMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> window = new HashMap<>();
        int match = 0;
        int minLen = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int start = 0;

        while (right < s.length()) {
            char c1 = s.charAt(right);
            if (tMap.containsKey(c1)) {
                window.put(c1, window.getOrDefault(c1, 0) + 1);
                if (window.get(c1).equals(tMap.get(c1))) {
                    match++;
                }
            }
            right++;

            // 尝试缩小窗口
            while (match == tMap.size()) {
                if (right - left < minLen) {
                    start = left;
                    minLen = right - left;
                }

                char c2 = s.charAt(left);
                if (tMap.containsKey(c2)) {
                    if (window.get(c2).equals(tMap.get(c2))) {
                        match--;
                    }
                    window.put(c2, window.get(c2) - 1);
                }
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    /**
     *
     * 官方解法
     * @param s
     * @param t
     * @return
     */
    public String minWindow02(String s, String t) {

        int sLen = s.length();
        int tLne = t.length();

        if (sLen < tLne) {
            return "";
        }
        // 分别记录t中字母的频率和窗口中字母出现的频率
        // 因为s,t由英文字母组成故128的长度足矣
        int[] tFreq = new int[128];
        int[] winFreq = new int[128];

        // charAt() 的api需要检查数组的下标是否越界，在Java中通常是采用讲字符串转换为char数组
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        // 定义滑动窗口的两端，[left,right)
        int left = 0, right = 0;
        for (int i = 0; i < t.length(); i++) {
            tFreq[tChars[i]]++;
        }
        int start = 0;
        int distance = 0;
        int minLen = sLen + 1;
        while (right < sLen) {
            char cRight = sChars[right];
            if (tFreq[cRight] == 0) {
                right++;
                continue;
            }

            if (winFreq[cRight] < tFreq[cRight]) {
                distance++;
            }
            winFreq[cRight]++;
            right++;
            // 从left 尝试缩小窗口
            while (distance == tLne) {
                if (right - left < minLen) {
                    minLen = right - left;
                    start = left;

                }
                char cLeft = sChars[left];
                if (tFreq[cLeft] == 0 ) {
                    left++;
                    continue;
                }

                if (winFreq[cLeft] == tFreq[cLeft]) {
                    distance--;
                }
                winFreq[cLeft]--;
                left++;
            }
        }
        return minLen == sLen + 1 ? "" : s.substring(start, start + minLen);
    }
}
