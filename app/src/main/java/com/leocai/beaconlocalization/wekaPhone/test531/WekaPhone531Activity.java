package com.leocai.beaconlocalization.wekaPhone.test531;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.leocai.beaconlocalization.R;
import com.leocai.beaconlocalization.train.TrainActivity;
import com.leocai.beaconlocalization.train.train531.Train531Activity;
import com.leocai.beaconlocalization.uitls.FileLogger;
import com.leocai.beaconlocalization.view.LocalizationView;
import com.leocai.beaconlocalization.wekaPhone.BufferLocalization;
import com.leocai.beaconlocalization.wekaPhone.WekaAlgorithm;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WekaPhone531Activity extends Activity implements BeaconConsumer {

    private static final String TAG = "WekaActivity";
    public static final String MODEL1 = "phone531-3.model";
    public static final String MODEL2 = "phone531-2.model";
    private BeaconManager beaconManager;
    private static final int BEACON_SIZE = 14;

    private WekaAlgorithm wekaAlgorithm;
    private double predict;

    private List<View> aeroViews = new ArrayList<View>();

    private BufferLocalization bufferLocalization = new BufferLocalization(3);

    private LocalizationView localizationView;

    @ViewInject(R.id.tv_info_top)
    private TextView tv_info_top;

    @OnClick(R.id.btn_model1)
    void btn_model1(View view) {
        wekaAlgorithm = new WekaAlgorithm();
        try {
            wekaAlgorithm.init(this, MODEL1);
            Toast.makeText(this, "modle1", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.btn_model2)
    void btn_model2(View view) {
        wekaAlgorithm = new WekaAlgorithm();
        try {
            wekaAlgorithm.init(this, MODEL2);
            Toast.makeText(this, "modle2", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_weka);
        ViewUtils.inject(this);

        wekaAlgorithm = new WekaAlgorithm();
        localizationView = (LocalizationView) findViewById(R.id.localizationView);
        localizationView.setMapWidth(2);
        localizationView.setMapHeigth(2);
        try {
            wekaAlgorithm.init(this, MODEL1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        aeroViews.add(findViewById(R.id.a0));
        aeroViews.add(findViewById(R.id.a1));
        aeroViews.add(findViewById(R.id.a2));
        aeroViews.add(findViewById(R.id.a3));
        aeroViews.add(findViewById(R.id.a4));
        aeroViews.add(findViewById(R.id.a5));
        aeroViews.add(findViewById(R.id.a6));
        aeroViews.add(findViewById(R.id.a7));
        initAeroColor();


        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.getBeaconParsers().add(new BeaconParser()
                .setBeaconLayout("m:0-3=4c000215,i:4-19,i:20-21,i:22-23,p:24-24"));
        beaconManager.bind(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weka, menu);
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
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                if (beacons.size() > 0) {
                    //addLimitBeacons(beacons);
                    double dists[] = new double[BEACON_SIZE + 1];
                    for (Beacon beacon : beacons) {
                        if(beacon.getId2().toInt()!=1) continue;
                        int id = Train531Activity.getBeaconIndex(beacon.getId3().toInt());
                        if (id > -1) {
                            dists[id] = beacon.getDistance();
                        }
                    }
//                    predict = wekaAlgorithm.predict(dists)[0];
                    final double[] results = wekaAlgorithm.predict(dists);
                    localizationView.setPos((int) results[0]);
                    localizationView.setProb((float) results[1]);
                    localizationView.postInvalidate();


//                    bufferLocalization.addPredict((int)predict);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            if (predict == -1) return;
                            tv_info_top.setText("" + (int) results[0] + ":" + String.format("%.4f", results[1]) + ";" + (int) results[2] + ":" + String.format("%.4f", results[3]));
                            initAeroColor();
                            FileLogger.log("test", "" + predict);
//                            aeroViews.get(bufferLocalization.getBufferPredict()).setBackgroundColor(Color.RED);
                            FileLogger.log("test", "4");
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

    private void initAeroColor() {
        for (View v : aeroViews) {
            v.setBackgroundColor(Color.GRAY);
        }
    }
}
