package com.zombie.dedzed;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SurvivalActivity extends Activity implements OnClickListener {

	private static final String TAG = "SurvivalActivity";
	
	Button mapButton, wallButton, tradeButton;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survival);
        
        mapButton = (Button) findViewById(R.id.buttonMap);
        wallButton = (Button) findViewById(R.id.buttonWall);
        tradeButton = (Button) findViewById(R.id.buttonTrade);
        
        mapButton.setOnClickListener(this);
        wallButton.setOnClickListener(this);
        tradeButton.setOnClickListener(this);
    }
    
	// Called when Map button is clicked //
    public void onClick(View v) {
    	Log.d(TAG, "onMapClicked");
		switch(v.getId()) {
		case R.id.buttonMap:
			startActivity(new Intent(this, MapActivity.class));
			Log.d(TAG, "Launched MapActivity");
		break;
		case R.id.buttonWall:
			startActivity(new Intent(this, WallActivity.class));
			Log.d(TAG, "Launched WallActivity");
		break;
		case R.id.buttonTrade:
			startActivity(new Intent(this, TradeActivity.class));
			Log.d(TAG, "Launched TradeActivity");
		break;
		}
		
		//NEED 2 LAUNCH MAP ACTIVITY!
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_survival, menu);
        return true;
    }
}
