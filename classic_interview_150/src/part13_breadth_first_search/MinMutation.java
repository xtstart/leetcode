package part13_breadth_first_search;

import java.util.*;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-07-05  09:46
 * @Version: 1.0
 * @Description:
 * No.433 [medium]
 * 【最小基因变化】
 * Q:
 * 基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 'A'、'C'、'G' 和 'T' 之一。
 * <p>
 * 假设我们需要调查从基因序列 start 变为 end 所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。
 * <p>
 * 例如，"AACCGGTT" --> "AACCGGTA" 就是一次基因变化。
 * 另有一个基因库 bank 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。（变化后的基因必须位于基因库 bank 中）
 * <p>
 * 给你两个基因序列 start 和 end ，以及一个基因库 bank ，请你找出并返回能够使 start 变化为 end 所需的最少变化次数。如果无法完成此基因变化，返回 -1 。
 * <p>
 * 注意：起始基因序列 start 默认是有效的，但是它并不一定会出现在基因库中。
 */

public class MinMutation {


    /**
     * 官方解法一：广度优先
     *
     * @param start
     * @param end
     * @param bank
     * @return
     */
    public int minMutation(String start, String end, String[] bank) {
        // 过程的集合
        Set<String> cnt = new HashSet<String>();
        // 访问过的集合
        Set<String> visited = new HashSet<String>();
        char[] keys = {'A', 'C', 'G', 'T'};
        for (String w : bank) {
            cnt.add(w);
        }
        // 起点即使终点返回0
        if (start.equals(end)) {
            return 0;
        }
        // 终点不包含再过程中直接返回不可到达
        if (!cnt.contains(end)) {
            return -1;
        }

        Queue<String> queue = new ArrayDeque<String>();
        queue.offer(start);
        visited.add(start);
        int step = 1;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                String curr = queue.poll();
                for (int j = 0; j < 8; j++) {
                    for (int k = 0; k < 4; k++) {
                        if (keys[k] != curr.charAt(j)) {
                            // 进行替换
                            StringBuffer sb = new StringBuffer(curr);
                            sb.setCharAt(j, keys[k]);
                            String next = sb.toString();

                            if (!visited.contains(next) && cnt.contains(next)) {
                                if (next.equals(end)) {
                                    return step;
                                }
                                queue.offer(next);
                                visited.add(next);
                            }
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }


    /**
     * 官方解法二：预处理优化
     * 将每个基因的合法变化关系存储在邻接表adj中，每次基因变化搜索只在adj中进行即可
     *
     * @param start
     * @param end
     * @param bank
     * @return
     */
    public int minMutation02(String start, String end, String[] bank) {
        int m = start.length();
        int n = bank.length;
        List<Integer>[] adj = new List[n];
        // 1.构建邻接表
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        int endIndex = -1;
        for (int i = 0; i < n; i++) {
            if (end.equals(bank[i])) {
                endIndex = i;
            }
            for (int j = i + 1; j < n; j++) {
                int mutations = 0;
                for (int k = 0; k < m; k++) {
                    if (bank[i].charAt(k) != bank[j].charAt(k)) {
                        mutations++;
                    }
                    if (mutations > 1) {
                        break;
                    }
                }
                if (mutations == 1) {
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }
        if (endIndex == -1) {
            return -1;
        }

        Queue<Integer> queue = new ArrayDeque<Integer>();
        boolean[] visited = new boolean[n];
        int step = 1;
        // 2. 找到和开始基因偏差在一个以内的开始遍历
        for (int i = 0; i < n; i++) {
            int mutations = 0;
            for (int k = 0; k < m; k++) {
                if (start.charAt(k) != bank[i].charAt(k)) {
                    mutations++;
                }
                if (mutations > 1) {
                    break;
                }
            }
            if (mutations == 1) {
                queue.offer(i);
                visited[i] = true;
            }
        }
        // 3.开始广度优先遍历
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                int curr = queue.poll();
                if (curr == endIndex) {
                    return step;
                }
                for (int next : adj[curr]) {
                    if (visited[next]) {
                        continue;
                    }
                    visited[next] = true;
                    queue.offer(next);
                }
            }
            step++;
        }
        return -1;
    }

}
