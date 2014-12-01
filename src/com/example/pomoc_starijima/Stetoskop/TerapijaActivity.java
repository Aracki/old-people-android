package com.example.pomoc_starijima.Stetoskop;

import java.util.Calendar;
import baze.SQLiteTerapija;

import com.example.pomoc_starijima.R;
import android.app.Activity;
//import android.support.v7.app.ActionBarActivity;
//import android.support.v7.app.ActionBar;
//import android.support.v4.app.Fragment;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TerapijaActivity extends Activity {

	Button sacuvaj, nazad;
	EditText unos;
	TextView ujutru, spisak, aktivna;
	Spinner kada, koliko;
	CheckBox aktivna_ck;
	AlarmManager alarm;

	private Handler mHandler = new Handler();
	final static int RQS_1 = 1;
	final static int RQS_2 = 2;
	final static int RQS_3 = 3;
	int sat = 0;
	int minut = 0;
	int deoDana = 5;
	SQLiteTerapija db;
	SQLiteDatabase database;
	String dbUjutru = "";
	String dbPopodne = "";
	String dbUvece = "";

	// ***
	int pozicijaVreme = 0;
	int pozicijaKolicina = 0;

	String[] kadaUj = { "Odaberite:", "6:00", "6:30", "7:00", "7:30", "8:00",
			"8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30" };
	String[] kadaPo = { "Odaberite:", "12:00", "12:30", "13:00", "13:30",
			"14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00",
			"17:30" };
	String[] kadaUv = { "Odaberite:", "18:00", "18:30", "19:00", "19:30",
			"20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00",
			"23:30" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_3terapija1);
		db = new SQLiteTerapija(this);
		initialize();

		final Animation animDugme = AnimationUtils.loadAnimation(this,
				R.anim.anim_alpha);

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

		sacuvaj.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				v.startAnimation(animDugme);

				mHandler.postDelayed(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						// ZA UJUTRU
						boolean proveri = true;
						if (kada.getSelectedItemPosition() == 0) {
							proveri = false;
							Toast t1 = Toast.makeText(getApplicationContext(),
									"UNESITE VREME", Toast.LENGTH_LONG);
							// t1.setDuration(5500);
							t1.setGravity(Gravity.CENTER, 0, 0);
							t1.show();
						} else if (koliko.getSelectedItemPosition() == 0) {
							proveri = false;
							Toast t1 = Toast.makeText(getApplicationContext(),
									"UNESITE KOLICINU", Toast.LENGTH_LONG);
							// t1.setDuration(5500);
							t1.setGravity(Gravity.CENTER, 0, 0);
							t1.show();
						} else if (unos.getText().toString().equals("")) {
							proveri = false;
							Toast t1 = Toast.makeText(getApplicationContext(),
									"UNESITE SPISAK LEKOVA", Toast.LENGTH_LONG);
							// t1.setDuration(5500);
							t1.setGravity(Gravity.CENTER, 0, 0);
							t1.show();

						}

						if (proveri) {
							if (deoDana == 1) {
								vratiKadaUjutru();
								String s = Integer.toString(sat);
								String m = Integer.toString(minut);
								String k = vratiKoliko();
								String ch = "";

								if (aktivna_ck.isChecked()) {
									ch = "T";
								} else {
									ch = "F";
								}
								// kada jos ne postoji baza
								if (dbUjutru == "") {

									db.dodajTerapiju(1, unos.getText()
											.toString(), s, m, k, ch, String
											.valueOf(pozicijaVreme), String
											.valueOf(pozicijaKolicina));
									Log.d("DSA", "DSA");

									// Log.d("Naziv:", db.vratiNaziv(1));
								} else {
									db.updateTerapija(1, unos.getText()
											.toString(), s, m, k, ch, String
											.valueOf(pozicijaVreme), String
											.valueOf(pozicijaKolicina));
									// UPDATE BAZE
									Log.d("Ujutru", "baza osvezena");
								}
								String terapija = "LEKOVI: "
										+ unos.getText().toString() + "\n"
										+ "KOLICINA: "
										+ koliko.getSelectedItem().toString();
								Intent alarmIntent = new Intent(
										getBaseContext(),
										AlarmReceiverTerapija.class);
								alarmIntent.putExtra("ID", "2");
								alarmIntent.putExtra("Terapija", terapija);
								PendingIntent sender = PendingIntent
										.getBroadcast(
												getBaseContext(),
												1,
												alarmIntent,
												PendingIntent.FLAG_UPDATE_CURRENT);
								if (aktivna_ck.isChecked()) {

								} else {
									alarm.cancel(sender);

								}

							} else if (deoDana == 2) {
								vratiKadaPopodne();
								String s = Integer.toString(sat);
								String m = Integer.toString(minut);
								String k = vratiKoliko();
								String ch = "";

								if (aktivna_ck.isChecked()) {
									ch = "T";
								} else {
									ch = "F";
								}
								// kada jos ne postoji baza
								if (dbPopodne == "") {

									db.dodajTerapiju(2, unos.getText()
											.toString(), s, m, k, ch, String
											.valueOf(pozicijaVreme), String
											.valueOf(pozicijaKolicina));
									Log.d("DSA", "DSA");

									// Log.d("Naziv:", db.vratiNaziv(1));
								} else {
									db.updateTerapija(2, unos.getText()
											.toString(), s, m, k, ch, String
											.valueOf(pozicijaVreme), String
											.valueOf(pozicijaKolicina));
									// UPDATE BAZE
									Log.d("Update", "Update");
								}
								String terapija = "LEKOVI: "
										+ unos.getText().toString() + "\n"
										+ "KOLICINA: "
										+ koliko.getSelectedItem().toString();
								Intent alarmIntent = new Intent(
										getBaseContext(),
										AlarmReceiverTerapija.class);
								alarmIntent.putExtra("Terapija", terapija);
								alarmIntent.putExtra("ID", "2");

								PendingIntent sender = PendingIntent
										.getBroadcast(
												getBaseContext(),
												2,
												alarmIntent,
												PendingIntent.FLAG_UPDATE_CURRENT);
								if (aktivna_ck.isChecked()) {
									pokreniAlarm(sender);

								} else {
									alarm.cancel(sender);

								}
							}

							else if (deoDana == 3) {
								vratiKadaUvece();
								String s = Integer.toString(sat);
								String m = Integer.toString(minut);
								String k = vratiKoliko();
								String ch = "";

								if (aktivna_ck.isChecked()) {
									ch = "T";
								} else {
									ch = "F";
								}

								// kada jos ne postoji baza
								if (dbUvece == "") {

									db.dodajTerapiju(3, unos.getText()
											.toString(), s, m, k, ch, String
											.valueOf(pozicijaVreme), String
											.valueOf(pozicijaKolicina));
									// Log.d("Naziv:", db.vratiNaziv(1));
								} else {
									db.updateTerapija(3, unos.getText()
											.toString(), s, m, k, ch, String
											.valueOf(pozicijaVreme), String
											.valueOf(pozicijaKolicina));
									// UPDATE BAZE
								}
								String terapija = "LEKOVI: "
										+ unos.getText().toString() + "\n"
										+ "KOLICINA: "
										+ koliko.getSelectedItem().toString();
								Intent alarmIntent = new Intent(
										getBaseContext(),
										AlarmReceiverTerapija.class);
								alarmIntent.putExtra("Terapija", terapija);
								alarmIntent.putExtra("ID", "3");
								PendingIntent sender = PendingIntent
										.getBroadcast(
												getBaseContext(),
												3,
												alarmIntent,
												PendingIntent.FLAG_UPDATE_CURRENT);

								if (aktivna_ck.isChecked()) {
									pokreniAlarm(sender);
								} else {
									alarm.cancel(sender);
								}
							}

							Toast t1 = Toast.makeText(getApplicationContext(),
									"TERAPIJA SACUVANA", Toast.LENGTH_LONG);
							t1.setDuration(5500);
							t1.setGravity(Gravity.CENTER, 0, 0);
							t1.show();
							finish();

							// if(aktivna_ck.isSelected())
							// pokreniAlarm(unos.getText().toString());

						}

					}
				}, 260);

			}
		});

	}

	private String vratiKoliko() {

		String x = "";

		switch (koliko.getSelectedItemPosition()) {
		case 0:
			x = "er";

			break;
		case 1:
			x = "Pola tablete";
			pozicijaKolicina = 1;
			break;
		case 2:
			x = "Jedna tableta";
			pozicijaKolicina = 2;
			break;
		case 3:
			x = "Dve tablete";
			pozicijaKolicina = 3;
			break;

		case 4:
			x = "Tri tablete";
			pozicijaKolicina = 4;
			break;

		case 5:
			x = "Vise tableta";
			pozicijaKolicina = 5;
			break;

		}
		return x;
	}

	private void initialize() {
		ujutru = (TextView) findViewById(R.id.txtNazivUjutru);
		spisak = (TextView) findViewById(R.id.txtSpisakLekova);
		unos = (EditText) findViewById(R.id.editSpisak);
		kada = (Spinner) findViewById(R.id.combo_kada);
		koliko = (Spinner) findViewById(R.id.combo_koliko);
		aktivna = (TextView) findViewById(R.id.terapijaAct);
		aktivna_ck = (CheckBox) findViewById(R.id.chbAktivna);
		sacuvaj = (Button) findViewById(R.id.btnSacuvajBroj);
		nazad = (Button) findViewById(R.id.btnNazadPodesavanja);

		deoDana = getIntent().getExtras().getInt("dan");

		dbUjutru = db.vratiTerapiju(1);
		dbPopodne = db.vratiTerapiju(2);
		dbUvece = db.vratiTerapiju(3);
		alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

		if (deoDana == 1) {

			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					TerapijaActivity.this,
					android.R.layout.simple_spinner_item, kadaUj);
			kada.setAdapter(adapter);

		} else if (deoDana == 2) {

			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					TerapijaActivity.this,
					android.R.layout.simple_spinner_item, kadaPo);
			kada.setAdapter(adapter);

		} else if (deoDana == 3) {

			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					TerapijaActivity.this,
					android.R.layout.simple_spinner_item, kadaUv);
			kada.setAdapter(adapter);

		}

	}

	// Probati kako radi calendar.clear?????????????????????????????????
	private void pokreniAlarm(PendingIntent pi) {
		Calendar calNow = Calendar.getInstance();
		Calendar calSet = (Calendar) calNow.clone();

		calSet.set(Calendar.HOUR_OF_DAY, sat);
		calSet.set(Calendar.MINUTE, minut);
		calSet.set(Calendar.SECOND, 0);
		calSet.set(Calendar.MILLISECOND, 0);

		// alarm.setRepeating(AlarmManager.RTC_WAKEUP, calSet.getTimeInMillis(),
		// AlarmManager.INTERVAL_DAY, pi);
		alarm.setRepeating(AlarmManager.RTC_WAKEUP, calSet.getTimeInMillis(),
				AlarmManager.INTERVAL_FIFTEEN_MINUTES, pi);

	}

	@Override
	protected void onResume() {
		super.onResume();
		if ((int) deoDana == 1) {
			ujutru.setText("UJUTRU");
			if (dbUjutru != "") {
				// vratiPodatke
				// Log.d("ujuj", "UJUJ");
				String[] niz = dbUjutru.split(":::");
				String spisak = niz[0];
				// String sat = niz[1];
				// String minut = niz[2];
				// String kolicina = niz[3];
				String aktivna = niz[4];
				String x = niz[5];
				String y = niz[6];

				unos.setText(spisak);
				kada.setSelection(Integer.parseInt(x));
				koliko.setSelection(Integer.parseInt(y));

				if (aktivna.equals("T")) {
					aktivna_ck.setChecked(true);
				}
			}

		} else if ((int) deoDana == 2) {
			ujutru.setText("POPODNE");
			if (dbPopodne != "") {
				// vratiPodatke
				// Log.d("ujuj", "UJUJ");
				String[] niz = dbPopodne.split(":::");
				String spisak = niz[0];
				// String sat = niz[1];
				// String minut = niz[2];
				// String kolicina = niz[3];
				String aktivna = niz[4];
				String x = niz[5];
				String y = niz[6];

				unos.setText(spisak);
				kada.setSelection(Integer.parseInt(x));
				koliko.setSelection(Integer.parseInt(y));

				if (aktivna.equals("T")) {
					aktivna_ck.setChecked(true);
				}
			}

		} else if ((int) deoDana == 3) {
			ujutru.setText("UVECE");
			if (dbUvece != "") {
				// vratiPodatke
				// Log.d("ujuj", "UJUJ");
				String[] niz = dbUvece.split(":::");
				String spisak = niz[0];
				// String sat = niz[1];
				// String minut = niz[2];
				// String kolicina = niz[3];
				String aktivna = niz[4];
				String x = niz[5];
				String y = niz[6];

				unos.setText(spisak);
				kada.setSelection(Integer.parseInt(x));
				koliko.setSelection(Integer.parseInt(y));

				if (aktivna.equals("T")) {
					aktivna_ck.setChecked(true);
				}
			}

		}

	}

	private void vratiKadaUjutru() {

		switch (kada.getSelectedItemPosition()) {
		case 0:
			sat = 0;
			minut = 0;
			break;
		case 1:
			sat = 6;
			minut = 0;
			pozicijaVreme = 1;
			break;
		case 2:
			sat = 6;
			minut = 30;
			pozicijaVreme = 2;
			break;

		case 3:
			sat = 7;
			minut = 0;
			pozicijaVreme = 3;
			break;
		case 4:
			sat = 7;
			minut = 30;
			pozicijaVreme = 4;
			break;
		case 5:
			sat = 8;
			minut = 0;
			pozicijaVreme = 5;
			break;
		case 6:
			sat = 8;
			minut = 30;
			pozicijaVreme = 6;
			break;
		case 7:
			sat = 9;
			minut = 0;
			pozicijaVreme = 7;
			break;
		case 8:
			sat = 9;
			minut = 30;
			pozicijaVreme = 8;
			break;
		case 9:
			sat = 10;
			minut = 0;
			pozicijaVreme = 9;
			break;
		case 10:
			sat = 10;
			minut = 30;
			pozicijaVreme = 10;
			break;
		case 11:
			sat = 11;
			minut = 00;
			pozicijaVreme = 11;
			break;
		// case 12:
		// sat = 11;
		// minut = 30;
		// break;
		case 12:
			sat = 18;
			minut = 30;
			pozicijaVreme = 12;
			break;

		}
	}

	private void vratiKadaPopodne() {

		switch (kada.getSelectedItemPosition()) {
		case 0:
			sat = 0;
			minut = 0;
			break;
		case 1:
			sat = 12;
			minut = 0;
			pozicijaVreme = 1;
			break;
		case 2:
			sat = 12;
			minut = 30;
			pozicijaVreme = 2;
			break;

		case 3:
			sat = 13;
			minut = 0;
			pozicijaVreme = 3;
			break;
		case 4:
			sat = 13;
			minut = 30;
			pozicijaVreme = 4;
			break;
		case 5:
			sat = 14;
			minut = 0;
			pozicijaVreme = 5;
			break;
		case 6:
			sat = 14;
			minut = 30;
			pozicijaVreme = 6;
			break;
		case 7:
			sat = 15;
			minut = 0;
			pozicijaVreme = 7;
			break;
		case 8:
			sat = 15;
			minut = 30;
			pozicijaVreme = 8;
			break;
		case 9:
			sat = 16;
			minut = 0;
			pozicijaVreme = 9;
			break;
		case 10:
			sat = 16;
			minut = 30;
			pozicijaVreme = 10;
			break;
		case 11:
			sat = 17;
			minut = 00;
			pozicijaVreme = 11;
			break;
		// case 12:
		// sat = 11;
		// minut = 30;
		// break;
		case 12:
			sat = 18;
			minut = 56;
			pozicijaVreme = 12;
			break;

		}
	}

	private void vratiKadaUvece() {

		switch (kada.getSelectedItemPosition()) {
		case 0:
			sat = 0;
			minut = 0;
			break;
		case 1:
			sat = 18;
			minut = 0;
			pozicijaVreme = 1;
			break;
		case 2:
			sat = 18;
			minut = 22;
			pozicijaVreme = 2;
			break;

		case 3:
			sat = 19;
			minut = 0;
			pozicijaVreme = 3;
			break;
		case 4:
			sat = 19;
			minut = 30;
			pozicijaVreme = 4;
			break;
		case 5:
			sat = 20;
			minut = 0;
			pozicijaVreme = 5;
			break;
		case 6:
			sat = 20;
			minut = 30;
			pozicijaVreme = 6;
			break;
		case 7:
			sat = 21;
			minut = 0;
			pozicijaVreme = 7;
			break;
		case 8:
			sat = 21;
			minut = 30;
			pozicijaVreme = 8;
			break;
		case 9:
			sat = 22;
			minut = 0;
			pozicijaVreme = 9;
			break;
		case 10:
			sat = 22;
			minut = 30;
			pozicijaVreme = 10;
			break;
		case 11:
			sat = 23;
			minut = 00;
			pozicijaVreme = 11;
			break;
		// case 12:
		// sat = 11;
		// minut = 30;
		// break;
		case 12:
			sat = 0;
			minut = 5;
			pozicijaVreme = 12;
			break;

		}
	}

}