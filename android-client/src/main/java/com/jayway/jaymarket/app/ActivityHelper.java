package com.jayway.jaymarket.app;

import android.app.Activity;

import com.jayway.jaymarket.model.ApplicationRepository;

public class ActivityHelper {

	private final JayMarketApplication app;

	public ActivityHelper(Activity activity) {
		app = (JayMarketApplication) activity.getApplication();
	}

	public ApplicationRepository getApplicationRepository() {
		return app.getApplicationRepository();
	}

}
