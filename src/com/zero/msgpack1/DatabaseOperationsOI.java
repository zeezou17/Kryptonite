package com.zero.msgpack1;

import com.zero.msgpack1.TableDataOI.TableInfoOI;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseOperationsOI extends SQLiteOpenHelper {
    public static final int database_version = 1;
		public String CREATE_QUERY1 = "CREATE TABLE "+TableInfoOI.TABLE_NAME1+" ("+TableInfoOI.OWN_PHONE+" TEXT,"+TableInfoOI.FRIENDS_PHONE+" TEXT,"+TableInfoOI.OWN_EMAIL+" TEXT,"+TableInfoOI.FRIENDS_EMAIL+" TEXT);";
    
	public DatabaseOperationsOI(Context context) {
		super(context, TableInfoOI.DATABASE_NAME,null,database_version);
		// TODO Auto-generated constructor stub
		Log.d("Database operations","Database Created ");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
       
		
		db.execSQL(CREATE_QUERY1);
		Log.d("Database operationsOI","Table Created ");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	
	
	
	
	public void putInformation1(DatabaseOperationsOI dop,String ownphone,String friendphone,String ownemail,String friendemail)
	{
	
	SQLiteDatabase SQ = dop.getWritableDatabase();
	ContentValues cv = new ContentValues();
	cv.put(TableInfoOI.OWN_PHONE, ownphone);
	cv.put(TableInfoOI.FRIENDS_PHONE, friendphone);
	cv.put(TableInfoOI.OWN_EMAIL, ownemail);
	cv.put(TableInfoOI.FRIENDS_EMAIL, friendemail);
	long k= SQ.insert(TableInfoOI.TABLE_NAME1, null, cv);
	Log.d("Database operations","One row inserted");
	 
	}
	
	
	
	
	
	
	
	public Cursor getInformation1(DatabaseOperationsOI dop)
	{
		SQLiteDatabase SQ = dop.getReadableDatabase();
		String[] coloumns = {TableInfoOI.OWN_PHONE,TableInfoOI.FRIENDS_PHONE,TableInfoOI.OWN_EMAIL,TableInfoOI.FRIENDS_EMAIL};
		Cursor CR = SQ.query(TableInfoOI.TABLE_NAME1, coloumns, null,null, null, null, null);
		return CR;
	}
	
	
	
	
	public void deleteInformation1(DatabaseOperationsOI dop)
	{
		//String selection = TableInfo.UNLOCK_PIN + " LIKE ?";
		//String args[]= {pin};
		SQLiteDatabase SQ = dop.getWritableDatabase();
		SQ.delete(TableInfoOI.TABLE_NAME1,null, null);
		
		Log.d("Database operationsOI","One row deleted");
	}
	
	

}
