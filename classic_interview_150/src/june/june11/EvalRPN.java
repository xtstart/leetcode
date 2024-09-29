package june.june11;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-11  10:10
 * @Version: 1.0
 * @Description: No.150 [Medium]
 * 【逆波兰表达式求值】
 * Q:
 * 给你一个字符串数组 tokens ，表示一个根据 逆波兰表示法 表示的算术表达式。
 * <p>
 * 请你计算该表达式。返回一个表示表达式值的整数。
 * <p>
 * 注意：
 * <p>
 * 有效的算符为 '+'、'-'、'*' 和 '/' 。
 * 每个操作数（运算对象）都可以是一个整数或者另一个表达式。
 * 两个整数之间的除法总是 向零截断 。
 * 表达式中不含除零运算。
 * 输入是一个根据逆波兰表示法表示的算术表达式。
 * 答案及所有中间计算结果可以用 32 位 整数表示。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：tokens = ["2","1","+","3","*"]
 * 输出：9
 * 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
 * 示例 2：
 * <p>
 * 输入：tokens = ["4","13","5","/","+"]
 * 输出：6
 * 解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
 * 示例 3：
 * <p>
 * 输入：tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * 输出：22
 * 解释：该算式转化为常见的中缀算术表达式为：
 * ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 */

public class EvalRPN {


    /**
     * 自己的解法
     * 正常解析计算
     *
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<Integer>();
        int result = 0;
        for (String item : tokens) {
            if (item.matches("^-*[0-9]+$")) {
                stack.push(Integer.valueOf(item));
            } else {
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push(calcu(num2, num1, item));
            }
        }
        return stack.pop();
    }

    public int calcu(int num1, int num2, String operation) {
        if ("+".equals(operation)) {
            return num1 + num2;
        } else if ("-".equals(operation)) {
            return num1 - num2;
        } else if ("*".equals(operation)) {
            return num1 * num2;
        } else if ("/".equals(operation)) {
            return num1 / num2;
        } else {
            throw new RuntimeException("该运算符不支持:" + operation);
        }
    }


    /**
     * 官网解法：方法一：栈
     * 1. 使用 Deque（LinkedList）避免了 Stack 中存在的同步开销。
     * 2. 使用简单的 isNumber 方法，而不是正则表达式匹配。
     * 3. 使用 switch 语句，比一系列 if-else 语句更高效。
     * 4. 通过在主循环中直接执行操作，避免了额外的函数调用开销。
     *
     * @param tokens
     * @return
     */
    public int evalRPN02(String[] tokens) {
        Deque<Integer> stack = new LinkedList<Integer>();
        int n = tokens.length;
        for (int i = 0; i < n; i++) {
            String token = tokens[i];
            if (isNumber(token)) {
                stack.push(Integer.parseInt(token));
            } else {
                int num2 = stack.pop();
                int num1 = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(num1 + num2);
                        break;
                    case "-":
                        stack.push(num1 - num2);
                        break;
                    case "*":
                        stack.push(num1 * num2);
                        break;
                    case "/":
                        stack.push(num1 / num2);
                        break;
                    default:
                }
            }
        }
        return stack.pop();
    }

    public boolean isNumber(String token) {
        return !("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token));
    }

    /**
     * 官方解法二：数组实现栈
     * @param tokens
     * @return
     */
    public int evalRPN03(String[] tokens) {
        int n = tokens.length;
        int[] stack = new int[(n + 1) / 2];
        int index = -1;
        for (int i = 0; i < n; i++) {
            String token = tokens[i];
            switch (token) {
                case "+":
                    index--;
                    stack[index] += stack[index + 1];
                    break;
                case "-":
                    index--;
                    stack[index] -= stack[index + 1];
                    break;
                case "*":
                    index--;
                    stack[index] *= stack[index + 1];
                    break;
                case "/":
                    index--;
                    stack[index] /= stack[index + 1];
                    break;
                default:
                    index++;
                    stack[index] = Integer.parseInt(token);
            }
        }
        return stack[index];
    }

}
