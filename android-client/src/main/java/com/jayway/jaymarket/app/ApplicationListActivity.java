package com.jayway.jaymarket.app;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.jayway.jaymarket.R;
import com.jayway.jaymarket.model.Application;
import com.jayway.jaymarket.model.ApplicationList;
import com.jayway.jaymarket.model.ApplicationListParser;

public class ApplicationListActivity extends ListActivity {

	private ApplicationList appList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		appList = new ApplicationListParser().parse(null);
		setListAdapter(new ArrayAdapter<Application>(this, R.layout.list_item,
				appList.getApps()));
	}

}
