package com.kajun258456357159.gps.task.vo;

public class LocationCategoryVO {
	private long _id = -1;
	private long _categoryId = -1;
	private long _locationNameId = -1;
	private boolean _isUploaded = Boolean.FALSE;
	
	public LocationCategoryVO(long id, long categoryId, long locationNameId, boolean isUploaded){
		set_id(id);
		set_categoryId(categoryId);
		set_locationNameId(locationNameId);
		set_isUploaded(isUploaded);
	}

	public LocationCategoryVO(){
		
	}
	
	
	public long get_id() {
		return _id;
	}
	public void set_id(long _id) {
		this._id = _id;
	}
	public long get_categoryId() {
		return _categoryId;
	}
	public void set_categoryId(long _categoryId) {
		this._categoryId = _categoryId;
	}
	public long get_locationNameId() {
		return _locationNameId;
	}
	public void set_locationNameId(long _locationNameId) {
		this._locationNameId = _locationNameId;
	}
	public boolean is_isUploaded() {
		return _isUploaded;
	}
	public void set_isUploaded(boolean _isUploaded) {
		this._isUploaded = _isUploaded;
	}

}
