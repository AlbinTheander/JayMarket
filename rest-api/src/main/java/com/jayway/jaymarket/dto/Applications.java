package com.jayway.jaymarket.dto;

import java.util.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * User: karinhofbauer Date: Sep 5, 2010 Time: 3:21:24 PM
 */

@XmlRootElement(name = "jaymarket")
@XmlType(name = "", propOrder = { "applications" })
public class Applications {
	private Map<String, Application> applications = new HashMap<String,Application>();

	public Applications() {
	}

	@XmlElement(name = "application", required = true)
	@XmlElementWrapper(name = "applications")
	public Collection<Application> getApplications() {
		return applications.values();
	}

	public void add(Application app) {
		applications.put(app.getId(), app);
	}

    public Application get(String appId) {
        return applications.get(appId);
    }
}
