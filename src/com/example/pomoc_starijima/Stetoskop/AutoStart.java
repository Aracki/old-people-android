package com.example.pomoc_starijima.Stetoskop;

import java.util.Calendar;

import baze.SQLiteTerapija;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

public class AutoStart extends BroadcastReceiver {

	static SQLiteTerapija sqlTerapija;
	static SQLiteDatabase db;

	@Override
	public void onReceive(Context context, Intent intent) {

		if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
			Toast t1 = Toast.makeText(context, "BOOT_COMPLETED",
					Toast.LENGTH_LONG);
			t1.setDuration(60000);
			t1.setGravity(Gravity.CENTER, 0, 0);
			t1.show();
			napuniTerapije(context);
			// SetAlarm(context, aCal);

		}
	}

	public void napuniTerapije(Context ctx) {

		sqlTerapija = new SQLiteTerapija(ctx);
		Cursor cursor = sqlTerapija.queueAll();
		
		while (cursor.moveToNext()) {
			if(cursor.getString(5).equals("F") || cursor.getString(5).equals("") ){
				continue;
			}
				String terapija = "LEKOVI: " +cursor.getString(1)+ "\n"
						+"KOLICINA: "+ cursor.getString(4);
			
			Calendar calNow = Calendar.getInstance();
			Calendar aCal = (Calendar) calNow.clone();
			
			aCal.set(Calendar.HOUR_OF_DAY,
					Integer.parseInt(cursor.getString(2)));
			Log.d("SAT", cursor.getString(2));
			aCal.set(Calendar.MINUTE, Integer.parseInt(cursor.getString(3)));
			Log.d("MINUT", cursor.getString(3));
			aCal.set(Calendar.SECOND, 0);
			aCal.set(Calendar.MILLISECOND, 0);
			Intent i = new Intent(ctx, AlarmReceiverTerapija.class);
			i.putExtra("Terapija", terapija);
			// OVA LINIJA ISPOD JE FALILA, NIJE PROSLEDIO ID ZA ALARM_RECEIVER_TERAPIJA
			int id = Integer.parseInt(cursor.getString(0));
			Log.d("ID", Integer.toString(id));
			i.putExtra("ID", cursor.getString(0));
			PendingIntent sender = PendingIntent.getBroadcast(ctx, id, i,
					PendingIntent.FLAG_UPDATE_CURRENT);
			AlarmManager am = (AlarmManager) ctx
					.getSystemService(Context.ALARM_SERVICE);
			am.setRepeating(AlarmManager.RTC_WAKEUP, aCal.getTimeInMillis(),
					AlarmManager.INTERVAL_DAY, sender);
			
			
		}

	}
}
