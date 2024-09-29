package part08_linkedlist;

import org.junit.Test;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-19  10:27
 * @Version: 1.0
 * @Description:
 * No.25 [hard]
 * [linked-list]
 * 【k个一组翻转链表】
 * Q:
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 */

public class ReverseKGroup {

    @Test
    public void test01(){
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;

        reverseKGroup(listNode1,2);
    }


    public ListNode reverseKGroup(ListNode head, int k) {
        int len = 0;
        ListNode curNode = head;
        while (null != curNode) {
            curNode = curNode.next;
            len++;
        }
        int times = len / k;

        ListNode newHead = new ListNode(-1);
        newHead.next = head;
        ListNode temp = newHead;

        for (int i = 0; i < times; i++) {
            reverseRange(temp, k);
            for (int j = 0; j < k; j++) {
                temp = temp.next;
            }
        }
        return newHead.next;
    }

    public void reverseRange(ListNode head, int k) {
        ListNode cur = head.next;
        for (int i = 1; i < k; i++) {
            ListNode temp = cur.next;
            cur.next = temp.next;
            temp.next = head.next;
            head.next = temp;
        }
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
