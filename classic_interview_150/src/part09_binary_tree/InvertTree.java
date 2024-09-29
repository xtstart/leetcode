package part09_binary_tree;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-22  23:13
 * @Version: 1.0
 * @Description: TODO
 */

public class InvertTree {

    @Test
    public void test01() {
        ArrayList<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Integer remove = list.remove(0);
        System.out.println(remove);
        Integer remove2 = list.remove(0);
        System.out.println(remove2);
    }

    @Test
    public void test02() {

        TreeNode root = new TreeNode(1);
        TreeNode listNode2 = new TreeNode(2);
        //TreeNode listNode3 = new TreeNode(3);
        root.left = listNode2;
       // root.right = listNode3;

        invertTree(root);
    }


    public TreeNode invertTree(TreeNode root) {
        TreeNode newRoot = new TreeNode(root.val);
        preOrder(root, newRoot);
        return newRoot;
    }

    public void preOrder(TreeNode node, TreeNode newNode) {

        if (node.left != null) {
            TreeNode newRight = new TreeNode(node.left.val);
            newNode.right = newRight;
            preOrder(node.left, newRight);
        }
        if (node.right != null) {
            TreeNode newLeft = new TreeNode(node.right.val);
            newNode.left = newLeft;
            preOrder(node.right, newLeft);
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
