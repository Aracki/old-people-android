package com.example.pomoc_starijima.Sat;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.example.pomoc_starijima.R;
import baze.SQLiteRodjendani;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.DialogFragment;
//import android.app.DialogFragment;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RodjendanActivity extends Activity {

	static TextView staviDatum;
	TextView naslov, izbor, ime, prezime;
	EditText unosIme, unosPrezime;
	int brojac = 10000;
	DatePicker datum;
	Button sacuvaj, nazad, dajDatum;
	private Handler mHandler = new Handler();
	private SQLiteRodjendani db = new SQLiteRodjendani(this);
	static AlarmManager alarmRodjendan;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setIcon(R.drawable.slika44);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_1rodjendan);

		naslov = (TextView) findViewById(R.id.txtRodjendanTitle);
		ime = (TextView) findViewById(R.id.txtIme);
		unosIme = (EditText) findViewById(R.id.editUnosIme);
		prezime = (TextView) findViewById(R.id.txtPrezime);
		unosPrezime = (EditText) findViewById(R.id.editUnosPrezime);
		sacuvaj = (Button) findViewById(R.id.btnSacuvajBroj);
		nazad = (Button) findViewById(R.id.btnNazadRodjendan);
		dajDatum = (Button) findViewById(R.id.izaberiDane);
		staviDatum = (TextView) findViewById(R.id.txtDatumR);
		final Animation animDugme = AnimationUtils.loadAnimation(this,
				R.anim.anim_alpha);
		alarmRodjendan = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		
		nazad.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				v.startAnimation(animDugme);
				mHandler.postDelayed(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						finish();
					}
				}, 260);
			}
		});

		dajDatum.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				DialogFragment newFragment = new Datum('R');
				newFragment.show(getFragmentManager(), "datePicker");

			}
		});

		sacuvaj.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				v.startAnimation(animDugme);
				mHandler.postDelayed(new Runnable() {
					String i = unosIme.getText().toString();
					String p = unosPrezime.getText().toString();
					String d = staviDatum.getText().toString();
					String[] datum = d.split("/");

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (i.equals("") || p.equals("") || d.equals("")) {
							Toast t2 = Toast.makeText(getApplicationContext(),
									"Niste uneli sve podatke",
									Toast.LENGTH_LONG);
							t2.show();
						} else {
							db.dodajRodjendan(i, p, d);
							Toast t1 = Toast.makeText(getApplicationContext(),
									"Uspešno ste uneli rodjendan",
									Toast.LENGTH_LONG);
							t1.show();
						}
						String poruka = i+" "+p;
						Intent alarmIntent = new Intent(
								getBaseContext(),
								Receiver.class);
						int rqs = brojac + Integer.parseInt(db.vratiRodjendan());
						alarmIntent.putExtra("Poruka", poruka);
						alarmIntent.putExtra("ID", Integer.toString(rqs));
						alarmIntent.putExtra("Datum", d);
						alarmIntent.putExtra("TipReceiver", "ReceiverR");
						PendingIntent sender = PendingIntent
								.getBroadcast(getBaseContext(), rqs,
										alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
						pokreniAlarm(sender, Integer.parseInt(datum[0]), Integer.parseInt(datum[1]));

						finish();
					}
				}, 260);
			}
		});
	}

	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		db.close();
	}
	
	private void pokreniAlarm(PendingIntent pi, int dan1, int mesec1) {
		GregorianCalendar calNow = (GregorianCalendar) GregorianCalendar.getInstance();
		GregorianCalendar calSet = (GregorianCalendar) GregorianCalendar.getInstance();
			
		int godina = calNow.get(Calendar.YEAR);
		int dan = dan1;
		int mesec = mesec1 - 1;
		int sat = 12;
		int minut = 0;


		
		calSet.set(godina, mesec, dan, sat, minut);
		
		if (calSet.before(calNow)){
			calSet.set(godina +1 , mesec, dan, sat, minut);
			Log.d("if:", "Usao u if");
			
		}
		
//	    if(calSet.isLeapYear(godina)){
//	    	alarmRodjendan.setRepeating(AlarmManager.RTC_WAKEUP, calSet.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 366, pi);
//	    	Log.d("Godina", "prestupna");
//	    }else{
//	          alarmRodjendan.setRepeating(AlarmManager.RTC_WAKEUP, calSet.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 365, pi);
//	    }
		alarmRodjendan.set(AlarmManager.RTC_WAKEUP,calSet.getTimeInMillis(),pi);
		Log.d("Datum:", calSet.getTime().toString());
	}
	
	public static void cancelAlarm(int rqs, Context ctx)
	{
		Intent alarmIntent = new Intent(
				ctx,
				Receiver.class);
		PendingIntent sender = PendingIntent
				.getBroadcast(ctx, rqs,
						alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		alarmRodjendan.cancel(sender);
		
	}


}
