package baze;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.example.pomoc_starijima.Sat._Pomen;
import com.example.pomoc_starijima.Sat._Pregled;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class SQLitePomeni extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "POMENI_DB";
	private static final String TABLE_POMENI = "POMENI";

	// private static final String DATABASE_PATH =
	// "/data/data/com.example.pomoc_starijima/databases/";

	private static final String[] COLUMNS = { "id", "ime", "mesto", "datum" };

	public SQLitePomeni(Context context) {
		super(context, Environment.getExternalStorageDirectory()
				+ File.separator + "baze" + File.separator + DATABASE_NAME,
				null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String create_primeri = "CREATE TABLE " + TABLE_POMENI + "(" + "id"
				+ " INTEGER PRIMARY KEY," + "ime TEXT," + "mesto TEXT,"
				+ "datum TEXT)";
		db.execSQL(create_primeri);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS" + TABLE_POMENI);
		onCreate(db);
	}
	


	public void dodajPomen(String ime, String mesto, String datum) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("ime", ime);
		values.put("mesto", mesto);
		values.put("datum", datum);
		db.insert(TABLE_POMENI, null, values);
		db.close();
	}

	public void obrisiPomen(int id) {
		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(TABLE_POMENI, "id=?", new String[] { String.valueOf(id) });

		db.close();
	}
	
	public List<_Pomen> vratiSvePomene() {

		List<_Pomen> pomeni;
		pomeni = new ArrayList<_Pomen>();
		String brojQuery = "SELECT * FROM " + TABLE_POMENI;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor y = db.rawQuery(brojQuery, null);
		
		y.moveToFirst();
		for(int i=0; i<y.getCount(); i++)
		{
			
			String s = y.getString(3);
			String[] datum = s.split("/");		
			_Pomen pomen = new _Pomen(Integer.parseInt(y.getString(0)), y.getString(1), y.getString(2),Integer.parseInt(datum[0]),
					Integer.parseInt(datum[1]), Integer.parseInt(datum[2]));
			pomeni.add(pomen);
			y.moveToNext();
		}
		
		return pomeni;

	}

	public String vratiPomen() {

		SQLiteDatabase db = this.getReadableDatabase();

		Cursor y = db.query(TABLE_POMENI, COLUMNS, null,
				 null, null, null, null, null);
		
		y.moveToLast();

		String x = y.getString(0);

		return x;
	}

	public Cursor queueAll() {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.query(getTablePomeni(), getColumns(), null, null, null,
				null, null, null);

	}
	private String getTablePomeni() {
		return TABLE_POMENI;
	}
	public static String[] getColumns() {
		return COLUMNS;
	}

}

