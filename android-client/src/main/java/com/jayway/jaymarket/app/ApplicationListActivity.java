package com.jayway.jaymarket.app;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.jayway.jaymarket.R;
import com.jayway.jaymarket.model.Application;
import com.jayway.jaymarket.model.ApplicationList;
import com.jayway.jaymarket.model.RestApplicationRepository;

public class ApplicationListActivity extends ListActivity {

	private ApplicationList appList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.application_list);
		RestApplicationRepository repo = new RestApplicationRepository(
				getString(R.string.base_url));
		appList = repo.getApplications();
		setListAdapter(new ArrayAdapter<Application>(this, R.layout.list_item,
				appList.getApps()));
	}

}
