package com.leocai.beaconlocalization;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.leocai.beaconlocalization.beacontool.BeaconToolActivity;
import com.leocai.beaconlocalization.chart.ChartActivity;
import com.leocai.beaconlocalization.demo.DemoAccelerometer;
import com.leocai.beaconlocalization.demo.EstimoteDemo;
import com.leocai.beaconlocalization.fingerprint.FingerActivity;
import com.leocai.beaconlocalization.localization.KNearActivity;
import com.leocai.beaconlocalization.sensortag.TrainTagActivity;
import com.leocai.beaconlocalization.sensortag.DeviceScanActivity;
import com.leocai.beaconlocalization.sensortag.wekaTag.SensorTagWekaActivity;
import com.leocai.beaconlocalization.train.TrainActivity;
import com.leocai.beaconlocalization.train.train531.Train531Activity;
import com.leocai.beaconlocalization.wekaPhone.WekaPhoneActivity;
import com.leocai.beaconlocalization.wekaPhone.test531.WekaPhone531Activity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;


public class StartActivity extends Activity {

    @OnClick(R.id.btnKNear)
    void btnKNear(View view){
        startActivity(new Intent(StartActivity.this,KNearActivity.class));
    }
    @OnClick(R.id.btn_chart)
    void btn_chart(View view){
        startActivity(new Intent(StartActivity.this,ChartActivity.class));
    }
    @OnClick(R.id.btn_finger)
    void btn_finger(View view){
        startActivity(new Intent(StartActivity.this,FingerActivity.class));
    }
    @OnClick(R.id.btn_train)
    void btn_train(View view){
        startActivity(new Intent(StartActivity.this,TrainActivity.class));
    }
    @OnClick(R.id.btn_weka)
    void btn_weka(View view){
        startActivity(new Intent(StartActivity.this,WekaPhoneActivity.class));
    }
    @OnClick(R.id.btn_sensortag)
    void btn_sensortag(View view){
        startActivity(new Intent(StartActivity.this,TrainTagActivity.class));
    }

    @OnClick(R.id.btn_beaconinfo)
    void btn_beaconinfo(View view){
        startActivity(new Intent(StartActivity.this,BeaconToolActivity.class));
    }
    @OnClick(R.id.btn_weka_tag)
    void btn_weka_tag(View view){
        startActivity(new Intent(StartActivity.this,SensorTagWekaActivity.class));
    }
    @OnClick(R.id.btn_train_531)
    void btn_train_531(View view){
        startActivity(new Intent(StartActivity.this,Train531Activity.class));
    }
    @OnClick(R.id.btn_test_531)
    void btn_test_531(View view){
        startActivity(new Intent(StartActivity.this,WekaPhone531Activity.class));
    }
    @OnClick(R.id.btn_acce)
    void btn_acce(View view){
        startActivity(new Intent(StartActivity.this,DemoAccelerometer.class));
    }
    @OnClick(R.id.btn_estimote)
    void btn_estimote(View view){
        startActivity(new Intent(StartActivity.this,EstimoteDemo.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ViewUtils.inject(this);
        findViewById(R.id.btnBeaconMap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this,BeaconMapActivity.class));
            }
        });
        findViewById(R.id.btnSensorTag).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this,DeviceScanActivity.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
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
