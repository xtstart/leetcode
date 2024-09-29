package may.may10;

import java.util.Arrays;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-05-11  11:22
 * @Version: 1.0
 * @Description: No.274 [Medium]
 * Q:
 * <p>
 * 给你一个整数数组 citations ，其中 citations[i] 表示研究者的第 i 篇论文被引用的次数。计算并返回该研究者的 h 指数。
 * <p>
 * 根据维基百科上 h 指数的定义：h 代表“高引用次数” ，一名科研人员的 h 指数 是指他（她）至少发表了 h 篇论文，并且 至少 有 h 篇论文被引用次数大于等于 h 。如果 h 有多种可能的值，h 指数 是其中最大的那个。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：citations = [3,0,6,1,5]
 * 输出：3
 * 解释：给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 3, 0, 6, 1, 5 次。
 * 由于研究者有 3 篇论文每篇 至少 被引用了 3 次，其余两篇论文每篇被引用 不多于 3 次，所以她的 h 指数是 3。
 * 示例 2：
 * <p>
 * 输入：citations = [1,3,1]
 * 输出：1
 */

public class hIndex {

    /**
     * 自己实现
     * 1.先进行排序
     * 2.遍历了两遍
     * 2.1 先倒序遍历找出引用数大于出现次数的h,h为出现次数
     * 2.2 再顺序遍历找出出现次数大于引用次数的值h，h为出现次数
     *
     * @param citations
     * @return
     */
    public int hIndex01(int[] citations) {
        int length = citations.length;
        Arrays.sort(citations);
        int h = 0;
        if (citations[0] > length) {
            return length;
        }
        for (int i = length - 1; i > 0; i--) {
            if (citations[i] >= length - i) {
                h = length - i;
            }
        }
        for (int i = 0; i < citations.length; i++) {
            if (citations[i] <= length - i) {
                h = Math.max(h, citations[i]);
            }
        }
        return h;
    }


    /**
     * 可以理解为找到有n个，大于n的数
     * @param citations
     * @return
     */
    public int hIndex02(int[] citations) {
        int length = citations.length;
        Arrays.sort(citations);
        int h = 0;

        for (int i = length - 1; i >= 0; i--) {
            if (citations[i] >= length - i) {
                h = length - i;
            }
        }

        return h;
    }
}
