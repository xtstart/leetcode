package part12_graph;

import org.junit.Test;

import java.util.*;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-07-04  14:51
 * @Version: 1.0
 * @Description: No.207 [medium]
 * 【课程表】
 * Q:
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * <p>
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 */

public class CanFinish {

    @Test
    public void test() {
        int[][] prerequisites = {{1,0}};
        canFinish(2, prerequisites);
    }


    List<List<Integer>> parents;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        parents = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            parents.add(i, new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            int course1 = prerequisite[0];
            int course2 = prerequisite[1];
            // 校验
            if (find(course1, course2)) {
                return false;
            }
            // 填入
           parents.get(course2).add(course1);
        }
        return true;
    }

    public boolean find(int x, int targetVal) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(x);
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            if (poll == targetVal) {
                return true;
            }
            List<Integer> pollParents = parents.get(poll);
            if (!pollParents.isEmpty()) {
                for (Integer pollParent : pollParents) {
                    queue.offer(pollParent);
                }
            }
        }
        return false;
    }
}
