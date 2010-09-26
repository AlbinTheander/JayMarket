package com.jayway.jaymarket.repository;

import com.jayway.jaymarket.model.Application;
import com.jayway.jaymarket.model.Applications;

/**
 * Created by IntelliJ IDEA.
 * User: karinhofbauer
 * Date: Sep 26, 2010
 * Time: 11:12:20 PM
 */

public class MongoApplicationRepository implements ApplicationRepository{

    @Override
    public Applications getApplications() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Application getApplication(String appId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean storeApplication(String name, String description, String filename) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
