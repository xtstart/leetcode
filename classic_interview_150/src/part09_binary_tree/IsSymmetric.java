package part09_binary_tree;

import org.junit.Test;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-23  22:59
 * @Version: 1.0
 * @Description:
 */

public class IsSymmetric {

    @Test
    public void test(){
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.left = node6;
        node3.right = node7;

        isSymmetric(node1);
    }


    public boolean isSymmetric(TreeNode root) {
        if (null == root) {
            return true;
        }
        StringBuilder str1 = new StringBuilder();
        StringBuilder str2 = new StringBuilder();
        preOrder(root, str1);
        preOrderNew(root, str2);
        return str1.toString().equals(str2.toString());
    }

    public void preOrder(TreeNode root, StringBuilder str) {
        if (null == root) {
            str.append("null");
            return;
        }
        str.append(root.val);
        preOrder(root.left, str);
        preOrder(root.right, str);
    }

    public void preOrderNew(TreeNode root, StringBuilder str) {
        if (null == root) {
            str.append("null");
            return;
        }
        str.append(root.val);
        preOrderNew(root.right, str);
        preOrderNew(root.left, str);
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
