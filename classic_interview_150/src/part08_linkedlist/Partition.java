package part08_linkedlist;

import org.junit.Test;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-20  09:50
 * @Version: 1.0
 * @Description: No.86 [medium]
 * [linked-list][two-pointers]
 * 【分隔链表】
 * Q:
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * <p>
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 */

public class Partition {

    @Test
    public void test01() {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(4);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(2);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(2);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;

        partition(listNode1, 3);
    }

    @Test
    public void test02() {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(4);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(0);
        ListNode listNode5 = new ListNode(2);
        ListNode listNode6 = new ListNode(5);
        ListNode listNode7 = new ListNode(2);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = listNode7;

        partition(listNode1, 3);
    }

    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return head;
        }
        // 1.添加一个节点
        ListNode newhead = new ListNode(-1, head);
        // 1. 需要找到第一个大于x的节点
        ListNode pre = newhead;
        while (pre.next != null) {
            if (pre.next.val >= x) {
                break;
            }
            pre = pre.next;
        }
        ListNode cur = pre;
        while (cur.next != null) {
            if (cur.next.val < x) {
                ListNode temp = cur.next;
                cur.next = cur.next.next;
                temp.next = pre.next;
                pre.next = temp;

                pre = temp;
            } else {
                cur = cur.next;
            }
        }
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
