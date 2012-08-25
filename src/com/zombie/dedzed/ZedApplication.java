package com.zombie.dedzed;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;
import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class ZedApplication extends Application {
	private static final String TAG = "ZedApplication";

	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(TAG, "onCreated");
		
		
		
       
	}
	
	@Override
	public void onTerminate() {
		super.onTerminate();
		Log.i(TAG, "onTerminated");
	}

}
