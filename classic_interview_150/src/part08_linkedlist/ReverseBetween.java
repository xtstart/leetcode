package part08_linkedlist;

import org.junit.Test;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-17  11:18
 * @Version: 1.0
 * @Description:
 * No.92 [medium]
 * 【反转链表】
 * Q:
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。
 * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 */

public class ReverseBetween {

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
        reverseBetween(listNode1, 2, 4);
    }


    @Test
    public void test02(){
        ListNode listNode1 = new ListNode(3);
        ListNode listNode2 = new ListNode(5);

        listNode1.next = listNode2;
        reverseBetween(listNode1, 1, 2);
    }


    /**
     * 自己的解法：
     * 使用头插法
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode newHead = new ListNode(-1);
        newHead.next = head;

        ListNode cur = newHead;
        for (int i = 1; i <left; i++) {
            cur = cur.next;
        }
        ListNode pre = cur;
        cur = cur.next;

        while ( left < right) {
            ListNode temp = cur.next;
            cur.next = temp.next;
            temp.next = pre.next;
            pre.next = temp;

            left++;
        }
        return newHead.next;
    }

    /**
     * chatgpt优化：
     * 1.使用哑节点（dummy node）： 这样可以简化边界条件的处理，特别是在 left 是 1 的时候，不需要特别处理。
     * 2.清晰的命名： 使用 dummy、pre 和 current 等更具语义的变量名，增强代码的可读性。
     * 3.循环逻辑： 在反转的过程中，每次将当前节点的下一个节点 temp 插入到 pre 后面，同时更新 current.next 指向下一个未反转的节点。这样可以有效地减少代码的复杂度。
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween02(ListNode head, int left, int right) {
        // 创建一个新的头节点，并指向原链表头
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // 找到需要反转的起点的前一个节点
        ListNode pre = dummy;
        for (int i = 1; i < left; i++) {
            pre = pre.next;
        }

        // 开始反转区间内的节点
        ListNode current = pre.next;
        for (int i = 0; i < right - left; i++) {
            ListNode temp = current.next;
            current.next = temp.next;
            temp.next = pre.next;
            pre.next = temp;
        }

        return dummy.next;
    }


    /**
     * [官网解法]
     * 直接定位再反转
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween03(ListNode head, int left, int right) {
        // 因为头节点有可能发生变化，使用虚拟头节点可以避免复杂的分类讨论
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode pre = dummyNode;
        // 第 1 步：从虚拟头节点走 left - 1 步，来到 left 节点的前一个节点
        // 建议写在 for 循环里，语义清晰
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        // 第 2 步：从 pre 再走 right - left + 1 步，来到 right 节点
        ListNode rightNode = pre;
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }

        // 第 3 步：切断出一个子链表（截取链表）
        ListNode leftNode = pre.next;
        ListNode curr = rightNode.next;

        // 注意：切断链接
        pre.next = null;
        rightNode.next = null;

        // 第 4 步：同第 206 题，反转链表的子区间
        reverseLinkedList(leftNode);

        // 第 5 步：接回到原来的链表中
        pre.next = rightNode;
        leftNode.next = curr;
        return dummyNode.next;
    }

    private void reverseLinkedList(ListNode head) {
        // 也可以使用递归反转一个链表
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
    }


    class ListNode {
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
