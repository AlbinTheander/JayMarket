package com.jayway.jaymarket.dto;

public class InMemoryApplicationRepository implements ApplicationRepository {

	Applications applications = new Applications();

	public InMemoryApplicationRepository() {
		applications.add(new ResourceApplication("Hello App", "/Hello1.apk"));
		applications.add(new ResourceApplication("Nice to be here App",
				"/Hello2.apk"));
		applications.add(new ResourceApplication("ByeBye  App", "/Hello3.apk"));
	}

	@Override
	public Applications getApplications() {
		return applications;
	}

}
