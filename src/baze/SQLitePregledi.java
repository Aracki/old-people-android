package baze;

import java.io.File;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class SQLitePregledi extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "PreglediBolnice";
	private static final String TABLE_PREGLEDI = "Pregledi";

	// private static final String DATABASE_PATH =
	// "/data/data/com.example.pomoc_starijima/databases/";

	private static final String[] COLUMNS = { "id", "bolnica", "datum", "vreme" };

	public SQLitePregledi(Context context) {
		super(context, Environment.getExternalStorageDirectory()
				+ File.separator + "baze" + File.separator + DATABASE_NAME,
				null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String create_primeri = "CREATE TABLE " + TABLE_PREGLEDI + "(" + "id"
				+ " INTEGER PRIMARY KEY," + "bolnica TEXT," + "datum TEXT,"
				+ "vreme TEXT)";
		db.execSQL(create_primeri);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS" + TABLE_PREGLEDI);
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

	public void dodajPregled(String bolnica, String datum, String vreme) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("bolnica", bolnica);
		values.put("datum", datum);
		values.put("vreme", vreme);

		db.insert(TABLE_PREGLEDI, null, values);
		db.close();
	}

	public void obrisiPregled(int id) {
		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(TABLE_PREGLEDI, "id=?", new String[] { String.valueOf(id) });

		db.close();
	}

	public String vratiPregled(int id) {

		SQLiteDatabase db = this.getReadableDatabase();

		Cursor y = db.query(TABLE_PREGLEDI, COLUMNS, "id=?",
				new String[] { String.valueOf(id) }, null, null, null, null);

		String x = "";

		if (y.moveToNext()) {
			x = y.getString(1) + ":::" + y.getString(2) + ":::"
					+ y.getString(3);
		}

		return x;
	}

	public int vratiBrojPregleda() {

		String brojQuery = "SELECT * FROM " + TABLE_PREGLEDI;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(brojQuery, null);

		int broj = cursor.getCount();
		cursor.close();
		db.close();
		return broj;

	}

}
