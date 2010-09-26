package com.jayway.jaymarket.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: karinhofbauer
 * Date: Sep 26, 2010
 * Time: 8:13:57 PM
 */

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "", propOrder = { "id", "name", "apkFileURL"})
public class ApplicationDTO {
    private String name;
    private String id;
	private String apkFileURL;

	public ApplicationDTO() {
	}

	public ApplicationDTO(String id, String name, String apkFileURL) {
        this.id = id;
		this.name = name;
		this.apkFileURL = apkFileURL;
	}

	@XmlElement(required = true)
	public String getName() {
		return name;
	}

    @XmlElement(required = true)
	public String getApkFileURL() {
		return apkFileURL;
	}    

    @XmlElement(required = true)
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Application{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", apkFileURL='" + apkFileURL + '\'' +
                '}';
    }

}
