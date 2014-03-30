package com.kajun258456357159.gps.task.IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import com.kajun258456357159.gps.task.vo.CategoryVO;
import com.kajun258456357159.gps.task.vo.LocationCategoryVO;
import com.kajun258456357159.gps.task.vo.LocationNameVO;
import com.kajun258456357159.gps.task.vo.LocationVO;
import com.kajun258456357159.gps.task.vo.TaskNameVO;
import com.kajun258456357159.gps.task.vo.TaskVO;

public class DbHelper extends SQLiteOpenHelper {
	public DbHelper(Context context, String name, CursorFactory factory,
			int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		// TODO Auto-generated constructor stub
	}



	static final String TAG = "DdHelper";
	static final String DB_NAME = "/data/data/com.kajun258456357159.gps.task/databases/Tasks.db";
	static final int DB_VERSION = 9;
	public static final int LOAD_ALL_CATEGORIES = -1;

	//Database tables
	static final String TABLE_LOCATION_NAME = 		"tableLocationName";
	static final String TABLE_LOCATION = 			"tableLocation";
	static final String TABLE_LOCATION_CATEGORY = 	"tableLocationCategory";
	static final String TABLE_TASK = 				"tableTask";
	static final String TABLE_TASK_NAME = 			"tableTaskName";
	static final String TABLE_CATEGORY = 			"tableCategory";
	
	//Fields for TABLE_LOCATION_NAME
	static final String LOCATION_NAME_ID = 			"intLocationNameId";
	static final String LOCATION_NAME = 			"txtLocationName";
	static final String LOCATION_NAME_IGNORE = 		"bLocationNameIgnore";
	static final String LOCATION_NAME_IS_PRIVATE =	"bLocationNameIsPrivate";
	static final String LOCATION_NAME_DELETE = 		"bLocationNameDelete";
	static final String LOCATION_NAME_UPLOADED = 	"bLocationNameUploaded";
	
	//Fields for TABLE_LOCATION
	static final String LOCATION_ID = 				"intLocationId";
//	static final String LOCATION_NAME_ID = 			"intLocationName";  <--link to LOCATION_NAME table
	static final String LOCATION_LAT = 				"flLocationLat";
	static final String LOCATION_LONG = 			"flLocationLong";
	static final String LOCATION_ADDRESS_LINE_0 = 	"txtLocationAddressLine0";
	static final String LOCATION_ADDRESS_LINE_1 = 	"txtLocationAddressLine1";
	static final String LOCATION_ADDRESS_LINE_2 = 	"txtLocationAddressLine2";
	static final String LOCATION_ADDRESS_LINE_3 = 	"txtLocationAddressLine3";
	static final String LOCATION_PHONE = 			"txtLocationPhoneNumber";
	static final String LOCATION_IGNORE = 			"bLocationIgnore";
	static final String LOCATION_IS_PRIVATE =		"bLocationIsPrivate";
	static final String LOCATION_DELETE = 			"bLocationDelete";
	static final String LOCATION_UPLOADED = 		"bLocationUploaded";

	//Fields for TABLE_TASK
	static final String TASK_ID = 					"intTaskId";
//	static final String TASK_NAME_ID = 				"intTaskNameId";
	static final String TASK_PRIORITY = 			"txtTaskPriority";
	static final String TASK_START_DATE = 			"txtTaskStartDate";
	static final String TASK_END_DATE = 			"txtTaskEndDate";
//	static final String LOCATION_NAME_ID = 			"txtLocationNameId";
//	static final String LOCATION_ID = 				"intLocationId";
//	static final String CATEGORY_ID = 				"intCategoryId";
	static final String TASK_PARENT_ID = 			"txtTaskParentId";
	static final String TASK_COMPLETE_DATE = 		"txtTaskCompleteDate";
	static final String TASK_COMPLETE = 			"bTaskComplete";	

	//Fields for TABLE_TASK_NAME
	static final String TASK_NAME_ID = 				"intTaskNameId";
	static final String TASK_NAME = 				"txtTaskName";
//	static final String CATEGORY_ID = 				"intCategoryId";
	static final String TASK_NAME_DELETED = 		"bTaskNameDeleted"; 
	static final String TASK_NAME_IS_PRIVATE =		"bTaskNameIsPrivate";
	static final String TASK_NAME_UPLOADED = 		"bTaskNameUploaded"; 

	//Fields for TABLE_CATEGORY
	static final String CATEGORY_ID = 				"intCategoryId";
	static final String CATEGORY_NAME = 			"txtCategoryName";
	static final String CATEGORY_PARENT_ID = 		"intCategoryParentId";
	static final String CATEGORY_IS_PRIVATE =		"bCategoryIsPrivate";
	static final String CATEGORY_DELETE = 			"bCategoryDelete";
	static final String CATEGORY_UPLOADED = 		"bCategoryUploaded";
	
	// Fields for TABLE_LOCATION_CATEGORY 
	static final String LOCATION_CATEGORY_ID = 		"txtID";
//	static final String CATEGORY_ID = 				"intCategoryId";
//	static final String LOCATION_NAME_ID = 			"intLocationName";
	static final String LOCATION_CATEGORY_UPLOADED ="bLocationCategoryUploaded";
	
	//Constructor
	public DbHelper(Context context){
		super(context,DB_NAME,null,DB_VERSION);
		BackupDatabaseFile();
		}
	
	// Called only once, first time the DB is created
	@Override
	public void onCreate(SQLiteDatabase db){
		Log.d(TAG, "onCreate started");

		String sqlLocationName = "CREATE table " + TABLE_LOCATION_NAME + "(" + LOCATION_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ LOCATION_NAME + " text, "
				+ LOCATION_NAME_IGNORE + " boolean, " + LOCATION_NAME_IS_PRIVATE + " boolean, " + LOCATION_NAME_DELETE + " boolean, " + LOCATION_NAME_UPLOADED + " boolean)";
		db.execSQL(sqlLocationName);
		Log.d(TAG, "onCreateed sql: " + sqlLocationName);
		
		String sqlLocation = "CREATE table " + TABLE_LOCATION + "(" + LOCATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + LOCATION_NAME_ID + " int, "
				+ LOCATION_LAT + " float, " + LOCATION_LONG + " float, "
				+ LOCATION_ADDRESS_LINE_0 + " text, " + LOCATION_ADDRESS_LINE_1 + " text, " + LOCATION_ADDRESS_LINE_2 + " text, " + LOCATION_ADDRESS_LINE_3 + " text, "
				+ LOCATION_PHONE + " text, "
				+ LOCATION_IGNORE + " boolean, " + LOCATION_IS_PRIVATE + " boolean, " + LOCATION_DELETE + " boolean, " + LOCATION_UPLOADED + " boolean)";
		db.execSQL(sqlLocation);
		Log.d(TAG, "onCreateed sql: " + sqlLocation);
		
		String sqlTask = "CREATE table " + TABLE_TASK + "(" + TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TASK_NAME_ID + " int, " + TASK_PRIORITY + " int, "
				+ TASK_START_DATE + " text, " + TASK_END_DATE +  " text, " + LOCATION_ID + " int, " + LOCATION_NAME_ID + " int, "
				+ TASK_PARENT_ID + " INT, " + TASK_COMPLETE + " boolean" + TASK_COMPLETE_DATE + " text";
		db.execSQL(sqlTask);
		Log.d(TAG, "onCreateed sql: " + sqlTask);
		
		String sqlTaskName = "CREATE table " + TABLE_TASK_NAME + "(" + TASK_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TASK_NAME + " text, "
				+ CATEGORY_ID + " int, " + TASK_NAME_IS_PRIVATE + " boolean, " + TASK_NAME_DELETED + " boolean, " + TASK_NAME_UPLOADED + " boolean)";
		db.execSQL(sqlTaskName);
		Log.d(TAG, "onCreateed sql: " + sqlTaskName);
		
		
		String sqlCategoy = "Create table " + TABLE_CATEGORY + "(" + CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CATEGORY_NAME + " int, "
				+ CATEGORY_PARENT_ID + " int, " + CATEGORY_IS_PRIVATE + " boolean, " + CATEGORY_DELETE + " boolean, " + CATEGORY_UPLOADED + " boolean)";
		db.execSQL(sqlCategoy);
		Log.d(TAG, "onCreateed sql: " + sqlCategoy);
		
		String sqlLocationDept = "CREATE table " + TABLE_LOCATION_CATEGORY + "(" + LOCATION_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ LOCATION_NAME_ID + " int, " + CATEGORY_ID + " int)";
		db.execSQL(sqlLocationDept);
		Log.d(TAG, "onCreateed sql: " + sqlLocationDept);
		Log.d(TAG, "onCreate finished");

	}
	
	
	//Called when newVersion != oldVersion
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		Log.d(TAG, "onUpdated started. Old version: " + oldVersion + ".  New version: " + newVersion);

		switch(oldVersion){
		case 2:
			//TASK TABLE added CATEGORY ID field
			db.execSQL("ALTER TABLE " + TABLE_TASK + " ADD COLUMN " + CATEGORY_ID + " int");
		case 3:
			//Location category table had an error with the a column name
			db.execSQL("DROP TABLE " + TABLE_LOCATION_CATEGORY);
			db.execSQL("CREATE table " + TABLE_LOCATION_CATEGORY + "(" + LOCATION_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ LOCATION_NAME_ID + " int, " + LOCATION_ID + " int)");
		case 4:
			//Lets try that again
			db.execSQL("DROP TABLE " + TABLE_LOCATION_CATEGORY);
			db.execSQL("CREATE table " + TABLE_LOCATION_CATEGORY + "(" + LOCATION_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ LOCATION_NAME_ID + " int, " + CATEGORY_ID + " int)");
		case 5:
			//add the address lines
			db.execSQL("ALTER TABLE " + TABLE_LOCATION + " ADD COLUMN " + LOCATION_ADDRESS_LINE_0 + " text");
			db.execSQL("ALTER TABLE " + TABLE_LOCATION + " ADD COLUMN " + LOCATION_ADDRESS_LINE_1 + " text");
			db.execSQL("ALTER TABLE " + TABLE_LOCATION + " ADD COLUMN " + LOCATION_ADDRESS_LINE_2 + " text");
			db.execSQL("ALTER TABLE " + TABLE_LOCATION + " ADD COLUMN " + LOCATION_ADDRESS_LINE_3 + " text");
		case 6:
			//do nothing, testing the database backup
		case 7:
			db.execSQL("ALTER TABLE " + TABLE_LOCATION + " ADD COLUMN " + LOCATION_PHONE + " text");
		case 8:
			db.execSQL("ALTER TABLE " + TABLE_TASK + " ADD COLUMN " + TASK_COMPLETE_DATE + " text");
			db.execSQL("ALTER TABLE " + TABLE_CATEGORY + " ADD COLUMN " + CATEGORY_PARENT_ID + " int");
			db.execSQL("ALTER TABLE " + TABLE_LOCATION_NAME + " ADD COLUMN " +  LOCATION_NAME_IS_PRIVATE + " boolean");
			db.execSQL("ALTER TABLE " + TABLE_LOCATION + " ADD COLUMN " + LOCATION_IS_PRIVATE + " boolean");
			db.execSQL("ALTER TABLE " + TABLE_TASK_NAME + " ADD COLUMN " + TASK_NAME_IS_PRIVATE + " boolean");
			db.execSQL("ALTER TABLE " + TABLE_CATEGORY + " ADD COLUMN " + CATEGORY_IS_PRIVATE + " boolean");
			
		}
		

		Log.d(TAG, "onUpdated finished. Old version: " + oldVersion + ".  New version: " + newVersion);
	}
	
	private Boolean BackupDatabaseFile(){
		Boolean result = Boolean.FALSE;
		try {
			InputStream in = new FileInputStream(DB_NAME);
			FileOutputStream out = new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath() + "/external_sd/POI/backup.db");
			byte[] buff = new byte[1024];
			int read = 0;
			try {
				while ((read = in.read(buff)) > 0) {
					out.write(buff, 0, read);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				} finally {
					try {
						in.close();
						out.close();
						result = Boolean.TRUE;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<LocationNameVO> loadTable(LocationNameVO foo){
		SQLiteDatabase db = this.getWritableDatabase();
		String sqlQuery = "SELECT " + TABLE_LOCATION_NAME + ".* " + " FROM " + TABLE_LOCATION_NAME;
		Cursor cursor = db.rawQuery(sqlQuery, null);
		int id 				= cursor.getColumnIndex(LOCATION_NAME_ID);
		int name	 		= cursor.getColumnIndex(LOCATION_NAME);
		int isBoycotted		= cursor.getColumnIndex(LOCATION_NAME_IGNORE);
		int isPrivate		= cursor.getColumnIndex(LOCATION_NAME_IS_PRIVATE);
		int isDeleted		= cursor.getColumnIndex(LOCATION_NAME_DELETE);
		int isUploaded		= cursor.getColumnIndex(LOCATION_NAME_UPLOADED);
		ArrayList<LocationNameVO> list = new ArrayList<LocationNameVO>();
		LocationNameVO locationName;
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
			locationName = new LocationNameVO();
			locationName.set_id									(cursor.getLong		(id));
			locationName.set_name								(cursor.getString	(name));
			locationName.set_isBoycotted(Boolean.parseBoolean	(cursor.getString	(isBoycotted)));
			locationName.set_isPrivate	(Boolean.parseBoolean	(cursor.getString	(isPrivate)));
			locationName.set_isDeleted	(Boolean.parseBoolean	(cursor.getString	(isDeleted)));
			locationName.set_isUploaded	(Boolean.parseBoolean	(cursor.getString	(isUploaded)));
			list.add(locationName);
		}
		db.close();
		return list;
	}
	
	
	public ArrayList<LocationVO> loadTable(LocationVO foo){
		SQLiteDatabase db = this.getWritableDatabase();
		String sqlQuery = "SELECT " + TABLE_LOCATION + ".* " + " FROM " + TABLE_LOCATION;
		Cursor cursor = db.rawQuery(sqlQuery, null);
		int id				= cursor.getColumnIndex(LOCATION_ID);
		int nameId			= cursor.getColumnIndex(LOCATION_NAME_ID);
		int latitude		= cursor.getColumnIndex(LOCATION_LAT);
		int longitude		= cursor.getColumnIndex(LOCATION_LONG);
		int addressLine0	= cursor.getColumnIndex(LOCATION_ADDRESS_LINE_0);
		int addressLine1	= cursor.getColumnIndex(LOCATION_ADDRESS_LINE_1);
		int addressLine2	= cursor.getColumnIndex(LOCATION_ADDRESS_LINE_2);
		int addressLine3	= cursor.getColumnIndex(LOCATION_ADDRESS_LINE_3);
		int phoneNumber		= cursor.getColumnIndex(LOCATION_PHONE);
		int isBoycotted		= cursor.getColumnIndex(LOCATION_IGNORE);
		int isPrivate		= cursor.getColumnIndex(LOCATION_IS_PRIVATE);
		int isDeleted		= cursor.getColumnIndex(LOCATION_DELETE);
		int isUploaded		= cursor.getColumnIndex(LOCATION_UPLOADED);
		
		ArrayList<LocationVO> list = new ArrayList<LocationVO>();
		LocationVO location;
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
			location = new LocationVO();
			location.set_id									(cursor.getLong		(id));
			location.get_locationName().set_id				(cursor.getLong		(nameId));
			location.get_coordinate().set_latitude			(cursor.getFloat	(latitude));
			location.get_coordinate().set_longitude			(cursor.getFloat	(longitude));
			if(cursor.isNull(addressLine0)) {
				location.get_address().setAddressLine(0,"");
			}else{
				location.get_address().setAddressLine(0, cursor.getString(addressLine0));
			}
			if(cursor.isNull(addressLine1)) {
				location.get_address().setAddressLine(1,"");
			}else{
				location.get_address().setAddressLine(1, cursor.getString(addressLine1));
			}
			if(cursor.isNull(addressLine2)) {
				location.get_address().setAddressLine(2,"");
			}else{
				location.get_address().setAddressLine(2, cursor.getString(addressLine2));
			}
			if(cursor.isNull(addressLine3)) {
				location.get_address().setAddressLine(3,"");
			}else{
				location.get_address().setAddressLine(3, cursor.getString(addressLine3));
			}
			location.get_address().setPhone					(cursor.getString	(phoneNumber));
			location.set_ignore		(Boolean.parseBoolean	(cursor.getString	(isBoycotted)));
			location.set_isPrivate	(Boolean.parseBoolean	(cursor.getString	(isPrivate)));
			location.set_isDeleted	(Boolean.parseBoolean	(cursor.getString	(isDeleted)));
			location.set_isUploaded	(Boolean.parseBoolean	(cursor.getString	(isUploaded)));
			list.add(location);
		}
		db.close();
		return list;
	}

	
	public ArrayList<TaskVO> loadTable(TaskVO foo){
		SQLiteDatabase db = this.getWritableDatabase();
		String sqlQuery = "SELECT " + TABLE_TASK + ".* " + " FROM " + TABLE_TASK;
		Cursor cursor = db.rawQuery(sqlQuery, null);
		int id 				= cursor.getColumnIndex(TASK_ID);
		int nameId			= cursor.getColumnIndex(TASK_NAME_ID);
		int priority		= cursor.getColumnIndex(TASK_PRIORITY);
		int startDateTime	= cursor.getColumnIndex(TASK_START_DATE);
		int endDateTime		= cursor.getColumnIndex(TASK_END_DATE);
		int locationId		= cursor.getColumnIndex(LOCATION_ID);
		int categoryId		= cursor.getColumnIndex(CATEGORY_ID);
		int parentId		= cursor.getColumnIndex(TASK_PARENT_ID);
		int completionDate	= cursor.getColumnIndex(TASK_COMPLETE_DATE);
		int isComplete		= cursor.getColumnIndex(TASK_COMPLETE);
		ArrayList<TaskVO> list = new ArrayList<TaskVO>();
		TaskVO task;
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
			task = new TaskVO();
			task.set_id									(cursor.getLong		(id));
			task.get_taskName().set_id					(cursor.getLong		(nameId));
			task.set_priority							(cursor.getInt		(priority));
//			task.set_startDateTime	(Date.valueOf		(cursor.getString	(startDateTime)));
//			task.set_endDateTime	(Date.valueOf		(cursor.getString	(endDateTime)));
			task.get_location().set_id					(cursor.getLong		(locationId));
			task.get_category().set_id					(cursor.getLong		(categoryId));
			task.set_parentId							(cursor.getLong		(parentId));
//			task.set_completionDate	(Date.valueOf		(cursor.getString	(completionDate)));
			task.set_isComplete	(Boolean.parseBoolean	(cursor.getString	(isComplete)));
			list.add(task);
		}
		db.close();
		return list;
	}
	
	public ArrayList<TaskNameVO> loadTable(TaskNameVO foo){
		SQLiteDatabase db = this.getWritableDatabase();
		String sqlQuery = "SELECT " + TABLE_TASK_NAME + ".* " + " FROM " + TABLE_TASK_NAME;
		Cursor cursor = db.rawQuery(sqlQuery, null);
		int id				= cursor.getColumnIndex(TASK_NAME_ID);
		int name			= cursor.getColumnIndex(TASK_NAME);
		int categoryId		= cursor.getColumnIndex(CATEGORY_ID);
		int isPrivate		= cursor.getColumnIndex(TASK_NAME_IS_PRIVATE);
		int isDeleted		= cursor.getColumnIndex(TASK_NAME_DELETED);
		int isUploaded		= cursor.getColumnIndex(TASK_NAME_UPLOADED);
		ArrayList<TaskNameVO> list = new ArrayList<TaskNameVO>();
		TaskNameVO taskName;
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
			taskName = new TaskNameVO();
			taskName.set_id									(cursor.getLong		(id));
			taskName.set_name								(cursor.getString	(name));
			taskName.set_categoryId							(cursor.getLong		(categoryId));
			taskName.set_isPrivate	(Boolean.parseBoolean	(cursor.getString	(isPrivate)));
			taskName.set_isDeleted	(Boolean.parseBoolean	(cursor.getString	(isDeleted)));
			taskName.set_isUploaded	(Boolean.parseBoolean	(cursor.getString	(isUploaded)));
			list.add(taskName);
		}
		db.close();
		return list;
	}

	public ArrayList<CategoryVO> loadTable(CategoryVO foo){
		SQLiteDatabase db = this.getWritableDatabase();
		String sqlQuery = "SELECT " + TABLE_CATEGORY + ".* " + " FROM " + TABLE_CATEGORY;
		Cursor cursor = db.rawQuery(sqlQuery, null);
		int id				= cursor.getColumnIndex(CATEGORY_ID);
		int name			= cursor.getColumnIndex(CATEGORY_NAME);
		int parentId		= cursor.getColumnIndex(CATEGORY_PARENT_ID);
		int isPrivate		= cursor.getColumnIndex(CATEGORY_IS_PRIVATE);
		int isDeleted		= cursor.getColumnIndex(CATEGORY_DELETE);
		int isUploaded		= cursor.getColumnIndex(CATEGORY_UPLOADED);
		ArrayList<CategoryVO> list = new ArrayList<CategoryVO>();
		CategoryVO category;
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
			category = new CategoryVO();
			category.set_id									(cursor.getLong		(id));
			category.set_name								(cursor.getString	(name));
			category.set_parentId							(cursor.getLong		(parentId));
			category.set_isPrivate	(Boolean.parseBoolean	(cursor.getString	(isPrivate)));
			category.set_isDeleted	(Boolean.parseBoolean	(cursor.getString	(isDeleted)));
			category.set_isUploaded	(Boolean.parseBoolean	(cursor.getString	(isUploaded)));
			list.add(category);
		}
		db.close();
		return list;
	}

	public ArrayList<LocationCategoryVO> loadTable(LocationCategoryVO foo){
		SQLiteDatabase db = this.getWritableDatabase();
		String sqlQuery = "SELECT " + TABLE_LOCATION_CATEGORY + ".* " + " FROM " + TABLE_LOCATION_CATEGORY;
		Cursor cursor = db.rawQuery(sqlQuery, null);
		int id				= cursor.getColumnIndex(LOCATION_CATEGORY_ID);
		int categoryId		= cursor.getColumnIndex(CATEGORY_ID);
		int locationNameId	= cursor.getColumnIndex(LOCATION_NAME_ID);
		int isUploaded		= cursor.getColumnIndex(LOCATION_CATEGORY_UPLOADED);
		ArrayList<LocationCategoryVO> list = new ArrayList<LocationCategoryVO>();
		LocationCategoryVO location_category;
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
			location_category = new LocationCategoryVO();
			location_category.set_id								(cursor.getLong		(id));
			location_category.set_categoryId						(cursor.getLong		(categoryId));
			location_category.set_locationNameId					(cursor.getLong		(locationNameId));
//			location_category.set_isUploaded(Boolean.parseBoolean	(cursor.getString	(isUploaded)));
			list.add(location_category);
		}
		db.close();
		return list;
	}
	
	
	public Long writeRecord(LocationNameVO locationName){
		ContentValues values = new ContentValues();
		
		String tableName = TABLE_LOCATION_NAME;
		String criteriaColumn = LOCATION_NAME_ID;
		values.put(LOCATION_NAME, locationName.get_name().toString());
		values.put(LOCATION_NAME_IGNORE, Boolean.toString(locationName.is_isBoycotted()));
		values.put(LOCATION_NAME_IS_PRIVATE, Boolean.toString(locationName.is_isPrivate()));
		values.put(LOCATION_NAME_DELETE, Boolean.toString(locationName.is_isDeleted()));
		values.put(LOCATION_NAME_UPLOADED, Boolean.FALSE);
		return(insertOrUpdate(tableName, criteriaColumn, values, locationName.get_id()));
	}
	
	public Long writeRecord(LocationCategoryVO locationCategory){
		ContentValues values = new ContentValues();
		
		String tableName = TABLE_LOCATION_CATEGORY;
		String criteriaColumn = LOCATION_CATEGORY_ID;
		values.put(CATEGORY_ID, locationCategory.get_categoryId());
		values.put(LOCATION_NAME_ID, locationCategory.get_locationNameId());
		values.put(LOCATION_CATEGORY_UPLOADED, Boolean.FALSE);
		return(insertOrUpdate(tableName, criteriaColumn, values, locationCategory.get_id()));
	}

	public Long writeRecord(TaskNameVO taskName){
		ContentValues values = new ContentValues();
		
		String tableName = TABLE_TASK_NAME;
		String criteriaColumn = TASK_NAME_ID;
		values.put(TASK_NAME, taskName.get_name().toString());
		values.put(CATEGORY_ID, taskName.get_id());
		values.put(TASK_NAME_DELETED, Boolean.toString(taskName.is_isDeleted()));
		values.put(TASK_NAME_IS_PRIVATE, Boolean.toString(taskName.is_isPrivate()));
		values.put(TASK_NAME_UPLOADED, Boolean.FALSE);
		return(insertOrUpdate(tableName, criteriaColumn, values, taskName.get_id()));
	}

	public Long writeRecord(TaskVO task){
		ContentValues values = new ContentValues();
		
		String tableName = TABLE_TASK;
		String criteriaColumn = TASK_ID;
		values.clear();
		values.put(TASK_NAME_ID, task.get_taskName().get_id());
		values.put(TASK_PRIORITY, task.get_priority());
		values.put(TASK_START_DATE, task.get_startDateTime().toString());
		values.put(TASK_END_DATE, task.get_endDateTime().toString());
		values.put(LOCATION_NAME_ID, task.get_location().get_locationName().get_id());
		values.put(LOCATION_ID, task.get_location().get_id());
		values.put(CATEGORY_ID, task.get_category().get_id());
		values.put(TASK_PARENT_ID, task.get_parentId());
		values.put(TASK_COMPLETE_DATE, task.get_completionDate().toString());
		values.put(TASK_COMPLETE, Boolean.toString(task.is_isComplete()));
		return(insertOrUpdate(tableName, criteriaColumn, values, task.get_id()));
	}
	
	public Long writeRecord(CategoryVO category){
		ContentValues values = new ContentValues();
		
		String tableName = TABLE_CATEGORY;
		String criteriaColumn = CATEGORY_ID;
		values.put(CATEGORY_NAME, category.get_name().toString());
		values.put(CATEGORY_PARENT_ID, category.get_parentId());
		values.put(CATEGORY_IS_PRIVATE, Boolean.toString(category.is_isPrivate()));
		values.put(CATEGORY_DELETE, Boolean.toString(category.is_isDeleted()));
		values.put(CATEGORY_UPLOADED, Boolean.toString(category.is_isUploaded()));
		return(insertOrUpdate(tableName, criteriaColumn, values, category.get_id()));
	}
	
	public Long writeRecord(LocationVO location){
		ContentValues values = new ContentValues();
		
		String tableName = TABLE_LOCATION;
		String criteriaColumn = LOCATION_ID;
		values.put(LOCATION_NAME_ID, location.get_locationName().get_id());
		values.put(LOCATION_LAT, location.get_coordinate().get_latitude());
		values.put(LOCATION_LONG, location.get_coordinate().get_longitude());
		values.put(LOCATION_ADDRESS_LINE_0, location.get_address().getAddressLine(0));
		values.put(LOCATION_ADDRESS_LINE_1, location.get_address().getAddressLine(1));
		values.put(LOCATION_ADDRESS_LINE_2, location.get_address().getAddressLine(2));
		values.put(LOCATION_ADDRESS_LINE_3, location.get_address().getAddressLine(3));
		values.put(LOCATION_PHONE, location.get_address().getPhone());
		values.put(LOCATION_IGNORE, Boolean.toString(location.is_ignore()));
		values.put(LOCATION_DELETE, Boolean.toString(location.is_isDeleted()));
		values.put(LOCATION_UPLOADED, Boolean.toString(location.is_isUploaded()));
		return(insertOrUpdate(tableName, criteriaColumn, values, location.get_id()));
	}
	
	private Long insertOrUpdate(String tableName, String criteriaColumn, ContentValues values, Long criteriaId){
		SQLiteDatabase db = this.getWritableDatabase();
		long i;
		if (criteriaId==-1){
			i = db.insertOrThrow(tableName, null, values);
		} else {
			i=criteriaId;
			db.update(tableName, values, criteriaColumn + "=" + criteriaId, null);
			Log.d(TAG, "record number " + String.valueOf(i));
		}
		db.close();
		return i;
	}//closes insertOrUpdate
	
} //closes class