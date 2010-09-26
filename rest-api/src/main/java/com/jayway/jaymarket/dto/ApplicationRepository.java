package com.jayway.jaymarket.dto;


public interface ApplicationRepository {

	Applications getApplications();

    Application getApplication (String appId);

}
