package com.example.pomoc_starijima;

import java.util.List;

import com.example.pomoc_starijima.Sat._Slava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapterSlave extends ArrayAdapter<_Slava> {

	public CustomAdapterSlave(Context context, int resource, int textViewResourceId,
			List<_Slava> slave) {
		super(context, resource, textViewResourceId, slave);
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

		_Slava s = getItem(position);
		
		

		if (s != null) {
			TextView tv1 = (TextView) v.findViewById(R.id.title1);

			if (tv1 != null) {
				tv1.setText(s.getImeSlave()+ " -- " + s.getDay()+ "/" + s.getMonth()+ " "+s.getKoSlavi());
			}
		}

		return v;

	}
}
