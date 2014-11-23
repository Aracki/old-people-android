package com.example.pomoc_starijima.Sat;

import baze.SQLiteSlave;

import com.example.pomoc_starijima.R;
import com.example.pomoc_starijima.R.id;
import com.example.pomoc_starijima.R.layout;
//import com.example.pomoc_starijima.R.menu;

//import android.support.v7.app.ActionBarActivity;
//import android.support.v7.app.ActionBar;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.DialogFragment;
import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slava);
		initialize();
		db = new SQLiteSlave(this);

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

}
