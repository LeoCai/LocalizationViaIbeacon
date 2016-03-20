package com.leocai.beaconlocalization.test;

import com.leocai.beaconlocalization.localization.AlgorithmLocalization.AlgorithmSumDist;
import com.leocai.beaconlocalization.localization.LocalizationInfo;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmSumDistTest extends TestCase {
    private List<LocalizationInfo> infos;
    private double[] lArray;

    public void setUp() throws Exception {
        super.setUp();
        infos = new ArrayList<LocalizationInfo>();
        infos.add(new LocalizationInfo(2,1));
        infos.add(new LocalizationInfo(3,2));
        infos.add(new LocalizationInfo(6,2));
        infos.add(new LocalizationInfo(7,2));
        infos.add(new LocalizationInfo(0,3));
        infos.add(new LocalizationInfo(4,3));
        infos.add(new LocalizationInfo(1,4));
        lArray = new double[16];
        for(LocalizationInfo info:infos){
            lArray[info.getBeaconId()]=info.getDist();
        }

    }

    public void testGetPosAeroIndex() throws Exception {
        assertEquals(1,AlgorithmSumDist.getPosAeroIndex(lArray));
    }
}