package com.leocai.beaconlocalization.uitls;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by leocai on 15-4-5.
 */
public class MathUtils {

    /**
     * 获取众数，及数量
     * @param values
     * @param i
     * @return
     */
    public static int[] getMost(int[] values, int valNum) {
        HashMap<Integer, Integer> valMaps = new HashMap<Integer, Integer>();
        for (int i=0 ;i< valNum;i++) {
            int val = values[i];
            valMaps.put(val, valMaps.get(val) == null ? 1 : valMaps.get(val) + 1);
        }
        Set<Integer> keySet = valMaps.keySet();
        int max = 0;
        int maxId = 0;
        for (int key : keySet) {
            int num = valMaps.get(key);
            if (num > max) {
                maxId = key;
                max = num;
            }
        }
        return new int[]{maxId, max};
    }

    public static double average(List<Double> values) {
        double sum = 0;
        for (double v : values) {
            sum += v;
        }
        return sum / values.size();
    }

    public static double sqrtVarience(List<Double> values) {
        double average = average(values);
        double sum = 0;
        for (double v : values) {
            sum += Math.pow(v - average, 2);
        }
        return Math.sqrt(sum / values.size());
    }
}
