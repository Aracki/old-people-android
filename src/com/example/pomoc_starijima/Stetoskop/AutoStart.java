package com.example.pomoc_starijima.Stetoskop;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.example.pomoc_starijima.Sat.Receiver;

import baze.SQLitePregledi;
import baze.SQLiteRodjendani;
import baze.SQLiteSlave;
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
	static SQLiteRodjendani sqlRodjendani;
	static SQLiteSlave sqlSlave;
	static SQLitePregledi sqlPregledi;
	static SQLiteDatabase db;
	int brojacRodjendana = 10000;
	int brojacSlava = 20000;
	int brojacPregleda = 30000;

	@Override
	public void onReceive(Context context, Intent intent) {

		if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
			Toast t1 = Toast.makeText(context, "BOOT_COMPLETED",
					Toast.LENGTH_LONG);
			t1.setDuration(60000);
			t1.setGravity(Gravity.CENTER, 0, 0);
			t1.show();
			napuniTerapije(context);
			napuniRodjendane(context);
			napuniSlave(context);
			napuniPreglede(context);
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
			if (aCal.before(calNow)){
				aCal.add(Calendar.DATE, 1);
				am.setRepeating(AlarmManager.RTC_WAKEUP, aCal.getTimeInMillis(),
						AlarmManager.INTERVAL_FIFTEEN_MINUTES, sender);	
						Log.d("If", "u petlji");
			}	
			am.setRepeating(AlarmManager.RTC_WAKEUP, aCal.getTimeInMillis(),
					AlarmManager.INTERVAL_FIFTEEN_MINUTES, sender);
			
			
		}
	}
		
		public void napuniRodjendane(Context ctx) {
			sqlRodjendani = new SQLiteRodjendani(ctx);
			Cursor cursor = sqlRodjendani.queueAll();
			
			while (cursor.moveToNext()) {
				
					
				String poruka = cursor.getString(1)+ " "+cursor.getString(2);
				String datum = cursor.getString(3);
				String id = cursor.getString(0);
				String[] datumNiz = datum.split("/");
				
				GregorianCalendar calNow = (GregorianCalendar) GregorianCalendar.getInstance();
				GregorianCalendar calSet = (GregorianCalendar) GregorianCalendar.getInstance();
					

				
				int godina = calNow.get(Calendar.YEAR);
				int dan = Integer.parseInt(datumNiz[0]);
				int mesec = Integer.parseInt(datumNiz[1]) - 1;
				int sat = 12;
				int minut = 0;
				
				calSet.set(godina, mesec, dan, sat, minut);
				
				if (calSet.before(calNow)){
					calSet.set(godina +1 , mesec, dan, sat, minut);
					Log.d("if:", "Usao u if");				
				}				
				Intent i = new Intent(ctx, Receiver.class);
				i.putExtra("Poruka", poruka);
				i.putExtra("ID", id);
				i.putExtra("IDAlarm", "2_Rodjendan");
				i.putExtra("TipReceiver", "ReceiverR");
				i.putExtra("Datum", datum);
				int rqs = brojacRodjendana + Integer.parseInt(cursor.getString(0));
				PendingIntent sender = PendingIntent.getBroadcast(ctx, rqs, i,
						PendingIntent.FLAG_UPDATE_CURRENT);
				AlarmManager am = (AlarmManager) ctx
						.getSystemService(Context.ALARM_SERVICE);
				am.set(AlarmManager.RTC_WAKEUP,calSet.getTimeInMillis(),sender);
				Log.d("RODJENDAN:", calSet.getTime().toString()+":::"+cursor.getString(0));
			
			}
		}
			
			public void napuniSlave(Context ctx) {
				sqlSlave = new SQLiteSlave(ctx);
				Cursor cursor = sqlSlave.queueAll();
				
				while (cursor.moveToNext()) {
					
					
					String poruka = cursor.getString(1)+" \n"+ "KO SVE SLAVI? \n"+cursor.getString(3);
					String datum = cursor.getString(2);
					String id = cursor.getString(0);
					String[] datumNiz = datum.split("/");
					
					GregorianCalendar calNow = (GregorianCalendar) GregorianCalendar.getInstance();
					GregorianCalendar calSet = (GregorianCalendar) GregorianCalendar.getInstance();
					
						

					
					int godina = calNow.get(Calendar.YEAR);
					int dan = Integer.parseInt(datumNiz[0]);
					int mesec = Integer.parseInt(datumNiz[1]) - 1;
//					int sat = 11;
//					int minut = 00;
					int sat = 11;
					int minut = 0;

					
					calSet.set(godina, mesec, dan, sat, minut);
					
					if (calSet.before(calNow)){
						calSet.set(godina +1 , mesec, dan, sat, minut);
						Log.d("if:", "Usao u if");				
					}				
					Intent i = new Intent(ctx, Receiver.class);
					i.putExtra("Poruka", poruka);
					i.putExtra("ID", id);
					i.putExtra("IDAlarm", "3_Slava");
					i.putExtra("TipReceiver", "ReceiverS");
					i.putExtra("Datum", datum);
					int rqs = brojacSlava + Integer.parseInt(cursor.getString(0));
					PendingIntent sender = PendingIntent.getBroadcast(ctx, rqs, i,
							PendingIntent.FLAG_UPDATE_CURRENT);
					AlarmManager am = (AlarmManager) ctx
							.getSystemService(Context.ALARM_SERVICE);
					am.set(AlarmManager.RTC_WAKEUP,calSet.getTimeInMillis(),sender);
					Log.d("SLAVA:", calSet.getTime().toString()+" :::"+cursor.getString(0));
				
			}

	}
			public void napuniPreglede(Context ctx) {
				sqlPregledi = new SQLitePregledi(ctx);
				Cursor cursor = sqlPregledi.queueAll();
				
				while (cursor.moveToNext()) {
					
					
					String poruka = cursor.getString(1);
					String datum = cursor.getString(2);
					String vreme = cursor.getString(3);
					String id = cursor.getString(0);
					String[] datumNiz = datum.split("/");
					
					GregorianCalendar calNow = (GregorianCalendar) GregorianCalendar.getInstance();
					GregorianCalendar calSet = (GregorianCalendar) GregorianCalendar.getInstance();
					

					int godina = Integer.parseInt(datumNiz[2]);
					int dan = Integer.parseInt(datumNiz[0]);
					int mesec = Integer.parseInt(datumNiz[1]) - 1;
//					int sat = 13;
//					int minut = 00;
					int sat = 10;
					int minut = 0;

					calSet.set(godina, mesec, dan, sat, minut);
					
					if (calSet.before(calNow)){
						calSet.set(godina +1 , mesec, dan, sat, minut);
						Log.d("if:", "Usao u if");				
					}				
					Intent i = new Intent(ctx, Receiver.class);
					i.putExtra("Poruka", poruka);
					i.putExtra("ID", id);
					i.putExtra("IDAlarm", "4_Lekar");
					i.putExtra("TipReceiver", "ReceiverL");
					i.putExtra("Datum", datum);
					i.putExtra("Vreme", vreme);
					int rqs = brojacPregleda + Integer.parseInt(cursor.getString(0));
					PendingIntent sender = PendingIntent.getBroadcast(ctx, rqs, i,
							PendingIntent.FLAG_UPDATE_CURRENT);
					AlarmManager am = (AlarmManager) ctx
							.getSystemService(Context.ALARM_SERVICE);
					am.set(AlarmManager.RTC_WAKEUP,calSet.getTimeInMillis() - AlarmManager.INTERVAL_DAY,sender);
					Log.d("LEKAR:", calSet.getTime().toString()+" :::"+cursor.getString(0));
				
			}

			}
	
}
