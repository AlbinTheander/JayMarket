package com.jayway.jaymarket.model;

public interface ApplicationRepository {

	ApplicationList getApplications();

	Application getApplication(String id);

}
