package com.kajun258456357159.gps.task;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.kajun258456357159.gps.task.R;
import com.kajun258456357159.gps.task.IO.DbDoAll;
import com.kajun258456357159.gps.task.layout.lists.CategoryList;
import com.kajun258456357159.gps.task.vo.LocationVO;

public class EditLocationNameCategories extends Activity {
	static final String TAG = "EditLocationNameCategories";
	public final static String EDITLOCATIONNAMECATEGORIES = "com.kajun258456357159.gps.task.EDITLOCATIONNAMECATEGORIES";
	public final static String ID = ".ID";
	private LinearLayout layoutLocationNameCategoryList;
	private CategoryList _categoryList;
	private ArrayList<Long> checkedCategoryIds;
	private EditText categoryName; 
	private Intent intent;
	private DbDoAll dbHelper;
	private LocationVO _location;

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate started");
		setContentView(R.layout.activity_edit_location_name_categories);
		dbHelper = new DbDoAll(this);
		_location = new LocationVO();
		intent = getIntent();
		_location.get_locationName().set_id(intent.getLongExtra(EDITLOCATIONNAMECATEGORIES + ID, 0));
		categoryName = (EditText)findViewById(R.id.editLocationNameCategories_CategorySearch);
		layoutLocationNameCategoryList = (LinearLayout)findViewById(R.id.listLocationName_Categories);
		
		//TODO find out why this dosent work
		getActionBar().setDisplayHomeAsUpEnabled(true);
		Log.d(TAG, "onCreate finished");
	}

	public void onResume(){
		super.onResume();
		Log.d(TAG, "onResume started");
		_location.get_locationName().set_id(intent.getLongExtra(EDITLOCATIONNAMECATEGORIES + ID, 0));
		
		checkedCategoryIds = dbHelper.loadLocationNameCategories(_location.get_locationName().get_id());
		//This builds the list of categories
		layoutLocationNameCategoryList.removeAllViews();
		_categoryList = new CategoryList(this);
		_categoryList.set_isMultiselect(Boolean.TRUE);
		_categoryList.set_categoryArrayList(dbHelper.loadCategoryList(DbDoAll.LOAD_ALL_CATEGORIES));
		_categoryList.setClickable(Boolean.TRUE);
		layoutLocationNameCategoryList.addView(_categoryList.get_categoryList(this, checkedCategoryIds));
		Log.d(TAG, "onResume finished");
	}

	public void onClickLocationNameCategoriesSave(View v){
		checkedCategoryIds = _categoryList.get_checkedItemIds();
		dbHelper.saveLocationNameCategories(checkedCategoryIds, _location.get_locationName().get_id());
		this.finish();
	}
}
