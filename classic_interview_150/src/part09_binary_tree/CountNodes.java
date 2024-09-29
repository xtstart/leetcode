package part09_binary_tree;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-26  09:46
 * @Version: 1.0
 * @Description: TODO
 */

public class CountNodes {

    int count = 0;

    public int countNodes(TreeNode root) {
         preOrder(root);
         return  count;
    }

    public void preOrder(TreeNode node) {
        if (null == node) {
            return;
        }
        count++;
         preOrder(node.left);
         preOrder(node.right);
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
