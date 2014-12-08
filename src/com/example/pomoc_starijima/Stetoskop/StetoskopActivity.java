package com.example.pomoc_starijima.Stetoskop;

import com.example.pomoc_starijima.R;
import android.app.Activity;
//import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class StetoskopActivity extends Activity {

	Button ujutru;
	Button upodne;
	Button uvece;
	TextView prazan;
	Button nazad;

	private Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setIcon(R.drawable.slika33);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_3stetoskop);

		ujutru = (Button) findViewById(R.id.btnUjutru);
		upodne = (Button) findViewById(R.id.btnUpodne);
		uvece = (Button) findViewById(R.id.btnUvece);
		prazan = (TextView) findViewById(R.id.txtPrazan);
		nazad = (Button) findViewById(R.id.btnNazad);
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

		ujutru.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				v.startAnimation(animDugme);
				mHandler.postDelayed(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Intent openTerapijaActivity = new Intent(
								"com.example.pomoc_starijima.TERAPIJA1");
						Bundle b1 = new Bundle();
						b1.putInt("dan", 1);
						openTerapijaActivity.putExtras(b1);
						startActivity(openTerapijaActivity);
					}
				}, 260);
			}
		});

		upodne.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				v.startAnimation(animDugme);
				mHandler.postDelayed(new Runnable() {

					@Override
					public void run() {
						Intent openTerapijaActivity = new Intent(
								"com.example.pomoc_starijima.TERAPIJA1");
						Bundle b1 = new Bundle();
						b1.putInt("dan", 2);
						openTerapijaActivity.putExtras(b1);
						startActivity(openTerapijaActivity);
					}
				}, 260);
			}
		});

		uvece.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				v.startAnimation(animDugme);
				mHandler.postDelayed(new Runnable() {

					@Override
					public void run() {
						Intent openTerapijaActivity = new Intent(
								"com.example.pomoc_starijima.TERAPIJA1");
						Bundle b1 = new Bundle();
						b1.putInt("dan", 3);
						openTerapijaActivity.putExtras(b1);
						startActivity(openTerapijaActivity);
					}
				}, 260);
			}
		});

	}

}
