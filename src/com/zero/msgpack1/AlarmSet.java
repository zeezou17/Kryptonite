package com.zero.msgpack1;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class AlarmSet extends Activity {
	  private SoundPool soundPool;
	  private int soundID;
	  String loaded = "false";
	  TextView text;
		@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarmset);
		text = (TextView) findViewById(R.id.textView1);
		// Load the sound
	    soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
	    
		soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
	      @Override
	      public void onLoadComplete(SoundPool soundPool, int sampleId,
	          int status) {
	    	  text.setText("Load");
	        loaded = "true";
	        ringAlarm();
	      }
	    });
	    soundID = soundPool.load(this, R.raw.sound1, 1);
	    
	}
    public void ringAlarm(){
    	text.setText("Ring config");
    	 AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
    	 audioManager.setStreamVolume (
    			 AudioManager.STREAM_MUSIC,
    			 audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC),
    			 0); 
         float actualVolume = (float) audioManager
             .getStreamVolume(AudioManager.STREAM_MUSIC);
         float maxVolume = (float) audioManager
             .getStreamMaxVolume(AudioManager.STREAM_MUSIC);
         float volume = actualVolume / maxVolume;
         // Is the sound loaded already?
         //if (loaded == "true") {
           soundPool.play(soundID, volume, volume, 1, -1, 1f);
           text.setText("Ringing");
           Log.e("Test", "Played sound");
         //}
    }
}