package june.june07;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-06-07  10:27
 * @Version: 1.0
 * @Description: TODO
 */

public class FindMinArrowShots {
    @Test
    public void test01() {
        int[][] arr = {{3, 9}, {7, 12}, {3, 8}, {6, 8}, {9, 10}, {2, 9}, {0, 9}, {3, 9}, {0, 6}, {2, 8}};
        int minArrowShots = findMinArrowShots(arr);
        System.out.println(minArrowShots);
    }

    @Test
    public void test02() {
        int[][] arr = {{9, 12}, {1, 10}, {4, 11}, {8, 12}, {3, 9}, {6, 9}, {6, 7}};
        int minArrowShots = findMinArrowShots(arr);
        System.out.println(minArrowShots);
    }


    /**
     * 1.先对数组按照0，1位置元素的优先级进行排序
     * 2.循环遍历数组，如果数组中的终止位置大于下一个数组的起始位置，维护一个R（数组中的终止位置）表示最小的有边界，继续遍历
     * 2.1.如果下一个数组的起始位置小于R，表示继续遍历，并不断用R和下一个数组的终止位置比较，R为较小的那个值
     * 3.否则计数器加一
     * 4.返回计数器哦的值
     *
     * @param points
     * @return
     */
            public int findMinArrowShots(int[][] points) {
                Arrays.sort(points, new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        if (o1[0] == o2[0]) {
                            return Integer.compare(o1[1], o2[1]);
                        }
                        return Integer.compare(o1[0], o2[0]);
                    }
                });
                int i = 0;
                int len = points.length;
                int index = 0;
                while (i < len) {
                    int R = points[i][1];
                    i++;
                    while (i < len && R >= points[i][0]) {
                        R = Math.min(R, points[i][1]);
                        i++;
                    }
                    index++;
                }
                return index;
            }

}
