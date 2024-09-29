package june.june07;

import org.junit.Test;

import java.util.Stack;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-07  14:23
 * @Version: 1.0
 * @Description: TODO
 */

public class SimplifyPath {
    @Test
    public void test01() {
        String path = "/../";
        System.out.println(simplifyPath((path)));

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("dddd");
        stringBuilder.insert(0, "aaa");
        System.out.println(stringBuilder.toString());
    }

    public String simplifyPath(String path) {
        String replace = path.replace("//", "/");
        String[] split = replace.split("/");
        String defaultName = "/";
        Stack<String> stack = new Stack<>();
        for (String item : split) {
            if (item.equals(".") || item.isEmpty()) {
                continue;
            } else if (item.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(item);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (!stack.isEmpty()) {

            for (String s : stack) {
                stringBuilder.append("/").append(s);
            }
            return stringBuilder.toString();
        }
        return defaultName;
    }
}

