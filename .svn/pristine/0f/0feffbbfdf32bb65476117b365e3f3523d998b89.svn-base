package com.leocai.beaconlocalization.localization;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.leocai.beaconlocalization.R;
import com.leocai.beaconlocalization.uitls.FileLogger;
import com.leocai.beaconlocalization.view.ArrowView;

public class OrientationActivity extends Activity {
    /** Called when the activity is first created. */
    TextView textview=null;
	private SensorManager sm=null;
	private Sensor aSensor=null;
	private Sensor mSensor=null;
	 
	float[] accelerometerValues=new float[3];
	float[] magneticFieldValues=new float[3];
	float[] values=new float[3];
	float[] rotate=new float[9];
    private ArrowView viewArrow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orentation);
        sm=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        aSensor=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensor=sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sm.registerListener(myListener, aSensor, SensorManager.SENSOR_DELAY_GAME);
        sm.registerListener(myListener, mSensor, SensorManager.SENSOR_DELAY_GAME);

       viewArrow =  (ArrowView)findViewById(R.id.viewArrow);
        
        
    }

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		sm.unregisterListener(myListener);
	}
	final SensorEventListener myListener=new SensorEventListener(){

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSensorChanged(SensorEvent event) {
			// TODO Auto-generated method stub
			if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
				accelerometerValues=event.values;
			}
			if(event.sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD){
				magneticFieldValues=event.values;
			}
			
			SensorManager.getRotationMatrix(rotate, null, accelerometerValues, magneticFieldValues);
			SensorManager.getOrientation(rotate, values);
            viewArrow.setCurrentAngle(values[0]);
//            FileLogger.log(values[0]+"\n");
			//经过SensorManager.getOrientation(rotate, values);得到的values值为弧度
			//转换为角度
//			values[0]=(float)Math.toDegrees(values[0]);
//			textview.setText("x="+values[0]);
		}};
		
	
    
}