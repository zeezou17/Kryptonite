package com.zero.msgpack1;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.TextView;
import android.widget.Toast;
import android.telephony.SmsManager;
import android.util.Log;

public class GpsCalc extends Activity implements LocationListener {
	String text,address;
	
	// The minimum distance to change Updates in meters
private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
// The minimum time between updates in milliseconds
private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
protected LocationManager locationManager;
protected Context context;
protected boolean gps_enabled, network_enabled;
TextView txtLat;


@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_gps);

ContentResolver contentResolver = getContentResolver();
Cursor smsInboxCursor = contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null);
int indexBody = smsInboxCursor.getColumnIndex("body");
int indexAddress = smsInboxCursor.getColumnIndex("address");
if (indexBody < 0 || !smsInboxCursor.moveToFirst()) return;
address = smsInboxCursor.getString(indexAddress);


txtLat = (TextView) findViewById(R.id.textview1);

locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
// getting GPS status
gps_enabled = locationManager
.isProviderEnabled(LocationManager.GPS_PROVIDER);
// getting network status
network_enabled = locationManager
.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

if (gps_enabled) {
locationManager.requestLocationUpdates(
LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES,
MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
} else if (network_enabled) {
locationManager.requestLocationUpdates(
LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES,
MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
};
}

@Override
public void onLocationChanged(Location location) {
txtLat = (TextView) findViewById(R.id.textview1);
txtLat.setText("Latitude:" + location.getLatitude() + ", Longitude:"
+ location.getLongitude());
text ="Latitude, Longitude:"+ location.getLatitude() + ","
		+ location.getLongitude();

sendSms(address,text);
}

@Override
public void onProviderDisabled(String provider) {
Log.d("Latitude", "disable");
}

@Override
public void onProviderEnabled(String provider) {
Log.d("Latitude", "enable");
}

@Override
public void onStatusChanged(String provider, int status, Bundle extras) {
Log.d("Latitude", "status");
}

protected void sendSms(String toPhoneNo, String text){
	txtLat.setText("Sending..");
	 try {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(toPhoneNo, null, text, null, null);
        Toast.makeText(getApplicationContext(), "SMS sent.",
                Toast.LENGTH_LONG).show();
    } catch (Exception e) {
        Toast.makeText(getApplicationContext(),
                "Sending SMS failed.",
                Toast.LENGTH_LONG).show();
        e.printStackTrace();
    }
}
}