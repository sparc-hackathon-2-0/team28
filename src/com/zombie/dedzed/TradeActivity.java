// Linear Accelerometer code adapted from example given at http://stackoverflow.com/questions/7858759/android-type-linear-acceleration-sensor-what-does-it-show 

package com.zombie.dedzed;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class TradeActivity extends Activity {

	// private TextView speedText;
	private static final String TAG = "TradeActivity";
	private Twitter twitter;
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trade);
        
        twitter = new Twitter("zedfreeradio", "zedfree13"); //
		twitter.setAPIRootUrl("http://yamba.marakana.com/api");
        
        String status = "ZED FREE RADIO, Broadcasting now!";
		new PostToTwitter().execute(status); //
		Log.d(TAG, "firstBroadcast");
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
			Toast.makeText(TradeActivity.this, result, Toast.LENGTH_LONG).show();
		}
	}
    
}
