package com.leocai.beaconlocalization.beacontool;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.leocai.beaconlocalization.R;
import com.leocai.beaconlocalization.fingerprint.TableView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.distance.ModelSpecificDistanceCalculator;

import java.util.Collection;

public class BeaconToolActivity extends Activity implements BeaconConsumer {

    private static final String TAG = "BeaconToolActivity";
    private BeaconManager beaconManager;

    @ViewInject(R.id.tv_beaconinfo)
    TextView tv_beaconinfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacon_tool);
        ViewUtils.inject(this);

//        Beacon beacon = new Beacon.Builder().setId3("3").setRssi(-71).setTxPower(-59).build();
//        Beacon.setDistanceCalculator(new ModelSpecificDistanceCalculator(this,BeaconManager.getDistanceModelUpdateUrl()));

//        double d = beacon.getDistance();
//        Log.d(TAG,""+d);

        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.getBeaconParsers().add(new BeaconParser()
                .setBeaconLayout("m:0-3=4c000215,i:4-19,i:20-21,i:22-23,p:24-24"));
        beaconManager.bind(this);
    }


    @Override
    public void onBeaconServiceConnect() {
        beaconManager.setRangeNotifier(new RangeNotifier() {
            String context="";
            public int i;

            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                if (beacons.size() > 0) {
                    //addLimitBeacons(beacons);
                    long startTime = System.nanoTime();
                    context="";
//                    Log.d( TAG, );
                    for (Beacon beacon : beacons) {
                        context += "id:"+beacon.getId3();
                        context += ";   rssi:"+beacon.getRssi();
                        context += ";   txPower:"+beacon.getTxPower();
                        context += ";   dist:"+String.format("%.2f",beacon.getDistance());
                        context += "\n\n";
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_beaconinfo.setText(context);
                        }
                    });
                    Log.d(TAG, 1.0f * (System.nanoTime() - startTime) / 1000000000 + "");
                }
            }

        });

        try {

            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_beacon_tool, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
