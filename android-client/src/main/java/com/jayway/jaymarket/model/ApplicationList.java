package com.jayway.jaymarket.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ApplicationList {

	private List<Application> apps = new ArrayList<Application>();

	public void add(Application app) {
		apps.add(app);
	}

	public List<Application> getApps() {
		return apps;
	}
}
