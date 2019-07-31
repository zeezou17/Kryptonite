package com.zero.msgpack1;

import com.zero.msgpack1.TableDataPIN.TableInfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseOperationsPIN extends SQLiteOpenHelper {
    public static final int database_version = 1;
	public String CREATE_QUERY = "CREATE TABLE "+TableInfo.TABLE_NAME+" ("+TableInfo.UNLOCK_PIN+" TEXT);";
	public String CREATE_QUERY1 = "CREATE TABLE "+TableInfo.TABLE_NAME1+" ("+TableInfo.OWN_PHONE+" TEXT,"+TableInfo.FRIENDS_PHONE+" TEXT,"+TableInfo.OWN_EMAIL+" TEXT,"+TableInfo.FRIENDS_EMAIL+" TEXT);";
    
	public DatabaseOperationsPIN(Context context) {
		super(context, TableInfo.DATABASE_NAME,null,database_version);
		// TODO Auto-generated constructor stub
		Log.d("Database operations","Database Created ");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
       
		db.execSQL(CREATE_QUERY);
		db.execSQL(CREATE_QUERY1);
		Log.d("Database operations","Table Created ");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	
	public void putInformation(DatabaseOperationsPIN dop,String pin)
	{
	
	SQLiteDatabase SQ = dop.getWritableDatabase();
	ContentValues cv = new ContentValues();
	cv.put(TableInfo.UNLOCK_PIN, pin);
	long k= SQ.insert(TableInfo.TABLE_NAME, null, cv);
	Log.d("Database operations","One row inserted");
	 
	}
	
	public void putInformation1(DatabaseOperationsPIN dop,String ownphone,String friendphone,String ownemail,String friendemail)
	{
	
	SQLiteDatabase SQ = dop.getWritableDatabase();
	ContentValues cv = new ContentValues();
	cv.put(TableInfo.OWN_PHONE, ownphone);
	cv.put(TableInfo.FRIENDS_PHONE, friendphone);
	cv.put(TableInfo.OWN_EMAIL, ownemail);
	cv.put(TableInfo.FRIENDS_EMAIL, friendemail);
	long k= SQ.insert(TableInfo.TABLE_NAME1, null, cv);
	Log.d("Database operations","One row inserted");
	 
	}
	
	
	
	
	
	public Cursor getInformation(DatabaseOperationsPIN dop)
	{
		SQLiteDatabase SQ = dop.getReadableDatabase();
		String[] coloumns = {TableInfo.UNLOCK_PIN};
		Cursor CR = SQ.query(TableInfo.TABLE_NAME, coloumns, null,null, null, null, null);
		return CR;
	}
	
	public Cursor getInformation1(DatabaseOperationsPIN dop)
	{
		SQLiteDatabase SQ = dop.getReadableDatabase();
		String[] coloumns = {TableInfo.OWN_PHONE,TableInfo.FRIENDS_PHONE,TableInfo.OWN_EMAIL,TableInfo.FRIENDS_EMAIL};
		Cursor CR = SQ.query(TableInfo.TABLE_NAME1, coloumns, null,null, null, null, null);
		return CR;
	}
	
	
	public void deleteInformation(DatabaseOperationsPIN dop,String pin)
	{
		String selection = TableInfo.UNLOCK_PIN + " LIKE ?";
		String args[]= {pin};
		SQLiteDatabase SQ = dop.getWritableDatabase();
		SQ.delete(TableInfo.TABLE_NAME, selection, args);
		
		Log.d("Database operations","One row deleted");
	}
	
	public void deleteInformation1(DatabaseOperationsPIN dop)
	{
		//String selection = TableInfo.UNLOCK_PIN + " LIKE ?";
		//String args[]= {pin};
		SQLiteDatabase SQ = dop.getWritableDatabase();
		SQ.delete(TableInfo.TABLE_NAME1,null, null);
		
		Log.d("Database operations","One row deleted");
	}
	
	

}

