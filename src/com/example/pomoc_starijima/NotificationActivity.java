package com.example.pomoc_starijima;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;


public class NotificationActivity extends Activity {

	private TextView naslov, poruka;
	private Button potvrdi;
	private Handler mHandler = new Handler();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);
		final Animation animDugme = AnimationUtils.loadAnimation(this,
				R.anim.anim_alpha);
		initialize();
		
		potvrdi.setOnClickListener(new View.OnClickListener() {

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
		
	}

	private void initialize() {
		naslov = (TextView) findViewById(R.id.txtObavestenje);
		poruka = (TextView) findViewById(R.id.txtPorukaNot);
		potvrdi = (Button) findViewById(R.id.btnPotvrdi);
		
		
		Bundle extras = getIntent().getExtras();
		
		String porukaSadrzaj = extras.getString("Terapija");
		String porukaNaslov = extras.getString("Naslov");
		String id = extras.getString("IDAlarm");
		
		if (id.equals("1_Terapija")){
		poruka.setText(porukaNaslov+" \n\n"+porukaSadrzaj);
		}
		if(id.equals("2_Rodjendan")){
			String naslov = extras.getString("Naslov");
			String poruka1 = extras.getString("Poruka");
			String datum = extras.getString("Datum");
			String[] datumNiz = datum.split("/");
			Calendar calNow = Calendar.getInstance();
			
			int brojGodina = calNow.get(Calendar.YEAR) - Integer.parseInt(datumNiz[2]);
			
			poruka.setText(naslov+ "\n"+poruka1+"\n"+datum+" \n" +"Puni godina: "+brojGodina);
		}
		if(id.equals("3_Slava")){
			String naslov = extras.getString("Naslov");
			String poruka1 = extras.getString("Poruka");

//			String datum = extras.getString("Datum");

			poruka.setText(naslov+"\n"+poruka1);
		}
		
		if(id.equals("4_Lekar")){
			String naslov = extras.getString("Naslov");
			String poruka1 = extras.getString("Poruka");
			String datum = extras.getString("Datum");
			String vreme = extras.getString("Vreme");

			poruka.setText(naslov+"\n"+poruka1+"\n"+datum+"\n"+vreme);
		}


	}
}
