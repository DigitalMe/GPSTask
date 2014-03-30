package com.kajun258456357159.gps.task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.kajun258456357159.gps.task.R;
import com.kajun258456357159.gps.task.IO.DbDoAll;
import com.kajun258456357159.gps.task.layout.lists.LocationList;
import com.kajun258456357159.gps.task.vo.LocationVO;

public class EditLocations extends Activity implements OnClickListener, LocationListener {
	static final String TAG = "EditLocations";
	public final static String EDITLOCATIONS = "com.kajun258456357159.gps.task.EDITLOCAION";
	public final static String ID = ".ID";
	public final static String POIFILE = ".POIFILE";
	public final static int ACTIVITY_ID_SELECT_POI_FILE = 2;
	private LinearLayout layoutLocationList;
	private LocationList locationList; 
	private TextView locationName;
	private EditText textLattitute;
	private EditText textLongitute;
	private CheckBox checkIsBoycotted;
	private EditText textAddress;
	private ImageButton buttonGPS;
	private LocationManager locationManager;
	private Geocoder geocoder;
	private DbDoAll dbHelper = new DbDoAll(this);
	private LocationVO location = new LocationVO();
	private Intent intent;
	
	private String filePath ="";
	private Spinner spinnerAdd1;
	private Spinner spinnerLat;
	private Spinner spinnerLong;
	private Spinner spinnerPhone;


	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate started");
		setContentView(R.layout.activity_edit_locations);
		
		intent = getIntent();
		location.get_locationName().set_id(intent.getLongExtra(EDITLOCATIONS+ID, 0));
		location = dbHelper.loadLocationName(location.get_locationName().get_id());
		
		locationName = (TextView)findViewById(R.id.editLocation_locationName);
		locationName.setText(location.get_locationName().get_name());
		
		textAddress = (EditText)findViewById(R.id.editLocation_locationAddress);
		textLattitute = (EditText)findViewById(R.id.editLocation_lattitute);
		textLongitute = (EditText)findViewById(R.id.editLocation_longitude);
		checkIsBoycotted = (CheckBox)findViewById(R.id.editLocation_isBoycotted);
		buttonGPS = (ImageButton)findViewById(R.id.editLocation_getGPS);
		layoutLocationList = (LinearLayout)findViewById(R.id.editLocation_listLocation);
		
		spinnerAdd1 = (Spinner)findViewById(R.id.spinnerAdd1);
		spinnerLat = (Spinner)findViewById(R.id.spinnerLat);
		spinnerLong = (Spinner)findViewById(R.id.spinnerLong);
		spinnerPhone = (Spinner)findViewById(R.id.spinnerPhone);

		
		locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
		geocoder = new Geocoder(this);
		//TODO find out why this dosent work
		getActionBar().setDisplayHomeAsUpEnabled(true);
		Log.d(TAG, "onCreate finished");
	}
	
	public void onResume(){
		super.onResume();
		Log.d(TAG, "onResume started");
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, Long.valueOf(1000), Float.valueOf(10), this);
		buttonGPS.setEnabled(Boolean.FALSE);
		
		//This builds the list of locations
		layoutLocationList.removeAllViews();
		locationList = new LocationList(this);

		locationList.set_locationArrayList(dbHelper.loadLocationList(location.get_locationName().get_id()));
		locationList.setClickable(Boolean.TRUE);
		locationList.setOnClickListener(this);
		layoutLocationList.addView(locationList.get_locationList(this));
		Log.d(TAG, "onResume finished");
		
	}
	
	protected void onPause(){
		super.onPause();
		locationManager.removeUpdates(this);
	}
	
	public void onClickGetGPSCoordinates(View View){
		Log.d(TAG, "onClickGetGPSCoordinates started");
		textLattitute.setText(String.valueOf(location.get_coordinate().get_latitude()));
		textLongitute.setText(String.valueOf(location.get_coordinate().get_longitude()));
		try{
			List<Address> addresses = geocoder.getFromLocation(location.get_coordinate().get_latitude(), location.get_coordinate().get_longitude(), 10);
			location.set_address(addresses.get(0));
			textAddress.setText(location.get_address().getAddressLine(0) + "\n" + location.get_address().getAddressLine(1) + "\n" + location.get_address().getAddressLine(2));
			checkIsBoycotted.setChecked(location.is_ignore());
			locationManager.removeUpdates(this);

		} catch(IOException e){
			
		}
		Log.d(TAG, "onClickGetGPSCoordinates finished");
	}
	
	public void onLocationChanged(Location gpsLocation){
		location.get_coordinate().set_latitude(gpsLocation.getLatitude());
		location.get_coordinate().set_longitude(gpsLocation.getLongitude());
		buttonGPS.setEnabled(Boolean.TRUE);
	}
	
	public void onClickLoadPOI(View view){
		Log.d(TAG, "onClickLoadPOI started");
		Intent intent1 = new Intent(this, LoadPOI.class);
		intent1.putExtra(LoadPOI.LOADPOI + LoadPOI.ID, location.get_locationName().get_id());
		startActivityForResult(intent1, ACTIVITY_ID_SELECT_POI_FILE);
		Log.d(TAG, "onClickLoadPOI finished");
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		Log.d(TAG, "onActivityResult started");
		// See which sub activity has finished
		switch (requestCode) {
			case ACTIVITY_ID_SELECT_POI_FILE: {
				// Check resultCode to see if it finished correctly
				if (resultCode == RESULT_OK) {
					if (data != null) {
						
							FileReader fileReader;
							try {
								filePath = data.getStringExtra(EDITLOCATIONS + POIFILE);
								fileReader = new FileReader(new File(filePath));
								BufferedReader bufferedReader = new BufferedReader(fileReader);
								
								try {
									String line ="";
									if((line=bufferedReader.readLine())!=null){
										line="None,"+line;
										String[] locationDetails = line.split(",");
										ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, locationDetails);
										// Specify the layout to use when the list of choices appears
										adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
										// Apply the adapter to the spinner
										spinnerAdd1.setAdapter(adapter);
										spinnerLat.setAdapter(adapter);
										spinnerLat.setSelection(2);
										spinnerLong.setAdapter(adapter);
										spinnerLong.setSelection(1);
										spinnerPhone.setAdapter(adapter);
										spinnerPhone.setSelection(spinnerPhone.getCount()-1);
									}
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
					return;
				}
				// default:
				break;
			 
			}
		}
		Log.d(TAG, "onActivityResult finished");
	}
	
	public void onClickSavePoi(View view){
		new processPoiFile().execute();
	}

	public void onClickDeleteLocation(View view){
		Log.d(TAG, "onClickDeleteLocation started");
		location.set_isDeleted(Boolean.TRUE);
		onClickSaveLocation(view);
		Log.d(TAG, "onClickDeleteLocation finished");
	}
	
	public void onClickSaveLocation(View view){
		Log.d(TAG, "onClickSaveLocation started");
		long i;

		//TODO add ID field to save
		String address = textAddress.getText().toString()+"\n\n\n\n";
		String[] addressLines = address.split("\n", 4);
		location.set_address(addressLines[0], addressLines[1], addressLines[2], addressLines[3]);
		location.set_coordinate(Double.valueOf(textLattitute.getText().toString()), Double.valueOf(textLongitute.getText().toString()));
		location.set_ignore(checkIsBoycotted.isChecked());
		
		dbHelper.saveLocation(location);
		i=location.get_locationName().get_id();
		location = new LocationVO();
		textAddress.setText("");
		textLattitute.setText("");
		textLongitute.setText("");
		checkIsBoycotted.setChecked(Boolean.FALSE);
		location.get_locationName().set_id(i);
		this.onResume();
		Log.d(TAG, "onClickSaveLocation finished");
		
//		this.finish();
		
	}
	public void onClick(View v) {
		// TODO Auto-generated method stub
		LocationList locationList = (LocationList) v;
		location = dbHelper.loadLocation(locationList.get_clickedItemId());
		locationName.setText(location.get_locationName().get_name());
		textAddress.setText(location.get_address().getAddressLine(0) + "\n" + location.get_address().getAddressLine(1) + "\n" + location.get_address().getAddressLine(2));
		textLattitute.setText(String.valueOf(location.get_coordinate().get_latitude()));
		textLongitute.setText(String.valueOf(location.get_coordinate().get_longitude()));
		checkIsBoycotted.setChecked(location.is_ignore());
	}
	
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
	}
	
	private class processPoiFile extends AsyncTask<String, Integer, String>{


		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			FileReader fileReader;
			try {
				File file =new File(filePath);
				fileReader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				
				try {
					String line ="";
					ArrayList<LocationVO> locationList = new ArrayList<LocationVO>();
					LocationVO locationLine;
					while((line=bufferedReader.readLine())!=null){
						locationLine = new LocationVO();
						String[] locationDetails = line.split(",");
						Log.d(TAG, locationDetails[0] + locationDetails[1] + locationDetails[2] + locationDetails[3] + locationDetails[4]);
						locationLine.get_locationName().set_id(location.get_locationName().get_id());
						if((spinnerLat.getSelectedItemPosition()!=0) && (spinnerLong.getSelectedItemPosition()!=0)){
							locationLine.set_coordinate(Double.valueOf(locationDetails[spinnerLat.getSelectedItemPosition()-1]), Double.valueOf(locationDetails[spinnerLong.getSelectedItemPosition()-1]));
							locationLine.set_address(geocoder.getFromLocation(locationLine.get_coordinate().get_latitude(),locationLine.get_coordinate().get_longitude(),1).get(0));
							if(spinnerAdd1.getSelectedItemPosition()!=0){
								locationLine.get_address().setAddressLine(0, locationDetails[spinnerAdd1.getSelectedItemPosition()-1]);							
							}
							if(spinnerPhone.getSelectedItemPosition()!=0){
								locationLine.get_address().setPhone( locationDetails[spinnerPhone.getSelectedItemPosition()-1].replaceAll("[^0-9]", ""));
							}

						}
						
						
						locationList.add(locationLine);
					}
					bufferedReader.close();
					fileReader.close();
					file.delete();
					dbHelper.saveLocationList(locationList);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		protected void onPostExecute(String result){
			onResume();
		}
	}

}