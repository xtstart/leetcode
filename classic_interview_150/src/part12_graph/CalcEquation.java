package part12_graph;

import org.junit.Test;

import java.util.*;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-07-01  09:23
 * @Version: 1.0
 * @Description: No.399 [medium]
 * 【除法求值】
 * Q:
 * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。
 * <p>
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
 * <p>
 * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。
 * <p>
 * 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
 * <p>
 * 注意：未在等式列表中出现的变量是未定义的，因此无法确定它们的答案。
 */

public class CalcEquation {

    @Test
    public void test01() {
        String[][] equations = {{"a", "b"}, {"b", "c"}, {"bc", "cd"}};
        List<List<String>> equatonList = new ArrayList<>();
        for (String[] equation : equations) {
            equatonList.add(Arrays.asList(equation));
        }
        double[] values = {1.5, 2.5, 5.0};
        String[][] queries = {{"a", "c"}, {"c", "b"}, {"bc", "cd"}, {"cd", "bc"}};
        List<List<String>> queryList = new ArrayList<>();
        for (String[] query : queries) {
            equatonList.add(Arrays.asList(query));
        }


        calcEquation(equatonList, values, queryList);
    }


    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int equationsSize = equations.size();

        UnionFind unionFind = new UnionFind(2 * equationsSize);
        // 第 1 步：预处理，将变量的值与 id 进行映射，使得并查集的底层使用数组实现，方便编码
        Map<String, Integer> hashMap = new HashMap<>(2 * equationsSize);
        int id = 0;
        for (int i = 0; i < equationsSize; i++) {
            List<String> equation = equations.get(i);
            String var1 = equation.get(0);
            String var2 = equation.get(1);

            if (!hashMap.containsKey(var1)) {
                hashMap.put(var1, id);
                id++;
            }
            if (!hashMap.containsKey(var2)) {
                hashMap.put(var2, id);
                id++;
            }
            unionFind.union(hashMap.get(var1), hashMap.get(var2), values[i]);
        }

        // 第 2 步：做查询
        int queriesSize = queries.size();
        double[] res = new double[queriesSize];
        for (int i = 0; i < queriesSize; i++) {
            String var1 = queries.get(i).get(0);
            String var2 = queries.get(i).get(1);

            Integer id1 = hashMap.get(var1);
            Integer id2 = hashMap.get(var2);

            if (id1 == null || id2 == null) {
                res[i] = -1.0d;
            } else {
                res[i] = unionFind.isConnected(id1, id2);
            }
        }
        return res;
    }

    private class UnionFind {

        private int[] parent;

        /**
         * 指向的父结点的权值
         */
        private double[] weight;


        public UnionFind(int n) {
            this.parent = new int[n];
            this.weight = new double[n];
            // 创建一个大小为'n'得数组，每个节点指向自己，初始权值为1
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                weight[i] = 1.0d;
            }
        }

        /**
         * 合并两个节点并更新权值关系
         *
         * @param x
         * @param y
         * @param value
         */
        public void union(int x, int y, double value) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            /**
             * 合并两个树
             */
            parent[rootX] = rootY;
            // 关系式的推导请见「参考代码」下方的示意图
            weight[rootX] = weight[y] * value / weight[x];
        }

        /**
         * 路径压缩
         * 使用路径压缩技术，提高查找效率
         * 完全压缩
         * @param x
         * @return 根结点的 id
         */
        public int find(int x) {
            if (x != parent[x]) {
                // 上一个结点的值，根据上一个结点的值来更新
                int origin = parent[x];
                // 将当前节点的父节点设置为根结点
                parent[x] = find(parent[x]);
                // 更新当前节点的值：
                weight[x] *= weight[origin];
            }
            // 返回的永远是根结点的值
            return parent[x];
        }

        /**
         * 判断两个节点是否在同一个集合中，并计算权值关系
         *
         * @param x
         * @param y
         * @return
         */
        public double isConnected(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return weight[x] / weight[y];
            } else {
                return -1.0d;
            }
        }
    }
}
