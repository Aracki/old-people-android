package com.example.pomoc_starijima.Stetoskop;

import com.example.pomoc_starijima.R;
import com.example.pomoc_starijima.R.anim;
import com.example.pomoc_starijima.R.id;
import com.example.pomoc_starijima.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class StetoskopActivity extends Activity {

	Button ujutru;
	Button upodne;
	Button uvece;
	Button opciono;
	Button nazad;

	private Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_3stetoskop);

		ujutru = (Button) findViewById(R.id.btnUjutru);
		upodne = (Button) findViewById(R.id.btnUpodne);
		uvece = (Button) findViewById(R.id.btnUvece);
		// opciono = (Button) findViewById(R.id.btnOpciono);
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
				Intent openTerapijaActivity = new Intent(
						"com.example.pomoc_starijima.TERAPIJA1");
				startActivity(openTerapijaActivity);
			}
		});

		// if (savedInstanceState == null) {
		// getSupportFragmentManager().beginTransaction()
		// .add(R.id.btnBackTerapije, new PlaceholderFragment()).commit();
		// }
	}

}
