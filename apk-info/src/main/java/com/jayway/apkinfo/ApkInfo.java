package com.jayway.apkinfo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ApkInfo {

	private static final String PACKAGE_NAME_REG_EX = "package: name='([^']*)'";
	private static final String VERSION_CODE_REG_EX = "versionCode='([^']*)'";
	private static final String VERSION_NAME_REG_EX = "versionName='([^']*)'";
	private static final String NAME_REG_EX = "application: label='([^']*)'";
	private static final String ICON_REG_EX = "icon='([^']*)'";
	private static String aaptPath;

	private String packageName;
	private String versionCode;
	private String versionName;
	private String name;
	private String iconPath;
	private String apkPath;

	public String getPackageName() {
		return packageName;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public String getVersionName() {
		return versionName;
	}

	public String getName() {
		return name;
	}

	public void extractIcon(String iconFileName) {
		try {
			ZipInputStream in = new ZipInputStream(new FileInputStream(apkPath));
			ZipEntry entry = in.getNextEntry();
			while (entry != null) {
				if (iconPath.equals(entry.getName())) {
					extractIcon(in, iconFileName);
					in.closeEntry();
					break;
				}
				in.closeEntry();
				entry = in.getNextEntry();
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void extractIcon(ZipInputStream in, String iconFileName)
			throws IOException {
		FileOutputStream out = new FileOutputStream(iconFileName);
		byte[] buffer = new byte[2048];
		int n;
		while ((n = in.read(buffer)) >= 0) {
			out.write(buffer, 0, n);
		}
		out.close();
	}

	public static void setAaptPath(String aaptPath) {
		ApkInfo.aaptPath = aaptPath;
	}

	public static String getAaptPath() {
		// TODO: Should make some guessing the location of aapth if the path is
		// null
		return aaptPath;
	}

	public static ApkInfo getInfo(String apkPath) {
		String response = getBadgingInfo(apkPath);
		ApkInfo apkInfo = new ApkInfo();
		apkInfo.packageName = getRegExMatch(response, PACKAGE_NAME_REG_EX);
		apkInfo.versionCode = getRegExMatch(response, VERSION_CODE_REG_EX);
		apkInfo.versionName = getRegExMatch(response, VERSION_NAME_REG_EX);
		apkInfo.name = getRegExMatch(response, NAME_REG_EX);
		apkInfo.iconPath = getRegExMatch(response, ICON_REG_EX);
		apkInfo.apkPath = apkPath;
		return apkInfo;
	}

	private static String getRegExMatch(String aaptResponse, String regEx) {
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(aaptResponse);
		matcher.find();
		return matcher.group(1);
	}

	private static String getBadgingInfo(String apkPath) {
		StringBuilder response = new StringBuilder();
		String aapt = ApkInfo.getAaptPath();
		try {
			Process p = Runtime.getRuntime().exec(
					new String[] { aapt, "dump", "badging", apkPath });
			BufferedReader aaptOutput = new BufferedReader(
					new InputStreamReader(p.getInputStream(), "utf-8"));
			String line;
			while ((line = aaptOutput.readLine()) != null) {
				response.append(line).append('\n');
			}
			aaptOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response.toString();
	}

}
