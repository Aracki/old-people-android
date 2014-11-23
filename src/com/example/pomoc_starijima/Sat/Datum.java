package com.example.pomoc_starijima.Sat;

import java.util.Calendar;

import android.R;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
//import android.support.v4.app.DialogFragment; // VAZNO!!!!!!!!!

import android.os.Bundle;
import android.widget.DatePicker;

@SuppressLint("ValidFragment")
public class Datum extends DialogFragment implements
		DatePickerDialog.OnDateSetListener {

	char x;

	public Datum(char x) {
		// TODO Auto-generated constructor stub
		this.x = x;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}

	public void onDateSet(DatePicker view, int year, int month, int day) {
		// Do something with the date chosen by the user
		// String datum = year + " " + month + " " + day;
		int m = month + 1;

		if (x == 'R') {
			RodjendanActivity.staviDatum.setText(day + "/" + m + "/" + year);
		} else if (x == 'P') {
			PreglediActivity.staviDatum.setText(day + "/" + m + "/" + year);
		} else if (x == 'S') {
			SlavaActivity.ispis.setText(day + "/" + m);
		} else if (x == 'O') {
			PomeniActivity.ispisDatum.setText(day + "/" + m + "/" + year);
		}

	}
}