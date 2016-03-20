package com.leocai.beaconlocalization.test;

import com.leocai.beaconlocalization.localization.AlgorithmLocalization.AlgorithmAll;

import junit.framework.TestCase;

public class AlgorithmAllTest extends TestCase {

    public void testGetPosAeroIndex() throws Exception {
        int aeroIndex = AlgorithmAll.getPosAeroIndex(4, new int[]{0, 2, 2});
        assertEquals(2,aeroIndex);
        int aeroIndex2 = AlgorithmAll.getPosAeroIndex(4, new int[]{1, 1, 2});
        assertEquals(1,aeroIndex2);

    }
}