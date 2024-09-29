package may.may17;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-05-17  09:21
 * @Version: 1.0
 * @Description: No.125 [Easy]
 * 【验证回文】
 *
 * Q:
 * 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
 *
 * 字母和数字都属于字母数字字符。
 *
 * 给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入: s = "A man, a plan, a canal: Panama"
 * 输出：true
 * 解释："amanaplanacanalpanama" 是回文串。
 * 示例 2：
 *
 * 输入：s = "race a car"
 * 输出：false
 * 解释："raceacar" 不是回文串。
 * 示例 3：
 *
 * 输入：s = " "
 * 输出：true
 * 解释：在移除非字母数字字符之后，s 是一个空字符串 "" 。
 * 由于空字符串正着反着读都一样，所以是回文串。
 */

public class IsPalindrome {


    /**
     * 自己的解法：
     * 1. 数字: [48,57]
     * 2. 大写字母[65,90] 小写[97,122]
     *
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        int n = s.length();
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            while (left < n - 1 && (s.charAt(left) < 48 || (s.charAt(left) > 57 && s.charAt(left) < 65)
                    || (90 < s.charAt(left) && s.charAt(left) < 97) || s.charAt(left) > 122)) {
                left++;
            }

            while (right > 0 && (s.charAt(right) < 48 || (s.charAt(right) > 57 && s.charAt(right) < 65)
                    || (90 < s.charAt(right) && s.charAt(right) < 97) || s.charAt(right) > 122)) {
                right--;
            }

            if (left >= right) {
                return true;
            }
            int leftNum = s.charAt(left);
            int rightNum = s.charAt(right);
            if( leftNum>= 97 &&  leftNum <= 122){
                leftNum -=32;
            }
            if( rightNum>= 97 &&  rightNum <= 122){
                rightNum -=32;
            }
            if (rightNum == leftNum) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }
}
