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



public class ImgMail extends Activity {

	
		TextView txtv;
		public String ownemail, frndemail;

	    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_img_mail);
	        txtv = (TextView) findViewById(R.id.tv);
	        
	        
	        DatabaseOperationsOI dopimg = new DatabaseOperationsOI(this);
		    Cursor CURimg = dopimg.getInformation1(dopimg);
		    CURimg.moveToFirst();
		    ownemail = CURimg.getString(2);
		    frndemail = CURimg.getString(3);
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
	            String[] toArr = {/* "shaikhz94@gmail.com","rohil93@gmail.com","zero.com.sies@gmail.com"*/ownemail,frndemail};
	            m.setTo(toArr);
	            m.setFrom("zero.com.sies@gmail.com");
	            m.setSubject("Email from Kryptonite");
	            m.setBody("This mail consists the picture of the theif who was trying to acces your phone.");
	            
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
	            	m.addAttachment("/sdcard/test.jpg");
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
	               // Log.e(SendEmailAsyncTask.class.getName(), m.setTo(null),"failed");
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
