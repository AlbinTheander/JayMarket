package com.jayway.jaymarket.model.xml;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.jayway.jaymarket.model.Application;
import com.jayway.jaymarket.model.ApplicationList;
import com.jayway.jaymarket.model.ApplicationRepository;

public class XMLApplicationRepository implements ApplicationRepository {

	private HttpClient httpClient;

	private String applicationListUrl;

	private ApplicationList list;

	public XMLApplicationRepository(String applicationListUrl) {
		super();
		this.applicationListUrl = applicationListUrl;
		httpClient = new DefaultHttpClient();
	}

	public ApplicationList getApplications() {
		if (list == null) {
			list = getApplicationsFromServer();
		}
		return list;
	}

	public Application getApplication(String id) {
		for (Application app : getApplications().getApps()) {
			if (id.equals(app.getId()))
				return app;
		}
		return null;
	}

	private ApplicationList getApplicationsFromServer() {
		HttpGet request = new HttpGet(applicationListUrl);
		try {
			list = httpClient.execute(request,
					new ResponseToApplicationHandler());
			return list;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ApplicationList();
	}

	private class ResponseToApplicationHandler implements
			ResponseHandler<ApplicationList> {

		public ApplicationList handleResponse(HttpResponse response)
				throws ClientProtocolException, IOException {
			try {
				XmlPullParser parser = XmlPullParserFactory.newInstance()
						.newPullParser();
				parser.setInput(response.getEntity().getContent(), "UTF-8");
				ApplicationListParser listParser = new ApplicationListParser();
				return listParser.parse(parser);
			} catch (XmlPullParserException e) {
				throw new IOException(e);
			}
		}

	}
}
