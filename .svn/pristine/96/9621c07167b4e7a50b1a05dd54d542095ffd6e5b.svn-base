package com.leocai.beaconlocalization.localization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by leocai on 15-4-3.
 */
public class HelpInfo {

    private List<int[]> resultsIndex = new ArrayList<int[]>();

    public void addResults(int aeroIndex) {
        int yIndex = aeroIndex / 2;
        int xIndex = aeroIndex - yIndex * 2;
        if (resultsIndex.size() >= 5)
            resultsIndex.remove(0);
        resultsIndex.add(new int[]{xIndex, yIndex});
    }

    public double getVariace() {
        double sum0 = 0;
        double sum1 = 0;
        double average0 = 0;
        double average1 = 0;
        for (int[] r : resultsIndex) {
            average0 += r[0];
            average1 += r[1];
        }
        average0 /= resultsIndex.size();
        average1 /= resultsIndex.size();
        for (int[] r : resultsIndex) {
            sum0 += Math.pow(r[0] - average0, 2);
            sum1 += Math.pow(r[1] - average1, 2);
        }
        sum0 /= resultsIndex.size();
        sum1 /= resultsIndex.size();
        return Math.sqrt(sum0) + Math.sqrt(sum1);

    }

}
