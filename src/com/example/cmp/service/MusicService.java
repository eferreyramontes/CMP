package com.example.cmp.service;


import java.io.IOException;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.cmp.MPlayerWidget;
import com.example.cmp.R;
import com.example.cmp.datasource.MusicDataSource;


public class MusicService extends Service 
{
	private MediaPlayer player;
	
	@Override
	public void onCreate() {
		
		Log.d(this.getClass().getName(), "onCreate()");
		player = new MediaPlayer(); // TODO: Take a look to check if it works
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		try {
			if (intent.getAction() != null && intent.getAction().equals(MPlayerWidget.ACTION_PLAY)) 
			{
				this.playMusic();
			} 
			else if (intent.getAction() != null && intent.getAction().equals(MPlayerWidget.ACTION_STOP)) 
			{
				this.stopMusic();
			}
			else if (intent.getAction() != null && intent.getAction().equals(MPlayerWidget.ACTION_PAUSE))
			{
				this.pauseMusic();
			}
		} 
		catch (Exception e) 
		{ }
		return super.onStartCommand(intent, START_STICKY, 1);
	}	
	
	private void nextSong(){
	}
	
	private void pauseMusic() {
		Log.d("SERVICE", "PAUSE");
		player.pause();
	}

	@Override
	public void onDestroy() 
	{
		super.onDestroy();
		stopMusic();
		Log.d("SERVICE", "DESTROY");
	}
	
	public void playMusic() 
	{
		Log.d("SERVICE", "PLAY");
		
		try {
			
			if(!player.isPlaying()){
				player.reset();
				player.setDataSource(MusicDataSource.getDataSource());
				player.start();
				player.prepare();
				player.start();
			}
			else
			{
				player.pause();
			}
		} catch (IllegalArgumentException e) {
			Log.d("MusicService", "Illegal Argument Exception: " + e.getStackTrace());
		} catch (IllegalStateException e) {
			Log.d("MusicService", "Illegal State Exception: " + e.getStackTrace());
		} catch (IOException e) {
			Log.d("MusicService", "IO Exception: " + e.getStackTrace());
		}	
	}	
	
	public void stopMusic() 
	{	
		Log.d("SERVICE", "STOP");
		if(player!= null){
			player.stop();
			player.release();
			player = null;		
		}
	}

	@Override
	public IBinder onBind(Intent intent) 
	{	
		return null;
	}
	
}
