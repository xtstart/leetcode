package may.may16;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-05-16  16:30
 * @Version: 1.0
 * @Description: TODO
 */

public class FullJustify {

    public static void main(String[] args) {
        FullJustify fullJustify = new FullJustify();
        String[] strs = {"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"};
        fullJustify.fullJustify02(strs,20);

    }
    public List<String> fullJustify(String[] words, int maxWidth) {
        int len = words.length;
        int left = 0;
        int right = 0;
        List<String> resultList = new ArrayList<String>();

        while (left < len) {
            StringBuilder strBuilder = new StringBuilder();
            int cur = 0;
            left = right;
            while (right < len && cur + words[right].length() + right - left <= maxWidth) {
                cur += words[right++].length();
            }

            // 最后一行
            if (right == len) {
                int index = 0;
                for (int i = left; i < right; i++) {
                    strBuilder.append(words[i]);
                    index ++;
                    if (i != right - 1) {
                        strBuilder.append(' ');
                    }
                }
                strBuilder.append(getEmpty(maxWidth - cur- index +1));
                resultList.add(strBuilder.toString());
                break;
            }

            int numWords = right - left;
            int numSpaces = maxWidth - cur;
            // 处理一行只有一个单词得情况
            if (numWords == 1) {
                strBuilder.append(words[left]);
                strBuilder.append(getEmpty(numSpaces));
                resultList.add(strBuilder.toString());
                continue;
            }

            // 处理一般情况
            int averageSpace = numSpaces / (numWords - 1);
            int extSpace = numSpaces % (numWords - 1);
            for (int i = left; i < right; i++) {
                strBuilder.append(words[i]);
                if (extSpace-- >0) {
                    strBuilder.append(' ');
                }
                if (i != right - 1) {
                    strBuilder.append(getEmpty(averageSpace));
                }
            }
            resultList.add(strBuilder.toString());
        }
        return resultList;
    }

    public String getEmpty(int n) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < n; i++) {
            str.append(' ');
        }
        return str.toString();
    }


    public List<String> fullJustify02(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<String>();
        int right = 0, n = words.length;
        while (true) {
            int left = right; // 当前行的第一个单词在 words 的位置
            int sumLen = 0; // 统计这一行单词长度之和
            // 循环确定当前行可以放多少单词，注意单词之间应至少有一个空格
            while (right < n && sumLen + words[right].length() + right - left <= maxWidth) {
                sumLen += words[right++].length();
            }

            // 当前行是最后一行：单词左对齐，且单词之间应只有一个空格，在行末填充剩余空格
            if (right == n) {
                StringBuffer sb = join(words, left, n, " ");
                sb.append(blank(maxWidth - sb.length()));
                ans.add(sb.toString());
                return ans;
            }

            int numWords = right - left;
            int numSpaces = maxWidth - sumLen;

            // 当前行只有一个单词：该单词左对齐，在行末填充剩余空格
            if (numWords == 1) {
                StringBuffer sb = new StringBuffer(words[left]);
                sb.append(blank(numSpaces));
                ans.add(sb.toString());
                continue;
            }

            // 当前行不只一个单词
            int avgSpaces = numSpaces / (numWords - 1);
            int extraSpaces = numSpaces % (numWords - 1);
            StringBuffer sb = new StringBuffer();
            sb.append(join(words, left, left + extraSpaces + 1, blank(avgSpaces + 1))); // 拼接额外加一个空格的单词
            sb.append(blank(avgSpaces));
            sb.append(join(words, left + extraSpaces + 1, right, blank(avgSpaces))); // 拼接其余单词
            ans.add(sb.toString());
        }
    }

    // blank 返回长度为 n 的由空格组成的字符串
    public String blank(int n) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; ++i) {
            sb.append(' ');
        }
        return sb.toString();
    }

    // join 返回用 sep 拼接 [left, right) 范围内的 words 组成的字符串
    public StringBuffer join(String[] words, int left, int right, String sep) {
        StringBuffer sb = new StringBuffer(words[left]);
        for (int i = left + 1; i < right; ++i) {
            sb.append(sep);
            sb.append(words[i]);
        }
        return sb;
    }

}
