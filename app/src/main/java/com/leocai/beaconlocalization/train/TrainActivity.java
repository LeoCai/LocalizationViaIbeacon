package com.leocai.beaconlocalization.train;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.leocai.beaconlocalization.R;
import com.leocai.beaconlocalization.uitls.FileLogger;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.ArrayList;
import java.util.Collection;

public class TrainActivity extends Activity implements BeaconConsumer,CuIndexSetter {
    public static final int TRAIN_MAX_NUM = 100;
    private BeaconManager beaconManager;
    private static final int BEACON_SIZE = 14;
    private int cuTrainIndex = -1;
    int dataCount[] = new int[8];
    private ArrayList<Button> btns;

    @OnClick(R.id.tstop)
    void tstop(View view) {
        cuTrainIndex = -1;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);
        ViewUtils.inject(this);
        beaconManager = BeaconManager.getInstanceForApplication(this);
//        beaconManager.setForegroundBetweenScanPeriod(200);
//        beaconManager.setForegroundScanPeriod(180);
        beaconManager.getBeaconParsers().add(new BeaconParser()
                .setBeaconLayout("m:0-3=4c000215,i:4-19,i:20-21,i:22-23,p:24-24"));
        beaconManager.bind(this);
        btns = new ArrayList<Button>();
        btns.add((Button) findViewById(R.id.t0));
        btns.add((Button) findViewById(R.id.t1));
        btns.add((Button) findViewById(R.id.t2));
        btns.add((Button) findViewById(R.id.t3));
        btns.add((Button) findViewById(R.id.t4));
        btns.add((Button) findViewById(R.id.t5));
        btns.add((Button) findViewById(R.id.t6));
        btns.add((Button) findViewById(R.id.t7));
        for (int i = 0; i < btns.size(); i++) {
            Button btn = btns.get(i);
            btn.setOnClickListener(new TrainBtnListener(i, this, btns));
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_train, menu);
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
            public int i;

            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                if (beacons.size() > 0) {
                    if (cuTrainIndex == -1) return;
                    double dists[] = new double[BEACON_SIZE];
                    for (Beacon beacon : beacons) {
                        int id = getBeaconIndex(beacon.getId3().toInt());
                        if ( id > -1) {
                            dists[id] = beacon.getDistance();
                        }
                    }
                    String info ="";
                    for(double dist:dists){
                        info +=dist+",";
                    }
                    info += cuTrainIndex+"\n";
                    FileLogger.log("train.csv", info);
                    Log.d("train.csv", info);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            btns.get(cuTrainIndex).setText(dataCount[cuTrainIndex] + "");
                            if (dataCount[cuTrainIndex] == TRAIN_MAX_NUM){
                                cuTrainIndex = -1;
                            }
                            else
                                dataCount[cuTrainIndex]++;
                        }
                    });

                }
            }



        });

        try {

            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {
        }
    }

    public static int getBeaconIndex(int i) {
        switch (i){
            case 1:return 0;
            case 2:return 1;
            case 3:return 2;
            case 4:return 3;
            case 5:return 4;
            case 6:return 5;
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:return i-10;
            default:return -1;
        }
    }

    public void setCuTrainIndex(int cuTrainIndex) {
        this.cuTrainIndex = cuTrainIndex;
    }

    public int getCuTrainIndex() {
        return cuTrainIndex;
    }
}
