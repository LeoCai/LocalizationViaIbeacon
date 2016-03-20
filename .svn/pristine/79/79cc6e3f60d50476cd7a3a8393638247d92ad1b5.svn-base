package com.leocai.beaconlocalization.localization;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.leocai.beaconlocalization.R;
import com.leocai.beaconlocalization.test.ILogger;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class KNearActivity extends Activity implements BeaconConsumer, Observer {
    private static final String TAG = KNearActivity.class.getSimpleName();
    private BeaconManager beaconManager;


    @ViewInject(R.id.tvNearBeacons)
    TextView tvNearBeacons;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knear);
        ViewUtils.inject(this);
        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.getBeaconParsers().add(new BeaconParser()
                .setBeaconLayout("m:0-3=4c000215,i:4-19,i:20-21,i:22-23,p:24-24"));
        beaconManager.bind(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_knear, menu);
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

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.setRangeNotifier(new RangeNotifier() {
            String context;
            String nearInfo="";

            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                if (beacons.size() > 0) {
                    Log.i(TAG, "" + beacons.size() + " beacons find!");
                    context = "" + beacons.size() + " beacons\n";
                    List<Beacon> lbeacons = new ArrayList<Beacon>();
                    for (Beacon beacon : beacons) {
                        lbeacons.add(beacon);
                    }
                    Collections.sort(lbeacons, new Comparator<Beacon>() {
                        @Override
                        public int compare(Beacon beacon1, Beacon beacon2) {
                            return beacon2.getRssi() - beacon1.getRssi();
                        }
                    });
                    nearInfo= "";
                    for(int i=0;i<lbeacons.size();i++){
                        Beacon beacon = lbeacons.get(i);
                        nearInfo+=beacon.getId3()+": "+beacon.getRssi()+"\n";
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvNearBeacons.setText(nearInfo);
                        }
                    });


//                    Log.i(TAG, "The first beacon I see is about " + beacons.iterator().next().getDistance() + " meters away.");
                }
            }

        });

        try {

            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {
        }
    }

    @Override
    public void update(Observable observable, Object data) {

    }
}
