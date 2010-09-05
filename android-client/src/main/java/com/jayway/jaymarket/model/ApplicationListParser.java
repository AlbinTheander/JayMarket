package com.jayway.jaymarket.model;

import org.xmlpull.v1.XmlPullParser;

public class ApplicationListParser {

	private ApplicationList appList;
	private ApplicationParser applicationParser;

	public ApplicationList parse(XmlPullParser parser) {
		appList = new ApplicationList();
		appList.add(getApplicationParser().parse(parser));
		appList.add(getApplicationParser().parse(parser));
		appList.add(getApplicationParser().parse(parser));
		return appList;
	}

	private ApplicationParser getApplicationParser() {
		if (applicationParser == null) {
			applicationParser = new ApplicationParser();
		}
		return applicationParser;
	}
}
