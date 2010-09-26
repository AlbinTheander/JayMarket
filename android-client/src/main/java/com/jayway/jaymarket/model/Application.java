package com.jayway.jaymarket.model;

public class Application {

	private String id;

	private String name;

	private String apkFileUrl;

	public Application() {
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setApkFileUrl(String apkFileUrl) {
		this.apkFileUrl = apkFileUrl;
	}

	public String getApkFileUrl() {
		return apkFileUrl;
	}

	@Override
	public String toString() {
		return name;
	}

}
