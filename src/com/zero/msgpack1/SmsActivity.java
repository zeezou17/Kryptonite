package com.zero.msgpack1;


import android.app.ListActivity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SmsActivity extends ListActivity {
	static final String TAG = "DevicePolicyDemoActivity";
	static final int ACTIVATION_REQUEST = 47; // identifies our request id
	DevicePolicyManager devicePolicyManager;
	ComponentName demoDeviceAdmin;
	
	public String frndno, pass;
	public String MessageBody;
	public String SenderAddress;

   
    
	
	String classes[]={"Change Lock Pin!","Additional Important Info","FAQ"};
	
	private static SmsActivity inst;
	public static SmsActivity instance() {
        return inst;
    }

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_sms);
		
		
		if((getIntent().getStringExtra("Message") != null) && (getIntent().getStringExtra("Address") != null))
		{
			devicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
			demoDeviceAdmin = new ComponentName(this, DemoDeviceAdminReceiver.class);
			
			MessageBody = getIntent().getStringExtra("Message");
			SenderAddress = getIntent().getStringExtra("Address");
			this.updateList(MessageBody, SenderAddress);
		}
		
		
		
		
		setListAdapter(new ArrayAdapter<String>(SmsActivity.this, android.R.layout.simple_list_item_1, classes));

		
		
		final String PREFS_NAME4 = "MyPrefsFile4";

		SharedPreferences settings4 = getSharedPreferences(PREFS_NAME4, 0);

		if (settings4.getBoolean("my_first_time", true)) {
		    //the app is being launched for first time, do something        
		    Log.d("Comments", "First time");
		    
		    Intent firstlaunch = new Intent(SmsActivity.this,DevicePolicyDemoActivity.class);
		    startActivity(firstlaunch);

		             // first time task

		    // record the fact that the app has been started at least once
		    settings4.edit().putBoolean("my_first_time", false).commit(); 
		}

		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
	    String selector[] ={"ChangePin","UserInfo","Faq"};
	    
	    
	    
	    try{
	        Class ourClass = Class.forName("com.zero.msgpack1."+ selector[position]);
	    	Intent ourIntent = new Intent(SmsActivity.this,ourClass);
	    	startActivity(ourIntent);
	    	
	    	//this.finish();
	    	
	    }
	    catch (ClassNotFoundException e){
	    	 e.printStackTrace();
	    }
	    
	    }
	public void updateList(final String smsMessage , String address) {
        //arrayAdapter.insert(smsMessage, 0);
       // arrayAdapter.notifyDataSetChanged();
		devicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
		demoDeviceAdmin = new ComponentName(this, DemoDeviceAdminReceiver.class);
		 DatabaseOperationsOI dop1 = new DatabaseOperationsOI(this);
	    Cursor CUR1 = dop1.getInformation1(dop1);
		CUR1.moveToFirst();
		frndno = CUR1.getString(1);
		
		DatabaseOperationsPIN doppin = new DatabaseOperationsPIN(this);
	    Cursor CURPIN = doppin.getInformation(doppin);
	    CURPIN.moveToFirst();
		pass = CURPIN.getString(0);
		
		if( (smsMessage.contains("Alarm") || smsMessage.contains("ALARM") || smsMessage.contains("alarm")) && (/*address.equals("+919870675955") || address.equals("+919930499340")*/ address.equals("+91"+frndno))){
        	Intent alarmset = new Intent(this , AlarmSet.class);
        	startActivity(alarmset);
        }
        else if( (smsMessage.contains("Lock") || smsMessage.contains("LOCK") || smsMessage.contains("lock")) && (/*address.equals("+919870675955") || address.equals("+919930499340") */ address.equals("+91"+frndno))){
        	
        		Toast.makeText(this, "Locking device...", Toast.LENGTH_LONG).show();
        		Log.d(TAG, "Locking device now");
        		//devicePolicyManager.lockNow();
        		devicePolicyManager.resetPassword(pass,5);
        		devicePolicyManager.lockNow();
        }
        	
        else if( (smsMessage.contains("Wipe"+pass) || smsMessage.contains("WIPE"+pass) || smsMessage.contains("wipe"+pass)) && (/*address.equals("+919870675955") || address.equals("+919930499340") */address.equals("+91"+frndno))){
        	
        	Toast.makeText(this, "Resetting Device...", Toast.LENGTH_LONG).show();
			Log.d(TAG,
					"RESETing device now - all user data will be ERASED to factory settings");
			devicePolicyManager.wipeData(ACTIVATION_REQUEST);
        }
        else if( (smsMessage.contains("GPS") || smsMessage.contains("gps") || smsMessage.contains("Gps")) && (/*address.equals("+919870675955") || address.equals("+919930499340") */address.equals("+91"+frndno))){
        	Intent gps = new Intent(this , GpsCalc.class);
        	startActivity(gps);
        }else if( (smsMessage.contains("BACKUP") || smsMessage.contains("backup") || smsMessage.contains("Backup")) && (/*address.equals("+919870675955") || address.equals("+919930499340") */ address.equals("+91"+frndno))) {
        	Intent backup = new Intent(this , BackupMain.class);
        	startActivity(backup);
        }
        else{
        	finish();
        }
    }
	
	

	
}
