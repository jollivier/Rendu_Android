package com.example.tpandroid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.widget.ProgressBar;


public class Loading extends Activity {

	
	private ProgressBar progressBar;
	private List<POI> liste;
	private List<Bitmap> listeImage;
	private List<Bitmap> listeSmallImage;
	private String URL = "http://cci.corellis.eu/pois.php";
	private boolean alreadyLoaded;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		new Read().execute();
	}

	@Override
	protected void onResume(){
		super.onResume();
		if (alreadyLoaded){
			finish();
		}
	}

	private class Read extends AsyncTask<Integer,Integer,Integer>{

		@Override
		protected Integer doInBackground(Integer... arg0) {
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(URL);
			try {
				HttpResponse response = httpClient.execute(httpGet);
				if (response != null) {
					String line = "";
					InputStream inputStream = response.getEntity().getContent();
					line = convertStreamToString(inputStream);
					try {
						JSONObject jsonObject = new JSONObject(line);
						JSONArray jsonArray = jsonObject.getJSONArray("results");
						progressBar.setMax(jsonArray.length());
						liste = new ArrayList<POI>();
						listeImage= new ArrayList<Bitmap>();
						listeSmallImage = new ArrayList<Bitmap>();
						for (int i = 0; i < jsonArray.length(); i++) {
							progressBar.setProgress(i);
							POI poi =newPOI(jsonArray.getJSONObject(i));
							//listeSmallImage.add(poi.getSmallImageBitmap());
							//listeImage.add(poi.getImageBitmap());
							liste.add(poi);
						
						}

					} catch (JSONException e) {
					}
				} else {
				}
			} catch (ClientProtocolException e) {
			} catch (IOException e) {
			}
// TODO Auto-generated method stub
			return 1;
		}
	

		@Override
	      protected void onPostExecute(Integer result) {
			MainActivity.pois = liste;
			MainActivity.images = listeImage;
			MainActivity.smallImages = listeSmallImage;
			alreadyLoaded = true;
			launchMain();

	      }


	
		
	}
	
	public void launchMain(){
		Intent main = new Intent(this, MainActivity.class);
		startActivity(main);
	}
	
    public String convertStreamToString(InputStream inputStream) {
		String ligne = "";
		StringBuilder total = new StringBuilder();
		BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
		try {
			while ((ligne = rd.readLine()) != null) {
				total.append(ligne);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return total.toString();
	}
    
    public POI newPOI(JSONObject jsonObject) throws JSONException{
		POI poi = new POI();
		poi.setId(jsonObject.getLong("id"));
		poi.setNom(jsonObject.getString("nom"));
		poi.setSecteur(jsonObject.getString("secteur"));
		poi.setCategorie(jsonObject.getString("categorie_id"));
		poi.setInformations(jsonObject.getString("informations"));
		poi.setLatitude(jsonObject.getDouble("lat"));
		poi.setLongitude(jsonObject.getDouble("lon"));
		poi.setQuartier(jsonObject.getString("quartier"));
		poi.setUrlImage(jsonObject.getString("image"));
		poi.setUrlSmallImage(jsonObject.getString("small_image"));
		return poi;
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_loading, menu);
		return true;
	}

}
