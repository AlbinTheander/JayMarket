package com.jayway.apkinfo;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.BeforeClass;
import org.junit.Test;

public class ApkInfoTest {

	private static File apkFile;

	@BeforeClass
	public static void copyApkTestFileToTmp() throws IOException {
		InputStream in = ApkInfoTest.class.getResourceAsStream("/Hello1.apk");
		apkFile = File.createTempFile("Hello1", "apk");
		FileOutputStream out = new FileOutputStream(apkFile);
		byte[] buffer = new byte[14000];
		int count = 0;
		count = in.read(buffer);
		while (count >= 0) {
			out.write(buffer, 0, count);
			count = in.read(buffer);
		}
		in.close();
		out.close();
		apkFile.deleteOnExit();
		ApkInfo.setAaptPath("/Volumes/Shared/android/android-sdk-mac_86/platforms/android-4/tools/aapt");
	}

	@Test
	public void apkFileExists() {
		assertTrue(apkFile.exists());
	}

	@Test
	public void canCreateApkInfo() {
		assertNotNull(ApkInfo.getInfo(apkFile.getAbsolutePath()));
	}

}
