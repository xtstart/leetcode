package may.may16;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-05-16  09:12
 * @Version: 1.0
 * @Description: No.6[Medium]
 * 【Z字形变换】
 * Q:
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 *
 *
 * 示例 1：
 *
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * 示例 3：
 *
 * 输入：s = "A", numRows = 1
 * 输出："A"
 */

public class Convert {

    public static void main(String[] args) {
        String str = "ABC";
        Convert convert = new Convert();
        String convert1 = convert.convert(str, 2);

        System.out.println(convert1);
    }

    /**
     * 自己实现
     * 先构造数组，再进行遍历
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        // 1. 构造出一个二位的数组，将数组中的数字转化为char存入数组中
        int[][] arr=new int[numRows][s.length()];
        boolean flag = true;
        char[] chars = s.toCharArray();
        int index=numRows >2 ? numRows -2:0;
        int length = 0;
        int i= 0;
        while(i< chars.length){
            if(flag){
                for(int j= 0;j<numRows;j++){
                    if(i == chars.length){
                        break;
                    }
                    arr[j][length] = chars[i];
                    i++;
                }
                flag=false;
                length++;
            }else{
                for(int j = index;j>0;j--){
                    if(i == chars.length){
                        break;
                    }
                    arr[j][length] =chars[i];
                    i++;
                    length++;
                }

                flag=true;
            }

        }
        // 2. 遍历构造好的数组，取出非零项转为char，拼接到str中
        StringBuilder str = new StringBuilder();
        for (int[] arrs : arr) {
            for (int item : arrs) {
                if (item!=0){
                    str.append((char) item);
                }
            }
        }
        return str.toString();
    }

}
