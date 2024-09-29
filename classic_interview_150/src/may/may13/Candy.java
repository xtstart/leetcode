package may.may13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-05-13  11:56
 * @Version: 1.0
 * @Description: No.135 [Hard]
 *
 * Q:
 * <p>
 * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 * <p>
 * 你需要按照以下要求，给这些孩子分发糖果：
 * <p>
 * 每个孩子至少分配到 1 个糖果。
 * 相邻两个孩子评分更高的孩子会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：ratings = [1,0,2]
 * 输出：5
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
 * 示例 2：
 * <p>
 * 输入：ratings = [1,2,2]
 * 输出：4
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。
 * 第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。
 */

public class Candy {

    public static void main(String[] args) {
        int[] arr = {1,2,87,87,87,2,1};
        Candy candy = new Candy();
        int i = candy.candy04(arr);
        System.out.println(i);
    }


    /**
     * 【自己的实现】
     * 【执行时间】：1788ms
     * 思路：
     * 1.首先先找到最大的等级
     * 2.创建一个孩子糖数的结果集
     * 3. 根据等级进行遍历
     * 3.1 先给所有的孩子1颗糖
     * 3.2 所有等级为1的孩子，如果左右有比自己等级低的孩子，则该孩子的糖数比左右孩子的糖数加一即可
     * ...
     * 4. 遍历糖数的结果集，得到最少糖果数目
     * 时间复杂度：最坏可能O(n^2)
     *
     * @param ratings
     * @return
     */
    public int candy01(int[] ratings) {
        int max = 0;
        for (int rating : ratings) {
            max = Math.max(max, rating);
        }
        int[] result = new int[ratings.length];
        for (int i = 0; i < max + 1; i++) {
            for (int j = 0; j < ratings.length; j++) {
                if (i == 0) {
                    result[j] = 1;
                } else {
                    if (ratings[j] == i) {
                        if (j > 0 && ratings[j - 1] < ratings[j] && result[j] <= result[j - 1]) {
                            result[j] = result[j - 1] + 1;
                        }
                        if (j < ratings.length - 1 && ratings[j + 1] < ratings[j] && result[j] <= result[j + 1]) {
                            result[j] = result[j + 1] + 1;
                        }
                    }
                }
            }
        }
        int resultVal = 0;
        for (int i = 0; i < ratings.length; i++) {
            resultVal += result[i];
        }
        return resultVal;
    }


    /**
     * 【自己写法的改进优化】
     * 【执行时间】：16ms
     *
     * @param ratings
     * @return
     */
    public int candy02(int[] ratings) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int max = 0;

        for (int i = 0; i < ratings.length; i++) {
            int rating = ratings[i];
            max = Math.max(max, rating);
            List<Integer> integers = map.getOrDefault(rating, null);
            if (integers == null) {
                integers = new ArrayList<Integer>();
                map.put(rating, integers);
            }
            integers.add(i);
        }
        int[] result = new int[ratings.length];
        for (int i = 0; i < max + 1; i++) {

            if (map.containsKey(i)) {
                List<Integer> integers = map.get(i);
                for (Integer j : integers) {
                    int left = 0;
                    int right = 0;
                    if (j > 0 && ratings[j - 1] < ratings[j] && result[j] <= result[j - 1]) {
                        left = result[j - 1] + 1;
                    }
                    if (j < ratings.length - 1 && ratings[j + 1] < ratings[j] && result[j] <= result[j + 1]) {
                        right = result[j + 1] + 1;
                    }
                    result[j] = Math.max(left, right) > 1 ? Math.max(left, right) : 1;
                }
            }
        }
        int resultVal = 0;
        for (int i = 0; i < ratings.length; i++) {
            resultVal += result[i];
        }
        return resultVal;
    }


    /**
     * 【官方解法：两次遍历】
     * 【执行时间】：2ms
     * 思路：
     * 我们可以将「相邻的孩子中，评分高的孩子必须获得更多的糖果」这句话拆分为两个规则，分别处理。
     * <p>
     * 左规则：当 ratings[i−1]<ratings[i]\textit{ratings}[i - 1] < \textit{ratings}[i]ratings[i−1]<ratings[i] 时，iii 号学生的糖果数量将比 i−1i - 1i−1 号孩子的糖果数量多。
     * <p>
     * 右规则：当 ratings[i]>ratings[i+1]\textit{ratings}[i] > \textit{ratings}[i + 1]ratings[i]>ratings[i+1] 时，iii 号学生的糖果数量将比 i+1i + 1i+1 号孩子的糖果数量多。
     * <p>
     * 我们遍历该数组两次，处理出每一个学生分别满足左规则或右规则时，最少需要被分得的糖果数量。每个人最终分得的糖果数量即为这两个数量的最大值。
     *
     * @param ratings
     * @return
     */
    public int candy03(int[] ratings) {
        int[] left = new int[ratings.length];
        int[] right = new int[ratings.length];
        for (int i = 0; i < ratings.length; i++) {
            if (i > 0 && ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        int returnVal = 0;
        for (int i = ratings.length - 1; i >= 0; i--) {
            if (i < ratings.length - 1 && ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 1;
            }

            returnVal += Math.max(left[i], right[i]);
        }
        return returnVal;
    }


    /**
     * @param ratings
     * @return
     */
    public int candy04(int[] ratings) {
        int n = ratings.length;
        int pre = 1;
        int index = 1;
        int result = 1;
        int inc = 1;
        int dec = 0;
        while (index < n) {
            if (ratings[index] >= ratings[index - 1]) {
                dec = 0;
                pre = ratings[index] == ratings[index - 1] ?1:pre+1;
                result += pre;
                inc =pre;
            } else {
                dec++;
                if (dec == inc) {
                    dec++;
                }

                result += dec;
                pre = 1;
            }
            index++;
        }
        return result;
    }

}
