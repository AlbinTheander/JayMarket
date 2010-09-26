package com.jayway.apkinfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ApkInfo {

	private static String aaptPath;

	public static void setAaptPath(String aaptPath) {
		ApkInfo.aaptPath = aaptPath;
	}

	public static String getAaptPath() {
		// TODO: Should make some guessing the location of aapth if the path is
		// null
		return aaptPath;
	}

	public static ApkInfo getInfo(String apkPath) {
		String aapt = ApkInfo.getAaptPath();
		try {
			Process p = Runtime.getRuntime().exec(
					new String[] { aapt, "dump", "badging", apkPath });
			BufferedReader aaptOutput = new BufferedReader(
					new InputStreamReader(p.getInputStream()));
			p.waitFor();
			String line;
			while ((line = aaptOutput.readLine()) != null) {
				System.out.println(line);
			}
			aaptOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new ApkInfo();
	}
}
