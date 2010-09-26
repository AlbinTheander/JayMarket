package com.jayway.jaymarket.model;

public class Application {

	private String id;

	private String name;

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

	@Override
	public String toString() {
		return name;
	}

}
