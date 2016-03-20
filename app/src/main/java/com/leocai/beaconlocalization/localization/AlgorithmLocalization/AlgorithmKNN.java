package com.leocai.beaconlocalization.localization.AlgorithmLocalization;

import com.leocai.beaconlocalization.localization.LocalizationInfo;

import java.util.Arrays;
import java.util.List;

/**
 * K近邻
 * Created by leocai on 15-4-2.
 */
public class AlgorithmKNN {

    public static int getPosAeroIndex(int k, List<LocalizationInfo> sortedInfos) {

        int[] beaconIds = getKNearBeaconIds(k, sortedInfos);

        int[] sum = getNearSumByBeaconIds(beaconIds);

        int nearestId = sortedInfos.get(0).getBeaconId();

        return getTargetAreoByNearSum(sum, nearestId);
    }

    private static int[] getKNearBeaconIds(int k, List<LocalizationInfo> infos) {
        int []beaconIds =new int[k];
        for (int i = 0; i < k; i++) {
            LocalizationInfo info = infos.get(i);
            beaconIds[i] = info.getBeaconId();
        }
        return beaconIds;
    }

    public static int getTargetAreoByNearSum(int[] sum, int nearestId) {
        int maxNum = 0;
        int maxIndex = 0;
        for (int i = 0; i < 4; i++) {
            if(sum[i]>maxNum){
                maxNum = sum[i];
                maxIndex = i;
            }else if(sum[i]==maxNum){
                int yIndex = nearestId/4;
                int xIndex = nearestId - yIndex*4;
                yIndex/=2;
                xIndex/=2;
                if(i == (yIndex*2+xIndex)){
                    maxNum = sum[i];
                    maxIndex = i;
                }
            }
        }
        return maxIndex;
    }

    private static int[] getNearSumByBeaconIds(int[] beaconIds) {
        int sum[] = new int[4];
        sum[0]=getMatchNum(new int[]{0,1,4,5},beaconIds);
        sum[1]=getMatchNum(new int[]{2,3,6,7},beaconIds);
        sum[2]=getMatchNum(new int[]{8,9,12,13},beaconIds);
        sum[3]=getMatchNum(new int[]{10,11,14,15},beaconIds);
        return sum;
    }
    private static int getMatchNum(int[] targetids, int[] beaconIds) {
        int sum = 0;
        for (int id:beaconIds){
            if(Arrays.binarySearch(targetids, id)>=0){
                sum+=1;
            }
        }
        return sum;
    }

}
