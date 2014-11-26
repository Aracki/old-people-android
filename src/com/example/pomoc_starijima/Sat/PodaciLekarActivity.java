package com.example.pomoc_starijima.Sat;

import java.util.ArrayList;
import java.util.List;

import baze.SQLitePregledi;

import com.example.pomoc_starijima.CustomAdapterLekar;
import com.example.pomoc_starijima.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class PodaciLekarActivity extends Activity {

	ListView listaPregleda;
	SQLitePregledi db;
	List<_Pregled> pregledi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_podaci_lekar);
		db = new SQLitePregledi(this);
		initialize();

		listaPregleda
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@SuppressLint("NewApi")
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						final int positionToRemove = position;

						// View viewToRemove = view;

						// Intent i1 = new
						// Intent("com.example.pomoc_starijima.RODJENDAN");
						// startActivity(i1);
						// TODO Auto-generated method stub
						AlertDialog.Builder builder1 = new AlertDialog.Builder(
								PodaciLekarActivity.this);
						builder1.setMessage("DA LI STE SIGURNI DA ŽELITE DA OBRIŠETE PREGLED ?");
						builder1.setCancelable(true);
						builder1.setPositiveButton("DA",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										int p1 = positionToRemove;
										_Pregled p = pregledi.get(p1);
										Log.d("PozitionToRemove",
												Integer.toString(p1));
										Log.d("Ime", p.getImeBolnice());
										// Log.d("id",
										// Integer.toString(r.getId()));
										db.obrisiPregled(p.getId());
										napuniPreglede();
										finish();

										// initialize();
									}
								});
						builder1.setNegativeButton("NE",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										dialog.cancel();
									}
								});

						builder1.show();

					}
				});
	}

	// private void inicijalizuj() {
	// listaRodjendana = (ListView) findViewById(R.id.listaRodjendana);
	//
	// int ukupanBroj = db.vratiBrojRodjendana();
	//
	// String[] poruke = new String[ukupanBroj];
	// if (ukupanBroj > 0) {
	//
	// for (int i = 0; i < ukupanBroj; i++) {
	// poruke[i] = db.vratiRodjendan(i + 1);
	// }
	//
	// ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
	// R.layout.list_row, R.id.title, poruke);
	//
	// listaRodjendana.setAdapter(adapter1);
	// } else {
	//
	// }
	//
	// }

	private void initialize() {
		listaPregleda = (ListView) findViewById(R.id.listaPregleda);
		napuniPreglede();

		if (pregledi.isEmpty()) {

		} else {
			CustomAdapterLekar ca = new CustomAdapterLekar(this,
					R.layout.list_item, R.id.title1, pregledi);

			// ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
			// R.layout.list_item, R.id.title, programi);

			listaPregleda.setAdapter(ca);
		}
	}

	private void napuniPreglede() {
		int size = db.vratiBrojPregleda();

		pregledi = new ArrayList<_Pregled>();

		for (int i = 0; i < size; i++) {
			String x = db.vratiPregled(i + 1);

			Log.d("Rodj: ", x);

			// treba isEmpty popraviti!!!
			if (!x.equals("")) {
				String[] ID_II_DD_VV = x.split(":::");

				String date = ID_II_DD_VV[2];
				String time = ID_II_DD_VV[3];
				String[] dateArray = date.split("/");
				String[] timeArray = time.split(" : ");

				int ss = Integer.parseInt(timeArray[0]);
				int min = Integer.parseInt(timeArray[1]);

				int dd = Integer.parseInt(dateArray[0]);
				int mm = Integer.parseInt(dateArray[1]);
				int yy = Integer.parseInt(dateArray[2]);

				_Pregled p = new _Pregled(Integer.parseInt(ID_II_DD_VV[0]),
						ID_II_DD_VV[1], dd, mm, yy, ss, min);
				;
				pregledi.add(p);
			}

		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

}