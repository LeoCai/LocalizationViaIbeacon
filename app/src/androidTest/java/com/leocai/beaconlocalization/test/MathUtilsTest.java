package com.leocai.beaconlocalization.test;

import com.leocai.beaconlocalization.uitls.MathUtils;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class MathUtilsTest extends TestCase {

    List<Double> vals = new ArrayList<Double>();

    @Override
    public void setUp() throws Exception {
        super.setUp();
        vals.add(3d);
        vals.add(2d);
        vals.add(1d);

    }

    public void testAverage() throws Exception {
        assertEquals(2d,MathUtils.average(vals));

    }

    public void testSqrtVarience() throws Exception {
//        assertEquals(1d,MathUtils.sqrtVarience(vals));

    }
}