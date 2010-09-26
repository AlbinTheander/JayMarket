package com.jayway.apkinfo;

import static org.junit.Assert.*;

import org.junit.Test;

public class ApkInfoTest {

	@Test
	public void canCreateApkInfo() {
		assertNotNull(ApkInfo.getInfo(""));
	}

}
