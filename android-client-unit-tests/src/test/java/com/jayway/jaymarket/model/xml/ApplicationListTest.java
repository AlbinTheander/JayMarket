package com.jayway.jaymarket.model.xml;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jayway.jaymarket.model.ApplicationList;

public class ApplicationListTest {

	@Test
	public void ApplicationListNeverReturnsNullList() {
		ApplicationList list = new ApplicationList();
		assertNotNull(list.getApps());
	}
}
