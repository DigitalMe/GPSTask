package com.kajun258456357159.gps.task.layout.lists;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.kajun258456357159.gps.task.vo.LocationVO;

public class LocationList extends LinearLayout implements OnClickListener{
	private static final String TAG = "LocationList";
	private ArrayList<LocationVO> _locationArrayList;
	private ArrayList<LocationItem> locationList;
	private long _clickedItemId;

	public LocationList(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	public LocationList(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	public LocationList(Context context) {
		super(context);
		Log.d(TAG, "CategoryList started");
		// TODO Auto-generated constructor stub
    	
    	//for loop to be add each task row to the task list
    	Log.d(TAG, "CategoryList finished");
	}
	public LinearLayout get_locationList(Context context){
		locationList = new ArrayList<LocationItem>();
    	for (int i = 0; i<_locationArrayList.size(); i++){
    		if (!_locationArrayList.get(i).is_isDeleted()){
	    		LocationItem locationItem = new LocationItem(context);
	    		locationItem.set_location(_locationArrayList.get(i));
	    		locationItem.setOnClickListener(this);
	    		locationList.add(locationItem);
	    		this.setOrientation(VERTICAL);
	    		this.addView(locationList.get(locationList.size()-1).get_locationItem(context));
    		}
    	}
		return this;
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		LocationItem item = (LocationItem) v;
		this.set_clickedItemId(item.get_location().get_id());
		this.performClick();
	}

	public long get_clickedItemId() {
		return _clickedItemId;
	}
	public void set_clickedItemId(long clickedItemId) {
		this._clickedItemId = clickedItemId;
	}
	public void set_locationArrayList(ArrayList<LocationVO> locationArrayList) {
		this._locationArrayList = locationArrayList;
	}
	
}