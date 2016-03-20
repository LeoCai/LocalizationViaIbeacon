package com.leocai.beaconlocalization.sensortag;

import android.util.Log;


/**
 * Created by leocai on 14-12-3.
 */
public class BeaconBasedPosListenerImpl implements BeaconBasedPosListener {

    public static final String TAG_BEACON_POS_INFO = "beaconPosInfo";

    @Override
    public void onBeaconBasedPosInfoChanged(byte[] beaconBasedPosInfo) {
        String info = "";
        for(byte b : beaconBasedPosInfo){
            info += b;
            info += ",";
        }
        byte beaconId = BeaconBasedPosInfoUtils.getNearestBeaconId(beaconBasedPosInfo);
        byte beaconRssi = BeaconBasedPosInfoUtils.getNearestBeaconRssi(beaconBasedPosInfo);
        Log.d(TAG_BEACON_POS_INFO, "id:" + beaconId + ", "+"rssi:" + beaconRssi);
//        Log.d("beaconPosInfo", info);
//        byte[] a =beaconBasedPosInfo;
    }

    @Override
    public int getNearestBeaconId() {
        return 0;
    }

    @Override
    public int[] getBeaconBasedPosition() {
        return new int[]{0, 0};
    }
}
