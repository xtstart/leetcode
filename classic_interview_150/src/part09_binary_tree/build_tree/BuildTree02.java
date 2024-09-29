package part09_binary_tree.build_tree;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-24  14:44
 * @Version: 1.0
 * @Description: TODO
 */

public class BuildTree02 {

    @Test
    public void test() {
        int[] inorder ={9,3,15,20,7};
        int[] postorder ={9,15,7,20,3};

        buildTree(inorder, postorder);
    }

    Map<Integer, Integer> map;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;
        map = new HashMap<Integer, Integer>(n);
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        return doBuildTree(inorder, postorder, 0, n - 1, 0, n - 1);
    }

    public TreeNode doBuildTree(int[] inorder, int[] postorder, int inLeft, int inRight, int postLeft, int postRight) {
        if (inLeft > inRight) {
            return null;
        }
        int rootVal = postorder[postRight];
        int rootIndex = map.get(rootVal);
        int leftSize = rootIndex - inLeft - 1;
        TreeNode root = new TreeNode(rootVal);
        root.left = doBuildTree(inorder, postorder, inLeft, rootIndex - 1, postLeft, postLeft + leftSize);
        root.right = doBuildTree(inorder, postorder, rootIndex + 1, inRight, postLeft + leftSize +1, postRight - 1);
        return root;
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
