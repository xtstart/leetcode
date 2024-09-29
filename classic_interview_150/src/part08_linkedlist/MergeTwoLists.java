package part08_linkedlist;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-17  10:02
 * @Version: 1.0
 * @Description:
 * No.21 [easy]
 * 【合并两个有序链表】
 * Q:
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 */

public class MergeTwoLists {


    /**
     * 自己的解法
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode();
        ListNode curNode = head;
        while (null != list1 && null != list2) {
            int num1 = list1.val;
            int num2 = list2.val;
            if (num1 > num2) {
                curNode.next = new ListNode(num2);
                list2 = list2.next;
            } else {
                curNode.next = new ListNode(num1);
                list1 = list1.next;
            }
            curNode = curNode.next;
        }
        if (null != list1) {
            curNode.next = list1;
        }
        if (null != list2) {
            curNode.next = list2;
        }
        return head.next;
    }


    /**
     * 官方解法一：使用递归
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists02(ListNode list1, ListNode list2) {
        if (null == list1) {
            return list2;
        } else if (null == list2) {
            return list1;
        } else if (list1.val > list2.val) {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        } else {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        }
    }

    /**
     * 官方解法二：迭代
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists03(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
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
