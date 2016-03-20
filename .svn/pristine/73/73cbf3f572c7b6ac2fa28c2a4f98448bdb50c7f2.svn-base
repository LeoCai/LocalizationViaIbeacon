package com.leocai.beaconlocalization.localization.AlgorithmLocalization;

/**
 * 根据四个区域加权和
 * Created by leocai on 15-4-2.
 */
public class AlgorithmSumDist {

    private static final double DEFAULT_DIST = 20;

    public static int getPosAeroIndex(double[] localizationInfoArray){
        double dists[] = new double[4];
        dists[0] = getSumDistByIds(new int[]{0},localizationInfoArray);
        dists[1] = getSumDistByIds(new int[]{3},localizationInfoArray);
        dists[2] = getSumDistByIds(new int[]{8},localizationInfoArray);
        dists[3] = getSumDistByIds(new int[]{15},localizationInfoArray);
        double minDist = Double.MAX_VALUE;
        int minindex = 0;
        for (int i = 0; i < dists.length; i++) {
            if (dists[i] < minDist) {
                minDist = dists[i];
                minindex = i;
            }
        }
        return minindex;
    }

    private static double getSumDistByIds(int[] beaconIds,double[] localizationInfoArray) {
        double sum = 0;
        for (int beaconId : beaconIds) {
            sum += localizationInfoArray[beaconId] == 0 ? DEFAULT_DIST : localizationInfoArray[beaconId];
        }
        return sum;
    }

}
