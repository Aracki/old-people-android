package com.example.pomoc_starijima.Sat;

import com.example.pomoc_starijima.R;

import baze.SQLiteTVprogrami;
import android.app.Activity;
import android.os.Bundle;
//import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PodaciTVprogramActivity extends Activity {

	SQLiteTVprogrami db;
	ListView listaPrograma;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_1podaci_tv);
		db = new SQLiteTVprogrami(this);
		initialize();
	}

	public void initialize() {
		// TODO Auto-generated method stub
		listaPrograma = (ListView) findViewById(R.id.listaTVprograma);

		int brojPrograma = db.vratiBrojPrograma();

		String[] programi = new String[brojPrograma];

		if (brojPrograma > 0) {

			for (int i = 0; i < brojPrograma; i++) {
				programi[i] = db.vratiTVprogram(i + 1);
			}

			ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
					R.layout.list_item, R.id.title, programi);

			listaPrograma.setAdapter(adapter1);

		}

	}
}
