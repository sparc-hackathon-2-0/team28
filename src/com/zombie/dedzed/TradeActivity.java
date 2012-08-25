// Linear Accelerometer code adapted from example given at http://stackoverflow.com/questions/7858759/android-type-linear-acceleration-sensor-what-does-it-show 

package com.zombie.dedzed;

import winterwell.jtwitter.Twitter;
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

public class TradeActivity extends Activity {

	private TextView speedText;
	private static final String TAG = "TradeActivity";

	Twitter twitter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trade);
        
        
        
    }
    
}
