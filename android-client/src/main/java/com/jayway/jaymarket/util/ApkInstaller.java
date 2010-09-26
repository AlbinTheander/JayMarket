package com.jayway.jaymarket.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class ApkInstaller {

	public static void installApk(Activity activity, String url) {
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
		activity.startActivity(intent);
	}

}
