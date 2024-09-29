package may.may23;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-05-23  10:25
 * @Version: 1.0
 * @Description: No.383 [Easy]
 * 【赎金信】
 * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
 *
 * 如果可以，返回 true ；否则返回 false 。
 *
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 *
 *
 *
 * 示例 1：
 *
 * 输入：ransomNote = "a", magazine = "b"
 * 输出：false
 * 示例 2：
 *
 * 输入：ransomNote = "aa", magazine = "ab"
 * 输出：false
 * 示例 3：
 *
 * 输入：ransomNote = "aa", magazine = "aab"
 * 输出：true
 */

public class CanConstruct {

    /**
     * 自己的解法：
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        char[] ransomNoteChars = ransomNote.toCharArray();
        char[] magazineChars = magazine.toCharArray();
        int[] ransomNotes = new int[128];
        int[] magazineNotes = new int[128];
        Set<Character> set = new HashSet<Character>();

        for(char c:ransomNoteChars){
            ransomNotes[c]++;
            set.add(c);
        }
        for(char c:magazineChars){
            magazineNotes[c]++;
        }
        boolean flag = true;

        for (Character c : set) {
            if (ransomNotes[c] > magazineNotes[c]) {
                flag =false;
            }
        }
        return flag;
    }


    /**
     * 官方解法
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct01(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        int[] cnt = new int[26];
        for (char c : magazine.toCharArray()) {
            cnt[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            cnt[c - 'a']--;
            if(cnt[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
