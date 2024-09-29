package part11_binary_search_tree;

import org.junit.Test;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-27  10:17
 * @Version: 1.0
 * @Description: TODO
 */

public class IsValidBST {

    @Test
    public void test01(){
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(1);
        node1.left = node2;
        isValidBST(node1);

    }

    Integer pre = null;


    /**
     * 自己的解法：
     * 利用二叉搜索树的中序遍历是一个递增的区间
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return inOrder(root);
    }

    public boolean inOrder(TreeNode node) {
        if (null == node) {
            return true;
        }
        boolean flag1 = inOrder(node.left);
        if (null != pre) {
            if (node.val <= pre) {
                return false;
            }
        }
        pre = node.val;
        boolean flag2 = inOrder(node.right);
        return flag1 && flag2;
    }
}
