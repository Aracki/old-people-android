package com.example.pomoc_starijima;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends Activity {

	Button sat, poruka, stetoskop, poziv, podesavanja;
	private Handler mHandler = new Handler();

	// KOMENTAAAAAAAAAAAAAAAAAAAAAARRRRRRRRRRRRRRRRRRRRRRRRR!!!!!!!!!!!!!!!!!!!!!!
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		initialize();
		final Animation animDugme = AnimationUtils.loadAnimation(this,
				R.anim.anim_alpha);

		podesavanja.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				v.startAnimation(animDugme);
				mHandler.postDelayed(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						// TODO Auto-generated method stub
						Intent openPodesavanjaActivity = new Intent(
								"com.example.pomoc_starijima.PODESAVANJA");
						startActivity(openPodesavanjaActivity);
					}
				}, 260);

			}
		});

		sat.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// TODO Auto-generated method stub
				Intent openSatActivity = new Intent("com.example.pomoc_starijima.SAT");
				startActivity(openSatActivity);

			}
		});

		poruka.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openPorukaActivity = new Intent(
						"com.example.pomoc_starijima.PORUKA");
				startActivity(openPorukaActivity);
			}
		});

		stetoskop.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openStetoskopActivity = new Intent(
						"com.example.pomoc_starijima.STETOSKOP");
				startActivity(openStetoskopActivity);
			}
		});

		poziv.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				final Uri broj = vratiBroj();

				AlertDialog.Builder builder1 = new AlertDialog.Builder(
						MainActivity.this);
				builder1.setMessage("Da li ste sigurni da želite da pozovete unesen broj?");
				builder1.setCancelable(true);
				builder1.setPositiveButton("Da",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Intent pozvati = new Intent(Intent.ACTION_CALL);
								pozvati.setData(broj);
								if (pozvati.getData() == null) {
									new AlertDialog.Builder(MainActivity.this)
											.setTitle("Greška")
											.setMessage(
													"U podešavanjima aplikacije ubacite broj za hitan poziv!")
											.setNeutralButton("OK", null)
											.setIcon(R.drawable.error).show();
								} else {
									startActivity(pozvati);
								}
							}
						});
				builder1.setNegativeButton("Ne",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

				builder1.show();
			}
		});

	}

	public void initialize() {
		sat = (Button) findViewById(R.id.btnSat);
		poruka = (Button) findViewById(R.id.btnPoruka);
		stetoskop = (Button) findViewById(R.id.btnStetoskop);
		poziv = (Button) findViewById(R.id.btnPoziv);
		podesavanja = (Button) findViewById(R.id.btnPodesavanja);

	}

	public Uri vratiBroj() {

		Uri brojTelefona;
		String brojIzFajla = "";

		try {
			BufferedReader inputReader = new BufferedReader(
					new InputStreamReader(openFileInput("broj.txt")));
			String inputString;
			StringBuffer stringBuffer = new StringBuffer();
			while ((inputString = inputReader.readLine()) != null) {
				stringBuffer.append(inputString + "\n");
			}
			brojIzFajla = stringBuffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}

		brojTelefona = Uri.parse("tel:" + brojIzFajla);

		return brojTelefona;
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	//
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }
	//
	// @Override
	// public boolean onOptionsItemSelected(MenuItem item) {
	// // Handle action bar item clicks here. The action bar will
	// // automatically handle clicks on the Home/Up button, so long
	// // as you specify a parent activity in AndroidManifest.xml.
	// int id = item.getItemId();
	// if (id == R.id.action_settings) {
	// return true;
	// }
	// return super.onOptionsItemSelected(item);
	// }

}