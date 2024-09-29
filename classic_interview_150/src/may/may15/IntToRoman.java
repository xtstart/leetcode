package may.may15;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-05-15  09:18
 * @Version: 1.0
 * @Description: No.12 [Medium]
 */

public class IntToRoman {

    public static void main(String[] args) {
        System.out.println(525 / 100 * 100);
    }
    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    /**
     * 【官方解法：模拟】
     * @param num
     * @return

     */
    public String intToRoman(int num) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (num>=values[i]){
                num -= values[i];
                str.append(symbols[i]);
            }
        }
        return str.toString();
    }
}
