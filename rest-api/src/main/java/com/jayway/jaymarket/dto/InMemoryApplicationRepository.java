package com.jayway.jaymarket.dto;

public class InMemoryApplicationRepository implements ApplicationRepository {

	Applications applications = new Applications();

	public InMemoryApplicationRepository() {
		applications.add(new ResourceApplication("0", "Hello App", "/Hello1.apk"));
		applications.add(new ResourceApplication("1","Nice to be here App",
				"/Hello2.apk"));
		applications.add(new ResourceApplication("2", "ByeBye  App", "/Hello3.apk"));
	}

	@Override
	public Applications getApplications() {
		return applications;
	}

    @Override
    public Application getApplication(String appId) {
        return applications.get(appId);
    }


}
