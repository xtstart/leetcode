package may.may10;

import java.util.*;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-05-11  15:23
 * @Version: 1.0
 * @Description: TODO
 */

public class RandomizedSet {

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        randomizedSet.insert(1);
        randomizedSet.insert(2);
        randomizedSet.insert(3);
        randomizedSet.insert(4);
        randomizedSet.insert(5);
        randomizedSet.remove(3);

        randomizedSet.istToString();


    }
    private Map<Integer,Integer> indices;

    private List<Integer> list;

    Random random;


    public RandomizedSet() {
        this.indices = new HashMap<Integer, Integer>();
        this.list = new ArrayList<Integer>();
        this.random = new Random();
    }

    public boolean insert(int val) {
        if (indices.containsKey(val)) {
            return false;
        }
        int index = list.size();
        list.add(val);
        indices.put(val, index);
        return true;
    }


    public boolean remove(int val) {
        if (!indices.containsKey(val)) {
            return false;
        }
        int index = indices.get(val);
        int lastIndex = list.size()-1;
        int lastValue = list.get(lastIndex);
        list.set(index,lastValue);
        list.remove(lastIndex);
        indices.put(lastValue, index);
        indices.remove(val);
        return true;
    }

    public int getRandom() {
        int i = random.nextInt(list.size());
        return list.get(i);
    }


    public void istToString(){
        System.out.println(Arrays.toString(list.toArray()));
    }
}
