package com.example.pomoc_starijima.Poruka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.example.pomoc_starijima.MainActivity;
import com.example.pomoc_starijima.R;
import baze.SQLitePrimeriPoruka;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SendPorukuX extends Activity {

	Button btnSend, btnNazad, btnSacuvaj;
	TextView txtPhoneNumb, txtPhoneNumbV, txtSms;
	EditText textSMS;
	SQLitePrimeriPoruka db = new SQLitePrimeriPoruka(this);
	private Handler mHandler = new Handler();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setIcon(R.drawable.slika11);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_2poruka_x);
		initialize();
		final Animation animDugme = AnimationUtils.loadAnimation(this,
				R.anim.anim_alpha);

		final int id = this.getIntent().getExtras().getInt("id");
		if (db.vratiPrimer(id).toString() != null) {
			textSMS.setText(db.vratiPrimer(id));
		}

		btnSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				v.startAnimation(animDugme);
				mHandler.postDelayed(new Runnable() {

					@Override
					public void run() {

						String phoneNo = txtPhoneNumb.getText().toString();
						String sms = textSMS.getText().toString();

						try {
							SmsManager smsManager = SmsManager.getDefault();
							smsManager.sendTextMessage(phoneNo, null, sms,
									null, null);
							Toast.makeText(getApplicationContext(),
									"SMS Sent!", Toast.LENGTH_LONG).show();
						} catch (Exception e) {
							Toast.makeText(getApplicationContext(),
									"SMS faild, please try again later!",
									Toast.LENGTH_LONG).show();
							e.printStackTrace();
						}

					}
				}, 260);
			}
		});

		btnSacuvaj.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String text = textSMS.getText().toString();
				if (text.length() == 0) {
					Toast t1 = Toast.makeText(getApplicationContext(),
							"Niste uneli tekst poruke", Toast.LENGTH_LONG);
					t1.show();
				} else {
					db.updatePrimer(text, id);
					Toast t1 = Toast
							.makeText(getApplicationContext(),
									"Uspešno ste uneli tekst poruke",
									Toast.LENGTH_LONG);
					t1.show();
					Intent i1 = new Intent(SendPorukuX.this, MainActivity.class);
					i1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i1);
				}

				// TREBALO BI PO ID- da se dodaju!
			}
		});

		btnNazad.setOnClickListener(new OnClickListener() {

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

		txtPhoneNumb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast t1 = Toast.makeText(getApplicationContext(),
						"BROJ SE MENJA U PODEŠAVANJIMA", Toast.LENGTH_LONG);
				t1.setDuration(2000);
				t1.show();

			}
		});
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		db.close();
	}

	public void initialize() {
		btnNazad = (Button) findViewById(R.id.btnBack);
		btnSend = (Button) findViewById(R.id.btnPosalji);
		btnSacuvaj = (Button) findViewById(R.id.btnSacuvajPoruku);
		txtPhoneNumb = (TextView) findViewById(R.id.txtBrojTelefona);
		txtPhoneNumbV = (TextView) findViewById(R.id.textViewPhoneNo);
		textSMS = (EditText) findViewById(R.id.txtSMSporukaX);
		txtSms = (TextView) findViewById(R.id.textViewSMS);
		txtPhoneNumb.setText(vratiBroj());
		// txtPhoneNumbV.setClickable(true);
	}

	// metoda iz podesavanjaAct
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

}
