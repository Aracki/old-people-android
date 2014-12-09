package com.example.pomoc_starijima.Sat;

import java.util.GregorianCalendar;

import baze.SQLitePregledi;
import com.example.pomoc_starijima.R;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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
import android.widget.TimePicker;
import android.widget.Toast;

public class PreglediActivity extends Activity {

	static TextView staviDatum, staviVreme;
	TextView naslov, ime, datum, vreme;
	EditText unos;
	TimePicker vremeP;
	DatePicker datumP;
	Button sacuvaj, nazad, dajDatum, dajVreme;
	private Handler mHandler = new Handler();
	static AlarmManager alarmLekar;
	int brojac = 30000;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setIcon(R.drawable.slika44);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_1pregledi);

		naslov = (TextView) findViewById(R.id.txtPregledNaslov);
		ime = (TextView) findViewById(R.id.txtPregledIme);
		unos = (EditText) findViewById(R.id.edtPregledUnos);
		sacuvaj = (Button) findViewById(R.id.btnSacuvajBroj);
		nazad = (Button) findViewById(R.id.btnNazadRodjendan);
		dajDatum = (Button) findViewById(R.id.izaberiDane);
		dajVreme = (Button) findViewById(R.id.izaberiVreme);
		staviDatum = (TextView) findViewById(R.id.txtDatumPr);
		staviVreme = (TextView) findViewById(R.id.txtVremeP);
		final Animation animDugme = AnimationUtils.loadAnimation(this,
				R.anim.anim_alpha);
		alarmLekar = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

		dajVreme.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DialogFragment newFragment = new Vreme('P');
				newFragment.show(getFragmentManager(), "timePicker");
			}
		});

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
				DialogFragment newFragment = new Datum('P');
				newFragment.show(getFragmentManager(), "datePicker");
			}
		});

		sacuvaj.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				v.startAnimation(animDugme);
				mHandler.postDelayed(new Runnable() {
					String imeBolnice = unos.getText().toString();
					String datum = staviDatum.getText().toString();
					String vreme = staviVreme.getText().toString();
					SQLitePregledi bazaPregledi = new SQLitePregledi(
							PreglediActivity.this);

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (imeBolnice.equals("") || datum.equals("")
								|| vreme.equals("")) {
							Toast t2 = Toast.makeText(getApplicationContext(),
									"Niste uneli sve podatke",
									Toast.LENGTH_LONG);
							t2.show();
						} else {
							// bazaPregledi.open();
							// bazaPregledi.open();
							bazaPregledi.dodajPregled(imeBolnice, datum, vreme);
							bazaPregledi.close();
							Toast t1 = Toast.makeText(getApplicationContext(),
									"Uspešno ste uneli pregled",
									Toast.LENGTH_LONG);
							t1.show();							
						}
						String poruka = imeBolnice;
						Intent alarmIntent = new Intent(getBaseContext(),Receiver.class);
						int rqs = brojac + Integer.parseInt(bazaPregledi.vratiPregled());
						alarmIntent.putExtra("Poruka", poruka);
						alarmIntent.putExtra("ID", Integer.toString(rqs));
						alarmIntent.putExtra("Datum", datum);
						alarmIntent.putExtra("Vreme", vreme);
						alarmIntent.putExtra("TipReceiver", "ReceiverL");
						PendingIntent sender = PendingIntent
								.getBroadcast(getBaseContext(), rqs,
										alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
						pokreniAlarm(sender, datum);

						finish();

					}
				}, 260);
			}
		});
	}
	private void pokreniAlarm(PendingIntent pi, String datum) {
		GregorianCalendar calNow = (GregorianCalendar) GregorianCalendar.getInstance();
		GregorianCalendar calSet = (GregorianCalendar) GregorianCalendar.getInstance();
		
		String[] datumNiz = datum.split("/");
		
			
		int godina = Integer.parseInt(datumNiz[2]);
		int dan = Integer.parseInt(datumNiz[0]);
		int mesec = Integer.parseInt(datumNiz[1]) -1;
//		int sat = 14;
//		int minut = 00;
		int sat = 10;
		int minut = 0;
	
		calSet.set(godina, mesec, dan, sat, minut);
		
		if (calSet.before(calNow)){
			calSet.set(godina +1 , mesec, dan, sat, minut);
			Log.d("if:", "Usao u if");
			
		}

		alarmLekar.set(AlarmManager.RTC_WAKEUP,calSet.getTimeInMillis()- AlarmManager.INTERVAL_DAY,pi);
		Log.d("Datum:", calSet.getTime().toString());

	}
//	
	public static void cancelAlarm(int rqs, Context ctx)
	{
		Intent alarmIntent = new Intent(
				ctx,
				Receiver.class);
		PendingIntent sender = PendingIntent
				.getBroadcast(ctx, rqs,
						alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		alarmLekar.cancel(sender);
		
	}


	// protected void onPause() {
	// // TODO Auto-generated method stub
	// super.onPause();
	// bazaPregledi.close();
	// }

}
