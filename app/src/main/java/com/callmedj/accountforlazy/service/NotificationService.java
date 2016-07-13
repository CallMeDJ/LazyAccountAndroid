package com.callmedj.accountforlazy.service;

import com.callmedj.accountforlazy.counter.MainActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class NotificationService extends Service{

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate(){
		super.onCreate();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onStart(Intent intent , int startId){
		super.onStart(intent, startId);
		String msg = intent.getStringExtra("msg");
		Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
		Intent i = new Intent(this,MainActivity.class);
		PendingIntent pi = PendingIntent.getActivity(this,0, i,0);
		NotificationManager manager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
		manager.cancel(0);
		Notification notification = new Notification();
		notification.tickerText = msg;
		notification.defaults = Notification.DEFAULT_SOUND;
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		manager.notify(1, notification);
	}
}
