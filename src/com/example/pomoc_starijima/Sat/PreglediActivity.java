package com.example.pomoc_starijima.Sat;

import baze.SQLitePregledi;
import com.example.pomoc_starijima.R;
import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_1pregledi);

		naslov = (TextView) findViewById(R.id.txtPregledNaslov);
		ime = (TextView) findViewById(R.id.txtPregledIme);
		unos = (EditText) findViewById(R.id.edtPregledUnos);
		// datum = (TextView) findViewById(R.id.txtDatum);
		// datumP = (DatePicker) findViewById(R.id.dpDatumPr);
		// vreme = (TextView) findViewById(R.id.txtVreme);
		// vremeP = (TimePicker) findViewById(R.id.tpVremePr);
		sacuvaj = (Button) findViewById(R.id.btnSacuvajBroj);
		nazad = (Button) findViewById(R.id.btnNazadRodjendan);
		dajDatum = (Button) findViewById(R.id.izaberiDane);
		dajVreme = (Button) findViewById(R.id.izaberiVreme);
		staviDatum = (TextView) findViewById(R.id.txtDatumPr);
		staviVreme = (TextView) findViewById(R.id.txtVremeP);
		final Animation animDugme = AnimationUtils.loadAnimation(this,
				R.anim.anim_alpha);

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
						finish();

					}
				}, 260);
			}
		});
	}

	// protected void onPause() {
	// // TODO Auto-generated method stub
	// super.onPause();
	// bazaPregledi.close();
	// }

}
