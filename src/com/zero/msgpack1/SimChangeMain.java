package com.zero.msgpack1;

import com.zero.msgpack1.DatabaseOperations;



import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class SimChangeMain extends Activity {

	public static String initialsimno;
	public static String newsimno;
	public static String imei;
	public static String imsi;
	Context ctx = this;
	
	public static String frndno;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simchange);
		
		
        TextView tv = (TextView)findViewById(R.id.textView1); 
        TelephonyManager mTelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		
		final String PREFS_NAME3 = "MyPrefsFile3";

		SharedPreferences settings3 = getSharedPreferences(PREFS_NAME3, 0);
		DatabaseOperations DOP = new DatabaseOperations(ctx);
		Cursor CR = DOP.getInformation(DOP);
		
		
		if (settings3.getBoolean("my_first_time", true)) {
		    //the app is being launched for first time, do something        
		    Log.d("Comments", "First time");
		    Toast.makeText(getBaseContext(), "First Time launch", Toast.LENGTH_LONG).show();
		   
		    
		    initialsimno = mTelephonyMgr.getSimSerialNumber();
		    imsi = mTelephonyMgr.getSubscriberId();
		    imei = mTelephonyMgr.getDeviceId(); 
		    DatabaseOperations DB = new DatabaseOperations(ctx);
			DB.putInformation(DB,initialsimno,imei,imsi );
			CR.moveToFirst();
			
			tv.setText("sim no "+CR.getString(0)+"\n imei "+CR.getString(1)+"\n imsi "+CR.getString(2));
		             // first time task

		    // record the fact that the app has been started at least once
		   // settings.edit().putBoolean("my_first_time", false).commit(); 
		    Toast.makeText(getBaseContext(), "Recorded initial sim number "+initialsimno, Toast.LENGTH_LONG).show();
		    
		    Intent smsAct = new Intent(this , SmsActivity.class);
		    settings3.edit().putBoolean("my_first_time", false).commit();
		    startActivity(smsAct);
		}
		
		else
		{
			/*Log.d("Comments", "Not First time");
			Toast.makeText(getBaseContext(), "Regular launch", Toast.LENGTH_LONG).show();
			newsimno = mTelephonyMgr.getSimSerialNumber();
			
		    if(!(initialsimno.equals(newsimno)))
		    {
        Toast.makeText(getBaseContext(), "Sim card changed", Toast.LENGTH_LONG).show();
		    tv.setText("the changed sim is"+newsimno);
		    try {
		        SmsManager smsManager = SmsManager.getDefault();
		        smsManager.sendTextMessage("+919930499340", null, "SIM Changed", null, null);
		        Toast.makeText(getApplicationContext(), "SMS sent.",
		                Toast.LENGTH_LONG).show();
		    } catch (Exception e) {
		        Toast.makeText(getApplicationContext(),
		                "Sending SMS failed.",
		                Toast.LENGTH_LONG).show();
		        e.printStackTrace();
		    }
		    }
		 */
			
			/*CR.moveToFirst();
			boolean simchangestatus = false;
			
			initialsimno = CR.getString(0);
			
			tv.setText("The old sim no is "+CR.getString(0)+" and the current sim no is "+newsimno);
			
				if(!newsimno.equals(initialsimno))
				{
					simchangestatus = true;
				}
				
		
			if(simchangestatus)
			{
			   Toast.makeText(getBaseContext(), "Sim has been changed!", Toast.LENGTH_LONG).show();
			   //tv.setText("the changed sim is"+newsimno+" the initial sim no was "+initialsimno);
			    try {
			        SmsManager smsManager = SmsManager.getDefault();
			        smsManager.sendTextMessage("+919930499340", null, "SIM Changed", null, null);
			        Toast.makeText(getApplicationContext(), "SMS sent.",
			                Toast.LENGTH_LONG).show();
			    } catch (Exception e) {
			        Toast.makeText(getApplicationContext(),
			                "Sending SMS failed.",
			                Toast.LENGTH_LONG).show();
			        e.printStackTrace();
			    }
			   
			}
		*/
			Toast.makeText(getBaseContext(), "Regular launch", Toast.LENGTH_LONG).show();
			Intent smsAct = new Intent(this , SmsActivity.class);
        	startActivity(smsAct);
		}
		
		
		//settings.edit().putBoolean("my_first_time", false).commit();
		
		
		
       

	}



	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		this.finish();
	}
	

	
}


