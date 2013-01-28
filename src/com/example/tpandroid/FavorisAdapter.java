package com.example.tpandroid;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FavorisAdapter extends BaseAdapter{
	List<POI> listePoi;
	LayoutInflater inflater;
	Button bsuppr;
	
	public FavorisAdapter(Context context, List<POI> objects){
		inflater = LayoutInflater.from(context);
		this.listePoi = objects;
	}
	
	
	
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listePoi.size();
	}

	@Override
	public POI getItem(int arg0) {
		// TODO Auto-generated method stub
		return listePoi.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = inflater.inflate(R.layout.poi_item_fav,null);
		ImageView ivIcone = (ImageView) convertView.findViewById(R.id.ivIcone);
		TextView tvNom = (TextView) convertView.findViewById(R.id.tvNom);
		TextView tvQuartier = (TextView) convertView.findViewById(R.id.tvQuartier);
//		TextView tvSecteur = (TextView) convertView.findViewById(R.id.tvSecteur);
		POI poi = listePoi.get(position);
		tvNom.setText(poi.getNom());
		tvQuartier.setText(poi.getQuartier());

		return convertView;
	}




	
	
	
}
