package com.jayway.jaymarket.app;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

import com.jayway.jaymarket.R;
import com.jayway.jaymarket.model.Application;
import com.jayway.jaymarket.model.ApplicationList;
import com.jayway.jaymarket.model.ApplicationRepository;

public class ApplicationListActivity extends ListActivity implements
		OnItemClickListener {

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
		getListView().setOnItemClickListener(this);
	}

	public void onItemClick(AdapterView<?> listView, View itemView,
			int position, long id) {
		Intent intent = new Intent(this, ApplicationDetailsActivity.class);
		intent.setData(Uri.parse("openApplication?appId=" + position));
		startActivity(intent);
	}

}
