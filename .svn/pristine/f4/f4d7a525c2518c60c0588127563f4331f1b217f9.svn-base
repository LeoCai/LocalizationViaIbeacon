package com.leocai.beaconlocalization.test;

import android.util.Log;

import com.leocai.beaconlocalization.localization.BeaconLocalization;

import java.util.HashSet;
import java.util.Random;

/**
 * Created by leocai on 15-3-25.
 */
public class TestCaseLocalization {

    public static void testRandomMap(final BeaconLocalization beaconLocalization){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                random.setSeed(System.currentTimeMillis());


                try {
                    Thread.sleep(1000);
                    for (int i = 0; i < 50; i++) {
                        for (int j = 0; j < 4; j++) {
                            HashSet<Integer> beaconIndexs = new HashSet<Integer>();
                            int beaconIndex = random.nextInt(16);
                            if(beaconIndexs.add(beaconIndex)){
                                beaconLocalization.addCurrentBeaconInfo(beaconIndex,random.nextInt(10));
                            }
                        }
                        beaconLocalization.customNotify();
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    public static void testAllMap(final BeaconLocalization beaconLocalization) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("pass","1");
                lineTest(0);
                Log.d("pass","2");
                lineTest(4);
                Log.d("pass","3");
                lineTest(8);
                Log.d("pass","4");

            }

            private void lineTest(int startIndex) {
                for (int i = startIndex; i < startIndex +3; i++) {
                    Log.d("test" , i+"");
//                int i=8;
                    beaconLocalization.addCurrentBeaconInfo(i, 1);
                    beaconLocalization.addCurrentBeaconInfo(i+1,2);
                    beaconLocalization.addCurrentBeaconInfo(i+4,2);
                    beaconLocalization.addCurrentBeaconInfo(i+4+1,2);
                    beaconLocalization.customNotify();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
