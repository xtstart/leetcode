package may.may21;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-05-21  14:46
 * @Version: 1.0
 * @Description: TODO
 */

public class FindSubstring {
    public static void main(String[] args) {
        /*String s = "wordgoodgoodgoodbestword";
        FindSubstring findSubstring = new FindSubstring();
        String[] words = {"word", "good", "best", "good"};
        findSubstring.findSubstring(s, words);
        */
        String a = "https://hainan.cdt.unicom.local:8088";
        String replace = a.replace("https", "http").replace("8088", "9908");
        System.out.println(replace);
    }

    /**
     * 暴力破解，会超时
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> results = new ArrayList<Integer>();
        int size = words.length * words[0].length();
        int l = words[0].length();

        int len = s.length();
        for (int i = 0; i < len; i++) {
            Map<String, Integer> map = initMap(words);
            if (i + size <= len) {
                int start = i;
                String value = s.substring(start, start + l);
                while (map.containsKey(value)) {
                    Integer j = map.get(value);
                    if (j > 0) {
                        map.put(value, j - 1);
                    } else {
                        break;
                    }
                    start = start + l;
                    if (start - i == size) {
                        results.add(i);
                        break;
                    }
                    value = s.substring(start, start + l);
                }

            }
        }
        return results;
    }

    public Map<String, Integer> initMap(String[] arr) {
        Map<String, Integer> map = new HashMap<String, Integer>();

        for (String s : arr) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        return map;
    }


    /**
     * ai优化的代码
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring01(String s, String[] words) {
        List<Integer> results = new ArrayList<>();
        if (s == null || words == null || words.length == 0 || s.length() < words.length * words[0].length()) {
            return results;
        }

        int wordLength = words[0].length();
        int wordsCount = words.length;
        int windowSize = wordLength * wordsCount;
        Map<String, Integer> wordCounts = new HashMap<>();
        for (String word : words) {
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }

        for (int i = 0; i <= s.length() - windowSize; i++) {
            Map<String, Integer> seen = new HashMap<>(wordCounts);
            int start = i;
            int matched = 0;

            while (start < i + windowSize) {
                String word = s.substring(start, start + wordLength);
                if (seen.containsKey(word)) {
                    seen.put(word, seen.get(word) - 1);
                    if (seen.get(word) == 0) {
                        matched++;
                    }
                    if (matched == wordsCount) {
                        results.add(i);
                        break;
                    }
                } else {
                    break;
                }
                start += wordLength;
            }
        }
        return results;
    }

}
