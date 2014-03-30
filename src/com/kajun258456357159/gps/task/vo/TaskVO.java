package com.kajun258456357159.gps.task.vo;

import java.util.Comparator;
import java.util.Date;

public class TaskVO implements Comparator<TaskVO>{
	private long _id = -1;
	private TaskNameVO _taskName = new TaskNameVO();
	private int _priority = 0;
	private long _parentId = 0;
	private Date _startDateTime = new Date();
	private Date _endDateTime = new Date();
	private LocationVO _location = new LocationVO();
	private CategoryVO _category = new CategoryVO();
	private Date _completionDate = new Date();
	private boolean _isComplete = Boolean.FALSE;
	
	//Constructor
	public TaskVO(long id, int priority, long parentId, Date startDateTime, Date endDateTime, LocationVO location, boolean isComplete, TaskNameVO taskName, CategoryVO category){
		set_id(id);
		set_priority(priority);
		set_parentId(parentId);
		set_startDateTime(startDateTime);
		set_endDateTime(endDateTime);
		set_location(location);
		set_isComplete(isComplete);
		set_taskName(taskName);
		set_category(category);
	}
	
	public TaskVO(){
	}
	
	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public int get_priority() {
		return _priority;
	}

	public void set_priority(int _priority) {
		this._priority = _priority;
	}

	public Date get_startDateTime() {
		return _startDateTime;
	}

	public void set_startDateTime(Date _startDateTime) {
		this._startDateTime = _startDateTime;
	}

	public Date get_endDateTime() {
		return _endDateTime;
	}

	public void set_endDateTime(Date _endDateTime) {
		this._endDateTime = _endDateTime;
	}

	public LocationVO get_location() {
		return _location;
	}

	public void set_location(LocationVO _location) {
		this._location = _location;
	}

	public boolean is_isComplete() {
		return _isComplete;
	}

	public void set_isComplete(boolean _isComplete) {
		this._isComplete = _isComplete;
	}

	public TaskNameVO get_taskName() {
		return _taskName;
	}

	public void set_taskName(TaskNameVO _taskName) {
		this._taskName = _taskName;
	}

	public CategoryVO get_category() {
		return _category;
	}

	public void set_category(CategoryVO _category) {
		this._category = _category;
	}

	public long get_parentId() {
		return _parentId;
	}

	public void set_parentId(long _parentId) {
		this._parentId = _parentId;
	}

	public Date get_completionDate() {
		return _completionDate;
	}

	public void set_completionDate(Date _completionDate) {
		this._completionDate = _completionDate;
	}
	
	@Override
    public int compare(TaskVO task1, TaskVO task2) {
        int priority1 = task1.get_priority();
        int priority2 = task2.get_priority();

        if (priority1 < priority2) 
            return -1;
        else if (priority1 > priority2) 
            return 1;
        else return 0;
    }

}
