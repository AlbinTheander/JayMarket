package com.jayway.jaymarket.app;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.jayway.jaymarket.R;
import com.jayway.jaymarket.model.Application;
import com.jayway.jaymarket.model.ApplicationList;
import com.jayway.jaymarket.model.ApplicationRepository;

public class ApplicationListActivity extends ListActivity {

	private ApplicationList appList;
	private ActivityHelper helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.application_list);
		helper = new ActivityHelper(this);
		ApplicationRepository repo = helper.getApplicationRepository();
		appList = repo.getApplications();
		setListAdapter(new ArrayAdapter<Application>(this, R.layout.list_item,
				R.id.list_item_text, appList.getApps()));
	}

}
