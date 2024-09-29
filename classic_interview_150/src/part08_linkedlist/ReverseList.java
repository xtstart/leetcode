package part08_linkedlist;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-17  16:00
 * @Version: 1.0
 * @Description:
 * No.206 [easy]
 * [linked-list] [recursion]
 * 【反转链表】
 * Q:
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 */

public class ReverseList {

    /**
     * 使用遍历的方式
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (null != cur) {
            ListNode next = cur.next;
            cur.next = pre;

            pre = cur;
            cur = next;
        }
        return pre;
    }


    /**
     * 使用递归的方式
     *
     * @param head
     * @return
     */
    public ListNode reverseList02(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }

        ListNode newHead = reverseList02(head.next);
        head.next.next = head;
        head.next = null;

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
