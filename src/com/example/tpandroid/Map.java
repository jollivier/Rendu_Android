package com.example.tpandroid;

import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Map extends MapActivity implements LocationListener{
	MapView mapView;
	MyLocationOverlay myLocation;
	MapController mapController;
	double longitude = 0.0;
	double latitude = 0.0;
	private List<Overlay> mapOverlays;
	private MyItemizedOverlay itemizedOverlay1;
	private List<OverlayItem> items = new ArrayList<OverlayItem>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		List<POI> pois = MainActivity.pois;
		for (int i=0; i<pois.size(); i++) {
			double latitude = ((POI)pois.get(i)).getLatitude()*1E6;
			double longitude = ((POI)pois.get(i)).getLongitude()*1E6;
			items.add(new OverlayItem(new GeoPoint((int)Math.round(latitude), (int)Math.round(longitude)), ((POI)pois.get(i)).getNom(), String.valueOf(i)));
		}
		
		mapView = (MapView) findViewById(R.id.mapview);
		
		setOverlay();
		
		mapView.setSatellite(true);
		mapView.setBuiltInZoomControls(true);
		
		mapController = mapView.getController();
		if (getIntent().getExtras()!=null){
			Bundle b = getIntent().getExtras();
			POI poi = b.getParcelable("poi");
			latitude =poi.getLatitude();
			longitude = poi.getLongitude();
		}
		myLocation = new MyLocationOverlay(getApplicationContext(), mapView);
		myLocation.enableMyLocation();
		if (latitude != 0.0 && longitude != 0.0){
			GeoPoint destination = new GeoPoint((int) (latitude*1000000),(int)(longitude*1000000));
			mapController.animateTo(destination);
			mapController.setZoom(20);
		}
		else{
			myLocation.runOnFirstFix(new Runnable(){
				@Override
				public void run() {
					mapController.animateTo(myLocation.getMyLocation());
					mapController.setZoom(17);
				}			
			});
		}
	}
	
	public void setOverlay() {
		mapOverlays = mapView.getOverlays();
		Drawable drawable1 = this.getResources().getDrawable(R.drawable.poipin);
		itemizedOverlay1 = new MyItemizedOverlay(drawable1, getApplicationContext());
		for (int i=0; i<items.size(); i++) {
			itemizedOverlay1.addOverlay(items.get(i));
		}
		mapOverlays.add(itemizedOverlay1);
		mapView.postInvalidate();
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		return true;
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub

		return true;
	}
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}
