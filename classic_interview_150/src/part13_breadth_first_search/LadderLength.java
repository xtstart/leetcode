package part13_breadth_first_search;

import java.util.*;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-07-09  10:14
 * @Version: 1.0
 * @Description:
 * No.127 [hard]
 *
 * 字典 wordList 中从单词 beginWord 到 endWord 的 转换序列 是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk：
 *
 * 每一对相邻的单词只差一个字母。
 *  对于 1 <= i <= k 时，每个 si 都在 wordList 中。注意， beginWord 不需要在 wordList 中。
 * sk == endWord
 * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，返回 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0 。
 */

public class LadderLength {


    /**
     * 自己的解法
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int m = beginWord.length();
        int n = wordList.size();
        List<Integer>[] adj = new List[n];
        boolean[] visited = new boolean[n];
        if (beginWord.equals(endWord)) {
            return 0;
        }
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        int endIndex = -1;
        for (int i = 0; i < n; i++) {
            if (endWord.equals(wordList.get(i))) {
                endIndex = i;
            }
            for (int j = i + 1; j < n; j++) {
                int diff = 0;
                for (int k = 0; k < m; k++) {
                    if (wordList.get(i).charAt(k) != wordList.get(j).charAt(k)) {
                        diff++;
                    }
                    if (diff > 1) {
                        break;
                    }
                }
                if (diff == 1) {
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }
        if (endIndex == -1) {
            return 0;
        }

        Queue<Integer> queue = new ArrayDeque<Integer>();

        for (int i = 0; i < n; i++) {
            int diff = 0;
            for (int j = 0; j < m; j++) {

                if (beginWord.charAt(j) != wordList.get(i).charAt(j)) {
                    diff++;
                }
                if (diff > 1) {
                    break;
                }

            }
            if (diff == 1) {
                queue.offer(i);
                visited[i] = true;
            }
        }

        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                if (cur == endIndex) {
                    return step+1;
                }
                for (int next : adj[cur]) {
                    if (!visited[next]) {
                        queue.offer(next);
                        visited[next] = true;
                    }
                }
            }
            step++;
        }
        return 0;
    }


    /**
     * 官方解法一：广度优先搜索+优化建图
     */

    /**
     * 维护节点对应的编号
     * key: word
     * value: 编号id
     */
    Map<String, Integer> wordId = new HashMap<String, Integer>();
    /**
     * 邻接表
     */
    List<List<Integer>> edge = new ArrayList<List<Integer>>();
    /**
     * 维护节点总数
     */
    int nodeNum = 0;

    public int ladderLength02(String beginWord, String endWord, List<String> wordList) {
        for (String word : wordList) {
            addEdge(word);
        }
        addEdge(beginWord);
        if (!wordId.containsKey(endWord)) {
            return 0;
        }
        int[] dis = new int[nodeNum];
        Arrays.fill(dis, Integer.MAX_VALUE);
        int beginId = wordId.get(beginWord), endId = wordId.get(endWord);
        dis[beginId] = 0;

        Queue<Integer> que = new LinkedList<Integer>();
        que.offer(beginId);
        while (!que.isEmpty()) {
            int x = que.poll();
            if (x == endId) {
                return dis[endId] / 2 + 1;
            }
            for (int it : edge.get(x)) {
                if (dis[it] == Integer.MAX_VALUE) {
                    dis[it] = dis[x] + 1;
                    que.offer(it);
                }
            }
        }
        return 0;
    }

    /**
     *
     * 将word放入map中，初始化邻接表，创建虚拟节点虚拟节点也放入map中
     * @param word
     */
    public void addEdge(String word) {
        addWord(word);
        int id1 = wordId.get(word);
        char[] array = word.toCharArray();
        int length = array.length;
        // 创建虚拟节点并放入map中
        for (int i = 0; i < length; ++i) {
            char tmp = array[i];
            array[i] = '*';
            String newWord = new String(array);
            addWord(newWord);
            int id2 = wordId.get(newWord);
            edge.get(id1).add(id2);
            edge.get(id2).add(id1);
            array[i] = tmp;
        }
    }

    /**
     * 将word编号加入map中，并初始化edge的边
     * @param word
     */
    public void addWord(String word) {
        if (!wordId.containsKey(word)) {
            wordId.put(word, nodeNum++);
            edge.add(new ArrayList<Integer>());
        }
    }


    /*
     * 官方解法二：
     * 双向广度优先搜索
     * */

    class Solution {

        Map<String, Integer> wordId = new HashMap<String, Integer>();
        List<List<Integer>> edge = new ArrayList<List<Integer>>();
        int nodeNum = 0;

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            for (String word : wordList) {
                addEdge(word);
            }
            addEdge(beginWord);
            if (!wordId.containsKey(endWord)) {
                return 0;
            }

            int[] disBegin = new int[nodeNum];
            Arrays.fill(disBegin, Integer.MAX_VALUE);
            int beginId = wordId.get(beginWord);
            disBegin[beginId] = 0;
            Queue<Integer> queBegin = new LinkedList<Integer>();
            queBegin.offer(beginId);

            int[] disEnd = new int[nodeNum];
            Arrays.fill(disEnd, Integer.MAX_VALUE);
            int endId = wordId.get(endWord);
            disEnd[endId] = 0;
            Queue<Integer> queEnd = new LinkedList<Integer>();
            queEnd.offer(endId);

            while (!queBegin.isEmpty() && !queEnd.isEmpty()) {
                int queBeginSize = queBegin.size();
                for (int i = 0; i < queBeginSize; ++i) {
                    int nodeBegin = queBegin.poll();
                    if (disEnd[nodeBegin] != Integer.MAX_VALUE) {
                        return (disBegin[nodeBegin] + disEnd[nodeBegin]) / 2 + 1;
                    }
                    for (int it : edge.get(nodeBegin)) {
                        if (disBegin[it] == Integer.MAX_VALUE) {
                            disBegin[it] = disBegin[nodeBegin] + 1;
                            queBegin.offer(it);
                        }
                    }
                }

                int queEndSize = queEnd.size();
                for (int i = 0; i < queEndSize; ++i) {
                    int nodeEnd = queEnd.poll();
                    if (disBegin[nodeEnd] != Integer.MAX_VALUE) {
                        return (disBegin[nodeEnd] + disEnd[nodeEnd]) / 2 + 1;
                    }
                    for (int it : edge.get(nodeEnd)) {
                        if (disEnd[it] == Integer.MAX_VALUE) {
                            disEnd[it] = disEnd[nodeEnd] + 1;
                            queEnd.offer(it);
                        }
                    }
                }
            }
            return 0;
        }

        public void addEdge(String word) {
            addWord(word);
            int id1 = wordId.get(word);
            char[] array = word.toCharArray();
            int length = array.length;
            for (int i = 0; i < length; ++i) {
                char tmp = array[i];
                array[i] = '*';
                String newWord = new String(array);
                addWord(newWord);
                int id2 = wordId.get(newWord);
                edge.get(id1).add(id2);
                edge.get(id2).add(id1);
                array[i] = tmp;
            }
        }

        public void addWord(String word) {
            if (!wordId.containsKey(word)) {
                wordId.put(word, nodeNum++);
                edge.add(new ArrayList<Integer>());
            }
        }

    }

}
