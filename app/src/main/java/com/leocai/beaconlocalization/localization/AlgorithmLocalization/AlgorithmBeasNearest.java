package com.leocai.beaconlocalization.localization.AlgorithmLocalization;

import com.leocai.beaconlocalization.localization.LocalizationInfo;

import java.util.List;

/**
 * 暂时没用
 * 根据最近的beacon调整方位
 * Created by leocai on 15-4-2.
 */
public class AlgorithmBeasNearest {


    private double[] getPositionByBaseNearest(List<LocalizationInfo> sortedLocalizationInfos) {

//        LocalizationInfo info0 = sortedLocalizationInfos.get(0);
//        int nearestBeaconId = info0.getBeaconId();
//        double[] nearestBeacon = getBeaconPos(info0.getBeaconId());
//        neighbours.add(nearestBeacon);
//        int[] nearestBeaconIndexAxis = getBeaconIndexAxis(nearestBeaconId);
//
//        double weights[] = new double[4];
//        weights[0] = getWeight(-1, -1, nearestBeaconIndexAxis);
//        weights[1] = getWeight(1, -1, nearestBeaconIndexAxis);
//        weights[2] = getWeight(-1, 1, nearestBeaconIndexAxis);
//        weights[3] = getWeight(1, 1, nearestBeaconIndexAxis);
//        double minWeight = Double.MAX_VALUE;
//        int minIndex = 0;
//        for (int i = 0; i < weights.length; i++) {
//            if (weights[i] < minWeight) {
//                minWeight = weights[i];
//                minIndex = i;
//            }
//        }
//        int offYIndex = minIndex / 2;
//        int offXIndex = minIndex - offYIndex * 2;
//        offYIndex = offYIndex * 2 - 1;
//        offXIndex = offXIndex * 2 - 1;
        return null;
//        return new double[]{nearestBeacon[0] + offXIndex * singleBeaconWidth / 2,
//                nearestBeacon[1] + offYIndex * singleBeaconWidth / 2};
    }

//
//    private double getWeight(int xoffset, int yoffset, int[] nearestBeaconIndexAxis) {
//        int xIndex = nearestBeaconIndexAxis[0] + xoffset;
//    int yIndex = nearestBeaconIndexAxis[1] + yoffset;
//    if (xIndex > 0 && yIndex > 0 && xIndex < widthBeaconNum && yIndex < widthBeaconNum) {
//        double[] dists = new double[3];
//        dists[0] = localizationInfoArray[yIndex * widthBeaconNum + xIndex];
//        dists[1] = localizationInfoArray[yIndex * widthBeaconNum + nearestBeaconIndexAxis[0]];
//        dists[2] = localizationInfoArray[nearestBeaconIndexAxis[1] * widthBeaconNum + xIndex];
//        double sum = 0;
//        int count = 0;
//        for (double dist : dists) {
//            if (dist > 0) {
//                sum += dist;
//            } else {
//                sum += DEFAULT_DIST;
//            }
//            count += 1;
//
//        }
//        return sum / count;
//    } else {
//        return Double.MAX_VALUE;
//    }
//}
//
//    private int[] getBeaconIndexAxis(int nearestBeaconId) {
//        int yIndex = nearestBeaconId / widthBeaconNum;
//        return new int[]{nearestBeaconId - yIndex * widthBeaconNum, yIndex};
//    }


}
