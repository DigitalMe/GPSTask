package com.kajun258456357159.gps.task.vo;

public class TaskNameVO {
	private long _id;
	private String _name;
	private long _categoryId;
	private boolean _isDeleted;
	private boolean _isPrivate;
	private boolean _isUploaded;
	
	
	//Constructor
	public TaskNameVO(long id, String name, long categoryId, boolean isDeleted){
		set_id(id);
		set_name(name);
		set_isDeleted(isDeleted);
		set_categoryId(categoryId);
	}

	public TaskNameVO(){
	}

	public long get_id() {
		return _id;
	}


	public void set_id(long _id) {
		this._id = _id;
	}


	public String get_name() {
		return _name;
	}


	public void set_name(String _name) {
		this._name = _name;
	}


	public boolean is_isDeleted() {
		return _isDeleted;
	}


	public void set_isDeleted(boolean _isDeleted) {
		this._isDeleted = _isDeleted;
	}

	public long get_categoryId() {
		return _categoryId;
	}

	public void set_categoryId(long _categoryId) {
		this._categoryId = _categoryId;
	}

	public boolean is_isPrivate() {
		return _isPrivate;
	}

	public void set_isPrivate(boolean _isPrivate) {
		this._isPrivate = _isPrivate;
	}

	public boolean is_isUploaded() {
		return _isUploaded;
	}

	public void set_isUploaded(boolean _isUploaded) {
		this._isUploaded = _isUploaded;
	}
}
