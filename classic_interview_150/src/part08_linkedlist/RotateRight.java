package part08_linkedlist;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-20  09:17
 * @Version: 1.0
 * @Description: No.61 [medium]
 * [linked-list][two-pointers]
 * 【旋转链表】
 * Q:
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 */

public class RotateRight {

    /**
     * 自己的解法
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (null == head || null == head.next || k == 0) {
            return head;
        }

        ListNode newHead = head;
        ListNode rear = new ListNode(-1, head);
        int len = 1;
        while (newHead.next != null) {
            newHead = newHead.next;
            rear = rear.next;
            len++;
        }
        newHead.next = head;

        int n = (len - k % len + 1);
        for (int i = 0; i < n; i++) {
            newHead = newHead.next;
            rear = rear.next;
        }
        rear.next = null;
        return newHead;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
