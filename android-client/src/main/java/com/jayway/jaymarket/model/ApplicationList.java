package com.jayway.jaymarket.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ApplicationList {

	@SerializedName("application")
	private List<Application> apps = new ArrayList<Application>();

	public void add(Application app) {
		apps.add(app);
	}

	public List<Application> getApps() {
		return apps;
	}
}
