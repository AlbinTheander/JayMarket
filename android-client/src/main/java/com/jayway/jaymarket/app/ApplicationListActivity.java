package com.jayway.jaymarket.app;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

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
		try {
			appList = new ApplicationListParser().parse(null);
			setListAdapter(new ArrayAdapter<Application>(this,
					R.layout.list_item, appList.getApps()));
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
