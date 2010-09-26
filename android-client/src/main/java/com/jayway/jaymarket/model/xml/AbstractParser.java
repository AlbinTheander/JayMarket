package com.jayway.jaymarket.model.xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AbstractParser {

	private XmlPullParser pullParser;

	protected boolean isAtStartTag(String tag) throws XmlPullParserException {
		return (pullParser.getEventType() == XmlPullParser.START_TAG)
				&& tag.equals(pullParser.getName());
	}

	protected boolean isAtEndTag(String tag) throws XmlPullParserException {
		return (pullParser.getEventType() == XmlPullParser.END_TAG)
				&& tag.equals(pullParser.getName());
	}

	public void setPullParser(XmlPullParser pullParser) {
		this.pullParser = pullParser;
	}

	public XmlPullParser getPullParser() {
		return pullParser;
	}

}
