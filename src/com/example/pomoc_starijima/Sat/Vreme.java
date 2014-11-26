package com.example.pomoc_starijima.Sat;

import java.util.Calendar;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
//import android.support.v4.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

@SuppressLint("ValidFragment")
public class Vreme extends DialogFragment implements
		TimePickerDialog.OnTimeSetListener {

	char x;

	public Vreme(char x) {
		this.x = x;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current time as the default values for the picker
		final Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);

		// Create a new instance of TimePickerDialog and return it
		return new TimePickerDialog(getActivity(), this, hour, minute,
				DateFormat.is24HourFormat(getActivity()));
	}

	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		// Do something with the time chosen by the user
		if (x == 'P') {
			PreglediActivity.staviVreme.setText(hourOfDay + " : " + minute);
		}
//		 else if (x == 'T') {
//			TvProgramActivity.staviVreme.setText(hourOfDay + " : " + minute);
//		}
	
	}
}