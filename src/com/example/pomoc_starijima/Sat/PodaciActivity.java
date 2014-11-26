package com.example.pomoc_starijima.Sat;

import com.example.pomoc_starijima.R;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class PodaciActivity extends Activity {

	Button btnPodaciRodj, btnPodaciLekar, btnPodaciSlave, btnPodaciPomen,
			btnNazad;

	private Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_1podaci);
		final Animation animDugme = AnimationUtils.loadAnimation(this,
				R.anim.anim_alpha);
		initialize();

		btnPodaciRodj.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				v.startAnimation(animDugme);
				mHandler.postDelayed(new Runnable() {

					@Override
					public void run() {
						Intent i1 = new Intent(
								"com.example.pomoc_starijima.podaciRodj");
						startActivity(i1);
					}
				}, 260);
			}
		});

		btnPodaciLekar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				v.startAnimation(animDugme);
				mHandler.postDelayed(new Runnable() {

					@Override
					public void run() {
						Intent i1 = new Intent(
								"com.example.pomoc_starijima.podaciLekar");
						startActivity(i1);
					}
				}, 260);
			}
		});

		btnNazad.setOnClickListener(new OnClickListener() {

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

	public void initialize() {
		btnPodaciRodj = (Button) findViewById(R.id.btnPodaciRodjendani);
		btnPodaciLekar = (Button) findViewById(R.id.btnPodaciLekar);
		btnPodaciSlave = (Button) findViewById(R.id.btnPodaciSlave);
		btnPodaciPomen = (Button) findViewById(R.id.btnPodaciPomeni);
		btnNazad = (Button) findViewById(R.id.btnPodaciNazad);

	}

}
