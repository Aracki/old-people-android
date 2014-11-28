package baze;

import java.io.File;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

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

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS" + TABLE_SLAVE);
		onCreate(db);
	}

	// public int updatePrimer(String primer, int id) {
	//
	// // 1. get reference to writable DB
	// SQLiteDatabase db = this.getWritableDatabase();
	//
	// // 2. create ContentValues to add key "column"/value
	// ContentValues values = new ContentValues();
	// values.put("primerID", primer);
	//
	// // 3. updating row
	// int i = db.update(TABLE_PRIMERI, // table
	// values, // column/value
	// "id=?", // selections
	// new String[] { String.valueOf(id) }); // selection
	// // args
	// Log.d(primer, "nja nja nja");
	// // 4. close
	// db.close();
	//
	// return i;
	// }

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

	public String vratiSlavu(int id) {

		SQLiteDatabase db = this.getReadableDatabase();

		Cursor y = db.query(TABLE_SLAVE, COLUMNS, "id=?",
				new String[] { String.valueOf(id) }, null, null, null, null);

		String x = "";

		if (y.moveToNext()) {
			x = y.getString(0)+":::" +y.getString(1) + ":::" + y.getString(2) + ":::"
					+ y.getString(3);
		}

		return x;
	}

	public int vratiBrojSlava() {

		String brojQuery = "SELECT * FROM " + TABLE_SLAVE;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(brojQuery, null);

		int broj = cursor.getCount();
		cursor.close();
		db.close();
		return broj;

	}

}
