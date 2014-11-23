package com.example.pomoc_starijima.Stetoskop;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.widget.Toast;

public class AlarmReceiverTerapija extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent intent) {
		// if
		// (intent.getAction().equals("android.intent.action.BOOT_COMPLETED"))
		// Toast.makeText(arg0, "VREME JE ZA TERAPIJU!!",
		// Toast.LENGTH_LONG).show();

		// PowerManager pm = (PowerManager)
		// arg0.getSystemService(Context.POWER_SERVICE);
		// PowerManager.WakeLock wl =
		// pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
		// // Get a wake lock
		// wl.acquire();
		// Bundle extras = intent.getExtras();
		// // Set values for use in this class
		// String nTitle = extras.getString("Naslov");
		Toast t1 = Toast.makeText(arg0, "VREME JE ZA TERAPIJU!",
				Toast.LENGTH_LONG);
		t1.setDuration(60000);
		t1.setGravity(Gravity.CENTER, 0, 0);
		t1.show();
		// final MediaPlayer mpSnoop = MediaPlayer.create(arg0,
		// com.example.pomoc_starijima.R.raw.snoop);
		// mpSnoop.start();

	}

}