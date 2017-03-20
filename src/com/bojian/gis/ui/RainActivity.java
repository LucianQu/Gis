//雨情查询界面
package com.bojian.gis.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.bojian.gis.R;
import com.bojian.gis.SyncService;
import com.bojian.gis.debug.L;
import com.bojian.gis.entity.Attribute;
import com.bojian.gis.entity.RainBean;
import com.bojian.gis.net.ExceptionInfo;
import com.bojian.gis.port.ImportRiver;
import com.bojian.gis.port.Rain;
import com.bojian.gis.port.RainAfterEight;
import com.bojian.gis.port.RainDetail;
import com.bojian.gis.port.RainWarn;
import com.bojian.gis.port.Reservior;
import com.bojian.gis.port.TotalRain;
import com.bojian.gis.port.Upload;
import com.bojian.gis.port.WaterWarn;
import com.bojian.gis.util.SharePreUtil;
import com.bojian.gis.util.UrlLib;

public class RainActivity extends BaseActivity implements OnClickListener,
		OnItemSelectedListener {
	private static final String TAG = "RainActivity";
	private Button backBtn;
	private Button refreshBtn;
	private TextView titleTv;
	private Button totalrainfallBtn;;
	private Button otherBtn;
	private SharePreUtil sp;
	private Upload mUpload;
	private Rain rc;
	private TextView textView[];
	private Spinner citySpinner;
	private int city;
	private String username;
	private String password;
	private BroadcastReceiver mBroadcastReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rain_condition);
		
		IntentFilter mIntentFilter = new IntentFilter("com.bojian.RainActivity");
		mBroadcastReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent data) {
				// TODO Auto-generated method stub
				L.info(TAG, "city =" + data.getIntExtra("city", 0)
						+ "&intent.getflag=" + data.getIntExtra("FLAG", 0));
				city=data.getIntExtra("city", 0);
				rc = new Rain(username, password, city);
				new SyncService(RainActivity.this, rc).execute();
			}
		};
		registerReceiver(mBroadcastReceiver, mIntentFilter);
		
		
		init();
		init_titlebar();
		//setListener();
		startLoad();
		startLoadMapData();
	}

	private void init() {
		// TODO Auto-generated method stub
		textView = new TextView[42];
		int ids[] = { R.id.rain_tab_1, R.id.rain_tab_2, R.id.rain_tab_3,
				R.id.rain_tab_4, R.id.rain_tab_5, R.id.rain_tab_6,
				R.id.rain_tab_7, R.id.rain_tab_8, R.id.rain_tab_9,
				R.id.rain_tab_10, R.id.rain_tab_11, R.id.rain_tab_12,
				R.id.rain_tab_13, R.id.rain_tab_14, R.id.rain_tab_15,
				R.id.rain_tab_16, R.id.rain_tab_17, R.id.rain_tab_18,
				R.id.rain_tab_19, R.id.rain_tab_20, R.id.rain_tab_21,
				R.id.rain_tab_22, R.id.rain_tab_23, R.id.rain_tab_24,
				R.id.rain_tab_25, R.id.rain_tab_26, R.id.rain_tab_27,
				R.id.rain_tab_28, R.id.rain_tab_29, R.id.rain_tab_30,
				R.id.rain_tab_31, R.id.rain_tab_32, R.id.rain_tab_33,
				R.id.rain_tab_34, R.id.rain_tab_35, R.id.rain_tab_36,
				R.id.rain_tab_37, R.id.rain_tab_38, R.id.rain_tab_39,
				R.id.rain_tab_40, R.id.rain_tab_41, R.id.rain_tab_42 };

		for (int i = 0; i < textView.length; i++) {
			textView[i] = (TextView) findViewById(ids[i]);
			textView[i].setTag(makeTags().get(i));
			textView[i].setClickable(true);
			textView[i].setOnClickListener(this);

		}
	}

	private List<Attribute> makeTags() {
		// TODO Auto-generated method stub
		List<Attribute> list = new ArrayList<Attribute>();
		list.add(new Attribute("1", "1"));
		list.add(new Attribute("2", "1"));
		list.add(new Attribute("3", "1"));
		list.add(new Attribute("6", "1"));
		list.add(new Attribute("12", "1"));
		list.add(new Attribute("24", "1"));

		list.add(new Attribute("1", "2"));
		list.add(new Attribute("2", "2"));
		list.add(new Attribute("3", "2"));
		list.add(new Attribute("6", "2"));
		list.add(new Attribute("12", "2"));
		list.add(new Attribute("24", "2"));

		list.add(new Attribute("1", "3"));
		list.add(new Attribute("2", "3"));
		list.add(new Attribute("3", "3"));
		list.add(new Attribute("6", "3"));
		list.add(new Attribute("12", "3"));
		list.add(new Attribute("24", "3"));

		list.add(new Attribute("1", "4"));
		list.add(new Attribute("2", "4"));
		list.add(new Attribute("3", "4"));
		list.add(new Attribute("6", "4"));
		list.add(new Attribute("12", "4"));
		list.add(new Attribute("24", "4"));

		list.add(new Attribute("1", "5"));
		list.add(new Attribute("2", "5"));
		list.add(new Attribute("3", "5"));
		list.add(new Attribute("6", "5"));
		list.add(new Attribute("12", "5"));
		list.add(new Attribute("24", "5"));

		list.add(new Attribute("1", "6"));
		list.add(new Attribute("2", "6"));
		list.add(new Attribute("3", "6"));
		list.add(new Attribute("6", "6"));
		list.add(new Attribute("12", "6"));
		list.add(new Attribute("24", "6"));

		list.add(new Attribute("1", "7"));
		list.add(new Attribute("2", "7"));
		list.add(new Attribute("3", "7"));
		list.add(new Attribute("6", "7"));
		list.add(new Attribute("12", "7"));
		list.add(new Attribute("24", "7"));

		return list;
	}

	private void setListener() {
		// TODO Auto-generated method stub
		backBtn.setOnClickListener(this);
		totalrainfallBtn.setOnClickListener(this);
	}

	private void init_titlebar() {
		backBtn = (Button) findViewById(R.id.titlebar_back);
		refreshBtn = (Button)findViewById(R.id.refresh_btn);
		refreshBtn.setOnClickListener(this);
		//titleTv = (TextView) findViewById(R.id.titlebar_title);
		//titleTv.setVisibility(View.GONE);
		//otherBtn = (Button)findViewById(R.id.titlebar_other);
		//citySpinner = (Spinner) findViewById(R.id.titlebar_city);
		//totalrainfallBtn = (Button) findViewById(R.id.total_rainfall);
		//totalrainfallBtn.setVisibility(View.VISIBLE);
		//otherBtn.setVisibility(View.GONE);
		//titleTv.setText("雨情查询");
//		if (UrlLib.isAdmin) {
//			citySpinner.setVisibility(View.VISIBLE);
//			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//					android.R.layout.simple_spinner_item, UrlLib.cityName);
//			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//			citySpinner.setAdapter(adapter);
//			citySpinner.setSelection(0,true);
//			citySpinner.setOnItemSelectedListener(this);
//		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == backBtn) {
			finish();
		} else if(v==refreshBtn){
			new SyncService(RainActivity.this, new Rain(username,password,city)).execute();
		}else if (v == totalrainfallBtn) {
			startIntent();
		} else {
			TextView t = (TextView) v;
			if (!t.getText().toString().equals("0")) {
				String hour = ((Attribute) t.getTag()).getHour();
				String duration = ((Attribute) t.getTag()).getDuration();
				Intent intent = new Intent(RainActivity.this, MActivity.class);
				if(UrlLib.isAdmin)
				intent.putExtra("city", city);
				intent.putExtra("hour", hour);
				intent.putExtra("duration", duration);
				intent.addFlags(UrlLib.RAIN_DETAIL);
				startActivity(intent);
			}

		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return super.onKeyDown(keyCode, event);
	}

	private void startLoad() {
		// TODO Auto-generated method stub
		sp = new SharePreUtil(RainActivity.this);
		username = sp.getStringValueByKey("username");
		password = sp.getStringValueByKey("password");
		
		if(UrlLib.isAdmin){
			city=0;
		}else{
			city = sp.getIntegerValueByKey("city");
		}
		rc = new Rain(username, password, city);
		L.info(TAG, "city11=="+city);
		SyncService ss = new SyncService(RainActivity.this, rc);
		ss.execute();
	}

	private void startLoadMapData()
	{
		
		mUpload = new RainAfterEight(username, password, city);
		
		SyncService ss = new SyncService(RainActivity.this, mUpload);
		//ss.mIsShowLoadDialog = true;
				
		
		ss.execute();
		
		
	}
	@Override
	public void onDataRequestSuccess() {
		// TODO Auto-generated method stub
		super.onDataRequestSuccess();
		new Thread(updateThread).run();
		
	}

	@Override
	public void onDataRequestFail(ExceptionInfo error) {
		// TODO Auto-generated method stub
		super.onDataRequestFail(error);
	}

	private void startIntent() {
		Intent intent = new Intent();
		if(UrlLib.isAdmin)
		intent.putExtra("city", city);
		intent.addFlags(10000);
		intent.setClass(RainActivity.this, TotalRainActivity.class);
		startActivity(intent);
	}

	Runnable updateThread = new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			updateHandler.sendEmptyMessage(0);
		}
	};

	private void reLoad() {
		rc = new Rain(username, password, city);
		SyncService ss = new SyncService(RainActivity.this, rc);
		ss.execute();
	}

	Handler updateHandler = new Handler() {
		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (rc.getList() != null && rc.getList().size() > 0) {
				List<RainBean> list = rc.getList();
				for (int i = 0; i < rc.getList().size(); i++) {
					L.info(TAG, "LIST=" + (RainBean) rc.getList().get(i));
				}
				textView[0].setText(list.get(0).getHour1count());
				textView[1].setText(list.get(0).getHour2count());
				textView[2].setText(list.get(0).getHour3count());
				textView[3].setText(list.get(0).getHour6count());
				textView[4].setText(list.get(0).getHour12count());
				textView[5].setText(list.get(0).getHour24count());

				textView[6].setText(list.get(1).getHour1count());
				textView[7].setText(list.get(1).getHour2count());
				textView[8].setText(list.get(1).getHour3count());
				textView[9].setText(list.get(1).getHour6count());
				textView[10].setText(list.get(1).getHour12count());
				textView[11].setText(list.get(1).getHour24count());

				textView[12].setText(list.get(2).getHour1count());
				textView[13].setText(list.get(2).getHour2count());
				textView[14].setText(list.get(2).getHour3count());
				textView[15].setText(list.get(2).getHour6count());
				textView[16].setText(list.get(2).getHour12count());
				textView[17].setText(list.get(2).getHour24count());

				textView[18].setText(list.get(3).getHour1count());
				textView[19].setText(list.get(3).getHour2count());
				textView[20].setText(list.get(3).getHour3count());
				textView[21].setText(list.get(3).getHour6count());
				textView[22].setText(list.get(3).getHour12count());
				textView[23].setText(list.get(3).getHour24count());

				textView[24].setText(list.get(4).getHour1count());
				textView[25].setText(list.get(4).getHour2count());
				textView[26].setText(list.get(4).getHour3count());
				textView[27].setText(list.get(4).getHour6count());
				textView[28].setText(list.get(4).getHour12count());
				textView[29].setText(list.get(4).getHour24count());

				textView[30].setText(list.get(5).getHour1count());
				textView[31].setText(list.get(5).getHour2count());
				textView[32].setText(list.get(5).getHour3count());
				textView[33].setText(list.get(5).getHour6count());
				textView[34].setText(list.get(5).getHour12count());
				textView[35].setText(list.get(5).getHour24count());

				textView[36].setText(list.get(6).getHour1count());
				textView[37].setText(list.get(6).getHour2count());
				textView[38].setText(list.get(6).getHour3count());
				textView[39].setText(list.get(6).getHour6count());
				textView[40].setText(list.get(6).getHour12count());
				textView[41].setText(list.get(6).getHour24count());
			}
		}
	};

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
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (mBroadcastReceiver != null) {
			unregisterReceiver(mBroadcastReceiver);
		}
	}
	
}
