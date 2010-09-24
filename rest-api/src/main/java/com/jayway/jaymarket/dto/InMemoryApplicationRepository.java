package com.jayway.jaymarket.dto;

public class InMemoryApplicationRepository implements ApplicationRepository {

	Applications applications = new Applications();

	public InMemoryApplicationRepository() {
		applications.add(new Application("Hello App"));
		applications.add(new Application("Nice to be here App"));
		applications.add(new Application("ByeBye  App"));
	}

	@Override
	public Applications getApplications() {
		return applications;
	}

}
