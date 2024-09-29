package part09_binary_tree;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-25  09:30
 * @Version: 1.0
 * @Description: No.114 [medium]
 * [stack][tree][dfs][linked-list][binary-tree]
 * 【二叉树展开为链表】
 * Q:
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * <p>
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 */

public class Flatten {


    /**
     * 自己的解法
     * @param root
     */
    public void flatten(TreeNode root) {
        if (null == root) {
            return;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            queue.offer(node);
            if (null != node.right) {
                stack.push(node.right);
            }
            if (null != node.left) {
                stack.push(node.left);
            }
        }
        TreeNode pre = null;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (null != pre) {
                pre.right = node;
            }
            node.left = null;
            pre = node;
        }
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
