package com.leocai.beaconlocalization.localization;

/**
 * Created by leocai on 15-3-25.
 */
public class BaseBeaconInfo {
    private float x;
    private float y;
    private float dist;

    public float getDist() {
        return dist;
    }

    public void setDist(float dist) {
        this.dist = dist;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
