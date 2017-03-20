package com.bojian.gis.ui;

import com.bojian.gis.net.ExceptionInfo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

public class BaseActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	public void onDataRequestSuccess() {
	}
	
	public void onDataRequestFail(ExceptionInfo error){}

	public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
		// TODO Auto-generated method stub
		
	}
}
