package com.jayway.jaymarket.model;

import org.xmlpull.v1.XmlPullParser;

public class ApplicationParser {

	private Application application;

	public Application parse(XmlPullParser parser) {
		application = new Application();
		application.setName("App " + (int) (Math.random() * 1000));
		return application;
	}
}
