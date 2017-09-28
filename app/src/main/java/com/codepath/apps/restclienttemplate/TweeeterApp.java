package com.codepath.apps.restclienttemplate;

import android.app.Application;
import android.content.Context;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowManager;

/*
 * This is the Android application itself and is used to configure various settings
 * including the image cache in memory and on disk. This also adds a singleton
 * for accessing the relevant rest client.
 *
 *     TweeeterClient client = TweeeterApp.getRestClient();
 *     // use client to send requests to API
 *
 */
public class TweeeterApp extends Application {
	private static Context context;

	@Override
	public void onCreate() {
		super.onCreate();

		FlowManager.init(new FlowConfig.Builder(this).build());
		FlowLog.setMinimumLoggingLevel(FlowLog.Level.V);

		TweeeterApp.context = this;
	}

	public static TweeeterClient getRestClient() {
		return (TweeeterClient) TweeeterClient.getInstance(TweeeterClient.class, TweeeterApp.context);
	}
}