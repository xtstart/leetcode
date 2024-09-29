package part11_binary_search_tree;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-27  10:17
 * @Version: 1.0
 * @Description: 二叉搜索树算法使用的节点
 */

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
