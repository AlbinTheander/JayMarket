package com.jayway.jaymarket.model.json;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.jaymarket.model.Application;
import com.jayway.jaymarket.model.ApplicationList;

public class GsonTest {

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
	public void objectToJSon() {
		Gson gson = new Gson();
		TestClass test = new TestClass(5, "Albin");
		String json = gson.toJson(test);
		System.out.println(json);
		TestClass test2 = gson.fromJson(json, TestClass.class);
		assertEquals(5, test2.i);
		assertEquals("Albin", test2.name);
		assertEquals(3, test2.strings.size());
	}

	@Test
	public void appToJSon() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Application app = new Application();
		app.setId("AppId");
		app.setName("AppName");
		app.setApkFileUrl("ApkUrl");
		String json = gson.toJson(app);
		System.out.println(json);
	}

	@Test
	public void appsToJson() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		ApplicationList apps = new ApplicationList();
		Application app = new Application();
		app.setId("AppId");
		app.setName("AppName");
		app.setApkFileUrl("ApkUrl");
		apps.add(app);
		String json = gson.toJson(apps);
		System.out.println(json);
		ApplicationList apps2 = gson.fromJson(json, ApplicationList.class);
		System.out.println(apps2.getApps().get(0));

	}

	public static class TestClass {
		private int i;
		private String name;
		private List<String> strings;

		private TestClass() {
		}

		public TestClass(int i, String name) {
			this.i = i;
			this.name = name;
			strings = new ArrayList<String>();
			strings.add("Albin");
			strings.add("Niklas");
			strings.add("Theander");
		}
	}

}
