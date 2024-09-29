package part12_graph;

import org.junit.Test;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-07-01  11:37
 * @Version: 1.0
 * @Description: No.990 [medium]
 * 【等式方程的可满足性】
 * [union-find]
 * Q:
 * 给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，并采用两种不同的形式之一："a==b" 或 "a!=b"。在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。
 * <p>
 * 只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。
 */

public class EquationsPossible {

    @Test
    public void test() {
        String[] arrs ={"a==b","a==c","a==d","d==e","c==f"};
        equationsPossible(arrs);
    }


   /*
        思路：由于相等关系具有传递性，所有相等的变量属于同一个集合
        只关心连通性，不关心距离，因此很容易想到并查集
        设计算法的流程：
        - 扫描所有等式，将等式两边的顶点进行合并；
        - 再扫描所有不等式，检查每一个不等式的两个顶点是不是在一个连通分量里；
            - 如果在，返回false表示等式方程有矛盾
            - 如果所有检查都没有矛盾，返回true
   */
   public boolean equationsPossible(String[] equations) {
       int[] parent = new int[26];
       for (int i = 0; i < 26; i++) {
           parent[i] = i;
       }
       for (String str : equations) {
           if (str.charAt(1) == '=') {
               int index1 = str.charAt(0) - 'a';
               int index2 = str.charAt(3) - 'a';
               union(parent, index1, index2);
           }
       }
       for (String str : equations) {
           if (str.charAt(1) == '!') {
               int index1 = str.charAt(0) - 'a';
               int index2 = str.charAt(3) - 'a';
               if (find(parent, index1) == find(parent, index2)) {
                   return false;
               }
           }
       }
       return true;
   }

    /**
     * 将最终父节点得值替换为当前值
     * @param parent
     * @param index1
     * @param index2
     */
    public void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    /**
     * 找到最终得父节点
     * 隔代压缩
     * @param parent
     * @param index
     * @return
     */
    public int find(int[] parent, int index) {
        while (parent[index] != index) {
            parent[index] = parent[parent[index]];
            index = parent[index];
        }
        return index;
    }
}
