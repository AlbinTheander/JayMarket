package com.jayway.jaymarket.repository;

import com.jayway.jaymarket.model.Applications;
import com.jayway.jaymarket.model.Application;


public interface ApplicationRepository {

	Applications getApplications();

    Application getApplication (String appId);

}
