package com.zero.msgpack1;

import android.provider.BaseColumns;

public class TableData {

	public TableData()
	{
	
	}
	
    public static abstract class TableInfo implements BaseColumns
    {
	
    	 public static final String IMEI = "imei" ;
    	 public static final String IMSI = "imsi" ;
    	 public static final String SIM_SR_NO = "sim_sr_no"; 
    	 public static final String DATABASE_NAME = "sim_info";
    	 public static final String TABLE_NAME = "sim_nos";
    }
}
