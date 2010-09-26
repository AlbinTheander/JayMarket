package com.jayway.jaymarket.model;

public class Application {

	private String name;

	public Application() {
        
    }


	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

}
