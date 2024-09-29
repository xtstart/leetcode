package part08_linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-18  09:12
 * @Version: 1.0
 * @Description:
 * No.138 [medium]
 * 【随机链表的复制】
 * Q:
 * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
 *
 * 构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。
 *
 * 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
 *
 * 返回复制链表的头节点。
 *
 * 用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
 *
 * val：一个表示 Node.val 的整数。
 * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
 * 你的代码 只 接受原链表的头节点 head 作为传入参数。
 */

public class CopyRandomList {


    /**
     * 自己的解法
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<Node, Node>();
        Node curNode = head;
        Node newHead = new Node(-1);
        Node preNode = null;
        while (null != curNode) {
            Node newNode = new Node(curNode.val);
            if (preNode == null) {
                preNode = newHead;
            }

            preNode.next = newNode;
            newNode.random = curNode.random;

            map.put(curNode, newNode);
            curNode = curNode.next;
            preNode = newNode;
        }

        curNode = newHead.next;
        while (null != curNode) {
            curNode.random = map.getOrDefault(curNode.random, null);
            curNode = curNode.next;
        }
        return newHead.next;
    }



    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
