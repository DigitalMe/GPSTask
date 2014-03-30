package com.kajun258456357159.gps.task.layout.lists;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kajun258456357159.gps.task.R;
import com.kajun258456357159.gps.task.IO.DataManager;
import com.kajun258456357159.gps.task.vo.CoordinateVO;
import com.kajun258456357159.gps.task.vo.LocationVO;

public class LocationItem extends LinearLayout implements OnClickListener{
	private LocationVO _location;
	private LinearLayout locationRow;
	private TextView textLocationName;
	private TextView textLocationAddress;
	private TextView textLocationDistance;
	private static final String TAG = "LocationItem";

	public LocationItem(Context context) {
		super(context);
		Log.d(TAG, "LocationItem started");
		locationRow = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.location_row, null);
		textLocationName = (TextView) locationRow.findViewById(R.id.textLocationName);
		textLocationAddress = (TextView) locationRow.findViewById(R.id.textLocationAddress);
		textLocationDistance = (TextView) locationRow.findViewById(R.id.textLocationDistance);
		
		Log.d(TAG, "LocationItem finished");
	}

	public LinearLayout get_locationItem(Context context){

		// TODO GET RID of this as per ROSS
		textLocationName.setOnClickListener(this);
		// TODO BUT NOT THIS
		this.setClickable(Boolean.TRUE);
    	this.addView(locationRow);
		Log.d(TAG, "get_categoryItem finished");
    	return this;
	}

	public LocationVO get_location() {
		return _location;
	}

	public void set_location(LocationVO location) {
		_location = location;
		textLocationName.setText(location.get_locationName().get_name());
		textLocationName.setId(Integer.valueOf(Long.toString(location.get_id())));
		textLocationAddress.setText(location.get_address().getAddressLine(0) + "\n" + location.get_address().getAddressLine(1) + "\n" + location.get_FormatedPhone());
		textLocationDistance.setText(location.get_coordinate().getDistance(DataManager.getLocation(), CoordinateVO.KMS) + " km");
	}


	public void onClick(View v) {
		// TODO Auto-generated method stub
		this.performClick();
	}
}
