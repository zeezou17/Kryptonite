package com.zero.msgpack1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PinLock extends Activity {
	
	Button PinLogin,PinSetup;
	EditText LOGIN_UNLOCK_PIN;
	String login_unlock_pin;
	Context CTX = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pin_lock);
		LOGIN_UNLOCK_PIN = (EditText) findViewById(R.id.LockEditTextPasswordPin);
		PinLogin = (Button) findViewById(R.id.PinLogin);
		//PinSetup = (Button) findViewById(R.id.PinSetup);
		
		/*if (settings.getBoolean("my_first_time", true)) {
		    //the app is being launched for first time, do something        
		    Log.d("Comments", "First time");
		    Toast.makeText(getBaseContext(), "First Time launch", Toast.LENGTH_LONG).show();
		    settings.edit().putBoolean("my_first_time", false).commit();
		    Intent finalIntent = new Intent(PinLock.this,PinSetup.class);
			finish();
			startActivity(finalIntent);
    
		}*/
		
		PinLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getBaseContext(), "Please Wait...", Toast.LENGTH_LONG).show();
				login_unlock_pin = LOGIN_UNLOCK_PIN.getText().toString();
				DatabaseOperationsPIN DOP = new DatabaseOperationsPIN(CTX);
				Cursor CR = DOP.getInformation(DOP);
				CR.moveToFirst();
				boolean loginstatus = false;
				String NAME = "";
				
				if(login_unlock_pin.equals(null))
				{
					Toast.makeText(getBaseContext(), "Enter some text!", Toast.LENGTH_LONG).show();
				}
				
				do
				{
					if(login_unlock_pin.equals(CR.getString(0)))
					{
						loginstatus = true;
					}
					
				}while(CR.moveToNext());
				if(loginstatus)
				{
				   Toast.makeText(getBaseContext(), "Login Successful!", Toast.LENGTH_LONG).show();
				   Intent cheese = new Intent(PinLock.this,SimChangeMain.class);
				   
				   finish();
				   startActivity(cheese);
				}
				else
				{
					
					Toast.makeText(getBaseContext(), "Login Failed!", Toast.LENGTH_LONG).show();
		            	
				}
				
				
			}
		});
		
		/*
		PinSetup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent finalIntent = new Intent(PinLock.this,PinSetup.class);
			
				startActivity(finalIntent);
			}
		});
	*/
	}
	
}
