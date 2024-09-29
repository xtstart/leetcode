package june.june07;

import java.util.*;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-07  16:45
 * @Version: 1.0
 * @Description: TODO
 */

public class MinStack {
    Stack<Integer> stack;
    List<Integer> list;

    public MinStack() {
        stack = new Stack<Integer>();
        list = new ArrayList<Integer>();
    }

    public void push(int val) {
        list.add(val);
        Collections.sort(list);
        stack.push(val);
    }

    public void pop() {
        int value = stack.pop();
        list.remove(Integer.valueOf(value));
        Collections.sort(list);
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return list.get(0);
    }

    class MinStack01 {
        Stack<Integer> stack;
        Stack<Integer> minStack;

        public MinStack01() {
            stack = new Stack<Integer>();
            minStack = new Stack<Integer>();
        }

        public void push(int val) {
            if(minStack.isEmpty()){
                minStack.push(val);
            }else{
                minStack.push(val < minStack.peek() ?  val : minStack.peek());
            }
            stack.push(val);
        }

        public void pop() {
            stack.pop();
            minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }

    }
}
