package com.example.project;



import java.util.List;
import java.util.Locale;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

public class GPSTracker extends Service implements LocationListener {
	private  Context mContext;
	
	boolean isGPSEnabled=false;
	boolean isNetworkEnabled=false;
	boolean canGetLocation=false;
	Location location;
	double latitude;
	double longitude;
	
	private static final long Min_Distance_Change_For_Update=10;
	private static final long Min_Time_Between_Update=1000*60*1;
	
	protected LocationManager locationManager;
	
	public GPSTracker(Context context){
	this.mContext=context;
	getLocation();
		
		
	}
	
	public Location getLocation(){
		try{
			locationManager=(LocationManager) mContext.getSystemService(LOCATION_SERVICE);
			isGPSEnabled=locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
			isNetworkEnabled=locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
			if(!isGPSEnabled && !isNetworkEnabled){
			Toast.makeText(getApplicationContext(), "Not Connected to Internet!", Toast.LENGTH_SHORT).show();	
				
			}else{
				this.canGetLocation=true;
				
				if(isNetworkEnabled){
locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, Min_Time_Between_Update,Min_Distance_Change_For_Update, this);
				Log.d("Network","Network");
				if(locationManager!=null){
					location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
					if(location!=null){
						latitude=location.getLatitude();
						longitude=location.getLongitude();
					}
	//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx			
				/*	Geocoder geo=new Geocoder(this, Locale.ENGLISH);
					try{
						
						 List<Address> addresses = geo.getFromLocation(latitude, longitude, 1);
						 if(addresses != null) {
				               
			                 Address fetchedAddress = addresses.get(0);
			                 StringBuilder strAddress = new StringBuilder();
			               
			                 for(int i=0; i<fetchedAddress.getMaxAddressLineIndex(); i++) {
			                       strAddress.append(fetchedAddress.getAddressLine(i)).append("\n");
			                 }
			               
			                 
			              abcd="I am at: " +strAddress.toString();
			             }
			              
			             else
			                 //tv.setText("No location found..!");
			            	 Toast.makeText(getApplicationContext(), "No location found!", Toast.LENGTH_LONG).show();
			       
						
						
					}catch(Exception e) {
						// TODO: handle exception
					e.printStackTrace();
					//Toast.makeText(getApplicationContext(), "Could not get Address!!  :-(", Toast.LENGTH_LONG).show();
						
					}		*/
	//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx			
				}
				
				}
				
				if(isGPSEnabled){
					if(location==null){
						locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, Min_Time_Between_Update, Min_Distance_Change_For_Update, this);
						Log.d("GPS Enabled", "GPS Enabled");
					if(locationManager!=null){
						location=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
						if(location!=null){
							latitude=location.getLatitude();
							longitude=location.getLongitude();
						}
					}
				}
				
				}
				
			}
		
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		return location;
	}
	
	public void stopGPS(){
		if(locationManager!=null){
		locationManager.removeUpdates(GPSTracker.this);	
		}
	}
	
public double getLatitude(){
	if(location!=null){
		latitude=location.getLatitude();
	}
	return latitude;
}
	
public double getLongitude(){
	if(location!=null){
		longitude=location.getLongitude();
	}
	return longitude;
}
	
public boolean canGetLocation(){
	return this.canGetLocation;
}
	
public void showSetting(){
	AlertDialog.Builder alert=new AlertDialog.Builder(mContext);
	alert.setTitle("GPS Settings");
	alert.setMessage("Please turn on the GPS.Do you want to go to settings menu?");
	alert.setPositiveButton("Location Settings", new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
		Intent intent=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		mContext.startActivity(intent);
		}
	});
	
	alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
		dialog.cancel();	
		}
	});
	
	alert.show();
}



	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}

