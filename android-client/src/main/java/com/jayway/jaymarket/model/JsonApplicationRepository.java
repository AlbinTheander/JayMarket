package com.jayway.jaymarket.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonApplicationRepository implements ApplicationRepository {
	private HttpClient httpClient;

	private String applicationListUrl;

	public JsonApplicationRepository(String applicationListUrl) {
		super();
		this.applicationListUrl = applicationListUrl;
		httpClient = new DefaultHttpClient();
	}

	public ApplicationList getApplications() {
		HttpGet request = new HttpGet(applicationListUrl);
		request.addHeader("Accept", "application/json");
		try {
			HttpResponse response = httpClient.execute(request);
			ApplicationList list = createApplicationList(response);
			return list;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new ApplicationList();
	}

	private ApplicationList createApplicationList(HttpResponse response)
			throws IOException, JSONException {
		InputStream in = response.getEntity().getContent();
		String json = readToString(in);
		System.out.println(json);
		JSONObject wrapper = new JSONObject(json);
		System.out.println(wrapper.toString(2));
		return createApplicationList(wrapper.getJSONObject("applications"));
	}

	private String readToString(InputStream in) throws IOException {
		InputStreamReader isr = new InputStreamReader(in, "utf-8");
		BufferedReader br = new BufferedReader(isr);
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line).append('\n');
		}
		return sb.toString();
	}

	private ApplicationList createApplicationList(JSONObject applications)
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
		return app;
	}
}
