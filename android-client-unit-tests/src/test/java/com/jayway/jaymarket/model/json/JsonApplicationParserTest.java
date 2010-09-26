package com.jayway.jaymarket.model.json;

import static org.junit.Assert.*;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.jayway.jaymarket.model.Application;
import com.jayway.jaymarket.model.ApplicationList;

public class JsonApplicationParserTest {

	private static final String VALID_JSON = "{           																"
			+ "        \"application\": [													"
			+ "            {																"
			+ "                \"id\": \"2\",												"
			+ "                \"name\": \"ByeBye  App\"									"
			+ "            },																"
			+ "            {																"
			+ "                \"id\": \"1\",												"
			+ "                \"name\": \"Nice to be here App\"							"
			+ "            },																"
			+ "            {																"
			+ "                \"id\": \"0\",												"
			+ "                \"name\": \"Hello App\"									"
			+ "            }																" + "        ]																	"
			+ "}																			";

	@Test
	public void testCreateApplicationList() throws JSONException {
		JSONObject json = new JSONObject(VALID_JSON);
		JsonApplicationParser parser = new JsonApplicationParser();
		ApplicationList list = parser.createApplicationList(json);
		List<Application> apps = list.getApps();
		assertEquals(3, apps.size());
		assertEquals("2", apps.get(0).getId());
		assertEquals("ByeBye  App", apps.get(0).getName());
	}

}
