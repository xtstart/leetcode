package may.may29;

import java.util.*;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-05-29  10:50
 * @Version: 1.0
 * @Description: No.49 [Medium]
 * 【字母异位次分组】
 * Q:
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 *
 * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
 *
 *
 *
 * 示例 1:
 *
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 示例 2:
 *
 * 输入: strs = [""]
 * 输出: [[""]]
 * 示例 3:
 *
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 */

public class GroupAnagrams {

    public static void main(String[] args) {
        String a = "bbca";
        char[] charArray = a.toCharArray();
        Arrays.sort(charArray);
        System.out.println(new String(charArray));
    }

    /**
     * 自己的解法
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<List<String>>();
        int len = strs.length;
        if (len == 0) {
            return result;
        }
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (int i = 0; i < len; i++) {
            String cur = strs[i];
            String value = getSortString(cur);
            if (map.containsKey(value)) {
                map.get(value).add(cur);
            } else {
                List<String> list = new ArrayList<String>();
                list.add(cur);
                result.add(list);
                map.put(value, list);
            }
        }
        return result;
    }

    public String getSortString(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }


    /**
     * 官方解答
     * 使用排序+哈希表
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams02(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }

    /**
     * 使用哈希表加计数
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams03(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            int[] counts = new int[26];
            int length = str.length();
            for (int i = 0; i < length; i++) {
                counts[str.charAt(i) - 'a']++;
            }
            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    sb.append((char) ('a' + i));
                    sb.append(counts[i]);
                }
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }


    /**
     * 超过百分百得解法
     * 通过相同得字符串转成ascii码的方式求和，相同的字符串求和相同
     * 注意：需要用数字本身四次方的方式避免不会有不同的数加起来也一样的错误
     *
     * TODO ：自己其实也想到了求和的方式来算，但是无法解决不同的值加起来数值却相同的错误
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams04(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<Integer,Integer> map = new HashMap<>();
        for(String s:strs){
            int temp=0;
            char[] chars = s.toCharArray();
            for(char c:chars){
                // TODO: 这是值得自己学习的一个思路
                temp+=(int)c*(int)c*(int)c*(int)c;
            }
            int index = map.getOrDefault(temp,-1);
            if(index!=-1){
                res.get(index).add(s);
            }else{
                map.put(temp,res.size());
                ArrayList<String> list = new ArrayList<>();
                list.add(s);
                res.add(list);
            }
        }
        return res;

    }

    /**
     * Chat Gpt对上一个算法进行了优化
     * 优化点
     * 哈希键：使用字符计数数组生成哈希键，避免了哈希冲突问题。
     * 效率提升：计算字符计数数组比计算每个字符的四次方和更高效。
     * 代码可读性：优化后的代码更加直观易懂，便于维护和学习。
     * 这种方法保证了相同字符组成的字符串会生成相同的哈希键，从而正确分组。
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams05(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        for (String s : strs) {
            int[] charCount = new int[26];
            for (char c : s.toCharArray()) {
                charCount[c - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int count : charCount) {
                sb.append(count).append("#");
            }
            String key = sb.toString();

            int index = map.getOrDefault(key, -1);
            if (index != -1) {
                res.get(index).add(s);
            } else {
                map.put(key, res.size());
                List<String> list = new ArrayList<>();
                list.add(s);
                res.add(list);
            }
        }
        return res;
    }
}
