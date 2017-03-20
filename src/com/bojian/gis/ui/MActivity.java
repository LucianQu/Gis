package com.bojian.gis.ui;

import java.util.ArrayList;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

import com.bojian.gis.R;
import com.bojian.gis.SyncService;
import com.bojian.gis.debug.L;
import com.bojian.gis.port.ImportRiver;
import com.bojian.gis.util.UrlLib;

public class MActivity extends TabActivity implements OnClickListener,
		OnCheckedChangeListener, OnItemSelectedListener {
	private static final String TAG = "MActivity";
	private Button backBtn;
	private TextView titleTv;
	private Button otherBtn;
	private ImportRiver wc;
	private SyncService ss;
	private Intent mIntent1;
	private Intent mIntent2;
	private Intent intent;
	private TabHost mTabHost;
	private RadioGroup radioGroup;
	private RadioButton map;
	private RadioButton data;
	private LinearLayout titlebar_ll;
	private LinearLayout head_of_rain_warn_ll;
	private LinearLayout head_of_water_warn_ll;
	private LinearLayout head_of_rain_ll;
	private LinearLayout head_of_import_river_ll;
	private LinearLayout head_of_reservior_ll;
	private LinearLayout head_of_rain_detail_ll;
	private Spinner citySpinner;
	private int city;
	public static ArrayList<? extends Parcelable> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.water_condition);
		init_titlebar();
		init_tabs();
	}

	private void init_titlebar() {
		// TODO Auto-generated method stub
		intent = getIntent();
		titlebar_ll = (LinearLayout) findViewById(R.id.titlebar);
		head_of_rain_warn_ll = (LinearLayout) findViewById(R.id.head_of_rain_warn);
		head_of_water_warn_ll = (LinearLayout) findViewById(R.id.head_of_water_warn);
		head_of_rain_ll = (LinearLayout) findViewById(R.id.head_of_rain);
		head_of_import_river_ll = (LinearLayout) findViewById(R.id.head_of_import_river);
		head_of_reservior_ll = (LinearLayout) findViewById(R.id.head_of_reservior);
		head_of_rain_detail_ll = (LinearLayout) findViewById(R.id.head_of_rain_detail);

		if (intent != null && intent.getFlags() == UrlLib.RAIN_WARNING) {
			citySpinner = (Spinner) findViewById(R.id.titlebar_city);
			if (UrlLib.isAdmin) {
				citySpinner.setVisibility(View.VISIBLE);
				//选择下拉列表内容
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
						android.R.layout.simple_spinner_item, UrlLib.cityName);
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				
				citySpinner.setAdapter(adapter);
				citySpinner.setSelection(0, true);
				citySpinner.setOnItemSelectedListener(this);
			}
			backBtn = (Button) findViewById(R.id.titlebar_back);
			titleTv = (TextView) findViewById(R.id.titlebar_title);
			otherBtn = (Button) findViewById(R.id.titlebar_other);
			titleTv.setText(R.string.rain_condition_station_distribute);
			otherBtn.setVisibility(View.GONE);
			backBtn.setOnClickListener(this);
			head_of_rain_warn_ll.setVisibility(View.VISIBLE);

		} else if (intent != null && intent.getFlags() == UrlLib.WATER_WARNING) {
			citySpinner = (Spinner) findViewById(R.id.titlebar_city);
			if (UrlLib.isAdmin) {
				citySpinner.setVisibility(View.VISIBLE);
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
						android.R.layout.simple_spinner_item, UrlLib.cityName);
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				citySpinner.setAdapter(adapter);
				citySpinner.setSelection(0, true);
				citySpinner.setOnItemSelectedListener(this);
			}
			backBtn = (Button) findViewById(R.id.titlebar_back);
			titleTv = (TextView) findViewById(R.id.titlebar_title);
			otherBtn = (Button) findViewById(R.id.titlebar_other);
			titleTv.setText(R.string.water_condition_station_distribute);
			otherBtn.setVisibility(View.GONE);
			backBtn.setOnClickListener(this);
			head_of_water_warn_ll.setVisibility(View.VISIBLE);
			//重点河道
		} else if (intent != null && intent.getFlags() == UrlLib.IMPORT_RIVER) {
			citySpinner = (Spinner) findViewById(R.id.titlebar_city);
			if (UrlLib.isAdmin) {
				citySpinner.setVisibility(View.VISIBLE);
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
						android.R.layout.simple_spinner_item, UrlLib.cityName);
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				citySpinner.setAdapter(adapter);
				citySpinner.setSelection(0, true);
				citySpinner.setOnItemSelectedListener(this);
				citySpinner.setVisibility(View.INVISIBLE);
			}
			head_of_import_river_ll.setVisibility(View.VISIBLE);
			backBtn = (Button) findViewById(R.id.titlebar_back);
			titleTv = (TextView) findViewById(R.id.titlebar_title);
			otherBtn = (Button) findViewById(R.id.titlebar_other);
			titleTv.setText(R.string.import_river);
			otherBtn.setVisibility(View.GONE);
			backBtn.setOnClickListener(this);
		} else if (intent != null && intent.getFlags() == UrlLib.RESERVIOR) {
			citySpinner = (Spinner) findViewById(R.id.titlebar_city);
			if (UrlLib.isAdmin) {
				citySpinner.setVisibility(View.VISIBLE);
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
						android.R.layout.simple_spinner_item, UrlLib.cityName);
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				citySpinner.setAdapter(adapter);
				citySpinner.setSelection(0, true);
				citySpinner.setOnItemSelectedListener(this);
			}
			backBtn = (Button) findViewById(R.id.titlebar_back);
			titleTv = (TextView) findViewById(R.id.titlebar_title);
			otherBtn = (Button) findViewById(R.id.titlebar_other);
			titleTv.setText(R.string.reservior);
			otherBtn.setVisibility(View.GONE);
			backBtn.setOnClickListener(this);
			head_of_reservior_ll.setVisibility(View.VISIBLE);
		} else if (intent != null && intent.getFlags() == UrlLib.RAIN_DETAIL) {
			head_of_rain_detail_ll.setVisibility(View.VISIBLE);
			backBtn = (Button) findViewById(R.id.titlebar_back);
			titleTv = (TextView) findViewById(R.id.titlebar_title);
			otherBtn = (Button) findViewById(R.id.titlebar_other);
			titleTv.setText(R.string.rain_detail);
			otherBtn.setVisibility(View.GONE);
			backBtn.setOnClickListener(this);
		}
	}

	private void init_tabs() {
		// TODO Auto-generated method stub
		mTabHost = getTabHost();
		L.info(TAG, "mTabHost = " + mTabHost);
		mIntent1 = new Intent();
		mIntent1.addFlags(intent.getFlags());

		mIntent2 = new Intent();
		mIntent2.addFlags(intent.getFlags());

		L.info(TAG, "intent.getflag1111111111=" + intent.getFlags());

		if (intent.getFlags() == UrlLib.RAIN_WARNING && UrlLib.isAdmin) {
			L.info(TAG, "city=" + mIntent1.getIntExtra("city", 0));
			mIntent1.putExtra("city", intent.getIntExtra("city", 0));
			mIntent1.setClass(MActivity.this, MListActivity.class);
		} else if (intent.getFlags() == UrlLib.WATER_WARNING && UrlLib.isAdmin) {
			L.info(TAG, "city=" + mIntent1.getIntExtra("city", 0));
			mIntent1.putExtra("city", intent.getIntExtra("city", 0));
			mIntent1.setClass(MActivity.this, MListActivity.class);
		} else if (intent.getFlags() == UrlLib.RAIN_DETAIL) {
			mIntent1.putExtra("city", intent.getIntExtra("city", 0));
			mIntent1.putExtra("hour", intent.getStringExtra("hour"));
			mIntent1.putExtra("duration", intent.getStringExtra("duration"));
			mIntent1.setClass(MActivity.this, MListActivity.class);
		} else if (intent.getFlags() == UrlLib.RESERVIOR && UrlLib.isAdmin) {
			mIntent1.putExtra("city", 0);
			mIntent1.setClass(MActivity.this, MListActivity.class);
		} else if (intent.getFlags() == UrlLib.IMPORT_RIVER && UrlLib.isAdmin) {
			mIntent1.putExtra("city", 0);
			mIntent1.setClass(MActivity.this, MListActivity.class);
		}
		mIntent1.setClass(MActivity.this, MListActivity.class);
		mIntent2.setClass(MActivity.this, MMapActivity.class);
		mTabHost.addTab(buildTabSpec("tab1", R.string.data_list, this.mIntent1));
		mTabHost.addTab(buildTabSpec("tab2", R.string.map, this.mIntent2));

		((RadioButton) findViewById(R.id.content_radio_button0))
				.setOnCheckedChangeListener(this);
		((RadioButton) findViewById(R.id.content_radio_button1))
				.setOnCheckedChangeListener(this);
	}

	private TabHost.TabSpec buildTabSpec(String tag, int resLabel,
			final Intent content) {
		return mTabHost.newTabSpec(tag).setIndicator(getString(resLabel))
				.setContent(content);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == backBtn) {
			finish();
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		if (isChecked) {
			switch (buttonView.getId()) {
			case R.id.content_radio_button0:
				if (citySpinner != null && UrlLib.isAdmin)
					citySpinner.setVisibility(View.VISIBLE);
				if (intent != null && intent.getFlags() == UrlLib.RAIN_WARNING)
					head_of_rain_warn_ll.setVisibility(View.VISIBLE);
				if (intent != null && intent.getFlags() == UrlLib.WATER_WARNING)
					head_of_water_warn_ll.setVisibility(View.VISIBLE);
				if (intent != null && intent.getFlags() == UrlLib.IMPORT_RIVER)
					head_of_import_river_ll.setVisibility(View.VISIBLE);
				if (intent != null && intent.getFlags() == UrlLib.RESERVIOR)
					head_of_reservior_ll.setVisibility(View.VISIBLE);
				if (intent != null && intent.getFlags() == UrlLib.RAIN_DETAIL)
					head_of_rain_detail_ll.setVisibility(View.VISIBLE);
				this.mTabHost.setCurrentTabByTag("tab1");
				break;
			case R.id.content_radio_button1:
				if (citySpinner != null)
					citySpinner.setVisibility(View.INVISIBLE);
				if (intent != null && intent.getFlags() == UrlLib.RAIN_WARNING)
					head_of_rain_warn_ll.setVisibility(View.GONE);
				if (intent != null && intent.getFlags() == UrlLib.WATER_WARNING)
					head_of_water_warn_ll.setVisibility(View.GONE);
				if (intent != null && intent.getFlags() == UrlLib.IMPORT_RIVER)
					head_of_import_river_ll.setVisibility(View.GONE);
				if (intent != null && intent.getFlags() == UrlLib.RESERVIOR)
					head_of_reservior_ll.setVisibility(View.GONE);
				if (intent != null && intent.getFlags() == UrlLib.RAIN_DETAIL)
					head_of_rain_detail_ll.setVisibility(View.GONE);
				this.mTabHost.setCurrentTabByTag("tab2");
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		city = UrlLib.areaBeanList.get(position).getId();
		Intent intent_br = new Intent("com.bojian.redraw");
		L.info(TAG, "intent.getflag=" + intent.getFlags());
		intent_br.putExtra("FLAG", intent.getFlags());
		intent_br.putExtra("city", city);
		sendBroadcast(intent_br);
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}

}
