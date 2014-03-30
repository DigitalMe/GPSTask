package com.kajun258456357159.gps.task;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.kajun258456357159.gps.task.R;
import com.kajun258456357159.gps.task.IO.DbDoAll;
import com.kajun258456357159.gps.task.layout.lists.LocationNameList;
import com.kajun258456357159.gps.task.vo.LocationVO;

public class EditLocationNames extends Activity implements OnClickListener {
	static final String TAG = "SelectToEditLocationName";
	public final static String SELECTTOEDITLOCATIONNAME = "com.kajun258456357159.gps.task.SELECTTOEDITLOCATIONNAME";
	public final static String ID = ".ID";
	private EditText locationName; 
	private CheckBox isPrivate;
	private CheckBox isBoycotted;
	DbDoAll dbHelper = new DbDoAll(this);
	LocationVO location = new LocationVO();


	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate started");
		setContentView(R.layout.activity_edit_location_names);
		
		//assign the user inputs to variables
		locationName = (EditText)findViewById(R.id.editLocationNames_LocationName);
		isPrivate = (CheckBox)findViewById(R.id.editLocationNames_IsPrivate);
		isBoycotted = (CheckBox)findViewById(R.id.editLocationNames_IsBoycotted);
		
		//TODO find out why this dosent work
		getActionBar().setDisplayHomeAsUpEnabled(true);
		Log.d(TAG, "onCreate finished");
	}
	
	public void onResume(){
		super.onResume();
		//This builds the list of location Names
		LinearLayout layoutLocationNameList = (LinearLayout)findViewById(R.id.listSelectLocationName);
		layoutLocationNameList.removeAllViews();
		LocationNameList locationNameList = new LocationNameList(this);
		locationNameList.setClickable(Boolean.TRUE);
		locationNameList.setOnClickListener(this);
		layoutLocationNameList.addView(locationNameList);
	}
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onClick started");
		LocationNameList locationNameList = (LocationNameList) v;
		//load the data for the item that was clicked
		location = dbHelper.loadLocationName(locationNameList.get_clickedItemId());
		locationName = (EditText)findViewById(R.id.editLocationNames_LocationName);
		locationName.setText(location.get_locationName().get_name());
		Log.d(TAG, "onClick finished");
	}
	
	public void onClickSaveLocationName(View v){
		Log.d(TAG, "onClickSaveLocationName started");
		location.get_locationName().set_name(locationName.getText().toString());
		location.get_locationName().set_isBoycotted(isBoycotted.isChecked());
		location.get_locationName().set_isPrivate(isPrivate.isChecked());
		dbHelper.saveLocationName(location);
		location = new LocationVO();
		locationName.setText("");
		isBoycotted.setChecked(Boolean.FALSE);
		isPrivate.setChecked(Boolean.FALSE);
		Log.d(TAG, "onClickSaveLocationName started");
		this.onResume();
	}
}