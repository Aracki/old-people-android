package com.example.pomoc_starijima;

import java.util.List;

import com.example.pomoc_starijima.Sat._Rodjendan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<_Rodjendan> {

	public CustomAdapter(Context context, int resource, int textViewResourceId,
			List<_Rodjendan> rodjendani) {
		super(context, resource, textViewResourceId, rodjendani);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		View v = convertView;

		if (v == null) {
			LayoutInflater vi;
			vi = LayoutInflater.from(getContext());
			v = vi.inflate(R.layout.list_item, parent, false);
		}

		_Rodjendan r = getItem(position);

		if (r != null) {
			TextView tv1 = (TextView) v.findViewById(R.id.title1);

			if (tv1 != null) {
				tv1.setText(r.getIme() + " " + r.getPrezime() + " "
						+ r.getDay() + " " + r.getMonth());
			}
		}

		return v;

	}
}