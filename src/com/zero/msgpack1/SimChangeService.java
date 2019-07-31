package com.zero.msgpack1;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class SimChangeService extends Service{
	
	public String newsimno;
	
    public String initialsimno;
    
	private static final String TAG = null;
	
	public static String frndno;
	
	public String imeino;

	
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
    public void onStart(Intent intent, int startid)
    {
		TelephonyManager mTelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
	    
		DatabaseOperations DOP = new DatabaseOperations(this);
		Cursor CR = DOP.getInformation(DOP);

        Toast.makeText(this, "My Service Started", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onStart");
        CR.moveToFirst();
		newsimno = mTelephonyMgr.getSimSerialNumber();
		
		boolean simchangestatus = false;
		
		initialsimno = CR.getString(0);
		
		final String PREFS_NAME6 = "MyPrefsFiles6";

		//SharedPreferences smsSettings6 = getSharedPreferences(PREFS_NAME6, 0);
		//if (smsSettings6.getBoolean("my_first_time", true)) {
			
		if(!newsimno.equals(initialsimno))
			{
				simchangestatus = true;
			}
			
			
			//smsSettings6.edit().putBoolean("my_first_time", false).commit();
		//}
		

	if(simchangestatus)
	{
		
		
		
		    DatabaseOperationsOI dop1 = new DatabaseOperationsOI(this);
			Cursor CUR1 = dop1.getInformation1(dop1);
		    CUR1.moveToFirst();
		    frndno=CUR1.getString(1);
		    imeino=CR.getString(1);
		
	   //Toast.makeText(getBaseContext(), "Sim has been changed!", Toast.LENGTH_LONG).show();
	   //tv.setText("the changed sim is"+newsimno+" the initial sim no was "+initialsimno);
	    try {
	        SmsManager smsManager = SmsManager.getDefault();
	        smsManager.sendTextMessage("+91"+frndno, null, "SIM Changed. Sim no is "+newsimno+" and IMEI of device is:"+imeino, null, null);
	        Log.d(TAG, "MsgSent");
	        stopSelf();
	        
	        
	        //Toast.makeText(getApplicationContext(), "SMS sent.",
	               // Toast.LENGTH_LONG).show();
	    } catch (Exception e) {
	        /*Toast.makeText(getApplicationContext(),
	                "Sending SMS failed.",
	                Toast.LENGTH_LONG).show();*/
	        e.printStackTrace();
	    }
	
    }
	
	

    }
}
