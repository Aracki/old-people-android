package baze;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.example.pomoc_starijima.Sat._Rodjendan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

public class SQLiteRodjendani extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "Rodjendani5";
	private static final String TABLE_RODJENDANI = "Rodjendani";

	// private static final String DATABASE_PATH =
	// "/data/data/com.example.pomoc_starijima/databases/";

	private static final String[] COLUMNS = { "id", "ime", "prezime", "datum" };

	public SQLiteRodjendani(Context context) {
		super(context, Environment.getExternalStorageDirectory()
				+ File.separator + "baze" + File.separator + DATABASE_NAME,
				null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String create_primeri = "CREATE TABLE " + TABLE_RODJENDANI + "(" + "id"
				+ " INTEGER PRIMARY KEY," + "ime TEXT," + "prezime TEXT,"
				+ "datum TEXT)";
		db.execSQL(create_primeri);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS" + TABLE_RODJENDANI);
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

	public void dodajRodjendan(String i, String p, String datum) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("ime", i);
		values.put("prezime", p);
		values.put("datum", datum);

		db.insert(TABLE_RODJENDANI, null, values);
		db.close();
	}

	public void obrisiRodjendan(int id) {
		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(TABLE_RODJENDANI, "id=?", new String[] { String.valueOf(id) });

		db.close();
	}

//	public String vratiRodjendan(int id) {
//
//		SQLiteDatabase db = this.getReadableDatabase();
//
//		Cursor y = db.query(TABLE_RODJENDANI, COLUMNS, "id=?",
//				new String[] { String.valueOf(id) }, null, null, null, null);
//
//		String x = "";
//
//		if (y.moveToNext()) {
//			x =y.getString(0)+":::"+ y.getString(1) + ":::" + y.getString(2) + ":::"
//					+ y.getString(3);
//		}
//
//		return x;
//	}

//	public int vratiBrojRodjendana() {
//
//		String brojQuery = "SELECT * FROM " + TABLE_RODJENDANI;
//
//		SQLiteDatabase db = this.getReadableDatabase();
//		Cursor cursor = db.rawQuery(brojQuery, null);
//
//		int broj = cursor.getCount();
//		cursor.close();
//		db.close();
//		return broj;
//
//	}
	
	public List<_Rodjendan> vratiSveRodjendane() {
		
		List<_Rodjendan> rodjendani;
		rodjendani = new ArrayList<_Rodjendan>();
		String brojQuery = "SELECT * FROM " + TABLE_RODJENDANI;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor y = db.rawQuery(brojQuery, null);
		Log.d("Broj",Integer.toString(y.getCount()));
		y.moveToFirst();
		for(int i=0; i<y.getCount(); i++)
		{
			
			String s = y.getString(3);
			String[] datum = s.split("/");
			_Rodjendan r = new _Rodjendan(Integer.parseInt(y.getString(0)), y.getString(1),y.getString(2)
						,Integer.parseInt(datum[0]),Integer.parseInt(datum[1]),Integer.parseInt(datum[2]));
			rodjendani.add(r);
			Log.d("IME:",r.getIme());
			Log.d("i", Integer.toString(i));
			y.moveToNext();
			
		}
		return rodjendani;
	}

}
