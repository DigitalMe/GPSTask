package com.kajun258456357159.gps.task;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kajun258456357159.gps.task.R;
import com.kajun258456357159.gps.task.IO.DataManager;
import com.kajun258456357159.gps.task.layout.lists.TaskItem;
import com.kajun258456357159.gps.task.vo.CoordinateVO;
import com.kajun258456357159.gps.task.vo.LocationVO;
import com.kajun258456357159.gps.task.vo.TaskVO;



public class TaskList extends Activity{
	DataManager dataModel  = DataManager.getInstance();
	public final static String TASKLIST = "com.kajun258456357159.gps.task.TASKLIST";
	public final static String NAME = ".NAME";
	public final static String ID = ".ID";
	public final static String ISNEW = ".ISNEW";
	public final static int UPDATEMINDISTANCE = 50; //in meters
	private CoordinateVO gpsLocation = new CoordinateVO();
	private final String TAG = "TaskList";
//  TODO used to search task list	
	private EditText editSearchAddTask;
	private ArrayList<TaskVO> taskList;
	private ArrayList<TaskItem> taskArrayList;
	private BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			Log.d(TAG, "onReceive");
			updateDistance();

		}
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_task_list);
//        dbHelper = new DbDoAll(this);
        taskList = new ArrayList<TaskVO>();
        editSearchAddTask = (EditText)findViewById(R.id.editTasks_SearchAddTask);

    }
    
    public void onResume(){
    	super.onResume();
    	//load tasks
		taskList = dataModel.getTaskList();
		Collections.sort(taskList, new TaskVO());
		Collections.reverse(taskList);
		taskArrayList = new ArrayList<TaskItem>();
    	//add a row for each task
    	LinearLayout layoutListTasks = (LinearLayout)this.findViewById(R.id.layoutListTasks);
    	layoutListTasks.removeAllViews();
    	
    	//for loop to be add each task row to the task list
    	for (int i = 0; i<taskList.size();i++){
    		if (taskList.get(i).is_isComplete()){
    			
    		} else {
    			TaskItem taskRow = new TaskItem(this);
    			taskRow.set_Task(taskList.get(i), layoutListTasks);
    			taskArrayList.add(taskRow);
            	layoutListTasks.addView(taskArrayList.get(taskArrayList.size()-1).get_taskItem(this));
    		}
    	}
    	updateDistance();
    	this.registerReceiver(receiver, new IntentFilter(DataManager.GPS_FIX));
    	this.registerReceiver(receiver, new IntentFilter(DataManager.DB_LOADED));

    }
    
    public void onPause()
    {
    	super.onPause();
    	this.unregisterReceiver(receiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    
    
    public void onClickTaskName(View v) {
		Intent intent = new Intent(this, EditTasks.class);
		intent.putExtra(TASKLIST + NAME, "");
		intent.putExtra(TASKLIST + ID, Long.valueOf(v.getId()));
		intent.putExtra(TASKLIST + ISNEW, Boolean.FALSE);
		startActivity(intent);
    }

    public void onClickTaskDistance(View v) {
		if (Long.valueOf(v.getId())==0){
    	//bring to screen to notify user there are no location with this category
			Toast.makeText(this, "No locations for this task", Toast.LENGTH_SHORT).show();
		} else {
			//show location info
	    	Intent intent = new Intent(this, LocationView.class);
	    	intent.putExtra(LocationView.LOCATIONVIEW + LocationView.CATEGORYID, Long.valueOf(v.getId()));
	    	startActivity(intent);
		}
    }
    
    
	public void addTask(View view) {
				
		Intent intent = new Intent(this, EditTasks.class);
		
		editSearchAddTask = (EditText) findViewById(R.id.editTasks_SearchAddTask);
		String message = editSearchAddTask.getText().toString();
		intent.putExtra((TASKLIST + NAME), message);
		intent.putExtra(TASKLIST + ID, 0);
		intent.putExtra((TASKLIST + ISNEW), Boolean.TRUE);
		editSearchAddTask.setText("");
		startActivity(intent);
	}
	
	public void updateTask(View v){
		
    	TaskVO listTask = new TaskVO();
    	CheckBox checkBox = (CheckBox) v.findViewById(v.getId());
    	
		//get the id of the checkbox that was clicked
    	listTask.set_id(v.getId());
    	listTask.set_isComplete(checkBox.isChecked());
    	
		//write the id number and checked status to db
    	dataModel.completeTask(listTask);
	}
	
	private void updateDistance() {
		// TODO Auto-generated method stub
		if(DataManager.getIsDbLoaded() && DataManager.getIsGpsFixed())
		{
			gpsLocation.set_latitude(DataManager.getLocation().getLatitude());
			gpsLocation.set_longitude(DataManager.getLocation().getLongitude());
	    	if(gpsLocation.get_latitude()+gpsLocation.get_longitude()!=0){
	    		ArrayList<LocationVO> locationList = dataModel.getLocationList();
	        	if(locationList.size() > 0){
	        		Collections.sort(locationList, new LocationVO());
	        		for(int i = 0; i < taskArrayList.size(); i++)
	        		{
	        			for(int j = 0; j < locationList.size();j++)
	        			{
	        				if(taskArrayList.get(i).get_Task().get_taskName().get_categoryId() == locationList.get(j).get_categoryId())
	        				{
	        					taskArrayList.get(i).set_TaskDistance(locationList.get(j).get_coordinate().getDistance(gpsLocation, CoordinateVO.KMS), locationList.get(j).get_categoryId());
	        					break;
	        				}
	        			}
	        		}
	        	}
	    	}
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			
			break;
		case R.id.menuEditLocations:
			startActivity(new Intent(this, SelectLocationName.class));
			break;
		case R.id.menuEditCategories:
			startActivity(new Intent(this, EditCategories.class));
			break;
		case R.id.menuSettings:
			break;
		case R.id.menuLoadPOI:
			startActivity(new Intent(this, LoadPOI.class));
			break;
		}
		return true;
	}

}