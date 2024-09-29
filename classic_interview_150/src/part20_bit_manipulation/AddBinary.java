package part20_bit_manipulation;

import org.junit.Test;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-08-07  11:21
 * @Version: 1.0
 * @Description: TODO
 */

public class AddBinary {

    @Test
    public void test() {
        String a = "11";
        String b = "1";
        addBinary(a, b);
    }

    public String addBinary(String a, String b) {
        int m = a.length();
        int n = b.length();
        int len = Math.max(m, n);
        if (m == len) {
            for (int i = n; i < len; i++) {
                b = "0" + b;
            }
        } else {
            for (int i = m; i < len; i++) {
                a = "0" + a;
            }
        }
        int pre = 0;
        String res = "";
        for (int i = len - 1; i >= 0; i--) {
            int intA = a.charAt(i) - 48;
            int intB = b.charAt(i) - 48;
            intA = intA + intB + pre;
            if (intA >= 2) {
                intA -= 2;
                pre = 1;
            } else {
                pre = 0;
            }
            res = intA + res;
        }
        if (pre == 1) {
            res = pre + res;
        }
        return res;
    }
}
