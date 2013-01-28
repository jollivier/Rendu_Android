package com.example.tpandroid;

import java.util.ArrayList;
import java.util.List;




import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Liste extends Activity implements OnItemClickListener, OnItemSelectedListener, TextWatcher{
	private List<POI>  pois;
	private ListeAdapter listeAdapter;
	private Spinner spinnerCategories;
	private List<POI> poisTrie = new ArrayList<POI>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
		setContentView(R.layout.activity_liste);
		pois = MainActivity.pois;
		poisTrie.addAll(pois);
		
		listeAdapter = new ListeAdapter(this,poisTrie);
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(listeAdapter);
		listView.setOnItemClickListener(this);
		
		EditText editText = (EditText) findViewById(R.id.editText1);
		editText.addTextChangedListener(this);
		
		spinnerCategories = (Spinner) findViewById(R.id.spinner1);
		spinnerCategories.setOnItemSelectedListener(this);



	}

	public void onResume(){
		super.onResume();
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(listeAdapter);
		listView.setOnItemClickListener(this);


        }
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		Intent detail = new Intent(this,Detail.class);
		POI poi = listeAdapter.getItem(position);
		detail.putExtra("poi", poi);
		startActivity(detail);
	
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
			poisTrie.clear();
			for (POI poi : pois){
				if(poi.getCategorie().contains(arg0.getItemAtPosition(arg2).toString())){
					poisTrie.add(poi);
				}
			}
			listeAdapter.notifyDataSetChanged();
		}


	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterTextChanged(Editable s) {
		poisTrie.clear();
		for (POI poi : pois) {
			if (poi.getNom().toLowerCase().contains(s.toString())){
				poisTrie.add(poi);
			}
		}
		listeAdapter.notifyDataSetChanged();		
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		
	}

}
