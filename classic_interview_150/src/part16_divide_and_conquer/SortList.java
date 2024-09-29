package part16_divide_and_conquer;

import org.junit.Test;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-07-23  10:36
 * @Version: 1.0
 * @Description: TODO
 */

public class SortList {

    @Test
    public void test01(){
        Solution solution = new Solution();
        ListNode head= new ListNode(1);
        ListNode node1= new ListNode(2);
        ListNode node2= new ListNode(3);
        head.next = node1;
        node1.next = node2;
        ListNode listNode = solution.sortList(head);


    }

    /**
     * 方法一：自顶向下归并排序
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(logn)
     */
    class Solution {
        public ListNode sortList(ListNode head) {
            return sortList(head, null);
        }

        public ListNode sortList(ListNode head, ListNode tail) {
            if (head == null) {
                return head;
            }
            if (head.next == tail) {
                // 断开连接
                head.next = null;
                return head;
            }
            ListNode slow = head, fast = head;
            while (fast != tail) {
                slow = slow.next;
                fast = fast.next;
                if (fast != tail) {
                    fast = fast.next;
                }
            }
            ListNode mid = slow;
            ListNode list1 = sortList(head, mid);
            ListNode list2 = sortList(mid, tail);
            ListNode sorted = merge(list1, list2);
            return sorted;
        }

        /**
         * 对 head1 -> head2 之间的节点进行排序
         * @param head1
         * @param head2
         * @return
         */
        public ListNode merge(ListNode head1, ListNode head2) {
            ListNode dummyHead = new ListNode(0);
            ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
            while (temp1 != null && temp2 != null) {
                if (temp1.val <= temp2.val) {
                    temp.next = temp1;
                    temp1 = temp1.next;
                } else {
                    temp.next = temp2;
                    temp2 = temp2.next;
                }
                temp = temp.next;
            }
            if (temp1 != null) {
                temp.next = temp1;
            } else if (temp2 != null) {
                temp.next = temp2;
            }
            return dummyHead.next;
        }
    }


    /**
     * 分治算法：自底向上
     */
    class Solution02 {
        public ListNode sortList(ListNode head) {
            if (head == null) {
                return head;
            }
            int length = 0;
            ListNode node = head;
            while (node != null) {
                length++;
                node = node.next;
            }
            ListNode dummyHead = new ListNode(0, head);
            for (int subLength = 1; subLength < length; subLength <<= 1) {
                ListNode prev = dummyHead, curr = dummyHead.next;
                while (curr != null) {
                    ListNode head1 = curr;
                    for (int i = 1; i < subLength && curr.next != null; i++) {
                        curr = curr.next;
                    }
                    ListNode head2 = curr.next;
                    curr.next = null;
                    curr = head2;
                    for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
                        curr = curr.next;
                    }
                    ListNode next = null;
                    if (curr != null) {
                        next = curr.next;
                        curr.next = null;
                    }
                    ListNode merged = merge(head1, head2);
                    prev.next = merged;
                    while (prev.next != null) {
                        prev = prev.next;
                    }
                    curr = next;
                }
            }
            return dummyHead.next;
        }

        public ListNode merge(ListNode head1, ListNode head2) {
            ListNode dummyHead = new ListNode(0);
            ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
            while (temp1 != null && temp2 != null) {
                if (temp1.val <= temp2.val) {
                    temp.next = temp1;
                    temp1 = temp1.next;
                } else {
                    temp.next = temp2;
                    temp2 = temp2.next;
                }
                temp = temp.next;
            }
            if (temp1 != null) {
                temp.next = temp1;
            } else if (temp2 != null) {
                temp.next = temp2;
            }
            return dummyHead.next;
        }
    }



    // Definition for singly-linked list.
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

    @Test
    public void test002(){
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(5);
        ListNode node2 = new ListNode(8);
        ListNode node3 = new ListNode(7);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(2);
        ListNode node6 = new ListNode(9);
        ListNode node7 = new ListNode(4);
        ListNode node8 = new ListNode(6);
        head.next = node1;
        node1.next=node2;
        node2.next=node3;
        node4.next=node5;
        node5.next=node6;
        node6.next=node2;
        node7.next=node8;

        sortList(head);
    }

    public ListNode sortList(ListNode head) {
        if (head == null) {
            return head;
        }
        int length = getLength(head);
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode[] split = new ListNode[2];
        for (int size = 1; size < length; size <<= 1) {
            ListNode prev = dummyHead;
            ListNode curr = dummyHead.next;
            while (curr != null) {
                split[0] = curr;
                split[1] = splitList(split[0], size);
                curr = splitList(split[1], size);
                prev = merge(split, prev);
            }
        }
        return dummyHead.next;
    }

    /**
     *
     * @param head
     * @return
     */
    private int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }

    /**
     * 按当前得size大小将链表分割成两部分
     * @param head
     * @param size 从head之后第几个开始分割
     * @return 返回分割后链表的头节点
     */
    private ListNode splitList(ListNode head, int size) {
        for (int i = 1; head != null && i < size; i++) {
            head = head.next;
        }
        if (head == null) {
            return null;
        }
        ListNode next = head.next;
        head.next = null;
        return next;
    }


    /**
     * 将两部分有序链表合并，并将合并结果接到前一部分链表的末尾
     * @param split
     * @param prev
     * @return 返回合并后链表的最后一个节点。
     */
    private ListNode merge(ListNode[] split, ListNode prev) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead;
        ListNode l1 = split[0], l2 = split[1];
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        temp.next = (l1 != null) ? l1 : l2;
        while (temp.next != null) {
            temp = temp.next;
        }
        prev.next = dummyHead.next;
        return temp;
    }

}
