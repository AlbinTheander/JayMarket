package com.jayway.jaymarket.model.json;

import static org.junit.Assert.*;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.jayway.jaymarket.model.Application;
import com.jayway.jaymarket.model.ApplicationList;

public class JsonApplicationParserTest {

	private static final String VALID_JSON = "{\n"
			+ "        \"application\": [\n"
			+ "            {\n"
			+ "                \"id\": \"2\",\n"
			+ "                \"name\": \"ByeBye  App\",\n"
			+ "                \"apkFileURL\": \"http://127.0.0.1:8080/jaymarket/applications/2/apk\"\n"
			+ "            },\n"
			+ "            {\n"
			+ "                \"id\": \"1\",\n"
			+ "                \"name\": \"Nice to be here App\",\n"
			+ "                \"apkFileURL\": \"http://127.0.0.1:8080/jaymarket/applications/1/apk\"\n"
			+ "            },\n"
			+ "            {\n"
			+ "                \"id\": \"0\",\n"
			+ "                \"name\": \"Hello App\",\n"
			+ "                \"apkFileURL\": \"http://127.0.0.1:8080/jaymarket/applications/0/apk\"\n"
			+ "            } \n" + "        ] \n" + "    }";

	@Test
	public void testCreateApplicationList() throws JSONException {
		JSONObject json = new JSONObject(VALID_JSON);
		JsonApplicationParser parser = new JsonApplicationParser();
		ApplicationList list = parser.createApplicationList(json);
		List<Application> apps = list.getApps();
		assertEquals(3, apps.size());
		assertEquals("2", apps.get(0).getId());
		assertEquals("ByeBye  App", apps.get(0).getName());
		assertEquals("http://127.0.0.1:8080/jaymarket/applications/2/apk", apps
				.get(0).getApkFileUrl());
	}

}
