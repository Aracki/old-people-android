package com.example.pomoc_starijima.Sat;



import com.example.pomoc_starijima.NotificationActivity;

import android.R;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.OpenableColumns;


public class Receiver extends BroadcastReceiver {

	@SuppressLint("NewApi") @Override
	public void onReceive(Context ctxt, Intent intent) {
		PowerManager pm = (PowerManager) ctxt.getSystemService(Context.POWER_SERVICE);
	    PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
	    // Get a wake lock
	    wl.acquire();

		Bundle extras = intent.getExtras();
		String tipReceiver = extras.getString("TipReceiver");
		int notifyId = Integer.parseInt(extras.getString("ID"));
		// pattern za pravljenje vibraije
		 long[] quickBurstsExtended = new long[]{0, 100, 70, 100, 70, 100, 70, 100, 70, 100, 70, 100, 70, 100, 70, 100, 70,
		            100, 70, 100, 70, 100, 70, 100, 70, 100, 70, 100, 70, 100, 70, 100, 70};
		 
		 NotificationManager nm = (NotificationManager) ctxt
			        .getSystemService(Context.NOTIFICATION_SERVICE);
		 Notification.Builder builder = new Notification.Builder(ctxt);
		 
		 //mozda je ovde greska RodjednaActivity????????
//		 Intent resultIntent = new Intent(ctxt, NotificationActivity.class);

		 
		 if (tipReceiver.equals("ReceiverR")){
		 Intent resultIntent = new Intent(ctxt, NotificationActivity.class);
		 
		 resultIntent.putExtra("Poruka", extras.getString("Poruka"));
		 resultIntent.putExtra("Naslov", "RODJENDAN");
		 resultIntent.putExtra("Datum", extras.getString("Datum"));
		 resultIntent.putExtra("IDAlarm", "2_Rodjendan");
		 resultIntent.putExtra("ID", extras.getString("ID"));
		 PendingIntent resultPendingIntent =
		     PendingIntent.getActivity(ctxt, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT); 
		 builder
         .setContentTitle("RODJENDAN")
         .setContentText(extras.getString("Poruka"))
         .setSmallIcon(R.drawable.ic_popup_reminder)
         .setContentIntent(resultPendingIntent)
		 .setVibrate(quickBurstsExtended)
		 .setLights(Color.CYAN, 500, 100)
		 .setAutoCancel(true)
		 .setWhen(System.currentTimeMillis());
		 

		 }
		 
		 if (tipReceiver.equals("ReceiverS")){
			 Intent resultIntent = new Intent(ctxt, NotificationActivity.class);
			 
			 resultIntent.putExtra("Poruka", extras.getString("Poruka"));
			 resultIntent.putExtra("Naslov", "SLAVA");
			 resultIntent.putExtra("Datum", extras.getString("Datum"));
			 resultIntent.putExtra("IDAlarm", "3_Slava");
			 resultIntent.putExtra("ID", extras.getString("ID"));
			 String[] nizPoruka = extras.getString("Poruka").split(" ");
			 PendingIntent resultPendingIntent =
			     PendingIntent.getActivity(ctxt, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT); 
			 builder
	         .setContentTitle("SLAVA")
	         .setContentText(nizPoruka[0])
	         .setSmallIcon(R.drawable.ic_popup_reminder)
	         .setContentIntent(resultPendingIntent)
			 .setVibrate(quickBurstsExtended)
			 .setLights(Color.CYAN, 500, 100)
			 .setAutoCancel(true)
			 .setWhen(System.currentTimeMillis());
			 
			
			 }
		 
		 if (tipReceiver.equals("ReceiverL")){
			 Intent resultIntent = new Intent(ctxt, NotificationActivity.class);
			 
			 resultIntent.putExtra("Poruka", extras.getString("Poruka"));
			 resultIntent.putExtra("Naslov", "LEKAR");
			 resultIntent.putExtra("Datum", extras.getString("Datum"));
			 resultIntent.putExtra("Vreme", extras.getString("Vreme"));
			 resultIntent.putExtra("IDAlarm", "4_Lekar");
			 resultIntent.putExtra("ID", extras.getString("ID"));
			 
			 String[] nizPoruka = extras.getString("Poruka").split(" ");
			 PendingIntent resultPendingIntent =
			     PendingIntent.getActivity(ctxt, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT); 
			 builder
	         .setContentTitle("LEKAR - Sutra")
	         .setContentText(extras.getString("Poruka"))
	         .setSmallIcon(R.drawable.ic_popup_reminder)
	         .setContentIntent(resultPendingIntent)
			 .setVibrate(quickBurstsExtended)
			 .setLights(Color.CYAN, 500, 100)
			 .setAutoCancel(true)
			 .setWhen(System.currentTimeMillis());
			 
			
			 }
		 
		 Notification n = builder.build();
//		 n.flags = Notification.FLAG_AUTO_CANCEL | Notification.FLAG_SHOW_LIGHTS;
		
		 nm.notify(notifyId, n);
		 
		
		 wl.release();
		
	}

}

