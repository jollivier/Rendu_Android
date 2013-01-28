package com.example.tpandroid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Favoris extends Activity implements OnItemClickListener{
	private List<POI> favoris = new ArrayList<POI>();
	private ListeAdapter listeAdapter;
	Map<String,Long> items;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favoris);
		SharedPreferences settings = getSharedPreferences("favoris", 0);
		items = (Map<String, Long>) settings.getAll();
		for(String key : items.keySet()){
			favoris.add(POI.getPoifromID(items.get(key)));

		}
		listeAdapter = new ListeAdapter(this,favoris);
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(listeAdapter);
		listView.setOnItemClickListener(this);
	}
	
	protected void onResume(){
		super.onResume();
		SharedPreferences settings = getSharedPreferences("favoris", 0);
		items = (Map<String, Long>) settings.getAll();
		for(String key : items.keySet()){
			if(favoris.contains(POI.getPoifromID(items.get(key)))==false){
			favoris.add(POI.getPoifromID(items.get(key)));
			}

		}

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_favoris, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		Intent detail = new Intent(this,DetailFav.class);
		POI poi = listeAdapter.getItem(position);
		detail.putExtra("poi", poi);
		startActivity(detail);
		
	}

}
