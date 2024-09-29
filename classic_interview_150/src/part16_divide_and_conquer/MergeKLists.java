package part16_divide_and_conquer;

import org.junit.Test;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-07-25  09:55
 * @Version: 1.0
 * @Description: TODO
 */

public class MergeKLists {

    @Test
    public void test() {
        ListNode[] lists = new ListNode[3];

        ListNode list1 = new ListNode(1);
        ListNode list2 = new ListNode(4);
        ListNode list3 = new ListNode(5);
        list1.next = list2;
        list2.next = list3;

        ListNode list4 = new ListNode(1);
        ListNode list5 = new ListNode(3);
        ListNode list6 = new ListNode(4);
        list4.next = list5;
        list5.next = list6;


        ListNode list7 = new ListNode(2);
        ListNode list8 = new ListNode(6);
        list7.next = list8;

        lists[0] = list1;
        lists[1] = list4;
        lists[2] = list7;
        mergeKLists(lists);
    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode newHead = new ListNode();
        ListNode cur = newHead;
        int len = lists.length;

        while (len > 0) {
            int min = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int i = 0; i < lists.length; i++) {
                if (null != lists[i] && lists[i].val < min) {
                    minIndex = i;
                    min = lists[i].val;
                }
            }
            ListNode next = lists[minIndex].next;
            cur.next = lists[minIndex];

            lists[minIndex].next = null;
            lists[minIndex] = next;
            if (null == next) {
                len--;
            }
            cur = cur.next;
        }
        return newHead.next;
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
