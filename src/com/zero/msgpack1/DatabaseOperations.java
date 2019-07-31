package com.zero.msgpack1;

import com.zero.msgpack1.TableData.TableInfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseOperations extends SQLiteOpenHelper {
    public static final int database_version = 1;
	public String CREATE_QUERY = "CREATE TABLE "+TableInfo.TABLE_NAME+" ("+TableInfo.SIM_SR_NO+" TEXT, "+TableInfo.IMEI+" TEXT, "+TableInfo.IMSI+" TEXT);";  
    
	public DatabaseOperations(Context context) {
		super(context, TableInfo.DATABASE_NAME,null,database_version);
		// TODO Auto-generated constructor stub
		Log.d("Database operations","Database Created ");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
       
		db.execSQL(CREATE_QUERY);
		Log.d("Database operations","Table Created ");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	
	public void putInformation(DatabaseOperations dop,String sim_sr_no,String imei,String imsi)
	{ 
	
	SQLiteDatabase SQ = dop.getWritableDatabase();
	ContentValues cv = new ContentValues();
	cv.put(TableInfo.SIM_SR_NO, sim_sr_no);
	cv.put(TableInfo.IMEI, imei);
	cv.put(TableInfo.IMSI, imsi);
	
	long k= SQ.insert(TableInfo.TABLE_NAME, null, cv);
	Log.d("Database operations","One row inserted");
	 
	}
	
	public Cursor getInformation(DatabaseOperations dop)
	{
		SQLiteDatabase SQ = dop.getReadableDatabase();
		String[] coloumns = {TableInfo.SIM_SR_NO,TableInfo.IMEI,TableInfo.IMSI};
		Cursor CR = SQ.query(TableInfo.TABLE_NAME, coloumns, null,null, null, null, null);
		return CR;
	}
	
	

}

