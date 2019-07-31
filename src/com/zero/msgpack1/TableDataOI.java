package com.zero.msgpack1;

import android.provider.BaseColumns;

public class TableDataOI {

	public TableDataOI()
	{
	
	}
	
    public static abstract class TableInfoOI implements BaseColumns
    {
	
    	 
    	 public static final String DATABASE_NAME = "user_info_OI";
    	 public static final String TABLE_NAME1 = "other_info";
    	 public static final String OWN_PHONE = "own_phone";
    	 public static final String FRIENDS_PHONE = "friends_phone";
    	 public static final String OWN_EMAIL = "own_email";
    	 public static final String FRIENDS_EMAIL = "friends_email";
    	 
    }
}