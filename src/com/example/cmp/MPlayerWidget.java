package com.example.cmp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MPlayerWidget extends AppWidgetProvider{

	public static final String ACTION_PLAY = "play";
	public static final String ACTION_STOP = "stop";
	public static final Object ACTION_PAUSE = "pause";
	
	public static String ACTION_WIDGET_RECEIVER1 = "ActionReceiverWidget1";
	public static String ACTION_WIDGET_RECEIVER2 = "ActionReceiverWidget2";
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

		// Create a RemoteViews object from layout
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.main);
		
		// For button #1
		Intent mIntent1 = new Intent(context, MPlayerWidget.class);
		mIntent1.setAction(ACTION_WIDGET_RECEIVER1);
		mIntent1.putExtra("msg", "Message for Button 1");

		PendingIntent mPendingIntent1 = PendingIntent.getBroadcast(context, 0, mIntent1, 0);
		remoteViews.setOnClickPendingIntent(R.id.button_one, mPendingIntent1);
				
		// For button #2
		Intent mIntent2 = new Intent(context, DummyActivity.class);
		mIntent2.setAction(ACTION_WIDGET_RECEIVER2);
		
		PendingIntent mPendingIntent2 = PendingIntent.getActivity(context, 0, mIntent2, 0);
		remoteViews.setOnClickPendingIntent(R.id.button_two, mPendingIntent2);

		// Update the widget
		appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
	}

	@Override
	public void onReceive(Context context, Intent intent) {

		// v1.5 fix that doesn't call onDelete Action
		final String action = intent.getAction();
		
		if (AppWidgetManager.ACTION_APPWIDGET_DELETED.equals(action)) 
		{
			final int appWidgetId = intent.getExtras().getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
			if (appWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID) 
			{
				this.onDeleted(context, new int[] { appWidgetId });
			}
			
		}
		
		if (intent.getAction().equals(ACTION_WIDGET_RECEIVER1)) 
		{
			String msg = "null";
			try 
			{
				msg = intent.getStringExtra("msg");
			} 
			catch (NullPointerException e) {
				Log.e("Error", "msg = null");
			}
			Toast.makeText(context, msg, Toast.LENGTH_LONG).show();

			PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, 0);
			NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
			Notification noty = new Notification(R.drawable.ic_launcher, "Button 1 clicked", System.currentTimeMillis());

			noty.setLatestEventInfo(context, "Notice", msg, contentIntent);
			notificationManager.notify(1, noty);

		} 

		super.onReceive(context, intent);

	}

}
