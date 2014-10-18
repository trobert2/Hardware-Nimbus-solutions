package com.solutions.nimbus.doorbell.gcm;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.solutions.nimbus.doorbell.Home;

public class AdventureGCMUtil {

	private static final String TAG = "doorbell-nimbus-666";
	private static final String SENDER_ID = "969132104245";
	private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
	private static final String PROPERTY_REG_ID = "registration_id";
	private static final String PROPERTY_APP_VERSION = "appVersion";
	
	private static GoogleCloudMessaging gcm;
	
	private static String regId;
	
	public static boolean checkPlayServices(Activity activity) {
		
	    int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);
	    
	    if (resultCode != ConnectionResult.SUCCESS) {
	    	
	        if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
	            GooglePlayServicesUtil.getErrorDialog(resultCode, activity, PLAY_SERVICES_RESOLUTION_REQUEST).show();
	        } else {
	            Log.i(TAG, "This device is not supported.");
	            activity.finish();
	        }
	        
	        return false;
	    }
	    
	    return true;
	}
	
	public static void registrateInGCM(Context context, Activity activity) {
		
		gcm = GoogleCloudMessaging.getInstance(activity);
        regId = getRegistrationId(context, activity);

        if (regId.isEmpty()) {
            registerInBackground(context, activity);
        }
        
	}

	private static String getRegistrationId(Context context, Activity activity) {
		
	    final SharedPreferences prefs = getGCMPreferences(context, activity);	    
	    String registrationId = prefs.getString(PROPERTY_REG_ID, "");
	    
	    if (registrationId.isEmpty()) {
	        Log.i(TAG, "Registration not found.");
	        return "";
	    }
	    
	    int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
	    int currentVersion = getAppVersion(context);
	    if (registeredVersion != currentVersion) {
	        Log.i(TAG, "App version changed.");
	        return "";
	    }
	    
	    return registrationId;
	}
	
	private static int getAppVersion(Context context) {
		
	    try {
	    	
	        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
	        return packageInfo.versionCode;
	        
	    } catch (NameNotFoundException e) {
	        throw new RuntimeException("Could not get package name: " + e);
	    }
	    
	}
	
	private static SharedPreferences getGCMPreferences(Context context, Activity activity) {
		
	    return activity.getSharedPreferences(Home.class.getSimpleName(), Context.MODE_PRIVATE);
	}
	
	private static void registerInBackground(Context context, Activity activity) {
		
		RegisterInBackground registerInBackground = new RegisterInBackground(context, activity);
		
		registerInBackground.execute(null, null, null);
	    
	}
	
	private static class RegisterInBackground extends AsyncTask<Void, Void, String> {
    	
    	private Context context;
    	private Activity activity;
    	
    	public RegisterInBackground(Context context, Activity activity) {
    		this.context = context;
    		this.activity = activity;
    	}
    	
        @Override
        protected String doInBackground(Void... params) {
            String msg = "";
            try {
                if (gcm == null) {
                    gcm = GoogleCloudMessaging.getInstance(context);
                }
                regId = gcm.register(SENDER_ID);
                msg = "Device registered, registration ID=" + regId;

                sendRegistrationIdToBackend();
                
                storeRegistrationId(context, regId, activity);
                
            } catch (IOException ex) {
                msg = "Error :" + ex.getMessage();
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String msg) {
        	Log.i(TAG, msg);
        }		
	}	
	
	private static void sendRegistrationIdToBackend() {

	}
	
	private static void storeRegistrationId(Context context, String newRegId, Activity activity) {
		
	    final SharedPreferences prefs = getGCMPreferences(context, activity);
	    int appVersion = getAppVersion(context);
	    
	    Log.i(TAG, "Saving regId on app version " + appVersion);
	    
	    SharedPreferences.Editor editor = prefs.edit();
	    editor.putString(PROPERTY_REG_ID, newRegId);
	    editor.putInt(PROPERTY_APP_VERSION, appVersion);
	    
	    regId = newRegId;
	    
	    editor.commit();
	    
	}

	public static String getRegId() {
		return regId;
	}
	
}
