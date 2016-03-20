package com.leocai.beaconlocalization.wekaPhone;

import junit.framework.TestCase;

public class BufferLocalizationTest extends TestCase {

    BufferLocalization bufferLocalization;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        bufferLocalization = new BufferLocalization(5);
        bufferLocalization.addPredict(1);
    }

    public void testAddPredict() throws Exception {


    }

    public void testGetBufferPredict() throws Exception {
        bufferLocalization.clear();
        bufferLocalization.addPredict(1);
        assertEquals(1,bufferLocalization.getBufferPredict());

        bufferLocalization.clear();
        bufferLocalization.addPredict(2);
        assertEquals(2,bufferLocalization.getBufferPredict());

        bufferLocalization.clear();
        bufferLocalization.addPredict(2);
        bufferLocalization.addPredict(2);
        bufferLocalization.addPredict(2);
        bufferLocalization.addPredict(2);
        bufferLocalization.addPredict(3);
        bufferLocalization.addPredict(3);
        bufferLocalization.addPredict(3);
        bufferLocalization.addPredict(3);
        bufferLocalization.addPredict(3);
        assertEquals(3,bufferLocalization.getBufferPredict());


    }

    public void testClear() throws Exception {

    }
}