package com.kajun258456357159.gps.task;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.kajun258456357159.gps.task.R;
import com.kajun258456357159.gps.task.IO.DbDoAll;
import com.kajun258456357159.gps.task.layout.lists.LocationNameList;
import com.kajun258456357159.gps.task.vo.LocationVO;

public class SelectLocationName extends Activity implements OnClickListener {
	static final String TAG = "SelectLocationName";
	public final static String SELECTLOCATIONNAME = "com.kajun258456357159.gps.task.SELECTLOCATIONNAME";
	public final static String ID = ".ID";
	private EditText locatonName; 
	DbDoAll dbHelper;
	LocationVO location;
	LocationNameList locationNameList;


	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate started");
		setContentView(R.layout.activity_select_location_name);
		
		dbHelper = new DbDoAll(this);
		location = new LocationVO();
		locatonName = (EditText)findViewById(R.id.selectLocationName_LocationName);
		locatonName.setText(location.get_locationName().get_name());


		//TODO find out why this dosent work
		getActionBar().setDisplayHomeAsUpEnabled(true);
		Log.d(TAG, "onCreate finished");
	}
	
	public void onResume(){
		super.onResume();
		//This builds the list of categories
		LinearLayout layoutLocationNameList = (LinearLayout)findViewById(R.id.listSelectLocationName);
		layoutLocationNameList.removeAllViews();
		LocationNameList locationNameList = new LocationNameList(this);
		locationNameList.setClickable(Boolean.TRUE);
		locationNameList.setOnClickListener(this);
		layoutLocationNameList.addView(locationNameList);
		
	}
	
//	public void onClickEditCategory (View view) {
//		Log.d(TAG, "onClickEditTask started");
//		Intent intent = new Intent(this, EditCategories.class);
//		startActivity(intent);
//		Log.d(TAG, "onClickEditTask finished");
//	}
	public void onClickAddLocatoinName(View v){
		Intent intent1 = new Intent();
		intent1.setClass(this, EditLocationNames.class);
		startActivity(intent1);
		
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onClick started");
		LocationNameList locationNameList = (LocationNameList) v;
		//load the data for the item that was clicked
		Intent intent1 = new Intent();
		switch (Integer.valueOf(v.getTag().toString())) {
		case 0:
			intent1.setClass(this, EditLocationNames.class);
			intent1.putExtra(EditLocationNames.SELECTTOEDITLOCATIONNAME + EditLocationNames.ID, locationNameList.get_clickedItemId());
			startActivity(intent1);
			break;
		case 1:
			intent1.setClass(this, EditLocations.class);
			intent1.putExtra(EditLocations.EDITLOCATIONS + EditLocations.ID, locationNameList.get_clickedItemId());
			startActivity(intent1);
			break;

		case 2:
			intent1.setClass(this, EditLocationNameCategories.class);
			intent1.putExtra(EditLocationNameCategories.EDITLOCATIONNAMECATEGORIES + EditLocationNameCategories.ID, locationNameList.get_clickedItemId());
			startActivity(intent1);
			break;
		}  
		
		Log.d(TAG, "onClick finished");
	}
}
