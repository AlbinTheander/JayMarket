package com.jayway.jaymarket.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * User: karinhofbauer Date: Sep 5, 2010 Time: 3:11:05 PM
 */

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "", propOrder = { "id", "name"})
public class Application {
	private String name;
    private String id;
	private String apkFilePath;

	public Application() {
	}

	public Application(String id, String name, String apkFileName) {
        this.id = id;
		this.name = name;
		this.apkFilePath = apkFileName;
	}

	@XmlElement(required = true)
	public String getName() {
		return name;
	}

	public String getApkFilePath() {
		return apkFilePath;
	}

	public String getApkFileName() {
		String[] pathParts = apkFilePath.split("/");
		return pathParts[pathParts.length - 1];
	}

	public InputStream getApkFile() {
		try {
			return new FileInputStream(apkFilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

    @Override
    public String toString() {
        return "Application{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", apkFilePath='" + apkFilePath + '\'' +
                '}';
    }

    @XmlElement(required = true)
    public String getId() {
        return id;
    }
}
