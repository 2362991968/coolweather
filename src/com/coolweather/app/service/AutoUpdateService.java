package com.coolweather.app.service;

import com.coolweather.app.receiver.AutoUpdateReceiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

public class AutoUpdateService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				updateWeather();
			}

		}).start();
		AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		int intervalTime = 8*60*60*1000;
		long triggerAtTime = SystemClock.elapsedRealtime() + intervalTime;
		PendingIntent pi = PendingIntent.getBroadcast(this, 0, new Intent(this, AutoUpdateReceiver.class),  0);
		manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
		return super.onStartCommand(intent, flags, startId);
	}

	/**
	 * 更新天气
	 */
	private void updateWeather() {
		// TODO Auto-generated method stub
		
	}
}
