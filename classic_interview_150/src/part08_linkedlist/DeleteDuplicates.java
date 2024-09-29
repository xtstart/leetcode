package part08_linkedlist;

import org.junit.Test;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-19  15:05
 * @Version: 1.0
 * @Description:
 * No.82 [medium]
 * [linked-list][two-pointers]
 * 【删除排序链表中的重复元素II】
 * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
 *
 * 输入：head = [1,2,3,3,4,4,5]
 * 输出：[1,2,5]
 */

public class DeleteDuplicates {

    @Test
    public void test01(){

        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(3);
        ListNode listNode5 = new ListNode(4);
        ListNode listNode6 = new ListNode(4);
        ListNode listNode7 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = listNode7;

        //deleteDuplicates(listNode1);
    }

    /**
     * 自己的实现
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode newHead = new ListNode(-1, head);
        ListNode pre = newHead;
        ListNode cur = head;
        while (cur != null) {
            if (cur.next == null) {
                pre.next = cur;
            } else {
                if (cur.val != cur.next.val) {
                    pre.next = cur;
                    pre = cur;
                } else {
                    while (null != cur.next &&cur.val == cur.next.val) {
                        cur = cur.next;
                    }
                    pre.next = null;
                }
            }
            cur = cur.next;
        }
        return newHead.next;
    }


    /**
     * chatgpt优化代码
     * 1.减少了冗余判断：原代码中有几处对cur.next是否为空的判断是重复的，优化后的代码减少了这些冗余判断。
     * 2.逻辑简化：通过引入一个duplicate标志来简化逻辑，使代码更容易理解和维护。
     * 3.处理方式更直接：在遇到重复元素时直接跳过，而不是先跳过后再设为null。
     * @param head
     * @return
     */
    public ListNode deleteDuplicates02(ListNode head) {
        if (head == null) return null;

        ListNode newHead = new ListNode(-1, head);
        ListNode pre = newHead;
        ListNode cur = head;

        while (cur != null) {
            boolean duplicate = false;

            // 检查当前节点是否有重复
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
                duplicate = true;
            }

            // 如果没有重复，pre的next指向当前节点
            if (!duplicate) {
                pre.next = cur;
                pre = pre.next;
            }
            // 如果有重复，pre的next指向cur的下一个节点
            else {
                pre.next = cur.next;
            }

            // 移动到下一个节点
            cur = cur.next;
        }

        return newHead.next;
    }


    /**
     * 官方解法：
     * 
     * @param head
     * @return
     */
    public ListNode deleteDuplicates03(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(0, head);

        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int x = cur.next.val;
                while (cur.next != null && cur.next.val == x) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }

        return dummy.next;
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
