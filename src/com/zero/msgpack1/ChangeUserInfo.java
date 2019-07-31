package com.zero.msgpack1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChangeUserInfo extends Activity{
	
	Button ChangeOI;
	TextView OPTV;
	TextView FPTV;
	TextView OETV;
	TextView FETV;
	
	Context CTX = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_user_info);
		
		OPTV = (TextView)findViewById(R.id.OPTextView);
		FPTV = (TextView)findViewById(R.id.FPTextView);
		OETV = (TextView)findViewById(R.id.OETextView);
		FETV = (TextView)findViewById(R.id.FETextView);
		ChangeOI = (Button)findViewById(R.id.ChangeOI);
		
		DatabaseOperationsOI DOP = new DatabaseOperationsOI(CTX);
		Cursor CR = DOP.getInformation1(DOP);
		CR.moveToFirst();
		if(CR == null)
		{
			Toast.makeText(getBaseContext(), "Null value", Toast.LENGTH_LONG).show();
		}

		//Toast.makeText(getBaseContext(), ""+CR.getString(0)+CR.getString(1)+CR.getString(2)+CR.getString(3), Toast.LENGTH_LONG).show();
		Log.d("Current Values",":"+CR.getString(0)+CR.getString(1)+CR.getString(2)+CR.getString(3));
		
		do{	
		OPTV.setText(CR.getString(0));
		FPTV.setText(CR.getString(1));
		OETV.setText(CR.getString(2));
		FETV.setText(CR.getString(3));
		}while(CR.moveToNext());
	
		
		ChangeOI.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				Intent cheese = new Intent(ChangeUserInfo.this,UserInfo.class);
				Bundle BN = new Bundle();
				BN.putString("flag","1");
				cheese.putExtras(BN);
				//DatabaseOperationsOI DOP = new DatabaseOperationsOI(CTX);
				//DOP.deleteInformation1(DOP);
				   finish();
				   startActivity(cheese);
				
			}
		});
		
	}
	
	
	
	
	
	
	
	
	
	}


