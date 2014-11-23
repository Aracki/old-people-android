package com.example.pomoc_starijima.Sat;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import baze.SQLiteTVprogrami;

import com.example.pomoc_starijima.R;
//import android.support.v7.app.ActionBarActivity;
//import android.support.v7.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Activity;
import android.app.DialogFragment;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
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

public class TvProgramActivity extends Activity {

	TextView naslov, naziv, datum, vreme;
	EditText unos;
	Button nazad, sacuvaj, dajDane, dajVreme;
	DatePicker datumP;
	TimePicker vremeP;
	static TextView staviVreme, staviDane;
	private Handler mHandler = new Handler();
	SQLiteTVprogrami db = new SQLiteTVprogrami(this);
	Map<Integer, String> daniX = new HashMap<Integer, String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_1tv_program);

		unos = (EditText) findViewById(R.id.edtTVUnos);
		sacuvaj = (Button) findViewById(R.id.btnSacuvajBroj);
		nazad = (Button) findViewById(R.id.btnNazadRodjendan);
		dajDane = (Button) findViewById(R.id.izaberiDane);
		dajVreme = (Button) findViewById(R.id.izaberiVreme);
		staviDane = (TextView) findViewById(R.id.txtDatumTV);
		staviVreme = (TextView) findViewById(R.id.txtVremeTV);
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
						db.dodajTVprogram(unos.getText().toString(), staviDane
								.getText().toString(), staviVreme.getText()
								.toString());
						Toast t1 = Toast.makeText(getApplicationContext(),
								"Uspešno ste uneli program", Toast.LENGTH_LONG);
						t1.show();
					}
				}, 260);
			}
		});
		dajDane.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final CharSequence[] items = { " Ponedeljak ", " Utorak ",
						" Sreda ", " Četvrtak ", " Petak ", " Subota ",
						" Nedelja " };

				AlertDialog.Builder builder = new AlertDialog.Builder(
						TvProgramActivity.this);
				builder.setTitle("Izaberi dane");
				builder.setMultiChoiceItems(items, null,
						new DialogInterface.OnMultiChoiceClickListener() {
							// indexSelected contains the index of item (of
							// which checkbox checked)
							@Override
							public void onClick(DialogInterface dialog,
									int indexSelected, boolean isChecked) {
								if (isChecked) {

									daniX.put(indexSelected,
											(String) items[indexSelected]);

								} else if (daniX.containsValue(indexSelected)) {

									daniX.remove(daniX.get(indexSelected));

								}
							}
						})
						// Set the action buttons
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int id) {

										staviDane.setText(vratiMapuDana());
									}
								})
						.setNegativeButton("Nazad",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int id) {
										// Your code when user clicked on Cancel

									}
								});

				Dialog dialog = builder.create();// AlertDialog dialog; create
													// like
													// this outside onClick
				dialog.show();
			}
		});

		dajVreme.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DialogFragment newFragment = new Vreme('T');
				newFragment.show(getFragmentManager(), "timePicker");
			}
		});
	}

	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		db.close();
	}

	public String vratiMapuDana() {
		String x = "";

		SortedSet<Integer> keys = new TreeSet<Integer>(daniX.keySet());
		for (Integer k : keys) {
			String value = daniX.get(k);
			x += value;
		}
		return x;
	}

}
