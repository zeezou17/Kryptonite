package com.zero.msgpack1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.util.Log;


public class BackupMain extends Activity {
	//Contacts 
	Cursor cursor;
	 ArrayList<String> vCard ;
	 String vfile;
	 
	 //SMS
	 public ArrayList<String> smsBuffer = new ArrayList<String>();
	    String smsFile = "Sms" + ".txt" ;
	    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_backupmain);
		//contacts
		try {
	        getVcardString();//contacts
	        backupSMS();//sms
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
		Intent i = new Intent(this, BackupMail.class);
	    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    startActivity(i);
	}

	//contacts
	private void getVcardString() throws IOException {

	     final String vfile = "ContactsRestore.vcf";
	    // TODO Auto-generated method stub
	    vCard = new ArrayList<String>();
	    cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
	    if(cursor!=null&&cursor.getCount()>0)
	    {
	        int i;
	        String storage_path = Environment.getExternalStorageDirectory().toString() + File.separator + vfile;
	        //String storage_path ="/sdcard/contacts.vcf";
	        FileOutputStream mFileOutputStream = new FileOutputStream(storage_path, false);
	        cursor.moveToFirst();
	        for(i = 0;i<cursor.getCount();i++)
	        {
	            get(cursor);
	            Log.d("TAG", "Contact "+(i+1)+"VcF String is"+vCard.get(i));
	            cursor.moveToNext();
	            mFileOutputStream.write(vCard.get(i).toString().getBytes());
	        }
	        mFileOutputStream.close();
	        cursor.close();
	    }
	    else
	    {
	        Log.d("TAG", "No Contacts in Your Phone");
	    }
	}
	private void get(Cursor cursor2) {
	    String lookupKey = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
	    Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_VCARD_URI, lookupKey);
	    AssetFileDescriptor fd;
	    try {
	        fd = this.getContentResolver().openAssetFileDescriptor(uri, "r");

	        FileInputStream fis = fd.createInputStream();
	        byte[] buf = new byte[(int) fd.getDeclaredLength()];
	        fis.read(buf);
	        String vcardstring= new String(buf);
	        vCard.add(vcardstring);
	    } catch (Exception e1) 
	    {
	        // TODO Auto-generated catch block
	        e1.printStackTrace();
	    }
	}
	
	
	
	
	
	
	//sms
	
		private void  backupSMS(){
   			smsBuffer.clear();
   			Uri mSmsinboxQueryUri = Uri.parse("content://sms");
   			Cursor cursor1 = getContentResolver().query(
   				mSmsinboxQueryUri,
   				new String[] { "_id", "thread_id", "address", "person", "date",
   							"body", "type" }, null, null, null);
   		//			startManagingCursor(cursor1);
   			String[] columns = new String[] { "_id", "thread_id", "address", "person", "date", "body",
   			"type" };
   			if (cursor1.getCount() > 0) {
   				String count = Integer.toString(cursor1.getCount());
   				Log.d("Count",count);
   				while (cursor1.moveToNext()) {
   				
   					String messageId = cursor1.getString(cursor1
   						.getColumnIndex(columns[0]));

   					String threadId = cursor1.getString(cursor1
   							.getColumnIndex(columns[1]));
   				
   					String address = cursor1.getString(cursor1
   							.getColumnIndex(columns[2]));
   					String name = cursor1.getString(cursor1
   							.getColumnIndex(columns[3]));
   					String date = cursor1.getString(cursor1
   							.getColumnIndex(columns[4]));
   					String msg = cursor1.getString(cursor1
   							.getColumnIndex(columns[5]));
   					String type = cursor1.getString(cursor1
   							.getColumnIndex(columns[6]));
   				


   					smsBuffer.add(messageId + ",\t"+ threadId+ ",\t"+ address + ",\t" + name + ",\t" + date + ",\t" + msg + ",\t"
   							+ type+"\n");

   				}           
   				generateCSVFileForSMS(smsBuffer);
   			}               
	}


	private void generateCSVFileForSMS(ArrayList<String> list)
	{

		try 
		{	
			String storage_path = Environment.getExternalStorageDirectory().toString() + File.separator + smsFile;
			FileWriter write = new FileWriter(storage_path);

			write.append("messageId, threadId, Address, Name, Date, msg, type");
			write.append('\n');

			for (String s : list)
			{
				write.append(s);
				write.append('\n');
			}
			write.flush();
			write.close();
		}

		catch (NullPointerException e) 
		{
			System.out.println("Nullpointer Exception "+e);
			//  e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		this.finish();
	}
}

