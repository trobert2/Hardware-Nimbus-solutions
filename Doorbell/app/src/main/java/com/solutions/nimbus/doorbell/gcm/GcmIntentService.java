package com.solutions.nimbus.doorbell.gcm;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.solutions.nimbus.doorbell.Home;
import com.solutions.nimbus.doorbell.R;
import com.solutions.nimbus.doorbell.Response;
import com.solutions.nimbus.doorbell.util.DoorbellEntrySource;

import java.sql.SQLException;

public class GcmIntentService extends IntentService {

	public static final int NOTIFICATION_ID = 1;
	
	public GcmIntentService() {
        super("GcmIntentService");
    }
	
	@Override
	protected void onHandleIntent(Intent intent) {
		
		Bundle extras = intent.getExtras();
		
        if (!extras.isEmpty()) {
            String date = extras.getString("date");
            String message = extras.getString("message");
            DoorbellEntrySource doorbellEntrySource = new DoorbellEntrySource(getApplicationContext());
            try {
                doorbellEntrySource.createDoorbellEntry(date, message);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            sendNotification(extras.getString("message"));
        }

        GcmBroadcastReceiver.completeWakefulIntent(intent);
        
	}

    private void sendNotification(String msg) {
    	
    	NotificationManager mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, Response.class), 0);

        NotificationCompat.Builder mBuilder =
        		new NotificationCompat.Builder(this)
		        .setSmallIcon(R.drawable.ic_launcher)
		        .setContentTitle("Ring Ring")
		        .setContentText(msg);

        mBuilder.setContentIntent(contentIntent);
        mBuilder.setAutoCancel(true);
        
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}
