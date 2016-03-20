package com.leocai.beaconlocalization.wekaPhone;

import com.leocai.beaconlocalization.uitls.MathUtils;

import java.util.Arrays;

/**
 * Created by leocai on 15-5-10.
 */
public class BufferArrayLocaization {
    int valBuffer[];
    int cuIndex;
    int bufferSize = 4;
    double arrayBuffer[][];

    public BufferArrayLocaization(int bufferSize) {
        this.bufferSize = bufferSize;
        valBuffer = new int[bufferSize];
        arrayBuffer = new double[bufferSize][];
    }

    public void addPredict(double[]result) {
        valBuffer[cuIndex % bufferSize] = (int)result[0];
        arrayBuffer[cuIndex% bufferSize] = result;
        cuIndex++;

    }

    public double[] getBufferPredict() {
        if(cuIndex<bufferSize){
            return null;
        }
        int most =  MathUtils.getMost(valBuffer, cuIndex >= bufferSize ? bufferSize : cuIndex)[0];
        for (int i = cuIndex-1; i > cuIndex-1-bufferSize; i--) {
            if(valBuffer[i%bufferSize] == most){
                return arrayBuffer[i%bufferSize];
            }
        }
        return null;
    }

    public void clear() {
        valBuffer = new int[bufferSize];
        arrayBuffer = new double[bufferSize][];
        cuIndex = 0;
    }

    public static void  main(String args[]){
        BufferArrayLocaization bufferArrayLocaization = new BufferArrayLocaization(5);
        bufferArrayLocaization.addPredict(new double[]{1,2});
        bufferArrayLocaization.addPredict(new double[]{2,3});
        bufferArrayLocaization.addPredict(new double[]{2,3});
        bufferArrayLocaization.addPredict(new double[]{1,3});
        bufferArrayLocaization.addPredict(new double[]{2,4});
        double[] r = bufferArrayLocaization.getBufferPredict();
        System.out.println(Arrays.toString(r));
    }

}
