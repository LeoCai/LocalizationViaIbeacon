package com.leocai.beaconlocalization.wekaPhone;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.leocai.beaconlocalization.R;
import com.leocai.beaconlocalization.train.TrainActivity;
import com.leocai.beaconlocalization.uitls.FileLogger;
import com.leocai.beaconlocalization.view.LocalizationView;
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

public class WekaPhoneActivity extends Activity implements BeaconConsumer, SensorEventListener {

    private static final String TAG = "WekaActivity";
    public static final String MODEL1 = "phone10.model";
    public static final String MODEL2 = "phone6.model";
    private org.altbeacon.beacon.BeaconManager beaconManager;
    private static final int BEACON_SIZE = 14;

    private WekaAlgorithm wekaAlgorithm;
    private double predict;

    private List<View> aeroViews = new ArrayList<View>();

    private BufferArrayLocaization bufferLocalization = new BufferArrayLocaization(3);


    private LocalizationView localizationView;

//    private static final String TAG = "Estimote";


    @ViewInject(R.id.tv_info_top)
    private TextView tv_info_top;
    private boolean caculatedAlready = true;
    private Region region =new Region("myRangingUniqueId", null, null, null);

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

    private SensorManager mSensorManager;
    private Sensor mSensor;

    double startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_weka);
        ViewUtils.inject(this);

        wekaAlgorithm = new WekaAlgorithm();
        localizationView = (LocalizationView) findViewById(R.id.localizationView);
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
//        beaconManager.setForegroundBetweenScanPeriod(0);
//        beaconManager.setForegroundScanPeriod(200);



        beaconManager.getBeaconParsers( ).add(new BeaconParser()
                .setBeaconLayout("m:0-3=4c000215,i:4-19,i:20-21,i:22-23,p:24-24"));
        beaconManager.bind(this);




//        beaconManager = BeaconManager.getInstanceForApplication(this);
//        beaconManager.getBeaconParsers().add(new BeaconParser()
//                .setBeaconLayout("m:0-3=4c000215,i:4-19,i:20-21,i:22-23,p:24-24"));
//        beaconManager.bind(this);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        startTime = 1.0*System.nanoTime()/1000000000;
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.setRangeNotifier(new RangeNotifier() {
            String context;
            public int i;

            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                if(!caculatedAlready) return;
                caculatedAlready = false;
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        tv_info_top.setText("isReadyCalulate");
//                    }
//                });

                if (beacons.size() > 0) {
//                    FileLogger.log("time.csv",String.format("%.2f",1.0*System.nanoTime()/1000000000 - startTime)+":startCaculate\n");
                    //addLimitBeacons(beacons);
                    double dists[] = new double[BEACON_SIZE + 1];
                    for (Beacon beacon : beacons) {
                        int id = TrainActivity.getBeaconIndex(beacon.getId3().toInt());
                        if (id > -1) {
                            dists[id] = beacon.getDistance();
                        }
                    }
//                    predict = wekaAlgorithm.predict(dists)[0];
                    final double[] results = wekaAlgorithm.predict(dists);
//                    predict = (int) results[0];
                    bufferLocalization.addPredict(results);
                    double[] predictResult = bufferLocalization.getBufferPredict();

                    if(predictResult!=null){
                    localizationView.setPos((int) predictResult[0]);
//                    localizationView.setProb((float) results[1]);
                    localizationView.setProb((float) predictResult[1]);
                    localizationView.postInvalidate();
                    }

//                    HumanState.setState(HumanState.CACULATED);
//                    FileLogger.log("time.csv",String.format("%.2f",1.0*System.nanoTime()/1000000000 - startTime)+":endCaculate;\n");

//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            if(HumanState.getState()==HumanState.CACULATED)
//                                tv_info_top.setText("CACULATED " + HumanState.getCaculateCount());
//                            else if(HumanState.getState() == HumanState.STOP_CACULATE){
//                                tv_info_top.setText("STOP CACULATED");
//                            }
//                        }
//                    });
//                    if(HumanState.getCaculateCount() == bufferLocalization.bufferSize){
//                        bufferLocalization.clear();
//                    }



//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            if (predict == -1) return;
//                            tv_info_top.setText("" + (int) results[0] + ":" + String.format("%.4f", results[1]) + ";" + (int) results[2] + ":" + String.format("%.4f", results[3]));
//                            initAeroColor();
//                            FileLogger.log("test", "" + predict);
//                            aeroViews.get(bufferLocalization.getBufferPredict()).setBackgroundColor(Color.RED);
//                            FileLogger.log("test", "4");
//                        }
//                    });
                    caculatedAlready = true;
//                    bufferLocalization.clear();


                }

            }



        });

        try {

            beaconManager.startRangingBeaconsInRegion(region);
        } catch (RemoteException e) {
        }
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
        switch (id){
            case R.id.buffer1:
                bufferLocalization = new BufferArrayLocaization(1);
                break;
            case R.id.buffer2:
                bufferLocalization = new BufferArrayLocaization(2);

                break;
            case R.id.buffer3:
                bufferLocalization = new BufferArrayLocaization(3);

                break;
            case R.id.buffer4:
                bufferLocalization = new BufferArrayLocaization(4);

                break;
            case R.id.buffer5:
                bufferLocalization = new BufferArrayLocaization(5);

                break;

        }

        return super.onOptionsItemSelected(item);
    }



    private void initAeroColor() {
        for (View v : aeroViews) {
            v.setBackgroundColor(Color.GRAY);
        }
    }


    int timeCount = 0;
    boolean stoped = false;

    @Override
    public void onSensorChanged(SensorEvent event) {
        final float x = event.values[0];
        final float y = event.values[1];
        final float z = event.values[2];
        double accer = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
        timeCount++;
        if(timeCount % 30 == 0){
            try {
                beaconManager.stopRangingBeaconsInRegion(region);
                stoped = true;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        if(stoped&&timeCount % 5 == 0){
            try {
                beaconManager.startRangingBeaconsInRegion(region);
                stoped =false;
            } catch (RemoteException e) {
                e.printStackTrace();
            }


        }
//        if(accer>11){
//            HumanState.setState(HumanState.WALKING);
//            tv_info_top.setText("WLAKING");
//        }else{
//            HumanState.setState(HumanState.STAND);
//            if(HumanState.getState()==HumanState.STAND)
//                tv_info_top.setText("STAND");
//

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);

    }
}
