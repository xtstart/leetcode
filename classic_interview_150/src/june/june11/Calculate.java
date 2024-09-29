package june.june11;

import org.junit.Test;

import java.util.*;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-11  11:24
 * @Version: 1.0
 * @Description: No.224 [Hard]
 * 【基本计算器】
 * Q:
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * <p>
 * 注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "1 + 1"
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：s = " 2-1 + 2 "
 * 输出：3
 * 示例 3：
 * <p>
 * 输入：s = "(1+(4+5+2)-3)+(6+8)"
 * 输出：23
 */

public class Calculate {

    /**
     * 1.处理空格和负数
     * 1.1 " " -> ""
     * 1.2 "-" -> "0-"
     * 1.3 "(-" -> "(0-"
     * 2.将中缀转为后缀
     * 3.计算后缀表达式
     *
     * @param expression
     * @return
     */
    public int calculate(String expression) {
        expression = expression.replace(" ", "").replaceAll("^-", "0-").replace("(-", "(0-");
        List<String> infixExpressionList = toInfixExpressionList(expression);
        // 3. 将得到的中缀表达式对应的List => 后缀表达式对应的List
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        return calculate(suffixExpressionList);
    }

    /**
     * 将中缀表达式对应的List => 后缀表达式对应的List
     *
     * @param infixExpressionList
     * @return
     */
    public static List<String> parseSuffixExpressionList(List<String> infixExpressionList) {
        // 定义两个栈
        // 符号栈
        Stack<String> s1 = new Stack<>();
        // 因为s2这个栈，在整个转换过程中，没有pop操作，而且后面我们还需要逆序输出
        // 因此比较麻烦，这里我们就不用Stack<String> 直接使用List<String> s2
        // 存储中间结果的栈
        List<String> s2 = new ArrayList<>();

        // 遍历infixExpressionList
        for (String item : infixExpressionList) {
            // 如果是一个数，加入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                // 如果是右括号”)“,则依次弹出s1栈顶的运算符，并压入s2，知道遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                // 将 '(' 丢弃
                s1.pop();
            } else {
                // 当item的优先级小于等于栈顶的运算符的优先级：将s1栈顶的运算符弹出并加入到s2，再次转到比较
                while (!s1.isEmpty() && (Operation.getValue(item) <= Operation.getValue(s1.peek()))) {
                    s2.add(s1.pop());
                }
                // 将item压入s1中
                s1.push(item);
            }
        }
        while (!s1.isEmpty()) {
            s2.add(s1.pop());
        }

        // 因为是存放到list中，因此按顺序输出就是对应的后缀表达式的结果
        return s2;
    }

    /**
     * 将中缀表达式转成对应的list
     *
     * @param s
     * @return
     */
    public static List<String> toInfixExpressionList(String s) {
        List<String> result = new ArrayList<>(s.length());
        int index = 0;
        String str = ""; // 对多位数的拼接工作
        char ch;
        do {
            // 如果是非数字就加入到result中去
            if ((ch = s.charAt(index)) < 48 || (ch = s.charAt(index)) > 57) {
                result.add(String.valueOf(ch));
                index++;
            } else { // 如果是数字则进行拼接
                str = ""; // 先将str置空
                while (index < s.length() && ((ch = s.charAt(index)) >= 48 && (ch = s.charAt(index)) <= 57)) {
                    str += ch; // 拼接
                    index++;
                }
                result.add(str);
            }

        } while (index < s.length());

        return result;
    }

    /**
     * 将一个逆波兰表达式，依次将数据和运算符 放入到ArrayList中
     *
     * @param suffixExpression 表达式
     * @return
     */
    public static List<String> getListStr(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>(split.length);
        for (String item : split) {
            list.add(item);
        }
        return list;
    }

    /**
     * 完成对逆波兰表达式的运算
     *
     * @param ls
     * @return
     */
    public static int calculate(List<String> ls) {
        Stack<String> stack = new Stack<>();

        for (String item : ls) {
            // 匹配的是数字
            if (item.matches("\\d+")) {
                // 入栈
                stack.push(item);
            } else {
                // pop出两个数运算,再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int result = 0;
                if (item.equals("+")) {
                    result = num1 + num2;
                } else if (item.equals("-")) {
                    result = num1 - num2;
                } else if (item.equals("*")) {
                    result = num1 * num2;
                } else if (item.equals("/")) {
                    result = num1 / num2;
                } else {
                    throw new RuntimeException("输入的运算符不合法！");
                }

                stack.push(String.valueOf(result));
            }
        }

        // 最后留在stack中的数据就是运算结果
        return Integer.parseInt(stack.pop());
    }

    @Test
    public void test02() {
        String str = "1+2+(3-(4+5))";
        int i = calculate02(str);
    }


    public int calculate02(String s) {
        Deque<Integer> ops = new LinkedList<Integer>();
        ops.push(1);
        int sign = 1;

        int ret = 0;
        int n = s.length();
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == ' ') {
                i++;
            } else if (s.charAt(i) == '+') {
                sign = ops.peek();
                i++;
            } else if (s.charAt(i) == '-') {
                sign = -ops.peek();
                i++;
            } else if (s.charAt(i) == '(') {
                ops.push(sign);
                i++;
            } else if (s.charAt(i) == ')') {
                ops.pop();
                i++;
            } else {
                long num = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                ret += sign * num;
            }
        }
        return ret;
    }


}

// 编写一个类Operation 可以返回一个运算符 对应的优先级
class Operation {

    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    /**
     * 返回运算符的优先级
     *
     * @param operator
     * @return
     */
    public static int getValue(String operator) {

        int result = 0;
        switch (operator) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                // System.out.println("运算符异常");
                break;
        }
        return result;
    }
}