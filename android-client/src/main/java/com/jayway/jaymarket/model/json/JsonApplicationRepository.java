package com.jayway.jaymarket.model.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import com.jayway.jaymarket.model.ApplicationList;
import com.jayway.jaymarket.model.ApplicationRepository;

public class JsonApplicationRepository implements ApplicationRepository {
	private HttpClient httpClient;

	private String applicationListUrl;

	private ApplicationList list;

	public JsonApplicationRepository(String applicationListUrl) {
		super();
		this.applicationListUrl = applicationListUrl;
		httpClient = new DefaultHttpClient();
	}

	public ApplicationList getApplications() {
		if (list == null) {
			getApplicationsFromServer();
		}
		return list;
	}

	public ApplicationList getApplicationsFromServer() {
		HttpGet request = new HttpGet(applicationListUrl);
		request.addHeader("Accept", "application/json");
		try {
			HttpResponse response = httpClient.execute(request);
			list = createApplicationList(response);
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
		JsonApplicationParser parser = new JsonApplicationParser();
		return parser.createApplicationList(wrapper
				.getJSONObject("applications"));
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

}
