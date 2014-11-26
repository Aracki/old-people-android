package com.example.pomoc_starijima;



import java.util.List;

import com.example.pomoc_starijima.Sat._Pregled;
import com.example.pomoc_starijima.Sat._Rodjendan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapterLekar extends ArrayAdapter<_Pregled> {

	public CustomAdapterLekar(Context context, int resource, int textViewResourceId,
			List<_Pregled> pregledi) {
		super(context, resource, textViewResourceId, pregledi);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		View v = convertView;

		if (v == null) {
			LayoutInflater vi;
			vi = LayoutInflater.from(getContext());
			v = vi.inflate(R.layout.list_item, null);
		}

		_Pregled p = getItem(position);
		
		

		if (p != null) {
			TextView tv1 = (TextView) v.findViewById(R.id.title1);

			if (tv1 != null) {
				tv1.setText(p.getImeBolnice()+ " " + p.getDay()+ "/" + p.getMonth());
			}
		}

		return v;

	}
}