package com.zero.msgpack1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class CheckSimChangeReceiver extends BroadcastReceiver {
	/*
	TelephonyManager mTelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
    String imsi = mTelephonyMgr.getSubscriberId();
    String imei = mTelephonyMgr.getDeviceId(); 
    String newsimno = mTelephonyMgr.getSimSerialNumber();
    String oldsimno; 
    */

	@Override
	public void onReceive(Context ctx, Intent intent) {
		
		 Intent myintent = new Intent(ctx,SimChangeService.class);
		 Log.i("Sim Change Service", "started");
			   
		 ctx.startService(myintent);
	        
		
		
		
		/*
		Intent startActivityIntent = new Intent(ctx, MainActivity.class);
        startActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(startActivityIntent);
		// TODO Auto-generated method stub
		
		oldsimno = MainActivity.initialsimno;
        if(!(oldsimno.equals(simno)))
        Toast.makeText(context, "Sim card changed", Toast.LENGTH_LONG).show();
        try {
	        SmsManager smsManager = SmsManager.getDefault();
	        smsManager.sendTextMessage("+919930499340", null, "SIM Changed", null, null);
	      
	                
	    } catch (Exception e) {
	     
	        e.printStackTrace();
	    }

		DatabaseOperations DOP = new DatabaseOperations(ctx);
		Cursor CR = DOP.getInformation(DOP);
		CR.moveToFirst();
		boolean simchangestatus = false;
		oldsimno = CR.getString(0);
		String NAME = "";
		
			if(!newsimno.equals(oldsimno))
			{
				simchangestatus = true;
			}
			
	
		if(simchangestatus)
		{
		   Toast.makeText( ctx, "Sim has been changed!", Toast.LENGTH_LONG).show();
		
		    try {
		        SmsManager smsManager = SmsManager.getDefault();
		        smsManager.sendTextMessage("+919930499340", null, "SIM Changed", null, null);
		        Toast.makeText(ctx, "SMS sent.",
		                Toast.LENGTH_LONG).show();
		    } catch (Exception e) {
		        Toast.makeText(ctx,
		                "Sending SMS failed.",
		                Toast.LENGTH_LONG).show();
		        e.printStackTrace();
		    }
		   
		}
        
	}
	
		
	private TelephonyManager getSystemService(String telephonyService) {
		// TODO Auto-generated method stub
		return null;
	}
	
*/
}
}

