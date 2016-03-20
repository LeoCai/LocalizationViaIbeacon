package com.leocai.beaconlocalization.test;

import com.leocai.beaconlocalization.localization.HelpInfo;

import junit.framework.TestCase;

public class HelpInfoTest extends TestCase {
    HelpInfo helpInfo = new HelpInfo();

    public void setUp() throws Exception {
        super.setUp();
        helpInfo.addResults(4);
        helpInfo.addResults(3);
        helpInfo.addResults(3);
        helpInfo.addResults(3);
        helpInfo.addResults(3);
        helpInfo.addResults(3);


    }

    public void testGetVariace() throws Exception {
        assertEquals(0.0d,helpInfo.getVariace());
    }
}