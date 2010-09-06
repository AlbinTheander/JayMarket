package com.jayway.jaymarket.model;

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

public class RestApplicationRepository implements ApplicationRepository {

	private HttpClient httpClient;

	private String applicationListUrl;

	public RestApplicationRepository(String applicationListUrl) {
		super();
		this.applicationListUrl = applicationListUrl;
		httpClient = new DefaultHttpClient();
	}

	public ApplicationList getApplications() {
		HttpGet request = new HttpGet(applicationListUrl);
		try {
			return httpClient.execute(request,
					new ResponseToApplicationHandler());
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
