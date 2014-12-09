package com.example.pomoc_starijima.Sat;

import java.util.Calendar;
import java.util.GregorianCalendar;

import baze.SQLiteSlave;

import com.example.pomoc_starijima.R;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SlavaActivity extends Activity {

	static TextView ispis;
	TextView naslov, ime, koSlavi;
	EditText unosIme, unosKo;
	Button izaberiDatum, sacuvaj, nazad;
	DatePicker datum;
	private Handler mHandler = new Handler();
	SQLiteSlave db;
	static AlarmManager alarmSlave;
	int brojac = 20000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setIcon(R.drawable.slika44);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_1slava);
		initialize();
		db = new SQLiteSlave(this);

		final Animation animDugme = AnimationUtils.loadAnimation(this,
				R.anim.anim_alpha);
		alarmSlave = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

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

		izaberiDatum.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				DialogFragment newFragment = new Datum('S');
				newFragment.show(getFragmentManager(), "datePicker");

			}
		});

		sacuvaj.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String imeSlave = unosIme.getText().toString();
				String koSlavi = unosKo.getText().toString();
				String datum = ispis.getText().toString();
				String[] datumNiz = datum.split("/");

				if (imeSlave.equals("") || koSlavi.equals("")
						|| datum.equals("")) {
					Toast t2 = Toast.makeText(getApplicationContext(),
							"NISTE UNELI SVE PODATKE", Toast.LENGTH_LONG);
					t2.show();
				} else {
					db.dodajSlavu(imeSlave, datum, koSlavi);

					Toast t1 = Toast.makeText(getApplicationContext(),
							"USPEŠNO STE SAČUVALI SLAVU", Toast.LENGTH_LONG);
					t1.show();
				}
				String poruka = imeSlave+" \n"+"KO SVE SLAVI? \n"+koSlavi;
				Intent alarmIntent = new Intent(getBaseContext(),Receiver.class);
				int rqs = brojac + Integer.parseInt(db.vratiSlavu());
				alarmIntent.putExtra("Poruka", poruka);
				alarmIntent.putExtra("ID", Integer.toString(rqs));
				alarmIntent.putExtra("Datum", datum);
				alarmIntent.putExtra("TipReceiver", "ReceiverS");
				PendingIntent sender = PendingIntent
						.getBroadcast(getBaseContext(), rqs,
								alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
				pokreniAlarm(sender, Integer.parseInt(datumNiz[0]), Integer.parseInt(datumNiz[1]));

				finish();
			}
		});
	}

	private void initialize() {
		naslov = (TextView) findViewById(R.id.txtSlaveTitle);
		ime = (TextView) findViewById(R.id.txtImeSlave);
		unosIme = (EditText) findViewById(R.id.editUnosSlave);
		izaberiDatum = (Button) findViewById(R.id.izaberiDane);
		ispis = (TextView) findViewById(R.id.txtIspisDatum);
		koSlavi = (TextView) findViewById(R.id.txtKoSlavi);
		unosKo = (EditText) findViewById(R.id.editKoSlavi);
		sacuvaj = (Button) findViewById(R.id.btnSacuvajBroj);
		nazad = (Button) findViewById(R.id.btnNazadRodjendan);
	}
	
	private void pokreniAlarm(PendingIntent pi, int dan1, int mesec1) {
		GregorianCalendar calNow = (GregorianCalendar) GregorianCalendar.getInstance();
		GregorianCalendar calSet = (GregorianCalendar) GregorianCalendar.getInstance();
			
		int godina = calNow.get(Calendar.YEAR);
		int dan = dan1;
		int mesec = mesec1 - 1;
//		int sat = 11;
//		int minut = 00;
		int sat = 11;
		int minut = 0;
	
		calSet.set(godina, mesec, dan, sat, minut);
		
		if (calSet.before(calNow)){
			calSet.set(godina +1 , mesec, dan, sat, minut);
			
			
		}

		alarmSlave.set(AlarmManager.RTC_WAKEUP,calSet.getTimeInMillis(),pi);
		

	}
	
	public static void cancelAlarm(int rqs, Context ctx)
	{
		Intent alarmIntent = new Intent(
				ctx,
				Receiver.class);
		PendingIntent sender = PendingIntent
				.getBroadcast(ctx, rqs,
						alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		alarmSlave.cancel(sender);
		
	}


}
