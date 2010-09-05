package com.jayway.jaymarket.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ApplicationListTest {

	@Test
	public void ApplicationListNeverReturnsNullList() {
		ApplicationList list = new ApplicationList();
		assertNotNull(list.getApps());
	}
}
