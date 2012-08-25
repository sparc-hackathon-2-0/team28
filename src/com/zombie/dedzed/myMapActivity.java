//This source happily doctored from http://hejp.co.uk/android/android-gps-example/ on Aug 25, 2012
//Responsible party: Mike "Da man" Havens

package com.zombie.dedzed;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class myMapActivity extends Activity implements LocationListener {

    /* this class implements LocationListener, which listens for both
      * changes in the location of the device and changes in the status
      * of the GPS system.
      * */

    static final String tag = "myMapActivity"; // for Log


    TextView txtInfo;
    LocationManager lm;
    StringBuilder sb;
    int noOfFixes = 0;

	private long lastLocEvent;
	private double lastLong;
	private double lastLat;
	private boolean firstLoc;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        /* get TextView to display the GPS data */
        txtInfo = (TextView) findViewById(R.id.textInfo);

        /* the location manager is the most vital part it allows access
           * to location and GPS status services */
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        
        firstLoc = true;
    }

    @Override
    protected void onResume() {
        /*
           * onResume is is always called after onStart, even if the app hasn't been
           * paused
           *
           * add location listener and request updates every 1000ms or 10m
           */
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        /* GPS, as it turns out, consumes battery like crazy */
        lm.removeUpdates(this);
        super.onPause();
    }
    
    private double getDistance(double nowLong, double nowLat, double lLong, double lLat) {
    	double changeLong = nowLong - lLong;
    	double changeLat = nowLat - lLat;
    	
    	return (10000 * Math.sqrt((changeLong*changeLong) + (changeLat*changeLat)));
    	
    }

    //@Override
    public void onLocationChanged(Location location) {

    	double currLong = location.getLongitude();
    	double currLat = location.getLatitude();
    	double distance;
    	
    	if(firstLoc) {
    		distance = 0.0;
    		firstLoc = false;
    	} else {
    		distance = this.getDistance(currLong, currLat, lastLong, lastLat);
    	}
    	
    	lastLong = currLong;
    	lastLat = currLat;
    	
        Log.v(tag, "Location Changed");

        sb = new StringBuilder(512);

        noOfFixes++;

        /* display some of the data in the TextView */

        sb.append("No. of Fixes: ");
        sb.append(noOfFixes);
        sb.append('\n');
        sb.append('\n');

        sb.append("Distance trav: ");
        sb.append(distance);
        sb.append('\n');
        
        sb.append("Longitude: ");
        sb.append(currLong);
        sb.append('\n');

        sb.append("Latitude: ");
        sb.append(currLat);
        sb.append('\n');

//        sb.append("Altitiude: ");
//        sb.append(location.getAltitude());
//        sb.append('\n');

        sb.append("Accuracy: ");
        sb.append(location.getAccuracy());
        sb.append('\n');

        sb.append("Timestamp: ");
        sb.append(location.getTime());
        sb.append('\n');

        txtInfo.setText(sb.toString());
    }

    //@Override
    public void onProviderDisabled(String provider) {
        /* this is called if/when the GPS is disabled in settings */
        Log.v(tag, "Disabled");

        /* bring up the GPS settings */
        Intent intent = new Intent(
                android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
    }

    //@Override
    public void onProviderEnabled(String provider) {
        Log.v(tag, "Enabled");
        Toast.makeText(this, "GPS Enabled", Toast.LENGTH_SHORT).show();

    }

    //@Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        /* This is called when the GPS status alters */
        switch (status) {
            case LocationProvider.OUT_OF_SERVICE:
                Log.v(tag, "Status Changed: Out of Service");
                Toast.makeText(this, "Status Changed: Out of Service",
                        Toast.LENGTH_SHORT).show();
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                Log.v(tag, "Status Changed: Temporarily Unavailable");
                Toast.makeText(this, "Status Changed: Temporarily Unavailable",
                        Toast.LENGTH_SHORT).show();
                break;
            case LocationProvider.AVAILABLE:
                Log.v(tag, "Status Changed: Available");
                Toast.makeText(this, "Status Changed: Available",
                        Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onStop() {
//        /* may as well just finish since saving the state is not important for this toy app */
//        finish();  MIKE TODO: WTF TO DO HERE, AARON?
        super.onStop();
    }
}
/////-----------------------//



//package com.zombie.dedzed;
//import android.os.Bundle;
//import com.google.android.maps.MapActivity;
//import com.google.android.maps.MapView;
//
//public class myMapActivity extends MapActivity {
//@Override
//public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.map);
//        MapView mapview = (MapView) findViewById(R.id.mapview);
//        mapview.setBuiltInZoomControls(true);
//}
//    @Override
//    protected boolean isRouteDisplayed() {
//        return false;
//    }
//}