package com.leocai.beaconlocalization.sensortag;

/**
 * Created by leocai on 14-12-23.
 */
public class BeaconInfo {
    private double dist;
    private int id;
    private int rssi;
    private int measuredPower;

    public BeaconInfo(int id, int rssi, int measuredPower) {
        this.id = id;
        this.rssi = rssi;
        this.measuredPower = measuredPower;
        this.dist = computeAccuracy() ;
    }

    public BeaconInfo(int id, int rssi, double dist) {
        this.id = id;
        this.rssi = rssi;
        this.dist = dist;
    }

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    public int getMeasuredPower() {
        return measuredPower;
    }

    public void setMeasuredPower(int measuredPower) {
        this.measuredPower = measuredPower;
    }

    public int getId() {
        return id;
    }

    public double getDist() {
        return dist;
    }

    private double computeAccuracy()
    {
        if (rssi == 0) {
            return -1.0D;
        }

        double ratio = rssi / measuredPower;
        double rssiCorrection = 0.96D + Math.pow(Math.abs(rssi), 3.0D) % 10.0D / 150.0D;

        if (ratio <= 1.0D) {
            return Math.pow(ratio, 9.98D) * rssiCorrection;
        }
        return (0.103D + 0.89978D * Math.pow(ratio, 7.71D)) * rssiCorrection;
    }

    @Override
    public String toString() {
        return "BeaconInfo{" +
                "rssi=" + rssi +
                ", dist=" + String.format("%.2f",dist) +
                ", id=" + id +
                '}';
    }
}
