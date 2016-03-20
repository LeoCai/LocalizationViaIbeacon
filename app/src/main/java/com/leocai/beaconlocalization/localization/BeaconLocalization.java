package com.leocai.beaconlocalization.localization;

import com.leocai.beaconlocalization.view.LocalizationMapView;

import java.util.List;
import java.util.Observer;

public interface BeaconLocalization {

    double DEFAULT_DIST = 20;

    int ALGOR_KNEAR_4 = 0;
    int ALGOR_KNEAR_6 = 1;
    int ALGOR_SINGLE_COMPARE = 2;
    int ALGOR_ALL = 3;


    void setSingleBeaconWidth(double singleBeaconWidth);

    void setWidthBeaconNum(int widthBeaconNum);

    double[] getPosition();

    void addCurrentBeaconInfo(int beaconId, double dist);

    void setTotalBeaconNum(int totalBeaconNum);

    List<BaseBeaconInfo> getAllBaseBeacons();

    void clearCuBeaconInfo();

    void customNotify();

    void addObserver(Observer observer);

    List<double[]> getNeighbours();

    void setAlgorithm(int algorithm);

    List<double[]> getHelpPoints();

    HelpInfo getHelpInfo();
}
