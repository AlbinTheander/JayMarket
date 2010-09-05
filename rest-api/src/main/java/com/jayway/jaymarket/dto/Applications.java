package com.jayway.jaymarket.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * User: karinhofbauer
 * Date: Sep 5, 2010
 * Time: 3:21:24 PM
 */

@XmlRootElement(name = "jaymarket")
@XmlType(name = "", propOrder = {"applications"})
public class Applications {
    private List<Application> applications = new ArrayList<Application>();

    public Applications() {
    }

    public Applications(List<Application> applications) {
        this.applications = applications;
    }

    @XmlElement(name = "application", required = true)
    @XmlElementWrapper(name = "applications")
    public List<Application> getApplications() {
        return applications;
    }
}
