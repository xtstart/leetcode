package part09_binary_tree;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-25  16:13
 * @Version: 1.0
 * @Description: TODO
 */

public class SumNumbers {


    public int sumNumbers(TreeNode root) {
        if (null == root) {
            return 0;
        }
        return preOrderCount(root, 0);
    }

    public int preOrderCount(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        sum = 10 * sum + root.val;
        if (null == root.left && null == root.right) {
            return sum;
        }
        return preOrderCount(root.left, sum) + preOrderCount(root.right, sum);
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
