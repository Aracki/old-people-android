package com.example.pomoc_starijima.Sat;

import java.util.ArrayList;
import java.util.List;

import com.example.pomoc_starijima.CustomAdapter;
import com.example.pomoc_starijima.CustomAdapterPomeni;
import com.example.pomoc_starijima.R;
import com.example.pomoc_starijima.R.id;
import com.example.pomoc_starijima.R.layout;

import baze.SQLitePomeni;
import baze.SQLiteRodjendani;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
//import android.support.v7.app.ActionBarActivity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class PodaciPomenActivity extends Activity {

	ListView listaPomena;
	SQLitePomeni db;
	List<_Pomen> pomeni;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_podaci_pomen);
		db = new SQLitePomeni(this);
		initialize();

		listaPomena.setOnItemClickListener(new AdapterView.OnItemClickListener() {

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
								PodaciPomenActivity.this);
						builder1.setMessage("DA LI STE SIGURNI DA ŽELITE DA OBRIŠETE POMEN ?");
						builder1.setCancelable(true);
						builder1.setPositiveButton("DA",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										int p1 = positionToRemove;
										_Pomen p = pomeni.get(p1);
										Log.d("PozitionToRemove",
												Integer.toString(p1));
										Log.d("Ime", p.getIme());
										Log.d("id", Integer.toString(p.getId()));
										db.obrisiPomen(p.getId());
										napuniPomene();
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
		listaPomena = (ListView) findViewById(R.id.listaPomena);
		napuniPomene();

		if (pomeni.isEmpty()) {

		} else {
			CustomAdapterPomeni ca = new CustomAdapterPomeni(this, R.layout.list_item,
					R.id.title1, pomeni);

			// ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
			// R.layout.list_item, R.id.title, programi);

			listaPomena.setAdapter(ca);
		}
	}

	private void napuniPomene() {
		int size = db.vratiBrojRodjendana();

		pomeni = new ArrayList<_Pomen>();

		for (int i = 0; i < size; i++) {
			String x = db.vratiPomeni(i + 1);

			Log.d("Rodj: ", x);

			// treba isEmpty popraviti!!!
			if (!x.equals("")) {
				String[] ID_II_MM_DD = x.split(":::");
				

				String date = ID_II_MM_DD[3];
				String[] dateArray = date.split("/");

				int dd = Integer.parseInt(dateArray[0]);
				int mm = Integer.parseInt(dateArray[1]);
				int yy = Integer.parseInt(dateArray[2]);

				_Pomen p = new _Pomen(Integer.parseInt(ID_II_MM_DD[0]),
						ID_II_MM_DD[1], ID_II_MM_DD[2], dd, mm, yy);
				;
				pomeni.add(p);
			}

		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

}
