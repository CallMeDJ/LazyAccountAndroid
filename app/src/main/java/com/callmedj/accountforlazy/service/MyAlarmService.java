package com.callmedj.accountforlazy.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class MyAlarmService extends Service {
	private Context context;

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
		new Thread(mission).start();
	}
	
	private Runnable mission = new Runnable(){
		@Override
		public void run() {
			showNotification();
		}
	};
	
	public void showNotification(){
		Intent intent = new Intent(this,NotificationService.class);
		intent.putExtra("msg", "�ü�����");
		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		PendingIntent pi = PendingIntent.getService(this, 0, intent, 0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		try {
			date = sdf.parse("2014-12-06 18:00:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,date.getTime(), 60*60*24*1000, pi);
	}
}
