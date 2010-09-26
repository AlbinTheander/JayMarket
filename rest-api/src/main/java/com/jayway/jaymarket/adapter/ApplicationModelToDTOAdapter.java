package com.jayway.jaymarket.adapter;

import com.jayway.jaymarket.dto.ApplicationDTO;
import com.jayway.jaymarket.dto.ApplicationsDTO;
import com.jayway.jaymarket.model.Application;
import com.jayway.jaymarket.model.Applications;

/**
 * Created by IntelliJ IDEA. User: karinhofbauer Date: Sep 26, 2010 Time:
 * 8:37:33 PM
 */
public class ApplicationModelToDTOAdapter {
	public static ApplicationsDTO convertApplications(Applications applications,
			String baseURL) {
		ApplicationsDTO dto = new ApplicationsDTO();
		for (Application application : applications.getApplications()) {
			dto.add(convertApplication(application, baseURL));
		}
		return dto;
	}

	private static ApplicationDTO convertApplication(Application application,
			String baseURL) {
		String appId = application.getId();
		String name = application.getName();
		String downloadURL = createDownloadURL(appId, baseURL);
		return new ApplicationDTO(appId, name, downloadURL);
	
	}

	private static String createDownloadURL(String appId, String baseURL) {
		StringBuilder builder = new StringBuilder();
		builder.append(baseURL);
		builder.append("applications/");
		builder.append(appId);
		builder.append("/apk");
		return builder.toString();
	}
}
