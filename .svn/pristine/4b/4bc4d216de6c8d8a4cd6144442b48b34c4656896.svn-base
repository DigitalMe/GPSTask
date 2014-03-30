package com.kajun258456357159.gps.task.vo;

import android.location.Location;

public class CoordinateVO {
	public final static int KMS = 0;
	public final static int METERS = 1;
	public final static int MILES = 20;
	private double _latitude = 0;
	private double _longitude = 0;
	
	//constructor
	public CoordinateVO(double latitude, double longitude){
		set_latitude(latitude);
		set_longitude(longitude);
	}
	
	public CoordinateVO(){
		set_latitude(0);
		set_longitude(0);
	}

	public double get_latitude() {
		return _latitude;
	}

	public void set_latitude(double _lattitude) {
		this._latitude = _lattitude;
	}

	public double get_longitude() {
		return _longitude;
	}

	public void set_longitude(double _longitude) {
		this._longitude = _longitude;
	}

	public float getDistance(Location location, int units)
	{
		return getDistance(location.getLatitude(), location.getLongitude(), units);
	}
	
	public float getDistance(CoordinateVO coordinate, int units)
	{
		return getDistance(coordinate.get_latitude(), coordinate.get_longitude(), units); 
	}
	
	public float getDistance(double latitude, double longitude, int units) {
		Float distance = Float.valueOf(0);
		Location location = new Location("");
		location.setLatitude(get_latitude());
		location.setLongitude(get_longitude());
		
		Location locationHere = new Location("");
		locationHere.setLatitude(latitude);
		locationHere.setLongitude(longitude);
		
		distance = locationHere.distanceTo(location);
		switch (units)
		{
			case KMS:
				distance = distance /1000;
				break;
			case METERS:
				break;
			case MILES:
				distance = (float) (distance * 0.00063);
				break;
		}
		distance = (float) Math.round(distance * 10) / 10;
		return distance;
	}

}
