package com.jayway.jaymarket.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * User: karinhofbauer
 * Date: Sep 5, 2010
 * Time: 3:11:05 PM
 */

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "", propOrder = {"name"})
public class Application {
    private String name;

    @XmlElement(required = true)
    public String getName() {
        return name;
    }

    public Application(){}

    public Application(String name){
        this.name=name;    
    }

    @Override
    public String toString() {
        return "Application{" +
                "name='" + name + '\'' +
                '}';
    }
}
