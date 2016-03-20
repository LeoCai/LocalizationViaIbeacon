package com.leocai.beaconlocalization.localization;

public class LocalizationInfo {

	private int beaconId;
	private double dist;

	public LocalizationInfo(int beaconId, double dist) {
		// TODO Auto-generated constructor stub
		this.beaconId = beaconId;
		this.dist = dist;
	}

	public int getBeaconId() {
		return beaconId;
	}

	public void setBeaconId(int beaconId) {
		this.beaconId = beaconId;
	}

	public double getDist() {
		return dist;
	}

	public void setDist(double dist) {
		this.dist = dist;
	}
	
	

}
