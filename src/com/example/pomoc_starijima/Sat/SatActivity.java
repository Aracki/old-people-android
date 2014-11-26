package com.example.pomoc_starijima.Sat;

import com.example.pomoc_starijima.R;
//import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class SatActivity extends Activity {

	Button lekar, rodjendani, slave, pomeni, nazad, sviPodaci;
	private Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_1sat);
		initialize();
		final Animation animDugme = AnimationUtils.loadAnimation(this,
				R.anim.anim_alpha);

		sviPodaci.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				v.startAnimation(animDugme);
				mHandler.postDelayed(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Intent openLista = new Intent(
								"com.example.pomoc_starijima.podaci");
						startActivity(openLista);
					}
				}, 260);
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

		rodjendani.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				v.startAnimation(animDugme);
				mHandler.postDelayed(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Intent openRodjendanActivity = new Intent(
								"com.example.pomoc_starijima.RODJENDAN");
						startActivity(openRodjendanActivity);
					}
				}, 260);

			}
		});

		// tv.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// v.startAnimation(animDugme);
		// mHandler.postDelayed(new Runnable() {

		// @Override
		// public void run() {
		// TODO Auto-generated method stub
		// Intent openTvProgramActivity = new Intent(
		// "com.example.pomoc_starijima.TVPROGRAM");
		// startActivity(openTvProgramActivity);
		// }
		// }, 260);

		// }
		// });

		lekar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				v.startAnimation(animDugme);
				mHandler.postDelayed(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Intent openPreglediActivity = new Intent(
								"com.example.pomoc_starijima.PREGLEDI");
						startActivity(openPreglediActivity);
					}
				}, 260);

			}
		});

		slave.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				v.startAnimation(animDugme);
				mHandler.postDelayed(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Intent openRodjendanActivity = new Intent(
								"com.example.pomoc_starijima.SLAVA");
						startActivity(openRodjendanActivity);
					}
				}, 260);

			}
		});

		pomeni.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				v.startAnimation(animDugme);
				mHandler.postDelayed(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Intent openLista = new Intent(
								"com.example.pomoc_starijima.POMENI");
						startActivity(openLista);
					}
				}, 260);
			}
		});

	}

	public void initialize() {

		lekar = (Button) findViewById(R.id.btnPregledi);
		rodjendani = (Button) findViewById(R.id.btnRodjendani);
		slave = (Button) findViewById(R.id.btnSlave);
		// tv = (Button) findViewById(R.id.btnTvProgram);
		pomeni = (Button) findViewById(R.id.btnPomeni);
		sviPodaci = (Button) findViewById(R.id.btnUneto);
		nazad = (Button) findViewById(R.id.btnNazad);

	}
}
