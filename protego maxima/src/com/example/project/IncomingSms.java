package com.example.project;



import java.util.List;
import java.util.Locale;

import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class IncomingSms extends BroadcastReceiver{

	boolean x=true;
String abcd="";
double latitude;
double longitude;
GPSTracker gps;
String numb;
String passw;

public static final String mypreference = "mypref";
public static final String Number = "numberKey";
public static final String Password = "passKey";


final SmsManager sms = SmsManager.getDefault();


@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		// Retrieves a map of extended data from the intent.
		final Bundle bundle = intent.getExtras();

		try {
			
			if (bundle != null) {
				
				final Object[] pdusObj = (Object[]) bundle.get("pdus");
				
				for (int i = 0; i < pdusObj.length; i++) {
					
					
					SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
					String phoneNumber = currentMessage.getDisplayOriginatingAddress();
					
			        String message = currentMessage.getDisplayMessageBody();
			        Log.i("SmsReceiver", "senderNum: "+ phoneNumber+ "; message: " + message);
			        Toast toast = Toast.makeText(context, "senderNum: "+phoneNumber + ", message: " + message, Toast.LENGTH_LONG);
			        toast.show();
					try{
						gps=new GPSTracker(context);
						if(gps.canGetLocation()){
							 latitude=gps.getLatitude();
							  longitude=gps.getLongitude();
							
						}else{
							gps.showSetting();
						}
						
						SharedPreferences spf=context.getSharedPreferences(mypreference,Context.MODE_PRIVATE);
						numb=spf.getString(Number, null);
						
						passw=spf.getString(Password, null);
if(phoneNumber.replaceAll("\\P{Print}","").trim().equals(numb) && message.replaceAll("\\P{Print}","").trim().equals(passw))
						{
						
							Geocoder geo=new Geocoder(context, Locale.ENGLISH);
							try{
								
								 List<Address> addresses = geo.getFromLocation(latitude, longitude, 1);
								 if(addresses != null) {
						               
					                 Address fetchedAddress = addresses.get(0);
					                 StringBuilder strAddress = new StringBuilder();
					               
					                 for(int j=0; j<fetchedAddress.getMaxAddressLineIndex(); j++) {
					                       strAddress.append(fetchedAddress.getAddressLine(j)).append("\n");
					                 }
					               
					                 
					             abcd="I am at: " +strAddress.toString();
					             Toast.makeText(context, abcd, Toast.LENGTH_LONG).show();
					    /*         Intent intent2=new Intent(context, MainActivity.class);
									PendingIntent pi1=PendingIntent.getActivity(context, 0, intent2, 0);
									
									SmsManager sms2=SmsManager.getDefault();
	sms2.sendTextMessage(numb, null, "Location:->\nLatitude: "+latitude+"\nLongitude:-> "+longitude+"\n"+abcd, pi1, null);  */
								 }
					              
					             else
					                 //tv.setText("No location found..!");
					            	 Toast.makeText(context, "No location found!", Toast.LENGTH_LONG).show();
					       
								
								
							}catch(Exception e) {
								// TODO: handle exception
							e.printStackTrace();
							//Toast.makeText(getApplicationContext(), "Could not get Address!!  :-(", Toast.LENGTH_LONG).show();
								
							}
							
							
							
							
					Intent intent1=new Intent(context, MainActivity.class);
					PendingIntent pi=PendingIntent.getActivity(context, 0, intent1, 0);
					
					SmsManager sms1=SmsManager.getDefault();
					sms1.sendTextMessage(numb, null, "Location:->\nLatitude: "+latitude+"\nLongitude:-> "+longitude+"\n"+abcd, pi, null);
					Toast.makeText(context, "Your Location sent to "+numb, Toast.LENGTH_LONG).show();
						}
					}catch (Exception e) {
						// TODO: handle exception
					e.printStackTrace();
					}
					
				} 
              } 
			
		} catch (Exception e) {
			Log.e("SmsReceiver", "Exception smsReceiver" +e);
			
		}
}


}
