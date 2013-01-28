package com.example.tpandroid;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Detail extends Activity implements OnClickListener{
	Button bFavoris;
	Button bCarte;
	Button bYaller;
	POI poi;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
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
		ImageView image = (ImageView) findViewById(R.id.imageView1);
		image.setImageBitmap(poi.getImageBitmap());
		bFavoris = (Button) findViewById(R.id.bFavoris);
		bCarte = (Button) findViewById(R.id.bCarte);
		bYaller = (Button) findViewById(R.id.bYaller);
		bFavoris.setOnClickListener(this);
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
		if(v== bFavoris){
			SharedPreferences settings = getSharedPreferences("favoris", 0);
			if (settings.contains(poi.getNom())==false){ 
				poi.setFavoris(true);
			    SharedPreferences.Editor editor = settings.edit();
			    editor.putLong(poi.getNom(),poi.getId());
			    editor.commit();
			    Toast.makeText(this, "Lieu ajouté aux favoris", Toast.LENGTH_SHORT).show();
			}
			else{
				Toast.makeText(this,"Lieu déjà dans les favoris",Toast.LENGTH_SHORT).show();
			}
		}
		if(v== bCarte){
			Intent intent = new Intent(this,Map.class);
			intent.putExtra("poi", poi);
			startActivity(intent);
		}
		// TODO Auto-generated method stub
		
	}

}
