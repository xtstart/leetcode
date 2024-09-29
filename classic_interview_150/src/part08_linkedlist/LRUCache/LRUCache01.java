package part08_linkedlist.LRUCache;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-20  11:05
 * @Version: 1.0
 * @Description:
 * No.146 [medium]
 * 【LRU缓存】
 * Q:
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * - LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 * - int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * - void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 */

public class LRUCache01 {

    int capacity;
    int count;

    DLinkedNode head;
    DLinkedNode tail;

    Map<Integer, DLinkedNode> map;



    public LRUCache01(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.count = 0;

        head = new DLinkedNode(-1);
        tail = new DLinkedNode(-1);

        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        // 将cur从原来的链表中断开
        DLinkedNode cur = removeNode(map.get(key));

        // 插入队尾
        insertToTail(cur);

        return cur.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            DLinkedNode cur = map.get(key);
            cur.value = value;
            insertToTail(removeNode(cur));
        } else {
            DLinkedNode cur = new DLinkedNode(key, value);
            insertToTail(cur);
            if (count >= capacity) {
                map.remove(head.next.key);
                removeNode(head.next);
            }
            map.put(key, cur);
            count++;
        }
    }

    public DLinkedNode removeNode(DLinkedNode cur) {
        cur.pre.next = cur.next;
        cur.next.pre = cur.pre;
        return cur;
    }

    public void insertToTail(DLinkedNode cur) {
        tail.pre.next = cur;
        cur.pre = tail.pre;
        cur.next = tail;
        tail.pre = cur;
    }

    public class DLinkedNode {

        int key;
        int value;
        DLinkedNode pre;
        DLinkedNode next;

        public DLinkedNode(int key) {
            this.key = key;
        }

        public DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }


    public static void main(String[] args) {
        LRUCache01 lruCache01 = new LRUCache01(2);
        lruCache01.put(1,0);
        lruCache01.put(2,2);
        lruCache01.get(1);
        lruCache01.put(3,3);
        lruCache01.get(2);
        lruCache01.put(4,4);
        lruCache01.get(1);
        lruCache01.get(3);
        lruCache01.get(4);

    }
}
