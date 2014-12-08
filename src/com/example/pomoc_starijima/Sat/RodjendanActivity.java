package com.example.pomoc_starijima.Sat;

import com.example.pomoc_starijima.R;
import baze.SQLiteRodjendani;

import android.app.Activity;
import android.app.DialogFragment;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.DialogFragment;
//import android.app.DialogFragment;
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

public class RodjendanActivity extends Activity {

	static TextView staviDatum;
	TextView naslov, izbor, ime, prezime;
	EditText unosIme, unosPrezime;
	DatePicker datum;
	Button sacuvaj, nazad, dajDatum;
	private Handler mHandler = new Handler();
	private SQLiteRodjendani db = new SQLiteRodjendani(this);

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

}
