package part12_graph;

import java.util.*;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-30  22:13
 * @Version: 1.0
 * @Description: No.133 [medium]
 * 【克隆图】
 * Q:
 * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
 * <p>
 * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
 * <p>
 * class Node {
 * public int val;
 * public List<Node> neighbors;
 * }
 */

public class CloneGraph {


    public Node cloneGraph(Node node) {
        HashMap<Integer, Node> map = new HashMap<>();
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(node);
        Node head = null;
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            Node newNode = map.getOrDefault(poll.val, new Node(poll.val));
            if (!map.containsKey(poll.val)) {
                map.put(newNode.val, newNode);
            }
            List<Node> newNeighbors = new ArrayList<>();
            for (Node neighbor : poll.neighbors) {
                Node newNeighbor = map.getOrDefault(neighbor.val, new Node(neighbor.val));
                newNeighbors.add(newNeighbor);

                if (!map.containsKey(neighbor.val)) {
                    map.put(neighbor.val, newNeighbor);

                    queue.offer(neighbor);
                }
            }

            newNode.neighbors = newNeighbors;
            if (null == head) {
                head = newNode;
            }
        }

        return head;
    }

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
