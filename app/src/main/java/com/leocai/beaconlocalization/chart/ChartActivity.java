package com.leocai.beaconlocalization.chart;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

//import com.github.mikephil.charting.charts.LineChart;
//import com.github.mikephil.charting.data.Entry;
//import com.github.mikephil.charting.data.LineData;
//import com.github.mikephil.charting.data.LineDataSet;
import com.leocai.beaconlocalization.R;
//import com.leocai.beaconlocalization.fingerprint.TableView;
import com.lidroid.xutils.ViewUtils;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class ChartActivity extends Activity implements BeaconConsumer {
//    ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();


    ArrayList<String> xVals = new ArrayList<String>();

    public static final int X_SIZE = 60;

    public static final int BEACON_SIZE = 16;

    private List<ChartBtnClickListener> listeners  = new ArrayList<ChartBtnClickListener>();


    {
//        data = new LineData(xVals, dataSets);
//        for(int i=0;i< X_SIZE;i++){
//            xVals.add(i+"");
//        }
//        newDataSets();
    }

    private BeaconManager beaconManager;
//    private LineData data;
//    private int[] currentXindex = new int[BEACON_SIZE];
//    private LineChart lineChart;
//    private int cuIndex=0;
//    private int cuMaxX=X_SIZE;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_chart);
//        ViewUtils.inject(this);
//        lineChart = (LineChart)findViewById(R.id.chartview);
//
//        lineChart.setData(data);
//        data.addEntry(new Entry(123, 0), 0);
//        data.addEntry(new Entry(123, 0), 0);
//        data.addEntry(new Entry(123,0),0);
//
//        lineChart.invalidate();

        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.getBeaconParsers().add(new BeaconParser()
                .setBeaconLayout("m:0-3=4c000215,i:4-19,i:20-21,i:22-23,p:24-24"));
        beaconManager.bind(this);

        final List<Button> btns = new ArrayList<Button>();
        btns.add((Button)findViewById(R.id.b0));
        btns.add((Button)findViewById(R.id.b1));
        btns.add((Button)findViewById(R.id.b2));
        btns.add((Button)findViewById(R.id.b3));
        btns.add((Button)findViewById(R.id.b4));
        btns.add((Button)findViewById(R.id.b5));
        btns.add((Button)findViewById(R.id.b6));
        btns.add((Button)findViewById(R.id.b7));
        btns.add((Button)findViewById(R.id.b8));
        btns.add((Button)findViewById(R.id.b9));
        btns.add((Button)findViewById(R.id.b10));
        btns.add((Button)findViewById(R.id.b11));
        btns.add((Button)findViewById(R.id.b12));
        btns.add((Button)findViewById(R.id.b13));
        btns.add((Button)findViewById(R.id.b14));
        btns.add((Button)findViewById(R.id.b15));
//        for(int i =0;i<btns.size();i++){
////            ChartBtnClickListener clickListener = new ChartBtnClickListener(data, lineChart,i, btns.get(i));
//            btns.get(i).setOnClickListener(clickListener);
//            listeners.add(clickListener);
//        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chart, menu);
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
                    //addLimitBeacons(beacons);

//                    double []beaconrssi = new double[BEACON_SIZE];
//                    double []beacondist = new double[BEACON_SIZE];
//                    for (Beacon beacon : beacons) {
//                        if(beacon.getId3().toInt()<beaconrssi.length){
//                            beaconrssi[beacon.getId3().toInt()] = beacon.getRssi()+100;
//                            beacondist[beacon.getId3().toInt()] = beacon.getDistance();
//                            data.addEntry(new Entry((float) beacon.getDistance(),cuIndex%X_SIZE), beacon.getId3().toInt());
//                        }
//                    }
//                    cuIndex++;
//                    if(cuIndex%X_SIZE==0) {
//                        dataSets.clear();
//                        newDataSets();
//                    }
//                    lineChart.setData(data);
//                    lineChart.postInvalidate();
                }
            }

        });

        try {

            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {
        }
    }

    private void newDataSets() {
        for(int i=0;i< BEACON_SIZE;i++){
//            LineDataSet lineDataSet = new LineDataSet(new ArrayList<Entry>(), "b" + i);
//            lineDataSet.setDrawValues(false);
//            lineDataSet.setCircleSize(0);
//            lineDataSet.setColor(Color.argb(255,(10+70*i)%255,(100+100*i)%255,(50+70*i)%255));
//            dataSets.add(lineDataSet);
//            if(listeners.size()==BEACON_SIZE)
//            listeners.get(i).checkShowLine();
        }
    }
}
