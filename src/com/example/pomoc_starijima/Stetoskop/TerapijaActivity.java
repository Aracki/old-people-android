package com.example.pomoc_starijima.Stetoskop;

import java.util.Calendar;
import java.util.Date;

import com.example.pomoc_starijima.R;
import com.example.pomoc_starijima.R.anim;
import com.example.pomoc_starijima.R.id;
import com.example.pomoc_starijima.R.layout;

//import android.support.v7.app.ActionBarActivity;
//import android.support.v7.app.ActionBar;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class TerapijaActivity extends Activity {

	Button sacuvaj, nazad;
	EditText unos;
	TextView ujutru, spisak, aktivna;
	Spinner kada, koliko;
	CheckBox aktivna_ck;
	private Handler mHandler = new Handler();
	final static int RQS_1 = 1;
	int sat = 0;
	int minut = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_3terapija1);

		ujutru = (TextView) findViewById(R.id.txtNazivUjutru);
		spisak = (TextView) findViewById(R.id.txtSpisakLekova);
		unos = (EditText) findViewById(R.id.editSpisak);
		kada = (Spinner) findViewById(R.id.combo_kada);
		koliko = (Spinner) findViewById(R.id.combo_koliko);
		aktivna = (TextView) findViewById(R.id.terapijaAct);
		aktivna_ck = (CheckBox) findViewById(R.id.chbAktivna);
		sacuvaj = (Button) findViewById(R.id.btnSacuvajBroj);
		nazad = (Button) findViewById(R.id.btnNazadPodesavanja);
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

		sacuvaj.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				v.startAnimation(animDugme);
				mHandler.postDelayed(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						switch (kada.getSelectedItemPosition()) {
						case 1:
							sat = 6;
							minut = 0;
						case 2:
							sat = 6;
							minut = 30;
						case 3:
							sat = 7;
							minut = 0;
						case 4:
							sat = 7;
							minut = 30;
						case 5:
							sat = 8;
							minut = 0;
						case 6:
							sat = 8;
							minut = 30;
						case 7:
							sat = 9;
							minut = 0;
						case 8:
							sat = 9;
							minut = 30;
						case 9:
							sat = 10;
							minut = 0;
						case 10:
							sat = 10;
							minut = 30;
						case 11:
							sat = 11;
							minut = 00;
						//case 12:
							//sat = 11;
						//	minut = 30;
						case 12:
							sat = 11;
							minut = 30;

						}
						Toast t1 = Toast.makeText(getApplicationContext(),
								"TERAPIJA SAČUVANA",
								Toast.LENGTH_LONG);
						t1.setDuration(5500);
						t1.setGravity(Gravity.CENTER, 0, 0);
						t1.show();
						//if(aktivna_ck.isSelected())
						pokreniAlarm();
					}
				}, 260);

			}
		});

	}

	private void initialize() {

	}

	private void pokreniAlarm() {
		Calendar calNow = Calendar.getInstance();
		Calendar calSet = (Calendar) calNow.clone();

		//calSet.set(Calendar.DAY_OF_WEEK, Calendar.DAY_OF_WEEK); - ne treba, podesava sam
		calSet.set(Calendar.HOUR_OF_DAY, sat);
		calSet.set(Calendar.MINUTE, minut);
		calSet.set(Calendar.SECOND, 0);
		calSet.set(Calendar.MILLISECOND, 0);

		Intent intent = new Intent(getBaseContext(),
				AlarmReceiverTerapija.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(
				getBaseContext(), RQS_1, intent, 0);
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		//alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,    
			//	calSet.getTimeInMillis(), 1*60*60 * 1000, pendingIntent);
		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
				calSet.getTimeInMillis(), 24 * 60 *60 * 1000, pendingIntent); // Ponavljanje_na_24h
	}

}
