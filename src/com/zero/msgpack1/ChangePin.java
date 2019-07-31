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

public class ChangePin extends Activity {
	
	Button PinChange;
	EditText Unlock_pin;
	String login_unlock_pin;
	Context CTX = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_pin);
		
		Unlock_pin = (EditText)findViewById(R.id.EnterPasswordEditText);
		PinChange = (Button)findViewById(R.id.ChangePinButton);
		
		PinChange.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getBaseContext(), "Please Wait...", Toast.LENGTH_LONG).show();
				login_unlock_pin = Unlock_pin.getText().toString();
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
				   
				   
				   
				   DOP.deleteInformation(DOP, login_unlock_pin);
				  
				   Intent cheese = new Intent(ChangePin.this,PinSetup.class);
				   Bundle BN = new Bundle();
					BN.putString("flag","1");
					cheese.putExtras(BN);
					
				   finish();
				   startActivity(cheese);
				}
				else
				{
					
					Toast.makeText(getBaseContext(), "Login Failed!", Toast.LENGTH_LONG).show();
		            	
				}
		
			}
		});
	
	
	}


}

