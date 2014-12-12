package com.example.pomoc_starijima.Sat;

import java.util.List;

import com.example.pomoc_starijima.CustomAdapterPomeni;
import com.example.pomoc_starijima.R;
import baze.SQLitePomeni;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
//import android.support.v7.app.ActionBarActivity;

import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class PodaciPomenActivity extends Activity {

	Button btnBack;
	ListView listaPomena;
	SQLitePomeni db;
	List<_Pomen> pomeni;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setIcon(R.drawable.slika44);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_1podaci_pomen);
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
										int rqs = 40000+p.getId();
										PomeniActivity.cancelAlarm(rqs, getBaseContext());
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
		
		btnBack.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
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
		btnBack = (Button) findViewById(R.id.btnBack);
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
			pomeni = db.vratiSvePomene();
	}
}
