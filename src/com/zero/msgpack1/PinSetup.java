package com.zero.msgpack1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zero.msgpack1.PinLock;

public class PinSetup extends Activity {

	EditText UNLOCK_PIN,CON_UNLOCK_PIN;
	Button PIN_REG;
	String unlock_pin,con_unlock_pin;
	Context ctx = this;
	String Flag1="0";
	String test= null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pin_setup);
		UNLOCK_PIN = (EditText) findViewById(R.id.EditTextPin);
		CON_UNLOCK_PIN = (EditText) findViewById(R.id.EditTextConfirmPin);
		PIN_REG = (Button) findViewById(R.id.PinRegisterButton);
		
		
		//Bundle BN = getIntent().getExtras();
		if(getIntent().getStringExtra("flag") != null)
		{
			Flag1 = "1";
		}
		
		
		
		final String PREFS_NAME = "MyPrefsFile";
		final SharedPreferences settings1 = getSharedPreferences(PREFS_NAME, 0);
		if(Flag1 == "1")
		{
			Log.d("Bundle Passed","successfully");
            
			settings1.edit().putBoolean("my_first_time", true).commit();
		}
		
		if (settings1.getBoolean("my_first_time", true))
		{
		
		PIN_REG.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				unlock_pin = UNLOCK_PIN.getText().toString();
				con_unlock_pin = CON_UNLOCK_PIN.getText().toString();
				String Nul = "";
				if(unlock_pin.equals(Nul) || con_unlock_pin.equals(Nul))
				{
					Toast.makeText(getBaseContext(),"The pins entered are not matching", Toast.LENGTH_LONG).show();
				}
				
				if(!(unlock_pin.equals(con_unlock_pin)))
				{
					Toast.makeText(getBaseContext(),"The pins entered are not matching", Toast.LENGTH_LONG).show();
				    UNLOCK_PIN.setText("");
				    CON_UNLOCK_PIN.setText("");
				
				}
				else{
					DatabaseOperationsPIN DB = new DatabaseOperationsPIN(ctx);
					DB.putInformation(DB, unlock_pin);
					Toast.makeText(getBaseContext(), "Pin setup success", Toast.LENGTH_LONG).show();
					if (Flag1 == "0"){
						Intent trialintent = new Intent(PinSetup.this,UserInfo.class);
					
						settings1.edit().putBoolean("my_first_time", false).commit();
						finish();
						startActivity(trialintent);
					}
					else if (Flag1 == "1"){
						Intent trialintent = new Intent(PinSetup.this,SmsActivity.class);
						
						settings1.edit().putBoolean("my_first_time", false).commit();
						finish();
						startActivity(trialintent);
					}
				}
			}
		});
		
		}
		else
		{
			Intent trialintent = new Intent(PinSetup.this,PinLock.class);
			finish();
			startActivity(trialintent);
		}
			
			
	}
}
