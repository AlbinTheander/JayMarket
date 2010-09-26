package com.jayway.jaymarket.model.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jayway.jaymarket.model.Application;
import com.jayway.jaymarket.model.ApplicationList;

public class JsonApplicationParser {

	public ApplicationList createApplicationList(JSONObject applications)
			throws JSONException {
		ApplicationList appList = new ApplicationList();
		JSONArray allApps = applications.optJSONArray("application");
		if (allApps == null) {
			JSONObject onlyApp = applications.getJSONObject("application");
			Application app = parseApp(onlyApp);
			appList.add(app);
		} else {
			for (int i = 0; i < allApps.length(); i++) {
				JSONObject jsonApp = allApps.getJSONObject(i);
				Application app = parseApp(jsonApp);
				appList.add(app);
			}
		}
		return appList;
	}

	private Application parseApp(JSONObject jsonApp) {
		Application app = new Application();
		app.setName(jsonApp.optString("name", ""));
		app.setId(jsonApp.optString("id", ""));
		return app;
	}

}
