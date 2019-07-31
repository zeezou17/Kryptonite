package com.zero.msgpack1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsBroadcastReceiver extends BroadcastReceiver {

    public static final String SMS_BUNDLE = "pdus";
    String address;
    String smsMessageStr="";
    public String frndno;
    public void onReceive(Context context, Intent intent) {
        Bundle intentExtras = intent.getExtras();
        if (intentExtras != null) {
            Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
            
            for (int i = 0; i < sms.length; ++i) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);

                String smsBody = smsMessage.getMessageBody().toString();
                address = smsMessage.getOriginatingAddress();
                
                smsMessageStr += "SMS From: " + address + "\n";
                smsMessageStr += smsBody + "\n";
            }
            Toast.makeText(context, smsMessageStr, Toast.LENGTH_SHORT).show();

           /* //this will update the UI with message
            SmsActivity inst = SmsActivity.instance();
            inst.updateList(smsMessageStr,address);
            */
            
           
            		Intent myintent = new Intent(context,SmsActivity.class);
            		Bundle extras = new Bundle();
            		extras.putString("Message",smsMessageStr);
            		extras.putString("Address",address);
            		myintent.putExtras(extras);
            		myintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            		context.startActivity(myintent);
            
            
        }
    }

	
}
