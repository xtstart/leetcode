package part08_linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-16  12:23
 * @Version: 1.0
 * @Description:
 * N0.141 [easy]
 * 【环形链表】
 * Q:
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 * <p>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
 * <p>
 * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
 */

public class HasCycle {

    /**
     * 我的解法，使用快慢指针
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        ListNode fastNode = head;
        ListNode slowNode = head;
        while (fastNode != null) {
            fastNode = fastNode.next;
            if (fastNode == null) {
                return false;
            }
            fastNode = fastNode.next;
            slowNode = slowNode.next;
            if (fastNode == slowNode) {
                return true;
            }
        }
        return false;
    }


    /**
     * 官方解法一：哈希表
     * @param head
     * @return
     */
    public boolean hasCycle02(ListNode head) {
        Set<ListNode> seen = new HashSet<>();
        while (null != head) {
            if (!seen.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    /**
     * chatgpt优化
     * 主要有三点优化：
     * 1.初始检查: 在开始的时候检查链表是否为空或者只有一个节点，如果是的话直接返回false，因为一个节点不可能形成环。
     * 2.指针初始化: fast指针从head.next开始，这样在循环内部可以避免额外的null检查。
     * 3.简化条件: 在while循环中合并了null检查和比较操作，使代码更简洁。
     * @param head
     * @return
     */
    public boolean hasCycle03(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        return true;
    }


    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}


