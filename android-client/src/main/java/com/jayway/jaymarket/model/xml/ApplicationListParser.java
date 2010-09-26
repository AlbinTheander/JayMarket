package com.jayway.jaymarket.model.xml;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.jayway.jaymarket.model.Application;
import com.jayway.jaymarket.model.ApplicationList;

public class ApplicationListParser extends AbstractParser {

	private ApplicationList appList;
	private ApplicationParser applicationParser;
	public ApplicationList parse(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		setPullParser(parser);
		appList = new ApplicationList();
		while (!isAtEndTag("applications")) {
			if (isAtStartTag("application")) {
				Application app = getApplicationParser().parse(getPullParser());
				appList.add(app);
			}
			getPullParser().next();
		}
		return appList;
	}

	private ApplicationParser getApplicationParser() {
		if (applicationParser == null) {
			applicationParser = new ApplicationParser();
		}
		return applicationParser;
	}
}
