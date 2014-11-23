package com.example.pomoc_starijima;



import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PodesavanjaActivity extends Activity {

	Button sacuvaj, povratak;
	EditText broj;
	TextView txtHitanBroj;
	private Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		setTitle("Podesavanja broja");
		getActionBar().setIcon(R.drawable.slika22);
		setContentView(R.layout.activity_podesavanja_poziva);
		initialize();
		final Animation animDugme = AnimationUtils.loadAnimation(this,
				R.anim.anim_alpha);

		povratak.setOnClickListener(new View.OnClickListener() {
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
						if (broj.getText().toString().matches("[0-9]+")
								&& broj.getText().toString().length() >= 9) {

							try {
								FileOutputStream fos = openFileOutput(
										"broj.txt", Context.MODE_PRIVATE);
								fos.write(broj.getText().toString().getBytes());
								fos.close();
							} catch (Exception e) {
								e.printStackTrace();
							}

							finish();

							Toast t1 = Toast.makeText(getApplicationContext(),
									"USPEŠNO STE DODALI BROJ",
									Toast.LENGTH_LONG);
							t1.setDuration(5500);
							t1.setGravity(Gravity.CENTER, 0, 0);
							t1.show();
						} else {
							// TODO: handle exception

							AlertDialog.Builder ad = new AlertDialog.Builder(
									PodesavanjaActivity.this);
							ad.setTitle("Unesite ponovo");
							ad.setMessage("MOLIMO UNESITE BROJ PONOVO");
							ad.setCancelable(true);
							ad.setNeutralButton("OK",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {
											dialog.cancel();
										}
									});

							AlertDialog alert11 = ad.create();
							alert11.show();
						}

					}
				}, 260);
			}
		});

	}

	// TODO Auto-generated method stub

	private void initialize() {
		sacuvaj = (Button) findViewById(R.id.btnSacuvajBroj);
		povratak = (Button) findViewById(R.id.btnNazadPodesavanja);
		broj = (EditText) findViewById(R.id.txtTelefon);
		txtHitanBroj = (TextView) findViewById(R.id.txtHitanBroj);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		txtHitanBroj.setText(vratiBroj());
	}

	public String vratiBroj() {

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
		return brojIzFajla;
	}
	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // TODO Auto-generated method stub
	// getMenuInflater().inflate(R.menu.poziv, menu);
	// return true;
	// }
	//
	// //
	// // @Override
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
