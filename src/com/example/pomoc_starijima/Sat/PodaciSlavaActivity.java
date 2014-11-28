package com.example.pomoc_starijima.Sat;

import java.util.ArrayList;
import java.util.List;

import baze.SQLitePregledi;
import baze.SQLiteSlave;


import com.example.pomoc_starijima.CustomAdapter;
import com.example.pomoc_starijima.CustomAdapterLekar;
import com.example.pomoc_starijima.CustomAdapterSlave;
import com.example.pomoc_starijima.R;
import com.example.pomoc_starijima.R.id;
import com.example.pomoc_starijima.R.layout;
import com.example.pomoc_starijima.R.menu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class PodaciSlavaActivity extends Activity {


	ListView listaSlava;
	SQLiteSlave db;
	List<_Slava> slave;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_podaci_slava);
		db = new SQLiteSlave(this);
		initialize();

		listaSlava.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@SuppressLint("NewApi")
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						final int positionToRemove = position;
						View viewToRemove = view;
						// Intent i1 = new
						// Intent("com.example.pomoc_starijima.RODJENDAN");
						// startActivity(i1);
						// TODO Auto-generated method stub
						AlertDialog.Builder builder1 = new AlertDialog.Builder(
								PodaciSlavaActivity.this);
						builder1.setMessage("DA LI STE SIGURNI DA ŽELITE DA OBRIŠETE PREGLED ?");
						builder1.setCancelable(true);
						builder1.setPositiveButton("DA",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										int p1 = positionToRemove;
										_Slava s = slave.get(p1);
										Log.d("PozitionToRemove",
												Integer.toString(p1));
										Log.d("Ime", s.getImeSlave());
//										Log.d("id", Integer.toString(r.getId()));
										db.obrisiSlavu(s.getId());
										napuniSlave();
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
		listaSlava = (ListView) findViewById(R.id.listaSlava);
		Log.d("Usao u napuniSlave", "");
		napuniSlave();
		Log.d("Izasao", "");

		if (slave.isEmpty()) {

		} else {
			CustomAdapterSlave ca = new CustomAdapterSlave(this, R.layout.list_item,
					R.id.title1, slave);

			// ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
			// R.layout.list_item, R.id.title, programi);

			listaSlava.setAdapter(ca);
		}
	}

	private void napuniSlave() {
		int size = db.vratiBrojSlava();

		slave = new ArrayList<_Slava>();

		for (int i = 0; i < size; i++) {
			String x = db.vratiSlavu(i + 1);


			// treba isEmpty popraviti!!!
			if (!x.equals("")) {
				String[] ID_II_DD_KK = x.split(":::");

				String date = ID_II_DD_KK[2];
			
				String[] dateArray = date.split("/");

				int dd = Integer.parseInt(dateArray[0]);
				int mm = Integer.parseInt(dateArray[1]);
				

				_Slava s = new _Slava(Integer.parseInt(ID_II_DD_KK[0]),
						ID_II_DD_KK[1], dd, mm, ID_II_DD_KK[3]);
				;
				slave.add(s);
			}

		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

}