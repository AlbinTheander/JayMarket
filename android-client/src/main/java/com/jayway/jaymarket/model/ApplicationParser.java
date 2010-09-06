package com.jayway.jaymarket.model;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ApplicationParser extends AbstractParser {

	private Application application;

	public Application parse(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		setPullParser(parser);
		application = new Application();
		while (!isAtEndTag("application")) {
			if (isAtStartTag("name")) {
				application.setName(getPullParser().nextText());
			} else {
				getPullParser().next();
			}
		}
		return application;
	}
}
