package part08_linkedlist;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-19  11:02
 * @Version: 1.0
 * @Description:
 * No.19 [medium]
 * [linked-list][two-pointers]
 * 【删除链表的倒数第N个节点】
 * Q: 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 */

public class RemoveNthFromEnd {


    /**
     * 自己得解法：
     * 思路：
     * 首先遍历一遍，记录链表的长度
     * 只用链表的长度，减去n，得到要删除的元素的位置，
     * 再遍历链表进行删除操作
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int len = 0;
        ListNode cur = head;
        while (null != cur) {
            cur = cur.next;
            len++;
        }

        ListNode newhead = new ListNode();
        newhead.next = head;
        ListNode pre = newhead;
        pre.next = head;
        cur = head;
        for (int i = 1; i <= len - n; i++) {
            pre = pre.next;
            cur = cur.next;
        }
        pre.next = cur.next;
        return newhead.next;
    }

    /**
     * 使用栈的方式
     * 将遍历过的链表元素压入栈中，然后再根据倒数第几个进行出栈
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd02(ListNode head, int n) {
        ListNode newhead = new ListNode(-1,head);
        ListNode cur = newhead;
        Deque<ListNode> stack = new LinkedList<>();
        while (null != cur) {
            stack.push(cur);
            cur = cur.next;
        }

        for (int i = 0; i < n; i++) {
            stack.pop();
        }
        ListNode pre = stack.peek();
        pre.next = pre.next.next;
        return newhead.next;
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
