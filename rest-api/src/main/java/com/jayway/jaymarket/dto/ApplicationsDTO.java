package com.jayway.jaymarket.dto;

import com.jayway.jaymarket.dto.ApplicationDTO;

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
public class ApplicationsDTO {
	private Map<String, ApplicationDTO> applications = new HashMap<String,ApplicationDTO>();

	public ApplicationsDTO() {
	}

	@XmlElement(name = "application", required = true)
	@XmlElementWrapper(name = "applications")
	public Collection<ApplicationDTO> getApplications() {
		return applications.values();
	}

	public void add(ApplicationDTO app) {
		applications.put(app.getId(), app);
	}

    public ApplicationDTO get(String appId) {
        return applications.get(appId);
    }
}
