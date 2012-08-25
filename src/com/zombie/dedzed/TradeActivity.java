// Linear Accelerometer code adapted from example given at http://stackoverflow.com/questions/7858759/android-type-linear-acceleration-sensor-what-does-it-show 

package com.zombie.dedzed;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TradeActivity extends Activity implements SensorEventListener {

	//RUNNING DETECTOR SENSOR MGR
	private SensorManager mSensorManager;
	private Sensor mSensor;
	private float goSpeed;
	private TextView speedText;
	private static final String TAG = "TradeActivity";
	private Button buttonSpeedRead;

	// Accelerometer Values
	private float lastX;
	private float lastY;
	private float lastZ;
	private long lastEvent;
	private long interval;
	
	private float highestX;
	private float highestY;
	private float highestZ;
	
	private float vX;
	private float vY;
	private float vZ;
	private float vSum;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trade);
        
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        
        // Grab resources
        speedText = (TextView) findViewById(R.id.speedText); 
        
        highestX = 0;
    	highestY = 0;
    	highestZ = 0;
        
        //set the sensor measurement start time
        lastEvent = System.currentTimeMillis();
        
        //Bind the sensorEventListener
        mSensorManager.registerListener(this, 
        		mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION), 
        		SensorManager.SENSOR_DELAY_GAME);
        
    }
    
    public void onClick(View v) {
    	Log.d(TAG, "Reading Linear Speed");
		
    	while (true) {
			goSpeed = mSensor.getResolution();
			speedText.setText(Float.toString(goSpeed));
    	}
	}
    
    // IS THIS NEEDED?
    public void onAccuracyChanged(Sensor s, int ammt) {
    	
    }
    
    public void onSensorChanged(SensorEvent se) {
    	lastX = se.values[SensorManager.DATA_X];
    	lastY = se.values[SensorManager.DATA_Y];
    	lastZ = se.values[SensorManager.DATA_Z];
    	long now = System.currentTimeMillis();
    	interval = now - lastEvent;
    	lastEvent = now;
    	
    	// Make all last values positive
    	if (lastX < 0)
    		lastX = 0 - lastX;
    	if (lastY < 0)
    		lastY = 0 - lastY;
    	if (lastZ < 0)
    		lastZ = 0 - lastZ;
    	
    	// Update highest values
    	if (lastX > highestX)
    		highestX = lastX;
    	if (lastY > highestY)
    		highestY = lastY;
    	if (lastZ > highestZ)
    		highestZ = lastZ;
    	
    	
    	speedText.setText(Float.toString(highestX) + "; " + 
    			Float.toString(highestY) + "; " +
    			Float.toString(highestZ) + "; " + 
    			Long.toString(interval));
    }
}
