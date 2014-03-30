package com.kajun258456357159.gps.task;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class LoadPOI extends ListActivity{
	static final String TAG = "LoadPOI";
	public final static String LOADPOI = "com.kajun258456357159.gps.task.LOADPOI";
	public final static String ID = ".ID";
	private List<String> fileList = new ArrayList<String>();
	 
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
			Log.d(TAG, "onCreate started");

	        File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/external_sd/POI");
	        ListDir(root);
	 
	    }
	     
	    void ListDir(File f){
	     File[] files = f.listFiles();
	     fileList.clear();
	     for (File file : files){
	      fileList.add(file.getName());
	     }
	      
//	     ArrayAdapter<String> directoryList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fileList);
	     ArrayAdapter<String> directoryList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fileList);
	     setListAdapter(directoryList);
	    }
	    
	    @Override
	    public void  onListItemClick(ListView l, View v, int position, long id) {
	    	super.onListItemClick(l, v, position, id);
//		public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
			// TODO Auto-generated method stub
	    	Toast.makeText(this, Environment.getExternalStorageDirectory().getAbsolutePath() + "/external_sd/POI/" + fileList.get(position) , Toast.LENGTH_LONG).show();
			Intent intent = new Intent();
			intent.putExtra(EditLocations.EDITLOCATIONS + EditLocations.POIFILE, Environment.getExternalStorageDirectory().getAbsolutePath() + "/external_sd/POI/" + fileList.get(position));
			setResult(RESULT_OK, intent);
			this.finish();
		}
		
	 
	}