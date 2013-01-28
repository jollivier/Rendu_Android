package com.example.tpandroid;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailFav extends Activity implements OnClickListener{

	Button bSupprFavoris;
	Button bCarte;
	Button bYaller;
	POI poi;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_fav);
		Bundle b = getIntent().getExtras();
		poi = b.getParcelable("poi");
		TextView tvNom = (TextView) findViewById(R.id.tvNom);
		tvNom.setText(poi.getNom());
		TextView tvQuartier = (TextView) findViewById(R.id.tvQuartier);
		tvQuartier.setText(poi.getQuartier());
		TextView tvSecteur = (TextView) findViewById(R.id.tvSecteur);
		tvSecteur.setText(poi.getSecteur());
		TextView tvInfos = (TextView) findViewById(R.id.tvInfos);
		tvInfos.setText(poi.getInformations().replaceAll("</br>", "\n"));
		bSupprFavoris = (Button) findViewById(R.id.bSupprFavoris);
		bCarte = (Button) findViewById(R.id.bCarte);
		bYaller = (Button) findViewById(R.id.bYaller);
		bSupprFavoris.setOnClickListener(this);
		bCarte.setOnClickListener(this);
		bYaller.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if(v== bSupprFavoris){
			SharedPreferences settings = getSharedPreferences("favoris", 0);
			if (settings.contains(poi.getNom())==false){ 
			    Toast.makeText(this, "Lieu déjà Supprimé", Toast.LENGTH_SHORT).show();
			}
			else{
				
				Toast.makeText(this,"Lieu supprimé des favoris",Toast.LENGTH_SHORT).show();
			    SharedPreferences.Editor editor = settings.edit();
			    editor.remove(poi.getNom());
			    editor.commit();
			    startActivity(new Intent(this,MainActivity.class));

			}
		}// TODO Auto-generated method stub
		if(v== bCarte){
			Intent intent = new Intent(this,Map.class);
			intent.putExtra("poi", poi);
			startActivity(intent);
		}
	}

}
