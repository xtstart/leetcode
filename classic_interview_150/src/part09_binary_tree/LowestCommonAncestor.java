package part09_binary_tree;

import org.junit.Test;

import java.util.*;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-26  09:57
 * @Version: 1.0
 * @Description: TODO
 */

public class LowestCommonAncestor {


    @Test
    public void test(){
        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(5);
        TreeNode treeNode3 = new TreeNode(1);
        TreeNode treeNode4 = new TreeNode(6);
        TreeNode treeNode5 = new TreeNode(2);
        TreeNode treeNode6 = new TreeNode(0);
        TreeNode treeNode7 = new TreeNode(8);
        TreeNode treeNode8 = new TreeNode(7);
        TreeNode treeNode9 = new TreeNode(4);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;

        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;

        treeNode3.left =treeNode6;
        treeNode3.right =treeNode7;

        treeNode5.left = treeNode8;
        treeNode5.right = treeNode9;

        lowestCommonAncestor(treeNode1, treeNode2, treeNode3);
    }


    /**
     * 自己的解法：
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> ps = new Stack<>();
        Stack<TreeNode> qs = new Stack<>();
        preOrder(root, new Stack<>(), p.val, ps);
        preOrder(root, new Stack<>(), q.val, qs);

        // 确定哪个栈的大小较大
        Stack<TreeNode> max = ps.size() > qs.size() ? ps : qs;
        Stack<TreeNode> min = ps.size() <= qs.size() ? ps : qs;

        // 使用 Set 来存储较长路径中的节点
        Set<TreeNode> set = new HashSet<>(max);

        // 查找第一个在较短路径中也存在的节点
        while (!min.isEmpty()) {
            TreeNode pop = min.pop();
            if (set.contains(pop)) {
                return pop;
            }
        }
        return null;
    }

    public void preOrder(TreeNode node, Stack<TreeNode> path, int targetVal, Stack<TreeNode> targetPath) {
        if (node == null) {
            return;
        }
        path.push(node);
        if (node.val == targetVal) {
            targetPath.addAll(path);
            return;
        }
        preOrder(node.left, path, targetVal, targetPath);
        preOrder(node.right, path, targetVal, targetPath);
        path.pop(); // 回溯
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
