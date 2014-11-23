package baze;

import java.io.File;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class SQLiteTVprogrami extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "TVprogramiX";
	private static final String TABLE_TV = "TVprogram";

	// private static final String DATABASE_PATH =
	// "/data/data/com.example.pomoc_starijima/databases/";

	private static final String[] COLUMNS = { "id", "naziv", "dani", "vreme" };

	public SQLiteTVprogrami(Context context) {
		super(context, Environment.getExternalStorageDirectory()
				+ File.separator + "baze" + File.separator + DATABASE_NAME,
				null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String create = "CREATE TABLE " + TABLE_TV
				+ "(id INTEGER PRIMARY KEY, naziv TEXT, dani TEXT, vreme TEXT)";
		db.execSQL(create);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS" + TABLE_TV);
		onCreate(db);
	}

	public void dodajTVprogram(String naziv, String dani, String vreme) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("naziv", naziv);
		values.put("dani", dani);
		values.put("vreme", vreme);

		db.insert(TABLE_TV, null, values);
		db.close();
	}

	public String vratiTVprogram(int id) {

		SQLiteDatabase db = this.getReadableDatabase();

		Cursor y = db.query(TABLE_TV, COLUMNS, "id=?",
				new String[] { String.valueOf(id) }, null, null, null, null);

		String x = "";

		if (y.moveToNext()) {
			x = y.getString(1) + " " + y.getString(2) + "-" + y.getString(3);
		}

		return x;
	}

	public int vratiBrojPrograma() {

		String brojQuery = "SELECT * FROM " + TABLE_TV;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(brojQuery, null);

		int broj = cursor.getCount();
		cursor.close();
		db.close();
		return broj;

	}

	public void obrisiProgram(int id) {
		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(TABLE_TV, "id=?", new String[] { String.valueOf(id) });

		db.close();
	}
}
