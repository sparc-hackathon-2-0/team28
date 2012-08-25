// Linear Accelerometer code adapted from example given at http://stackoverflow.com/questions/7858759/android-type-linear-acceleration-sensor-what-does-it-show 

package com.zombie.dedzed;

import java.util.List;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TradeActivity extends Activity implements OnClickListener {

	// private TextView speedText;
	private static final String TAG = "TradeActivity";
	public Twitter twitter;
	private List<Twitter.Status> timeline;
	private String timelineText;

	TextView statusView;
	
	private Button testButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trade);
        
        twitter = new Twitter("zedfreeradio", "zedfree13"); //
		twitter.setAPIRootUrl("http://yamba.marakana.com/api");
		
		timelineText = "";
		testButton = (Button) findViewById(R.id.buttonStatTest);
		testButton.setOnClickListener(this);
		
		statusView = (TextView) findViewById(R.id.speedText);
        
        String status = "ZED FREE RADIO, Broadcasting still!";
		new PostToTwitter().execute(status); //
		Log.d(TAG, "firstBroadcast");
    }
    
    public void onClick(View v) {
    		//Get the timeline from cloud
		
    	timelineText = "gettingStatuses...";
//    	try{
//			timeline = twitter.getHomeTimeline();
//		} catch (TwitterException e) {
//			Log.e(TAG, "Failure to connect to twitter service", e);
//		}
//		
//		for (Twitter.Status status : timeline) {
//			Log.d(TAG, status.text);
//			timelineText = ("" + timelineText + status.text);
//		}
//    	
//		Log.d(TAG, "onClicked");
    	
    	//statusView.setText(text)
    	
	}
    
 // TODO Attribution!
    class PostToTwitter extends AsyncTask<String, Integer, String> { //
		// Called to initiate the background activity
		@Override
		protected String doInBackground(String... statuses) { //
			try {
				Twitter.Status status = twitter.updateStatus(statuses[0]);
				return status.text;
			} catch (TwitterException e) {
				Log.e(TAG, e.toString());
				e.printStackTrace();
				return "Failed to post";
			}
		}
		
		// Called when there's a status to be updated
		@Override
		protected void onProgressUpdate(Integer... values) { //
			super.onProgressUpdate(values);
			// Not used in this case
		}
		
		// Called once the background activity has completed
		@Override
		protected void onPostExecute(String result) {
			// Toast.makeText(ZedApplication.this, result, Toast.LENGTH_LONG).show();
		}
	}
	

    
    
    
}
