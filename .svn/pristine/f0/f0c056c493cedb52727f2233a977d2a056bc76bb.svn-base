package com.leocai.beaconlocalization;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.leocai.beaconlocalization.R;
import com.leocai.beaconlocalization.localization.BeaconLocalization;
import com.leocai.beaconlocalization.localization.BeaconLocalizationImpl;
import com.leocai.beaconlocalization.localization.LocalizationEvaluation;
import com.leocai.beaconlocalization.test.ILogger;
import com.leocai.beaconlocalization.test.TestCaseLocalization;
import com.leocai.beaconlocalization.uitls.FileLogger;
import com.leocai.beaconlocalization.view.LocalizationMapView;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class BeaconMapActivity extends Activity implements BeaconConsumer, Observer {

    protected static final String TAG = "RangingActivity";
    private BeaconManager beaconManager;
    private BeaconLocalization beaconLocalization;
    private TextView textViewConsole;

    private LocalizationEvaluation localizationEvaluation;

    @ViewInject(R.id.btn_kn4)
    Button btn_kn4;
    @ViewInject(R.id.btn_kn6)
    Button btn_kn6;
    @ViewInject(R.id.btn_singleCompare)
    Button btn_singleCompare;



    @OnClick(R.id.btn_right)
    void btn_right(View view) {
        localizationEvaluation.addCollect();
    }

    @OnClick(R.id.btn_wrong)
    void btn_wrong(View view) {
        localizationEvaluation.addFalse();
    }

    @OnClick(R.id.btn_clear)
    void btn_clear(View view){
        localizationEvaluation.clearAnswers();
    }

    @OnClick(R.id.btn_kn4)
    void btn_kn4(View view){
        beaconLocalization.setAlgorithm(BeaconLocalization.ALGOR_KNEAR_4);
        setAlgBtnActive(btn_kn4);
    }

    private void setAlgBtnActive(Button btn) {
        btn_kn4.setBackgroundColor(Color.GRAY);
        btn_singleCompare.setBackgroundColor(Color.GRAY);
        btn_kn6.setBackgroundColor(Color.GRAY);
        btn.setBackgroundColor(Color.BLUE);
    }

    @OnClick(R.id.btn_singleCompare)
    void btn_singleCompare(View view){
        beaconLocalization.setAlgorithm(BeaconLocalization.ALGOR_SINGLE_COMPARE);
        setAlgBtnActive(btn_singleCompare);
    }

    @OnClick(R.id.btn_kn6)
    void btn_btn_kn6(View view){
        beaconLocalization.setAlgorithm(BeaconLocalization.ALGOR_KNEAR_6);
        setAlgBtnActive(btn_kn6);
    }

    @OnClick(R.id.btn_test)
    void btn_test(View view){
        TestCaseLocalization.testAllMap(beaconLocalization);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacon_map);
        ViewUtils.inject(this);
        beaconLocalization = new BeaconLocalizationImpl();
        beaconLocalization.setSingleBeaconWidth(4);
        beaconLocalization.setWidthBeaconNum(4);
        beaconLocalization.setTotalBeaconNum(16);
        LocalizationMapView localizationMapView = (LocalizationMapView) findViewById(R.id.localizationMapView);
        textViewConsole = (TextView) findViewById(R.id.tvConsole);
        localizationMapView.setBeaconLocalization(beaconLocalization);
//        addContentView(localizationMapView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        TestCaseLocalization.testAllMap(beaconLocalization);
//        TestCaseLocalization.testRandomMap(beaconLocalization);

        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.getBeaconParsers().add(new BeaconParser()
                .setBeaconLayout("m:0-3=4c000215,i:4-19,i:20-21,i:22-23,p:24-24"));
        beaconManager.bind(this);
        ILogger.getInstance().addObserver(this);

        localizationEvaluation = new LocalizationEvaluation(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_beacon_map, menu);
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

            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                if (beacons.size() > 0) {
                    Log.i(TAG, "" + beacons.size() + " beacons find!");
                    context = "" + beacons.size() + " beacons\n";
                    //addLimitBeacons(beacons);
                    double []beaconrssi = new double[16];
                    double []beacondist = new double[16];


                    for (Beacon beacon : beacons) {
                        Log.i(TAG, beacon.getId3().toInt() + ":" + String.format("%d", beacon.getRssi()) + "db");
                        context += beacon.getBluetoothName() + ":" + String.format("%d", beacon.getRssi()) + "m\n";
                        beaconLocalization.addCurrentBeaconInfo(beacon.getId3().toInt(), beacon.getDistance());

                        if(beacon.getId3().toInt()<beaconrssi.length){
                            beaconrssi[beacon.getId3().toInt()] = beacon.getRssi()+100;
                            beacondist[beacon.getId3().toInt()] = beacon.getDistance();
                        }
//                        FileLogger.log(beacon);
                    }
                    String text = "";
                    String textDist = "";
                    for(double rssi:beaconrssi){
                        text+=rssi+",";
                    }
                    for(double dist:beacondist){
                        textDist+=String.format("%.2f",dist)+",";
                    }
                    text = text.substring(0,text.length()-1);
                    text+="\n";
                    textDist = textDist.substring(0,text.length()-1);
                    textDist+="\n";

                    FileLogger.log("rssi.csv",text);
                    FileLogger.log("dist.csv",textDist);
                    beaconLocalization.customNotify();


//                    Log.i(TAG, "The first beacon I see is about " + beacons.iterator().next().getDistance() + " meters away.");
                }
            }

            private void addLimitBeacons(Collection<Beacon> beacons) {
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
                int limitSize = lbeacons.size() <= 6 ? lbeacons.size() : 6;
                for (int i = 0; i < limitSize; i++) {
                    Beacon beacon = lbeacons.get(i);
//                        Log.i(TAG, beacon.getId3().toInt() + ":" + String.format("%.2f", beacon.getDistance()) + "m");
                    context += beacon.getBluetoothName() + ":" + String.format("%.2f", beacon.getDistance()) + "m\n";
//                        Log.d(TAG,beacon.getTxPower()+"");
                    ILogger.getInstance().log("" + beacon.getTxPower());
                    beaconLocalization.addCurrentBeaconInfo(beacon.getId3().toInt(), beacon.getDistance());
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
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //textViewConsole.setText(ILogger.getInstance().getLogInfo());
                String rightInfo = "";
                rightInfo += "rightNum:" + localizationEvaluation.getRightAnswers() + "\n";
                rightInfo += "wrongNum:" + localizationEvaluation.getWrongAnswers() + "\n";
                rightInfo += "Percent:" + String.format("%.2f", localizationEvaluation.getRightPercent()) + "%\n";
                textViewConsole.setText(rightInfo);
            }
        });
    }
}
