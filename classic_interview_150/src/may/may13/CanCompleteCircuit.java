package may.may13;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-05-13  10:23
 * @Version: 1.0
 * @Description: TODO
 */

public class CanCompleteCircuit {

    public int canCompleteCircuit01(int[] gas, int[] cost) {
        int n = gas.length;


        int gasMax=0;
        int costMax =0;

        boolean flag = true;
        for(int i =0;i<n;i++){
            if(gas[i] < cost[i]){
                flag =false;
            }
            gasMax +=gas[i];
            costMax +=cost[i];
        }
        if(gasMax < costMax){
            return -1;
        }
        if(flag){
            return 0;
        }
        // 先for循环遍历找到gas数组笔cost数组大的序号
        for (int i = 0; i < n; i++) {
            if (gas[i] == 0) {
                continue;
            }
            if (gas[i] >= cost[i]) {
                int j = (i + 1) % n;
                int residue = gas[i] - cost[i];
                while (residue >= 0) {
                    if (j == i) {
                        return i;
                    }
                    residue += gas[j] - cost[j];
                    j = (j + 1) % n;
                }
            }
        }
        return -1;
    }
}
