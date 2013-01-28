package com.example.tpandroid;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

public class POI implements Parcelable {

	
	private Long id;
	private String nom;
	private String secteur;
	private double longitude;
	private double latitude;
	private String urlImage;
	private String urlSmallImage;
	private String quartier;
	private String informations;
	private String categorie;
	private boolean favoris;
	
	public POI() {
		
	}

	public POI(Parcel in){
		readFromParcel(in);
		
		
	}
	
	public void writeToParcel(Parcel dest, int flags) {
		 
		// We just need to write each field into the
		// parcel. When we read from parcel, they
		// will come back in the same order
		dest.writeLong(id);
		dest.writeString(nom);
		dest.writeString(secteur);
		dest.writeDouble(longitude);
		dest.writeDouble(latitude);
		dest.writeString(urlImage);
		dest.writeString(urlSmallImage);
		dest.writeString(quartier);
		dest.writeString(informations);
		dest.writeString(categorie);
	}
		
	private void readFromParcel(Parcel in) {
		 
		// We just need to read back each
		// field in the order that it was
		// written to the parcel
		id = in.readLong();
		nom = in.readString();
		secteur = in.readString();
		longitude = in.readDouble();
		latitude = in.readDouble();
		urlImage = in.readString();
		urlSmallImage = in.readString();
		quartier = in.readString();
		informations = in.readString();
		categorie=in.readString();
	}
	
	
	public static final Parcelable.Creator CREATOR =
	    	new Parcelable.Creator() {
	            public POI createFromParcel(Parcel in) {
	                return new POI(in);
	            }
	 
	            public POI[] newArray(int size) {
	                return new POI[size];
	            }
	        };
	        
	        
   	public static POI getPoifromID(long Id){
   		for(POI poi : MainActivity.pois){
   			if (poi.getId()==Id){
   				return poi;
   			}
   		}
   	return null;
   	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public String getUrlSmallImage() {
		return urlSmallImage;
	}

	public void setUrlSmallImage(String urlSmallImage) {
		this.urlSmallImage = urlSmallImage;
	}

	public String getQuartier() {
		return quartier;
	}

	public void setQuartier(String quartier) {
		this.quartier = quartier;
	}

	public String getInformations() {
		return informations;
	}

	public void setInformations(String informations) {
		this.informations = informations;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getSecteur() {
		return secteur;
	}

	public void setSecteur(String secteur) {
		this.secteur = secteur;
	}

	public void setFavoris(boolean favoris){
		this.favoris = favoris;
	}
	
	public boolean getFavoris(){
		return this.favoris;
	}
	@Override
	public String toString() {
		return "PointOfInterest [nom=" + nom + ", secteur=" + secteur + ", longitude=" + longitude + ", latitude="
				+ latitude + ", urlImage=" + urlImage + ", urlSmallImage=" + urlSmallImage + ", quartier=" + quartier
				+ ", informations=" + informations + ", categorie=" + categorie + "]";
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Bitmap getSmallImageBitmap(){
	Bitmap bm=null;
	URL url;
	try {
		url = new URL(urlSmallImage);
	    URLConnection conn;
			conn = url.openConnection();
	    conn.connect();
	    InputStream is = conn.getInputStream();
	    BufferedInputStream bis = new BufferedInputStream(is);
	    bm = BitmapFactory.decodeStream(bis);
	    bis.close();
	    is.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    return bm;
	}

	public Bitmap getImageBitmap(){
	Bitmap bm=null;
	URL url;
	try {
		url = new URL(urlImage);
	    URLConnection conn;
			conn = url.openConnection();
	    conn.connect();
	    InputStream is = conn.getInputStream();
	    BufferedInputStream bis = new BufferedInputStream(is);
	    bm = BitmapFactory.decodeStream(bis);
	    bis.close();
	    is.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    return bm;
	}

}
