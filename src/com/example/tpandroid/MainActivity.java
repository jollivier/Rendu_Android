package com.example.tpandroid;

import java.util.List;

import android.os.Bundle;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

	public static List<POI> pois;
	public static List<Bitmap> images;
	public static List<Bitmap> smallImages;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TabHost tabHost = getTabHost();

		Intent intentList = new Intent(this,Liste.class);
		Intent intentFavoris = new Intent(this,Favoris.class);
		Intent intentMap = new Intent(this,Map.class);

		TabSpec tabSpec = tabHost.newTabSpec("Liste").setIndicator("Liste").setContent(intentList);
		tabHost.addTab(tabSpec);
		tabSpec = tabHost.newTabSpec("Favoris").setIndicator("Favoris").setContent(intentFavoris);
		tabHost.addTab(tabSpec);
		tabSpec= tabHost.newTabSpec("Carte").setIndicator("Carte").setContent(intentMap);
		tabHost.addTab(tabSpec);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
