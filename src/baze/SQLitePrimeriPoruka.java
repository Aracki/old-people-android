package baze;

import java.io.File;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

public class SQLitePrimeriPoruka extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "PrimeriPorukaF";
	private static final String TABLE_PRIMERI = "primeri";

	// private static final String DATABASE_PATH =
	// "/data/data/com.example.pomoc_starijima/databases/";

	private static final String[] COLUMNS = { "id", "primerID" };

	public SQLitePrimeriPoruka(Context context) {
		super(context, Environment.getExternalStorageDirectory()
				+ File.separator + "baze" + File.separator + DATABASE_NAME,
				null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String create_primeri = "CREATE TABLE " + TABLE_PRIMERI + "(" + "id"
				+ " INTEGER PRIMARY KEY," + "primerID" + " TEXT" + ")";
		db.execSQL(create_primeri);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS" + TABLE_PRIMERI);
		onCreate(db);
	}

	public int updatePrimer(String primer, int id) {

		// 1. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();

		// 2. create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();
		values.put("primerID", primer);

		// 3. updating row
		int i = db.update(TABLE_PRIMERI, // table
				values, // column/value
				"id=?", // selections
				new String[] { String.valueOf(id) }); // selection
														// args
		Log.d(primer, "nja nja nja");
		// 4. close
		db.close();

		return i;
	}

	public void dodajPrimer(String primerPoruke) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("primerID", primerPoruke);

		db.insert(TABLE_PRIMERI, null, values);
		db.close();
	}

	public String vratiPrimer(int id) {

		SQLiteDatabase db = this.getReadableDatabase();

		Cursor y = db.query(TABLE_PRIMERI, COLUMNS, "id=?",
				new String[] { String.valueOf(id) }, null, null, null, null);

		String x = "";

		if (y.moveToNext()) {
			x = y.getString(1);
		}

		Log.d("getPrimer(" + id + ")", x);
		return x;
	}

	public int vratiBrojPrimera() {

		String brojQuery = "SELECT * FROM " + TABLE_PRIMERI;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(brojQuery, null);

		int broj = cursor.getCount();
		cursor.close();
		db.close();
		return broj;
	}

}
