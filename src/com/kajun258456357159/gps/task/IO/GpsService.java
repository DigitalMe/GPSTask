package com.kajun258456357159.gps.task.IO;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class GpsService extends Service implements LocationListener {
	public static final String GPS_LOOSE = "com.kajun258456357159.gps.task.model.GpsService.GPS_LOOSE";
	public static final String GPS_EXACT = "com.kajun258456357159.gps.task.model.GpsService.GPS_EXACT";
	public static final String LATITUDE = "LATITUDE";
	public static final String LONGITUDE = "LONGITUDE";
	private static final String TAG = "GpsService";
	private LocationManager locationManager;
	private int minDistanceForUpdate = 50; // in meters
	private Location _looseGpsLocation;
	private Location _exactGpsLocation;
	


	@Override
	public IBinder onBind(Intent intent){
		sendLooseBroadcast();
		sendExactBroadcast();
		return null;
	}
	
	@Override
	public void onCreate(){
		super.onCreate();
		Log.d(TAG, "onCreated");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId){
		super.onStartCommand(intent, flags, startId);
		locationManager = (LocationManager)this.getSystemService(LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, Long.valueOf(1000), 0, this);
		Log.d(TAG, "onStarted");
		return START_STICKY;
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		Log.d(TAG, "onDestroy");
		locationManager.removeUpdates(this);
	}

	public void onLocationChanged(Location newLocation) {
		// TODO Auto-generated method stub
		set_exactGpsLocation(newLocation);
		if(get_looseGpsLocation()==null){
			set_looseGpsLocation(newLocation);
		} else {
			Location oldLocation = get_looseGpsLocation();
			if (minDistanceForUpdate < oldLocation.distanceTo(newLocation)){
				set_looseGpsLocation(newLocation);
			}
		}
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
	private ContentValues locationToValues(Location location){
		ContentValues values = new ContentValues();
		values.put(LATITUDE, get_exactGpsLocation().getLatitude());
		values.put(LONGITUDE, get_exactGpsLocation().getLongitude());
		return values;
	}

	public void setMinDistanceForUpdate(int minDistanceForUpdate) {
		this.minDistanceForUpdate = minDistanceForUpdate;
	}

	private void set_looseGpsLocation(Location looseGpsLocation){
		this._looseGpsLocation = looseGpsLocation;
		sendLooseBroadcast();
	}
	
	public Location get_looseGpsLocation() {
		return _looseGpsLocation;
	}

	private void sendLooseBroadcast(){
		Intent sendLooseGps = new Intent();
		sendLooseGps.setAction(GPS_LOOSE);
		sendLooseGps.putExtra(GPS_LOOSE, locationToValues(get_looseGpsLocation()));
		this.sendBroadcast(sendLooseGps);
	}
	
	private void set_exactGpsLocation(Location exactGpsLocation) {
		this._exactGpsLocation = exactGpsLocation;
		sendExactBroadcast();
	}

	public Location get_exactGpsLocation() {
		return _exactGpsLocation;
	}

	private void sendExactBroadcast(){
		Intent sendExactGps = new Intent();
		sendExactGps.setAction(GPS_EXACT);
		sendExactGps.putExtra(GPS_EXACT, locationToValues(get_looseGpsLocation()));
		this.sendBroadcast(sendExactGps);
	}
}
