package com.kajun258456357159.gps.task;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;
import android.util.Log;

import com.kajun258456357159.gps.task.IO.DataManager;
import com.kajun258456357159.gps.task.IO.GpsService;

public class GpsTaskApplication extends Application
	implements OnSharedPreferenceChangeListener{
	private static String TAG = "GpsTaskApplication";
	private SharedPreferences _prefs;
	private boolean _gpsServiceRunning;
	public static DataManager dataModel = DataManager.getInstance();
	
	@Override
	public  void onCreate(){
		super.onCreate();
		this._prefs = PreferenceManager.getDefaultSharedPreferences(this);
		this._prefs.registerOnSharedPreferenceChangeListener(this);
		//Start services and set flags to true unless null is returned (service not started)
//		set_gpsServiceRunning(startService(new Intent(this, GpsService.class))!=null);
		Log.d(TAG, "Start request. gps started " + is_gpsServiceRunning());
		dataModel.init(getApplicationContext());
	}
	
	@Override
	public void onTerminate(){
		set_gpsServiceRunning(stopService(new Intent(this, GpsService.class)));
		Log.d(TAG, "Stop request. gps started " + is_gpsServiceRunning());
	}
	
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		// TODO Auto-generated method stub
		
	}

	public boolean is_gpsServiceRunning() {
		return _gpsServiceRunning;
	}

	private void set_gpsServiceRunning(boolean _gpsServiceRunning) {
		this._gpsServiceRunning = _gpsServiceRunning;
	}
}
