package part09_binary_tree;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-25  15:26
 * @Version: 1.0
 * @Description: TODO
 */

public class HasPathSum {
    boolean flag = false;

    /**
     * 自己的解法：
     * 思路：
     * 使用递归进行逐层求和进行比较
     * 判断返回true的条件：
     * root.left == null && root.right == null && sum == targetSum
     *
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {

        if (root == null) {
            return flag;
        }
        preOrder(root, targetSum, 0);
        return flag;
    }

    public void preOrder(TreeNode root, int targetSum, int sum) {
        if (root == null) {
            return;
        }
        sum += root.val;
        if (root.left == null && root.right == null && sum == targetSum) {
            flag = true;
        }
        preOrder(root.left, targetSum, sum);
        preOrder(root.right, targetSum, sum);
    }


    /**
     * chatGpt优化的算法
     *
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum01(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        return preOrder01(root, targetSum, 0);
    }

    public boolean preOrder01(TreeNode root, int targetSum, int sum) {
        if (root == null) {
            return false;
        }
        sum += root.val;
        if (root.left == null && root.right == null) { // 检查是否是叶子节点
            return sum == targetSum;
        }
        return preOrder01(root.left, targetSum, sum) || preOrder01(root.right, targetSum, sum);
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
