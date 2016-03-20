package com.leocai.beaconlocalization.localization;

import android.util.Log;

import com.leocai.beaconlocalization.localization.AlgorithmLocalization.AlgorithmAll;
import com.leocai.beaconlocalization.localization.AlgorithmLocalization.AlgorithmKNN;
import com.leocai.beaconlocalization.localization.AlgorithmLocalization.AlgorithmSumDist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;

public class BeaconLocalizationImpl extends Observable implements BeaconLocalization {

    private static final String TAG = "BeaconLocalizationImpl";

    private int widthBeaconNum;
    private double singleBeaconWidth;
    private int totalBeaconNum;

    private List<LocalizationInfo> localizationInfos = new ArrayList<LocalizationInfo>();
    private double[] localizationInfoArray = new double[16];

    private List<double[]> neighbours = new ArrayList<double[]>();

    private int algorithm = ALGOR_ALL;

    private List<double[]> helpPoints = new ArrayList<double[]>();

    private HelpInfo helpInfo = new HelpInfo();


    @Override
    public void addCurrentBeaconInfo(int beaconId, double dist) {
        // TODO Auto-generated method stub
        if (beaconId < 0 || beaconId > 15) return;
        Log.d(TAG, "" + beaconId + "," + dist);
        localizationInfos.add(new LocalizationInfo(beaconId, dist));
        localizationInfoArray[beaconId] = dist;
    }

    @Override
    public double[] getPosition() {
        // TODO Auto-generated method stub
        neighbours.clear();
        if (localizationInfos.size() < 1) return null;
        double[] pos = null;
        sortLocalizationInfos();
        pos = getPosAndNeighboursByAlgorithm();
        clearCuBeaconInfo();
        return pos;
    }

    private double[] getPosAndNeighboursByAlgorithm() {
        int k = 0;
        int aeroIndex = 0;
        switch (algorithm) {
            case ALGOR_KNEAR_4:
                k = Math.min(4, localizationInfos.size());
                aeroIndex = AlgorithmKNN.getPosAeroIndex(k, localizationInfos);
                break;
            case ALGOR_KNEAR_6:
                k = Math.min(6, localizationInfos.size());
                aeroIndex = AlgorithmKNN.getPosAeroIndex(k, localizationInfos);
                break;
            case ALGOR_SINGLE_COMPARE:
                aeroIndex = AlgorithmSumDist.getPosAeroIndex(localizationInfoArray);
                break;
            case ALGOR_ALL:
                int algoNum = localizationInfos.size()+1;
                int []results =new int[localizationInfos.size()+1];
                results[0]= AlgorithmSumDist.getPosAeroIndex(localizationInfoArray);
                for (int i = 2; i < localizationInfos.size()+1; i+=2) {
                    results[i] =AlgorithmKNN.getPosAeroIndex(i, localizationInfos);
                }
                helpPoints.clear();
                for (int result : results) {
                    helpPoints.add(getPosByAreoIndex(result));
                }
                aeroIndex = AlgorithmAll.getPosAeroIndex(algoNum,results);
                break;
            default:
                aeroIndex = AlgorithmSumDist.getPosAeroIndex(localizationInfoArray);
                break;
        }
        for (int i = 0; i < k; i++) {
            LocalizationInfo info = localizationInfos.get(i);
            neighbours.add(getBeaconPos(info.getBeaconId()));
        }

        helpInfo.addResults(aeroIndex);

        return getPosByAreoIndex(aeroIndex);
    }



    private void sortLocalizationInfos() {
        Collections.sort(localizationInfos, new Comparator<LocalizationInfo>() {
            @Override
            public int compare(LocalizationInfo info1, LocalizationInfo info2) {
                return (int) (info1.getDist() - info2.getDist());
            }
        });
    }

    private double[] getPosByAreoIndex(int aeroIndex) {
        int yIndex = aeroIndex / 2;
        int xIndex = aeroIndex - yIndex * 2;
        double baseWidth = singleBeaconWidth * 3 / 4;
        return new double[]{baseWidth + xIndex * baseWidth * 2, baseWidth + yIndex * baseWidth * 2};
    }

    public double[] getBeaconPos(int beaconId) {
        // TODO Auto-generated method stub
        int yIndex = beaconId / widthBeaconNum;
        int xIndex = beaconId - yIndex * widthBeaconNum;
        return new double[]{xIndex * singleBeaconWidth,
                yIndex * singleBeaconWidth};
    }

    @Override
    public void setWidthBeaconNum(int widthBeaconNum) {
        // TODO Auto-generated method stub
        this.widthBeaconNum = widthBeaconNum;
    }

    @Override
    public void setSingleBeaconWidth(double singleBeaconWidth) {
        // TODO Auto-generated method stub
        this.singleBeaconWidth = singleBeaconWidth;
    }

    public void setTotalBeaconNum(int totalBeaconNum) {
        this.totalBeaconNum = totalBeaconNum;
    }

    @Override
    public List<BaseBeaconInfo> getAllBaseBeacons() {
        List<BaseBeaconInfo> allBaseBeaconInfos = new ArrayList<BaseBeaconInfo>();
        for (int i = 0; i < totalBeaconNum; i++) {
            BaseBeaconInfo baseBeaconInfo = new BaseBeaconInfo();
            double[] beaconPos = getBeaconPos(i);
            baseBeaconInfo.setX((float) beaconPos[0]);
            baseBeaconInfo.setY((float) beaconPos[1]);
            allBaseBeaconInfos.add(baseBeaconInfo);
        }
        return allBaseBeaconInfos;
    }

    @Override
    public void clearCuBeaconInfo() {
        localizationInfos.clear();
        localizationInfoArray = new double[16];
    }

    @Override
    public void customNotify() {
        setChanged();
        notifyObservers();
    }

    @Override
    public List<double[]> getNeighbours() {
        return neighbours;
    }

    @Override
    public void setAlgorithm(int algorithm) {
        this.algorithm = algorithm;
    }

    public int getAlgorithm() {
        return algorithm;
    }

    public List<double[]> getHelpPoints() {
        return helpPoints;
    }

    @Override
    public HelpInfo getHelpInfo() {
        return helpInfo;
    }
}
