package com.zero.msgpack1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserInfo extends Activity {

	Button SubmitInformation;
	EditText OwnPhone;
	EditText FriendPhone;
	EditText OwnEmail;
	EditText FriendEmail;
	String OP;
	String FP;
	String OE;
	String FE;
	Context CTX = this;
    String Flag1="0";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info);

		OwnPhone = (EditText)findViewById(R.id.OwnPhoneNo);
		FriendPhone = (EditText)findViewById(R.id.FriendsPhoneNo);
		OwnEmail = (EditText)findViewById(R.id.OwnEmailId);
		FriendEmail = (EditText)findViewById(R.id.FriendsEmailID);
		SubmitInformation = (Button)findViewById(R.id.SubmitUserInfo);	
	    
		if(getIntent().getStringExtra("flag") != null)
		{
			Flag1 = "1";
		}

		
		
		
		final String PREFS_NAME2 = "MyPrefsFile2";
		final SharedPreferences settings2 = getSharedPreferences(PREFS_NAME2, 0);
		
		if(Flag1 == "1")
		{
			Log.d("Bundle Passed","Successfully");
			DatabaseOperationsOI DOP = new DatabaseOperationsOI(CTX);
			DOP.deleteInformation1(DOP);
			
			settings2.edit().putBoolean("my_first_time", true).commit();
		}
		
		//settings1.edit().putBoolean("my_first_time", true).commit();
		if (settings2.getBoolean("my_first_time",true))
		{
					SubmitInformation.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				OP = OwnPhone.getText().toString();
				FP = FriendPhone.getText().toString();
				OE = OwnEmail.getText().toString();
				FE = FriendEmail.getText().toString();
				
				if(OP.equals(null) || FP.equals(null)|| OE.equals(null)|| FE.equals(null))
				{
					Toast.makeText(getBaseContext(), "Enter some text!", Toast.LENGTH_LONG).show();
				}
				
				
				if(emailValidator(OE) && emailValidator(FE) && (OP.length()==10) && (FP.length()==10)){
					DatabaseOperationsOI DOP = new DatabaseOperationsOI(CTX);
					DOP.putInformation1(DOP, OP, FP, OE, FE);
					Toast.makeText(getBaseContext(), "User Info Set up success", Toast.LENGTH_LONG).show();
					if (Flag1 ==  "0"){
						Intent trialintent = new Intent(UserInfo.this,PinLock.class);
					
						Log.d("USer Info","Submission successful");
						settings2.edit().putBoolean("my_first_time", false).commit();
						finish();
						startActivity(trialintent);
					}
					else if (Flag1 =="1"){
						Intent trialintent = new Intent(UserInfo.this,SmsActivity.class);
						
						Log.d("USer Info","Submission successful");
						settings2.edit().putBoolean("my_first_time", false).commit();
						finish();
						startActivity(trialintent);
					}

					}else{
					Toast.makeText(getBaseContext(), "invalid email address or phone number",
					                    Toast.LENGTH_SHORT).show();

					}
				
			
				
			}
		});
		
		
		}
      
		else
		{
			Intent trialintent = new Intent(UserInfo.this,ChangeUserInfo.class);
			
			finish();
			startActivity(trialintent);
		}
	
	}
	


public static boolean emailValidator(final String mailAddress) {

    Pattern pattern;
    Matcher matcher;

    final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    pattern = Pattern.compile(EMAIL_PATTERN);
    matcher = pattern.matcher(mailAddress);
    return matcher.matches();

}
}

