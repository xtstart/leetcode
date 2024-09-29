package part21_math;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-08-12  11:24
 * @Version: 1.0
 * @Description: TODO
 */

public class PlusOne {

    @Test
    public void test() {
        int x = 8;
        int position = 0;
        int num = x;
        int a = 1;
        while (num > 0) {
            position++;
            num >>= 1; // 右移一位
        }
        int len = position / 2;
        for (int i = 0; i < len; i++) {
            a <<= 1;
        }
        x = a | x;
        int mask = (1 << len + 1) - 1;
        x = x & mask;
        System.out.println(x);
    }

   /* public int trailingZeroes(int n) {
        long facNum = factorial(n);
        String str = String.valueOf(facNum);
        int ans = 0;
        for (int i = str.length() - 1; i >= 0 ; i--) {
            if (str.charAt(i) - 48 == 0) {
                ans++;
            } else {
                break;
            }
        }
        return ans;
    }

    public long factorial(BigDecimal n) {
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }*/
}
