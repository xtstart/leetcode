package may.may17;

import java.util.*;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-05-17  16:04
 * @Version: 1.0
 * @Description: TODO
 */

public class ThreeSum {

    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        int[] arr = {3,0,-2,-1,1,2};
        List<List<Integer>> lists = threeSum.threeSum(arr);


    }


    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        Set<String> set = new HashSet<>();
        for (int i = 0; i < nums.length-2; i++) {
            Map<Integer, Integer> valuesTimes = initMap(nums, i+1);
            for (int j = i + 1; j < nums.length; j++) {

                valuesTimes.put(nums[j], valuesTimes.get(nums[j]) - 1);
                if (valuesTimes.get(nums[j]) < 0){
                    continue;
                }
                int mul = -(nums[i] + nums[j]);
                if (valuesTimes.getOrDefault(mul, 0) > 0) {
                    if (nums[j] > mul){
                        continue;
                    }
                    String str = nums[i]+""+ nums[j]+""+mul;
                    if (set.contains(str)) {
                        continue;
                    }
                    set.add(str);
                    List<Integer> list = Arrays.asList( nums[i], nums[j],mul);
                    valuesTimes.put(mul, valuesTimes.get(mul) - 1);
                    result.add(list);
                }
            }
        }
        return result;
    }

    private Map<Integer, Integer> initMap(int[] nums,int start) {
        Map<Integer, Integer> valuesTimes = new HashMap<Integer, Integer>();
        for (int j = start; j < nums.length; j++) {

            valuesTimes.put(nums[j], valuesTimes.getOrDefault(nums[j], 0) + 1);
        }
        return valuesTimes;
    }

}
