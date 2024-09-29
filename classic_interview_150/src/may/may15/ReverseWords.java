package may.may15;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-05-15  17:42
 * @Version: 1.0
 * @Description: TODO
 */

public class ReverseWords {

    public static void main(String[] args) {
        ReverseWords reverseWords = new ReverseWords();
        reverseWords.reverseWords("a good   example");
        String b = "   aa  ddd   ";

        System.out.println(b.trim());
    }

    public String reverseWords(String s) {
        StringBuilder str = new StringBuilder();
        int left = s.length() - 1;
        int right = 0;
        while (left >= 0) {
            while (left >= 0 && s.charAt(left) == ' ') {
                left--;
            }
            right = left;
            while (left >= 0 && s.charAt(left) != ' ') {
                left--;
            }
            if (left + 1 >= 0) {
                str.append(s.substring(left + 1, right + 1));
            }
            while (left >= 0 && s.charAt(left) == ' ') {
                left--;
            }
            if (left >= 0) {
                str.append(' ');
            }
        }
        return str.toString();
    }
}
