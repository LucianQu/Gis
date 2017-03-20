package com.bojian.gis.ui;

import com.bojian.gis.R;
import com.bojian.gis.SyncService;
import com.bojian.gis.debug.L;
import com.bojian.gis.port.Rain;
import com.bojian.gis.util.SharePreUtil;
import com.bojian.gis.util.UrlLib;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TabHost;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class RainTabActivity extends TabActivity implements OnClickListener,
OnCheckedChangeListener, OnItemSelectedListener {
	private static final String TAG = "RainTabActivity";
	private Button titlebar_back_btn;
	private Button total_rainfall_btn;
	
	private Button titlebar_other_btn;
	private Spinner titlebar_city;
	private RadioButton content_radio0_rbtn;
	private RadioButton content_radio1_rbtn;
	private TabHost mTabHost;
	private Intent mIntent1;
	private Intent mIntent2;
	private Intent intent;
	private Rain rc;
	private String username;
	private String password;
	private TextView titleTv;
	private int city;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rain_tab);
		init_titlebar();
	    init_tabs();
	    setListener();
	}
	
	private void init_titlebar()
	{
		titlebar_back_btn=(Button)findViewById(R.id.titlebar_back);
		titlebar_city=(Spinner)findViewById(R.id.titlebar_city);
		total_rainfall_btn=(Button)findViewById(R.id.total_rainfall);
		titlebar_other_btn=(Button)findViewById(R.id.titlebar_other);
		titleTv = (TextView) findViewById(R.id.titlebar_title);
		titleTv.setText("²âÕ¾Í³¼Æ");
	
		if (UrlLib.isAdmin) 
			titlebar_city.setVisibility(View.VISIBLE);
		//total_rainfall_btn.setVisibility(View.VISIBLE);
		titlebar_other_btn.setVisibility(View.GONE);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, UrlLib.cityName);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		titlebar_city.setAdapter(adapter);
		titlebar_city.setSelection(0,true);
		
		
		
		
	}
	private void init_tabs()
	{
		
		
		mTabHost = getTabHost();
		L.info(TAG, "mTabHost = " + mTabHost);
		mIntent1 = new Intent();
		mIntent1.addFlags(getIntent().getFlags());

		mIntent2 = new Intent();
		mIntent2.addFlags(getIntent().getFlags());
		int i=getIntent().getFlags();    
		L.info(TAG, "intent.getflag1111111111=" +i);
        
		
		mIntent1.setClass(RainTabActivity.this, RainActivity.class);
		mIntent2.setClass(RainTabActivity.this, MMapActivity.class);
		mTabHost.addTab(buildTabSpec("tab1", R.string.data_list, this.mIntent1));
		mTabHost.addTab(buildTabSpec("tab2", R.string.map, this.mIntent2));
		content_radio0_rbtn=(RadioButton)findViewById(R.id.content_radio_button0);
		content_radio1_rbtn=(RadioButton)findViewById(R.id.content_radio_button1);	
	}
	
	private TabHost.TabSpec buildTabSpec(String tag, int resLabel,
			final Intent content) {
		return mTabHost.newTabSpec(tag).setIndicator(getString(resLabel))
				.setContent(content);
	}

	

	private void setListener()
	{
		total_rainfall_btn.setOnClickListener(this);
		titlebar_back_btn.setOnClickListener(this);
		titlebar_city.setOnItemSelectedListener(this);
		content_radio0_rbtn.setOnCheckedChangeListener(this);
		content_radio1_rbtn.setOnCheckedChangeListener(this);
	}
	
	private void reLoad() {
		Intent intent_br = new Intent("com.bojian.RainActivity");
		intent_br.putExtra("city", city);
		sendBroadcast(intent_br);
	}
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		city = UrlLib.areaBeanList.get(position).getId();
		reLoad();
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		if (isChecked) {
			switch (buttonView.getId()) {
			case R.id.content_radio_button0:
				
				this.mTabHost.setCurrentTabByTag("tab1");
				if (UrlLib.isAdmin) 
					titlebar_city.setVisibility(View.VISIBLE);
				break;
			case R.id.content_radio_button1:
				
				this.mTabHost.setCurrentTabByTag("tab2");
				titlebar_city.setVisibility(View.INVISIBLE);
				break;
			default:
				break;
			}
		}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == titlebar_back_btn) {
			finish();
		}
		else if (v == total_rainfall_btn) 
		{
			Intent intent = new Intent();
			if(UrlLib.isAdmin)
			intent.putExtra("city", city);
			intent.addFlags(10000);
			intent.setClass(RainTabActivity.this, TotalRainActivity.class);
			startActivity(intent);
		}
		else if (v == titlebar_city) 
		{
			
		}
	}
}
