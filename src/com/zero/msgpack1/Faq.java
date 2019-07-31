package com.zero.msgpack1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Faq extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_faq);
		
		TextView text = (TextView) findViewById(R.id.TEXT_STATUS_ID);
		text.setText("Using the application:"+ System.getProperty ("line.separator")
 +" Enter a pin lock for the application which is used for locking the application.Enter your friends phone number and email-id at the time of set up.All remote control features and response will be sent to this friend.The friends information can be changed later on and updated."
 + System.getProperty ("line.separator")+" In case your phone is stolen:"
 + System.getProperty ("line.separator")+"1. If the thief changes the SIM card the application will immediately detect it and send a message to your friend's phone number."
 + System.getProperty ("line.separator")+"2.Remotely lock your phone by an SMS. The phone is locked with the pin lock you set at  installation.The lock command is : lock/LOCK/Lock"
 + System.getProperty ("line.separator")+"3.Remotely activate a siren on the phone even if it is silent. Command: alarm/ALARM/Alarm"
 + System.getProperty ("line.separator")+"4.Remotely wipe(Factory Reset) your phones data. Command: Wipe+Pin Lock. for e.g if your pin lock is 1234. Then command will be: wipe1234"
 + System.getProperty ("line.separator")+"5.Remotely access GPS location for your phone.The phone will send an SMS of the gps co-ordinates. Command: gps/GPS/Gps"
 + System.getProperty ("line.separator")+"6.Remotely take a backup which will be mailed to you and your friend. Command: backup/BACKUP/Backup"
 + System.getProperty ("line.separator")+"7.If the thief tries to unlock the phone. After some failed attempts the front camera gets activated and a photo of the thief is  mailed to you and your friend.");
		
	}

}
