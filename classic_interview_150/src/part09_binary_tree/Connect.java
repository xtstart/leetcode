package part09_binary_tree;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-24  14:57
 * @Version: 1.0
 * @Description: TODO
 */

public class Connect {

    @Test
    public void test() {
        //[1,2,3,4,5,null,7]
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(7);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;

        connect(node1);


    }


    /**
     * 官方解法：层次遍历
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new ArrayDeque<Node>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            Node last = null;
            for (int i = 1; i <= n; ++i) {
                Node f = queue.poll();
                if (f.left != null) {
                    queue.offer(f.left);
                }
                if (f.right != null) {
                    queue.offer(f.right);
                }
                if (i != 1) {
                    last.next = f;
                }
                last = f;
            }
        }
        return root;
    }


    /**
     * chatgpt
     *
     * @param root
     * @return
     */
    public Node connect02(Node root) {
        if (root == null) {
            return null;
        }

        Node head = root; // 当前层的头节点

        // 循环遍历每一层，从上至下
        while (head != null) {
            Node dummy = new Node(0); //下一层的虚拟头节点
            Node temp = dummy; //当前处理的节点

            // 遍历当前层，连接下一层的节点
            for (Node cur = head; cur != null; cur = cur.next) {
                if (cur.left != null) {
                    temp.next = cur.left;
                    temp = temp.next; //移动temp
                }
                if (cur.right != null) {
                    temp.next = cur.right;
                    temp = temp.next; //移动temp
                }
            }
            // 移动到下一层的实际头节点处
            head = dummy.next;
        }

        return root;
    }


    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    ;
}
