package com.zero.msgpack1;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


 
public class BackupMail extends Activity {
	public String ownemail, email;
	public String frndemail;
	
		TextView txtv;
		

	    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_img_mail);
	        txtv = (TextView) findViewById(R.id.tv);
	        
	        
	        DatabaseOperationsOI dopbackup = new DatabaseOperationsOI(this);
		    Cursor CURbackup = dopbackup.getInformation1(dopbackup);
		    CURbackup.moveToFirst();
		    ownemail = CURbackup.getString(2);
		    frndemail = CURbackup.getString(3);
		    email = ownemail + " " + frndemail;
		    Toast.makeText(BackupMail.this, email, Toast.LENGTH_LONG).show();
		    new SendEmailAsyncTask().execute();
	    }
	
	   /* @SuppressLint("SdCardPath")
		public void sendEMAIL(){
	    	txtv.setText("Trying to send");
	    	Mail m = new Mail("zero.com.sies@gmail.com", "zero1234zero1234"); 

	        String[] toArr = {"shaikhz94@gmail.com", "rohil93@gmail.com"}; 
	        m.setTo(toArr); 
	        m.setFrom("zero.com.sies@gmail.com.com"); 
	        m.setSubject("This is an email sent using my Mail JavaMail wrapper from an Android device."); 
	        m.setBody("Email body.");
	        
	        try { 
	           // m.addAttachment("/sdcard/test.jpg"); 

	            if(m.send()) { 
	            	txtv.setText("SENT");
	              //Toast.makeText(MailApp.this, "Email was sent successfully.", Toast.LENGTH_LONG).show(); 
	            } else { 
	            	txtv.setText("Failed");
	             // Toast.makeText(MailApp.this, "Email was not sent.", Toast.LENGTH_LONG).show(); 
	            } 
	          } catch(Exception e) { 
	            //Toast.makeText(MailApp.this, "There was a problem sending the email.", Toast.LENGTH_LONG).show(); 
	            Log.e("MailApp", "Could not send email", e); 
	          } 
	    }*/
	    class SendEmailAsyncTask extends AsyncTask <Void, Void, Boolean> {
	        Mail m = new Mail("zero.com.sies@gmail.com", "zero1234zero1234");
	        
	        public SendEmailAsyncTask() {
	        	txtv.setText("Trying to send...");
	            if (BuildConfig.DEBUG) Log.v(SendEmailAsyncTask.class.getName(), "SendEmailAsyncTask()");
	            String[] toArr= new String[]{ /*"shaikhz94@gmail.com","rohil93@gmail.com","zero.com.sies@gmail.com"*/ownemail,frndemail};
	            
	            m.setTo(toArr);
	            m.setFrom("zero.com.sies@gmail.com");
	            m.setSubject("Email from Kryptonite");
	            m.setBody("Following Email consists of your contact and SMS backup ");
	            
	            /*try {
					m.addAttachment("/sdcard/test.jpg");
				} catch (Exception e) {
					txtv.setText("No Attachment");
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
	        }

	        @Override
	        protected Boolean doInBackground(Void... params) {
	            if (BuildConfig.DEBUG) Log.v(SendEmailAsyncTask.class.getName(), "doInBackground()");
	            try {
	            	m.addAttachment("/sdcard/ContactsRestore.vcf");
	            	m.addAttachment("/sdcard/Sms.txt");
	                m.send();
	                //txtv.setText("SENT");
	                return true;
	            } catch (AuthenticationFailedException e) {
	                Log.e(SendEmailAsyncTask.class.getName(), "Bad account details");
	                e.printStackTrace();
	                //txtv.setText("Authentication Failed");
	                return false;
	            } catch (MessagingException e) {
	            	//txtv.setText("Messaging failed");
	                Log.e(SendEmailAsyncTask.class.getName(),"failed");
	                e.printStackTrace();
	                return false;
	            } catch (Exception e) {
	            	//txtv.setText("OTHER");
	                e.printStackTrace();
	                return false;
	            }
	        }
	       
	    }
	    
}

