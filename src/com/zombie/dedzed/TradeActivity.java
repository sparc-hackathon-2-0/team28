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
import android.widget.TextView;

public class TradeActivity extends Activity implements SensorEventListener {

	//RUNNING DETECTOR SENSOR MGR
	private SensorManager mSensorManager;
	private Sensor mSensor;
	private float goSpeed;
	private TextView speedText;
	private static final String TAG = "TradeActivity";

	// Accelerometer Values
	private float lastX;
	private float lastY;
	private float lastZ;
	private long lastEvent;
	private long interval;

//	private float highestX;
//	private float highestY;
//	private float highestZ;
	
	private float vX;
	private float vY;
	private float vZ;
	private float vSum;
	private float vectorXY;
	
	private float calibTotalX;
	private float calibTotalY;
	private float calibTotalZ;
	private float calibX;
	private float calibY;
	private float calibZ;
	
	private int calibrationCount;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trade);
        
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        
        // Grab resources
        speedText = (TextView) findViewById(R.id.speedText); 
        
//        highestX = 0;
//    	highestY = 0;
//    	highestZ = 0;
        vX = 0;
        vY = 0;
        vZ = 0;
        vSum = 0;
        vectorXY = 0;
        calibrationCount = 0;
        calibTotalX = 0;
        calibTotalY = 0;
        calibTotalZ = 0;
        
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
    	// Initial calibration loop
    	if (calibrationCount < 1000) {
    		calibTotalX += se.values[SensorManager.DATA_X];
    		calibTotalY += se.values[SensorManager.DATA_Y];
    		calibTotalZ += se.values[SensorManager.DATA_Z];
    		
    		calibrationCount++;
    		speedText.setText("Calibrating... keep device still (" + calibrationCount +")");
    		return;
    	} else if (calibrationCount == 1000) {
    		calibX = calibTotalX / 1000;
    		calibY = calibTotalY / 1000;
    		calibZ = calibTotalZ / 1000;
    	}
    	
    	lastX = se.values[SensorManager.DATA_X] - calibX;
    	lastY = se.values[SensorManager.DATA_Y] - calibY;
    	lastZ = se.values[SensorManager.DATA_Z] - calibZ;
    	long now = System.currentTimeMillis();
    	interval = now - lastEvent;
    	lastEvent = now;
    	
    	vX = vX + (lastX * interval);
    	vY = vY + (lastY * interval);
    	vZ = vZ + (lastZ * interval);
    	
    	vectorXY = (float)Math.sqrt((vX*vX)+(vY*vY));
    	vSum = (float)Math.sqrt((vectorXY*vectorXY)+ (vZ*vZ));
    	
    	// Make all last values positive
    	
//    	// Update highest values
//    	if (lastX > highestX)
//    		highestX = lastX;
//    	if (lastY > highestY)
//    		highestY = lastY;
//    	if (lastZ > highestZ)
//    		highestZ = lastZ;
    	
    	
    	speedText.setText(Float.toString(vX) + "; " + 
    			Float.toString(vY) + "; " +
    			Float.toString(vZ) + "; " + 
    			Long.toString(interval) + "; \n" + 
    			Float.toString(vSum));
    }
}
