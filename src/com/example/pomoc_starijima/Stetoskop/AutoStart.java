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
import android.view.Gravity;
import android.widget.Toast;



public class AutoStart extends BroadcastReceiver {
	
	static SQLiteTerapija sqlTerapija;
	static SQLiteDatabase db;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
        	Toast t1 = Toast.makeText(context,"BOOT_COMPLETED",Toast.LENGTH_LONG);
    		t1.setDuration(60000);
    		t1.setGravity(Gravity.CENTER, 0, 0);
    		t1.show();
    		 sqlTerapija = new SQLiteTerapija(context);
    		    db = sqlTerapija.getWritableDatabase();
    		    // static?
    		    
    		   // Cursor cursor =  db.query(sqlTerapija.getTableTerapija(), sqlTerapija.getColumns(), null, null, null, null, null);
    		    Cursor cursor = sqlTerapija.queueAll();
            while (cursor.moveToNext()) {
                    Calendar aCal = Calendar.getInstance();
                    aCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(cursor.getString(2)));
            		aCal.set(Calendar.MINUTE, Integer.parseInt(cursor.getString(3)));
            		aCal.set(Calendar.SECOND, 0);
            		aCal.set(Calendar.MILLISECOND, 0); 
            	//	String title = cursor.getString(cursor.getColumnIndex(cursor.getString(1)));
//                    int alarmID = cursor.getInt(cursor.getColumnIndex(SQLiteAdapter.getAlarmID()));
//                    AlarmUtils.setAlarm(title, notes, alarmID, context, aCal);
            	SetAlarm(context,aCal);
//            		AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
//            		Intent i = new Intent(context, AlarmReceiverTerapija.class);
//            		PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, PendingIntent.FLAG_UPDATE_CURRENT | Intent.FILL_IN_DATA);
//            		am.setRepeating(AlarmManager.RTC_WAKEUP, aCal.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pi); // Millisec * Second * Minute
            		    
            		
            		
            		
            }		
            }        
    }
    
    public void SetAlarm(Context context, Calendar cal){
        AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, AlarmReceiverTerapija.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0,
    i, PendingIntent.FLAG_UPDATE_CURRENT | Intent.FILL_IN_DATA);
        am.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY, sender); // Millisec * Second * Minute
	}

}
