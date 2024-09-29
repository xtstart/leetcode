package part08_linkedlist;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-17  09:41
 * @Version: 1.0
 * @Description:
 * No.2 [medium]
 * 【两数之和】
 * Q:
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 */

public class AddTwoNumbers {

    /**
     * 自己的解法
     * 思路：
     * 1.遍历l1,l2 取出节点相加
     * 2.使用carry记录进位的值，并在高位的加法上相加
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode curNode = head;
        int carry = 0;

        while (null != l1 || null != l2 || carry != 0) {
            int l1Value = l1 == null ? 0 : l1.val;
            int l2Value = l2 == null ? 0 : l2.val;

            int sum = l1Value + l2Value + carry;
            if (sum >= 10) {
                carry = 1;
                sum = sum % 10;
            } else {
                carry = 0;
            }
            ListNode newNode = new ListNode(sum);
            curNode.next = newNode;
            curNode = curNode.next;
            if (null != l1) {
                l1 = l1.next;
            }
            if (null != l2) {
                l2 = l2.next;
            }
        }

        return head.next;
    }


    /**
     *
     * 使用chatgpt优化算法：
     * 1.使用哨兵节点（dummy node）来简化链表的初始化和边界情况处理。
     * 2.将计算 sum 和处理进位 carry 的逻辑合并在一起，简化代码结构。
     * 3.在计算 sum 的同时直接进行进位和求余数操作，减少了代码行数。
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers02(ListNode l1, ListNode l2) {
        // 创建一个哨兵节点来简化代码
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
        }

        return dummy.next;
    }


    /**
     * 官方解法：模拟
     * 思路一样
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers03(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
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
