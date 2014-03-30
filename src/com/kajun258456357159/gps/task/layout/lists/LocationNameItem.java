package com.kajun258456357159.gps.task.layout.lists;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kajun258456357159.gps.task.R;
import com.kajun258456357159.gps.task.vo.LocationVO;

public class LocationNameItem extends RelativeLayout implements OnClickListener{
	private LocationVO _location;
	private RelativeLayout locationRow;
	private TextView textLocationName;
	private ImageButton buttonLocationEdit;
	private ImageButton buttonLocationCategoryEdit;
	private static final String TAG = "LocationItem";
	private int _clickedView;

	public LocationNameItem(Context context) {
		super(context);
		Log.d(TAG, "LocationItem started");
//		if (_category.is_isDeleted()){
			
//		} else {
		locationRow = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.location_name_row, null);
		textLocationName = (TextView) locationRow.findViewById(R.id.textLocationName);
		buttonLocationEdit = (ImageButton) locationRow.findViewById(R.id.locationEdit);
		buttonLocationCategoryEdit = (ImageButton) locationRow.findViewById(R.id.locationCategoryEdit);
		textLocationName.setTag(0);
		buttonLocationEdit.setTag(1);
		buttonLocationCategoryEdit.setTag(2);
		

//onClickListener click
			
			// TODO GET RID of this as per ROSS
		buttonLocationEdit.setOnClickListener(this);
		buttonLocationCategoryEdit.setOnClickListener(this);
		textLocationName.setOnClickListener(this);
			// TODO BUT NOT THIS
			this.setClickable(Boolean.TRUE);
        	this.addView(locationRow);
//		}
		Log.d(TAG, "LocationItem finished");
	}

	
	public LocationVO get_location() {
		return _location;
	}

	public void set_location(LocationVO location) {
		_location = location;
		textLocationName.setText(location.get_locationName().get_name());
//		textLocationName.setId(Integer.valueOf(Long.toString(location.get_id())));
	}


	public void onClick(View v) {
		// TODO Auto-generated method stub
		set_clickedView(Integer.valueOf(v.getTag().toString()));
		this.performClick();
	}


	public int get_clickedView() {
		return _clickedView;
	}


	public void set_clickedView(int _clickedView) {
		this._clickedView = _clickedView;
	}


}
