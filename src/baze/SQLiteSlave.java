package baze;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.example.pomoc_starijima.Sat._Rodjendan;
import com.example.pomoc_starijima.Sat._Slava;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

public class SQLiteSlave extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "SlaveX";
	private static final String TABLE_SLAVE = "Slave";

	// private static final String DATABASE_PATH =
	// "/data/data/com.example.pomoc_starijima/databases/";

	private static final String[] COLUMNS = { "id", "ime", "datum", "ko_slavi" };

	public SQLiteSlave(Context context) {
		super(context, Environment.getExternalStorageDirectory()
				+ File.separator + "baze" + File.separator + DATABASE_NAME,
				null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String c = "CREATE TABLE " + TABLE_SLAVE + "(" + "id"
				+ " INTEGER PRIMARY KEY," + "ime TEXT," + "datum TEXT,"
				+ "ko_slavi TEXT)";
		db.execSQL(c);
	}
	
	public String vratiSlavu() {

		SQLiteDatabase db = this.getReadableDatabase();

		Cursor y = db.query(TABLE_SLAVE, COLUMNS, null,
				 null, null, null, null, null);
		
		y.moveToLast();

		String x = y.getString(0);

		return x;
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS" + TABLE_SLAVE);
		onCreate(db);
	}



	public void dodajSlavu(String i, String d, String ko) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("ime", i);
		values.put("datum", d);
		values.put("ko_slavi", ko);

		db.insert(TABLE_SLAVE, null, values);
		db.close();
	}

	public void obrisiSlavu(int id) {
		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(TABLE_SLAVE, "id=?", new String[] { String.valueOf(id) });

		db.close();
	}

	
	public List<_Slava> vratiSveSlave() {

		List<_Slava> slave;
		slave = new ArrayList<_Slava>();
		String brojQuery = "SELECT * FROM " + TABLE_SLAVE;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor y = db.rawQuery(brojQuery, null);
		
		y.moveToFirst();
		for(int i=0; i<y.getCount(); i++)
		{
			
			String s = y.getString(2);
			String[] datum = s.split("/");
			_Slava slava = new _Slava(Integer.parseInt(y.getString(0)), y.getString(1), Integer.parseInt(datum[0]),
					Integer.parseInt(datum[1]), y.getString(3));
			slave.add(slava);
			y.moveToNext();
		}
		
		return slave;

	}

	public Cursor queueAll() {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.query(getTableSlave(), getColumns(), null, null, null,
				null, null, null);

	}
	private String getTableSlave() {
		return TABLE_SLAVE;
	}
	public static String[] getColumns() {
		return COLUMNS;
	}

}
