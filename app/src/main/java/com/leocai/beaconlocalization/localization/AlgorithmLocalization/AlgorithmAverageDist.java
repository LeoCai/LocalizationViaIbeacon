package com.leocai.beaconlocalization.localization.AlgorithmLocalization;

import com.leocai.beaconlocalization.localization.BeaconLocalization;

/**
 * 暂时没用，根据平均距离返回
 * Created by leocai on 15-4-2.
 */
public class AlgorithmAverageDist {

    private double[] getPosAeroIndex(double[] localizationInfoArray) {
        double minSum = Double.MAX_VALUE;
        int minSquarePos = 0;
        double defaultDist = BeaconLocalization.DEFAULT_DIST;
        for (int i = 0; i < 11; i++) {
            if ((i + 1) % 4 == 0) continue;
            double sum = 0;
            sum += localizationInfoArray[i] == 0 ?defaultDist: localizationInfoArray[i];
            sum += localizationInfoArray[i + 1] == 0 ? defaultDist : localizationInfoArray[i + 1];
            sum += localizationInfoArray[i + 4] == 0 ? defaultDist : localizationInfoArray[i + 4];
            sum += localizationInfoArray[i + 5] == 0 ? defaultDist : localizationInfoArray[i + 5];
            if (sum < minSum) {
                minSum = sum;
                minSquarePos = i;
            }
        }
        return null;
        //double[] basePos = getBeaconPos(minSquarePos);
        //return new double[]{basePos[0] + singleBeaconWidth / 2, basePos[1] + singleBeaconWidth / 2};
    }
}
