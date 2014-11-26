package com.example.pomoc_starijima.Poruka;

import java.util.LinkedList;

import com.example.pomoc_starijima.R;
import baze.SQLitePrimeriPoruka;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class PorukaActivity extends Activity {

	Button back;
	ListView lista;

	SQLitePrimeriPoruka db;
	private Handler mHandler = new Handler();
	int brojPoruka = 5;

	LinkedList<Intent> listaIntentova = new LinkedList<Intent>();

	public void napuniListuIntentova() {
		for (int i = 1; i <= brojPoruka; i++) {
			Intent i1 = new Intent("com.example.pomoc_starijima.PORUKAX");
			Bundle b1 = new Bundle();
			b1.putInt("id", i);
			i1.putExtras(b1);
			listaIntentova.add(i1);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Slanje hitnih poruka");
		getActionBar().setIcon(R.drawable.slika11);
		napuniListuIntentova();
		setContentView(R.layout.activity_2poruka);
		db = new SQLitePrimeriPoruka(this);

		initialize();
		final Animation animDugme = AnimationUtils.loadAnimation(this,
				R.anim.anim_alpha);

		back.setOnClickListener(new View.OnClickListener() {

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

		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				startActivity(listaIntentova.get(position));
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
		back = (Button) findViewById(R.id.btnNazadPoruka);
		lista = (ListView) findViewById(R.id.listaPoruka);

		if (db.vratiBrojPrimera() == 0) {
			db.dodajPrimer("Hitno me pozovi!");
			db.dodajPrimer("Kupi mi tri kesice fervexa");
			db.dodajPrimer("Spremila sam pihtije pozuri!");
			db.dodajPrimer("Kupi mi kurir!");
			db.dodajPrimer("Svaka cast vucicu, digli nam penzije");
			;
		}

		// int duzina = db.vratiBrojPrimera();
		String[] poruke = new String[] { db.vratiPrimer(1), db.vratiPrimer(2),
				db.vratiPrimer(3), db.vratiPrimer(4), db.vratiPrimer(5),

		};

		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
				R.layout.list_item, R.id.title1, poruke);

		lista.setAdapter(adapter1);
	}

}
