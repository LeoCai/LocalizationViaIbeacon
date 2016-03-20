package com.leocai.beaconlocalization.localization.AlgorithmLocalization;

import java.util.HashMap;

/**
 * Created by leocai on 15-4-2.
 */
public class AlgorithmAll {

    public static int getPosAeroIndex(int maxSize,int[] results) {
        int vote[] = new int[maxSize];
        for(int i:results){
            vote[i]+=1;
        }
        int maxIndex = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < vote.length; i++) {
            if(vote[i]>max){
                maxIndex = i;
                max = vote[i];
            }
        }
        return maxIndex;
    }
}
