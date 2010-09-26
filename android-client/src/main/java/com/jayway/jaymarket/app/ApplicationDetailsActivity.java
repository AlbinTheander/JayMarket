package com.jayway.jaymarket.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jayway.jaymarket.R;
import com.jayway.jaymarket.model.Application;
import com.jayway.jaymarket.model.ApplicationRepository;
import com.jayway.jaymarket.util.ApkInstaller;

public class ApplicationDetailsActivity extends Activity {

	private ActivityHelper helper;
	private Application app;
	private int appId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.application_detail);
		helper = new ActivityHelper(this);
		ApplicationRepository repo = helper.getApplicationRepository();
		Intent intent = getIntent();
		appId = Integer.parseInt(intent.getData().getQueryParameter("appId"));
		app = repo.getApplications().getApps().get(appId);
		TextView titleTV = (TextView) findViewById(R.id.title);
		titleTV.setText(app.getName());
		Button installButton = (Button) findViewById(R.id.install_button);
		installButton.setOnClickListener(new InstallButtonListener());
	}

	private class InstallButtonListener implements OnClickListener {

		public void onClick(View v) {
			Toast toast = Toast.makeText(ApplicationDetailsActivity.this,
					"Will install " + app.getName(), Toast.LENGTH_LONG);
			toast.show();
			ApkInstaller.installApk(ApplicationDetailsActivity.this,
					app.getApkFileUrl());
		}

	}

}
