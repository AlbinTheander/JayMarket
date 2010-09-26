package com.jayway.jaymarket.model;

import com.jayway.jaymarket.model.Application;

import java.io.InputStream;

public class ResourceApplication extends Application {

	public ResourceApplication(String id, String name, String apkFileName) {
		super(id, name, apkFileName);
	}

	@Override
	public InputStream getApkFile() {
		// Get from Resource
		return getClass().getResourceAsStream(getApkFilePath());
	}

}
