package com.example.pomoc_starijima.Stetoskop;

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

@SuppressLint("NewApi")
public class AlarmReceiverTerapija extends BroadcastReceiver {

	@Override
	public void onReceive(Context ctxt, Intent intent) {

		PowerManager pm = (PowerManager) ctxt
				.getSystemService(Context.POWER_SERVICE);
		PowerManager.WakeLock wl = pm.newWakeLock(
				PowerManager.PARTIAL_WAKE_LOCK, "");
		// Get a wake lock
		wl.acquire();

		Bundle extras = intent.getExtras();
		// pattern za pravljenje vibraije
		long[] quickBurstsExtended = new long[] { 0, 100, 70, 100, 70, 100, 70,
				100, 70, 100, 70, 100, 70, 100, 70, 100, 70, 100, 70, 100, 70,
				100, 70, 100, 70, 100, 70, 100, 70, 100, 70, 100, 70 };

		NotificationManager nm = (NotificationManager) ctxt
				.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification.Builder builder = new Notification.Builder(ctxt);

		Intent resultIntent = new Intent(ctxt, TerapijaActivity.class);

		// Because clicking the notification opens a new ("special") activity,
		// there's
		// no need to create an artificial back stack.
		PendingIntent resultPendingIntent = PendingIntent.getActivity(ctxt, 0,
				resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

		builder.setContentTitle("VREME JE ZA TERAPIJU!")
				.setContentText(extras.getString("Terapija"))
				.setSmallIcon(R.drawable.ic_popup_reminder)
				.setContentIntent(resultPendingIntent)
				.setVibrate(quickBurstsExtended)
				.setLights(Color.CYAN, 500, 100)
				.setWhen(System.currentTimeMillis());

		Notification n = builder.build();
		nm.notify(Integer.parseInt(extras.getString("ID")), n);

		wl.release();

	}

}
