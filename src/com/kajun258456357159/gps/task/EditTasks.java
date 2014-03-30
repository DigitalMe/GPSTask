package com.kajun258456357159.gps.task;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.kajun258456357159.gps.task.R;
import com.kajun258456357159.gps.task.IO.DbDoAll;
import com.kajun258456357159.gps.task.vo.TaskVO;

public class EditTasks extends Activity{
	public final static  String EDITTASK = "com.kajun258456357159.gps.task.EDITTASK";
	public final static String ID = ".ID";
	public final static String CATEGORYID = ".CategoryID";
	public final static int ACTIVITY_ID_SELECT_CATEGORY = 1;

	private static final String TAG = "EditTasks";
	private DbDoAll dbHelper = new DbDoAll(this);
	private TaskVO task = new TaskVO();
	private boolean isNew;
	private EditText categoryName;
	private EditText taskName; 
	private Spinner spinner;
	private Intent intent;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate started");
		
		setContentView(R.layout.activity_edit_tasks);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		//get the task details
		intent = getIntent();
		
		task.get_taskName().set_name(intent.getStringExtra(TaskList.TASKLIST + TaskList.NAME));
		task.set_id(intent.getLongExtra(TaskList.TASKLIST + TaskList.ID, 0));
		isNew = intent.getBooleanExtra(TaskList.TASKLIST + TaskList.ISNEW, Boolean.TRUE);
		
		taskName = (EditText)findViewById(R.id.editEditTask_TaskName);
		categoryName = (EditText)findViewById(R.id.editEditTask_CategoryName);
		spinner = (Spinner) findViewById(R.id.spinnerTaskPriority);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.priorities , android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);

		if (isNew){
			taskName.setText(task.get_taskName().get_name());
			//TODO setup settings to set a default priority pulled from settings
			spinner.setSelection(1, false);
		} else {
			// load task if it already exists
			task = dbHelper.loadEditTask(task.get_id());
			taskName.setText(task.get_taskName().get_name());
			spinner.setSelection(task.get_priority(), false);
			categoryName.setText(task.get_category().get_name());
		}

		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		Log.d(TAG, "onCreate finnished");
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
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
	
	public void onClickSelectCategory (View view) {
		Log.d(TAG, "onClickSelectCategory started");
		task.get_taskName().set_name(taskName.getText().toString());
		task.set_priority(Integer.valueOf(String.valueOf(spinner.getSelectedItemId())));

		Intent intent1 = new Intent(this, SelectCategory.class);
		intent1.putExtra(SelectCategory.SELECTCATEGORY + SelectCategory.ID, task.get_category().get_id());
		startActivityForResult(intent1, ACTIVITY_ID_SELECT_CATEGORY);
		Log.d(TAG, "onClickSelectCategory finished");
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		Log.d(TAG, "onActivityResult started");
		// See which sub activity has finished
		switch (requestCode) {
			case ACTIVITY_ID_SELECT_CATEGORY: {
				// Check resultCode to see if it finished correctly
				if (resultCode == RESULT_OK) {
					if (data != null) {
						task.set_category(dbHelper.loadCategory(data.getLongExtra(EDITTASK + CATEGORYID, 0)));
						categoryName.setText(task.get_category().get_name().toString());
						categoryName.setId(Integer.valueOf(Long.toString(task.get_category().get_id())));
					}
					return;
				}
				// default:
				break;
			 
			}
		}
		Log.d(TAG, "onActivityResult finished");
	}


	public void editTask_saveTask(View view){
		Log.d(TAG, "editTask_saveTask started");

		//TODO add ID field to save
		task.get_taskName().set_name(taskName.getText().toString());
		task.set_priority(Integer.valueOf(String.valueOf(spinner.getSelectedItemId())));
		
		
		dbHelper.saveTask(task);
		// clean up this code to use DBHelper to save the task items to the db
		taskName.setText("");
		Log.d(TAG, "editTask_saveTask finished");
		this.finish();
		
	}
}