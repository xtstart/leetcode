package part09_binary_tree.BSTIterator;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-25  22:37
 * @Version: 1.0
 * @Description: TODO
 */

public class BSTIterator01 {

    TreeNode root;

    Queue<TreeNode> queue;

    public BSTIterator01(TreeNode root) {
        this.root = root;
        queue = new ArrayDeque<TreeNode>();

        inOrder(root);
    }

    public void inOrder(TreeNode node) {
        if (null == node) {
            return;
        }
        inOrder(node.left);
        queue.offer(node);
        inOrder(node.right);
    }

    public int next() {
        return queue.poll().val;
    }

    public boolean hasNext() {
        return !queue.isEmpty();
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
