package com.kajun258456357159.gps.task.layout.lists;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kajun258456357159.gps.task.R;
import com.kajun258456357159.gps.task.vo.CategoryVO;

public class CategoryItem extends LinearLayout implements OnClickListener{
	private CategoryVO _category;
	private LinearLayout categoryRow;
	private TextView textCategoryName;
	private CheckBox _checkCategory;
	private Boolean _isMultiselect;
	private static final String TAG = "CategoryItem";

	public CategoryItem(Context context) {
		super(context);
		Log.d(TAG, "CategoryItem started");
		categoryRow = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.category_row, null);
		textCategoryName = (TextView) categoryRow.findViewById(R.id.textCategory);
		_checkCategory = (CheckBox) categoryRow.findViewById(R.id.checkCategory);
		Log.d(TAG, "CategoryItem finished");
	}

	public LinearLayout get_categoryItem(Context context){

		// TODO GET RID of this as per ROSS
		textCategoryName.setOnClickListener(this);
		if (_isMultiselect) {
		}else{
			//remove the checkbox if it is not a multiselect list
			((LinearLayout)_checkCategory.getParent()).removeView(_checkCategory);
		}
		// TODO BUT NOT THIS
		this.setClickable(Boolean.TRUE);
    	this.addView(categoryRow);
		Log.d(TAG, "get_categoryItem finished");
    	return this;
	}

	public CategoryVO get_category() {
		return _category;
	}

	public void set_category(CategoryVO category) {
		_category = category;
		textCategoryName.setText(category.get_name());
		textCategoryName.setId(Integer.valueOf(Long.toString(category.get_id())));
		_checkCategory.setId(Integer.valueOf(Long.toString(category.get_id())));
	}


	public void onClick(View v) {
		// TODO Auto-generated method stub
		this.performClick();
	}

	public void set_isMultiselect(Boolean _isMultiselect) {
		this._isMultiselect = _isMultiselect;
	}
	public Boolean isChecked(){
		return _checkCategory.isChecked();
	}
	public void setChecked(){
		_checkCategory.setChecked(Boolean.TRUE);
	}
}
