package com.jayway.jaymarket.app;

import android.app.Application;

import com.jayway.jaymarket.R;
import com.jayway.jaymarket.model.ApplicationRepository;
import com.jayway.jaymarket.model.json.JsonApplicationRepository;

public class JayMarketApplication extends Application {

	private ApplicationRepository repo;

	public ApplicationRepository getApplicationRepository() {
		if (repo == null) {
			repo = new JsonApplicationRepository(getString(R.string.base_url));
		}
		return repo;
	}

}
