package com.leocai.beaconlocalization.wekaPhone;

import com.leocai.beaconlocalization.uitls.MathUtils;

/**
 * Created by leocai on 15-4-11.
 */
public class BufferLocalization {

    int buffer[];
    int cuIndex;
    int bufferSize = 4;

    public BufferLocalization(int bufferSize) {
        this.bufferSize = bufferSize;
        buffer = new int[bufferSize];
    }

    public void addPredict(int predict) {
        buffer[cuIndex++ % bufferSize] = predict;
    }

    public int getBufferPredict() {
        if(cuIndex<=2){
            return -1;
        }
        return MathUtils.getMost(buffer, cuIndex >= bufferSize ? bufferSize : cuIndex)[0];
    }

    public void clear() {
        buffer = new int[bufferSize];
        cuIndex = 0;
    }

}
