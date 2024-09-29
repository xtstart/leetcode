package june.june07;

import org.junit.Test;

import java.util.Stack;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-07  11:04
 * @Version: 1.0
 * @Description: TODO
 */

public class IsValid {

    @Test
    public void test01(){
        Stack<Integer> stack = new Stack<>();
        System.out.println(stack.peek());
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char[] charArray = s.toCharArray();
        int index = 0;
        for (char c : charArray) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }


}
