package com.example.pomoc_starijima;


import java.util.List;

import com.example.pomoc_starijima.Sat._Pomen;
import com.example.pomoc_starijima.Sat._Pregled;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapterPomeni extends ArrayAdapter<_Pomen> {

	public CustomAdapterPomeni(Context context, int resource,
			int textViewResourceId, List<_Pomen> pomeni) {
		super(context, resource, textViewResourceId, pomeni);
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

		_Pomen p = getItem(position);

		if (p != null) {
			TextView tv1 = (TextView) v.findViewById(R.id.title1);

			if (tv1 != null) {
				tv1.setText(p.getIme() + " " + p.getDay() + "/"
						+ p.getMonth()+ " "+p.getMesto());
			}
		}

		return v;

	}
}
