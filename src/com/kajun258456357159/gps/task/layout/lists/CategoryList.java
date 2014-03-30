package com.kajun258456357159.gps.task.layout.lists;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.kajun258456357159.gps.task.vo.CategoryVO;

public class CategoryList extends LinearLayout implements OnClickListener{
	private static final String TAG = "CategoryList";
	private ArrayList<CategoryVO> _categoryArrayList;
	private ArrayList<CategoryItem> categoryList;
	private long _clickedItemId;
	private Boolean _isMultiselect;
	

	public CategoryList(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	public CategoryList(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	public CategoryList(Context context) {
		super(context);
		Log.d(TAG, "CategoryList started");
		// TODO Auto-generated constructor stub
    	
    	//for loop to be add each task row to the task list
    	Log.d(TAG, "CategoryList finished");
	}
	public void onClick(View v) {
		// TODO Auto-generated method stub
		CategoryItem item = (CategoryItem) v;
		this.set_clickedItemId(item.get_category().get_id());
		this.performClick();
	}
	public LinearLayout get_categoryList(Context context, ArrayList<Long> checkedItemIds){
		categoryList = new ArrayList<CategoryItem>();
    	for (int i = 0; i<_categoryArrayList.size(); i++){
    		
    		CategoryItem categoryItem = new CategoryItem(context);
    		categoryItem.set_category(_categoryArrayList.get(i));
    		for (int x=0;x<checkedItemIds.size();x++){
        		if(checkedItemIds.get(x)==_categoryArrayList.get(i).get_id()){
        			categoryItem.setChecked();
        			x=checkedItemIds.size();
        		}
    		}
    		categoryItem.setOnClickListener(this);
    		categoryItem.set_isMultiselect(_isMultiselect);
    		categoryList.add(categoryItem);
    		this.setOrientation(VERTICAL);
    		this.addView(categoryList.get(categoryList.size()-1).get_categoryItem(context));
    	}
		return this;
	}
	public LinearLayout get_categoryList(Context context){
		categoryList = new ArrayList<CategoryItem>();
    	for (int i = 0; i<_categoryArrayList.size(); i++){
    		
    		CategoryItem categoryItem = new CategoryItem(context);
    		categoryItem.set_category(_categoryArrayList.get(i));
    		categoryItem.setOnClickListener(this);
    		categoryItem.set_isMultiselect(_isMultiselect);
    		categoryList.add(categoryItem);
    		this.setOrientation(VERTICAL);
    		this.addView(categoryList.get(categoryList.size()-1).get_categoryItem(context));
    	}
		return this;
	}
	

	public long get_clickedItemId() {
		return _clickedItemId;
	}
	public void set_clickedItemId(long clickedItemId) {
		this._clickedItemId = clickedItemId;
	}
	public void set_categoryArrayList(ArrayList<CategoryVO> categoryArrayList) {
		this._categoryArrayList = categoryArrayList;
	}
	public void set_isMultiselect(Boolean _isMultiselect) {
		this._isMultiselect = _isMultiselect;
	}
	
	public ArrayList<Long> get_checkedItemIds(){
		ArrayList<Long> categories = new ArrayList<Long>(); 
		for (int i = 0; i<categoryList.size(); i++){
			if(categoryList.get(i).isChecked()){
				categories.add(categoryList.get(i).get_category().get_id());
			}
		}
		return categories;
	}
}
