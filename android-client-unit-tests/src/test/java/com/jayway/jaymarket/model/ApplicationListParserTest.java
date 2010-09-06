package com.jayway.jaymarket.model;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class ApplicationListParserTest {

	private XmlPullParser pullParser;

	@Before
	public void createApplicationListPullParser() throws XmlPullParserException {
		InputStream applicationListXml = getClass().getResourceAsStream(
				"/ApplicationList.xml");
		pullParser = XmlPullParserFactory.newInstance().newPullParser();
		pullParser.setInput(applicationListXml, "UTF-8");
	}

	@Test
	public void correctNumberOfApplicationsIsParsed()
			throws XmlPullParserException, IOException {
		ApplicationListParser parser = new ApplicationListParser();
		ApplicationList list = parser.parse(pullParser);
		assertEquals(3, list.getApps().size());
	}

	@Test
	public void applicationTitlesAreCorrectParsed()
			throws XmlPullParserException, IOException {
		ApplicationListParser parser = new ApplicationListParser();
		ApplicationList list = parser.parse(pullParser);
		List<Application> apps = list.getApps();
		assertEquals("Hello App", apps.get(0).getName());
		assertEquals("ByeBye App", apps.get(2).getName());
	}
}
