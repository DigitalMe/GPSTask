package com.kajun258456357159.gps.task.vo;

import java.util.Comparator;
import java.util.Locale;

import com.kajun258456357159.gps.task.IO.DataManager;

import android.R.string;
import android.location.Address;
import android.telephony.PhoneNumberUtils;

public class LocationVO implements Comparator<LocationVO>{
	DataManager dataModel  = DataManager.getInstance();
	private long _id;
	private LocationNameVO _locationName = new LocationNameVO();
	private CoordinateVO _coordinate = new CoordinateVO();
	private Address _address = new Address(Locale.CANADA);
	private long _categoryId = -1;
	private boolean _ignore = Boolean.FALSE;
	private boolean _isPrivate = Boolean.FALSE;
	private boolean _isDeleted = Boolean.FALSE;
	private boolean _isUploaded = Boolean.FALSE;
	
	//Constructor
		public LocationVO(long id, LocationNameVO locationName, CoordinateVO coordinate, Address address, long categoryId, boolean isBoycotted, boolean isDeleted, boolean isUploaded){
			set_id(id);
			set_locationName(locationName);
			set_coordinate(coordinate);
			set_address(address);
			set_categoryId(categoryId);
			set_ignore(isBoycotted);
			set_isDeleted(isDeleted);
			set_isUploaded(isUploaded);
		}
		
		public LocationVO(){
			
		}
		
        
        
//		public boolean equals(LocationVO otherLocation)
//		{
//			return (otherLocation._id == _id && otherLocation.lattitude == latitude && otherLocation.longitude == longitude);
//			return true;
//		}
		public long get_id() {
			return _id;
		}

		public void set_id(long _id) {
			this._id = _id;
		}

		public CoordinateVO get_coordinate() {
			return _coordinate;
		}

		public void set_coordinate(CoordinateVO _coordinate) {
			this._coordinate = _coordinate;
		}
		public void set_coordinate(double lattitude, double longitude){
			this._coordinate.set_latitude(lattitude);
			this._coordinate.set_longitude(longitude);
		}

		public boolean is_ignore() {
			return _ignore;
		}

		public void set_ignore(boolean _ignore) {
			this._ignore = _ignore;
		}

		public boolean is_isDeleted() {
			return _isDeleted;
		}

		public void set_isDeleted(boolean _isDeleted) {
			this._isDeleted = _isDeleted;
		}

		public String get_FormatedPhone() {
			String phone = "";
			if(get_address().getPhone() != null)
			{
				phone = get_address().getPhone();
				if (phone.length() == 10)
				{
					phone = "(" + phone.substring(0, 3) + ") " + phone.substring(3, 6) + "-" + phone.substring(6, 10);
				}
				
			}
			return phone;
		}
		public Address get_address() {
			return _address;
		}

		public void set_address(Address address) {
			this._address = address;
		}
		public void set_address(String addressLine0, String addressLine1, String addressLine2, String addressLine3){
			this._address.setAddressLine(0, addressLine0);
			this._address.setAddressLine(1, addressLine1);
			this._address.setAddressLine(2, addressLine2);
			this._address.setAddressLine(3, addressLine3);
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

		public LocationNameVO get_locationName() {
			return _locationName;
		}

		public void set_locationName(LocationNameVO _locationName) {
			this._locationName = _locationName;
		}

		@Override
        public int compare(LocationVO location1, LocationVO location2) {
        	CoordinateVO currentLocation = new CoordinateVO(DataManager.getLocation().getLatitude(), DataManager.getLocation().getLongitude());
            double lat1 = location1.get_coordinate().get_latitude();
            double lng1 = location1.get_coordinate().get_longitude();

            double lat2 = location2.get_coordinate().get_latitude();
            double lng2 = location2.get_coordinate().get_longitude();

            double location1Distance = currentLocation.getDistance(lat1,lng1, CoordinateVO.METERS);
            double location2Distance = currentLocation.getDistance(lat2,lng2, CoordinateVO.METERS);
            if (location1Distance < location2Distance) 
                return -1;
            else if (location1Distance > location2Distance) 
                return 1;
            else return 0;
        }
}
