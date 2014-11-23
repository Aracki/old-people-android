package com.example.pomoc_starijima.Sat;

import com.example.pomoc_starijima.R;
import com.example.pomoc_starijima.R.anim;
import com.example.pomoc_starijima.R.id;
import com.example.pomoc_starijima.R.layout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class PodaciActivity extends Activity {

	Button btnPodaciRodj;
	Button btnPodaciTV;
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
						Intent i1 = new Intent("com.example.pomoc_starijima.podaciRodj");
						startActivity(i1);
					}
				}, 260);
			}
		});

		btnPodaciTV.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				v.startAnimation(animDugme);
				mHandler.postDelayed(new Runnable() {

					@Override
					public void run() {
						Intent i1 = new Intent("com.example.pomoc_starijima.podaciTV");
						startActivity(i1);
					}
				}, 260);
			}
		});
	}

	public void initialize() {
		btnPodaciRodj = (Button) findViewById(R.id.btnPodaciRodjendani);
		btnPodaciTV = (Button) findViewById(R.id.btnPodaciTVprogram);
	}

}
