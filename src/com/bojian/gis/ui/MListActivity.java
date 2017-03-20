package com.bojian.gis.ui;

import java.util.ArrayList;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.bojian.gis.R;
import com.bojian.gis.SyncService;
import com.bojian.gis.adapter.ImportRiverAdapter;
import com.bojian.gis.adapter.RainDetailAdapter;
import com.bojian.gis.adapter.TotalRainAdapter;
import com.bojian.gis.adapter.WaterWarnAdapter;
import com.bojian.gis.debug.L;
import com.bojian.gis.entity.ImportRiverBean;
import com.bojian.gis.entity.RainDetailBean;
import com.bojian.gis.entity.TotalRainBean;
import com.bojian.gis.entity.WaterWarnBean;
import com.bojian.gis.net.ExceptionInfo;
import com.bojian.gis.port.ImportRiver;
import com.bojian.gis.port.RainDetail;
import com.bojian.gis.port.RainWarn;
import com.bojian.gis.port.Reservior;
import com.bojian.gis.port.TotalRain;
import com.bojian.gis.port.Upload;
import com.bojian.gis.port.WaterWarn;
import com.bojian.gis.util.SharePreUtil;
import com.bojian.gis.util.UrlLib;

public class MListActivity extends BaseActivity implements OnItemClickListener {
	private static final String TAG = "MListActivity";
	public static ListView mListView;
	private int city;
	private SharePreUtil sp;
	private Intent intent;
	private Intent mIntent;
	private Upload mUpload;
	private SyncService ss;
	private SyncService sNew;
	private String username;
	private String password;
	private BaseAdapter adapter;
	private BroadcastReceiver mBroadcastReceiver;
	private ArrayList<WaterWarnBean> list1;
	private ArrayList<WaterWarnBean> list2;
	private ArrayList<ImportRiverBean> list3;
	private ArrayList<ImportRiverBean> list4;
	private ArrayList<TotalRainBean> list5;
	private ArrayList<RainDetailBean> list6;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.water_condition_list);
		init();
		startLoad();

		IntentFilter mIntentFilter = new IntentFilter("com.bojian.redraw");
		mBroadcastReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent data) {
				// TODO Auto-generated method stub
				L.info(TAG, "city =" + data.getIntExtra("city", 0)
						+ "&intent.getflag=" + data.getIntExtra("FLAG", 0));
				startReload(data.getIntExtra("FLAG", 0),
						data.getIntExtra("city", 0));
			}
		};
		registerReceiver(mBroadcastReceiver, mIntentFilter);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	private void init() {
		// TODO Auto-generated method stub
		mListView = (ListView) findViewById(R.id.water_condition_listview);
		mListView.setAdapter(null);
		sp = new SharePreUtil(MListActivity.this);
		intent = getIntent();
		L.info(TAG, "intent.getflag11111111=" + intent.getFlags());
		username = sp.getStringValueByKey("username");
		password = sp.getStringValueByKey("password");
		if (UrlLib.isAdmin) {
			city = intent.getIntExtra("city", 0);
		} else {
			city = sp.getIntegerValueByKey("city");
		}
	}

	private void startLoad() {
		// TODO Auto-generated method stub
		switch (intent.getFlags()) {
		case UrlLib.RAIN_WARNING:
			mUpload = new RainWarn(username, password, city);
			break;
		case UrlLib.WATER_WARNING:
			mUpload = new WaterWarn(username, password, city);
			break;
		case UrlLib.IMPORT_RIVER:
			mUpload = new ImportRiver(username, password, city);
			mListView.setOnItemClickListener(this);
			break;
		case UrlLib.RESERVIOR:
			mUpload = new Reservior(username, password, city);
			mListView.setOnItemClickListener(this);
			break;
		case UrlLib.TOTAL_RAIN:
			String startdate = intent.getStringExtra("startdate");
			String enddate = intent.getStringExtra("enddate");
			mUpload = new TotalRain(username, password, city, startdate,
					enddate);
			break;
		case UrlLib.WATER_USING:								//用水量功能
			String startdate_w = intent.getStringExtra("startdate");
			String enddate_w = intent.getStringExtra("enddate");
			mUpload = new TotalRain(username, password, city, startdate_w,
					enddate_w);
			break;
		case UrlLib.RECHARGE:								    //充值记录信息
			String startdate_r = intent.getStringExtra("startdate");
			String enddate_r = intent.getStringExtra("enddate");
			mUpload = new TotalRain(username, password, city, startdate_r,
					enddate_r);
			break;
		case UrlLib.RAIN_DETAIL:
			String hour = intent.getStringExtra("hour");
			String duration = intent.getStringExtra("duration");
			mUpload = new RainDetail(username, password, city, hour, duration);
			mListView.setOnItemClickListener(this);
			break;
		default:
			break;
		}
		ss = new SyncService(MListActivity.this, mUpload);
		ss.isShowLoadDialog = true;
		ss.execute();
	}

	@Override
	public void onDataRequestSuccess() {
		// TODO Auto-generated method stub
		super.onDataRequestSuccess();
		L.info(TAG, "FLAG=" + intent.getFlags());
		switch (intent.getFlags()) {
		case UrlLib.RAIN_WARNING:
			if (list1 != null && adapter != null) {
				list1.removeAll(list1);
				adapter.notifyDataSetChanged();
			}
			list1 = ((RainWarn) mUpload).getList();
			if (list1 != null && list1.size() > 0) {
				list1 = ((RainWarn) mUpload).getList();
				adapter = new WaterWarnAdapter(MListActivity.this, list1);
				mListView.setAdapter(adapter);
				MActivity.list = list1;
			} else {
				MActivity.list = list1;
				showToast("无数据！");
			}
			break;
		case UrlLib.WATER_WARNING:
			if (list2 != null && adapter != null) {
				list2.removeAll(list2);
				adapter.notifyDataSetChanged();
			}
			list2 = ((WaterWarn) mUpload).getList();
			if (list2 != null && list2.size() > 0) {
				adapter = new WaterWarnAdapter(MListActivity.this, list2);
				mListView.setAdapter(adapter);
				MActivity.list = list2;
			} else {
				MActivity.list = list1;
				showToast("无数据！");
			}

			break;
		case UrlLib.IMPORT_RIVER:
			if (list3 != null && adapter != null) {
				list3.removeAll(list3);
				adapter.notifyDataSetChanged();
			}
		
			list3 = ((ImportRiver) mUpload).getList();
			if (list3 != null && list3.size() > 0) {
				adapter = new ImportRiverAdapter(MListActivity.this, list3);
				mListView.setAdapter(adapter);
				MActivity.list = list3;
			} else {
				MActivity.list = list1;
				showToast("无数据！");
			}

			break;
		case UrlLib.RESERVIOR:
			if (list4 != null && adapter != null) {
				list4.removeAll(list4);
				adapter.notifyDataSetChanged();
			}
			list4 = ((Reservior) mUpload).getList();
			if (list4 != null && list4.size() > 0) {
				adapter = new ImportRiverAdapter(MListActivity.this, list4);
				mListView.setAdapter(adapter);
				MActivity.list = list4;
			} else {
				MActivity.list = list1;
				showToast("无数据！");
			}
			break;
		case UrlLib.TOTAL_RAIN:
			if (list5 != null && adapter != null) {
				list5.removeAll(list5);
				adapter.notifyDataSetChanged();
			}
			list5 = ((TotalRain) mUpload).getList();
			if (list5 != null && list5.size() > 0) {
				adapter = new TotalRainAdapter(MListActivity.this, list5);
				mListView.setAdapter(adapter);
				MActivity.list = list5;
			} else {
				MActivity.list = list1;
				showToast("无数据！");
			}

			break;
		case UrlLib.RAIN_DETAIL:
			if (list6 != null && adapter != null) {
				list6.removeAll(list6);
				adapter.notifyDataSetChanged();
			}
			list6 = ((RainDetail) mUpload).getList();
			if (list6 != null && list6.size() > 0) {
				adapter = new RainDetailAdapter(MListActivity.this, list6);
				mListView.setAdapter(adapter);
				MActivity.list = list6;
			} else {
				MActivity.list = list1;
				showToast("无数据！");
			}
		default:
			break;
		}

	}

	private void showToast(String msg) {
		// TODO Auto-generated method stub
		Toast.makeText(MListActivity.this, msg, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onDataRequestFail(ExceptionInfo error) {
		// TODO Auto-generated method stub
		super.onDataRequestFail(error);
	}

	private void startReload(int id, int city) {
		// TODO Auto-generated method stub
		switch (id) {
		case UrlLib.RAIN_WARNING:
			mUpload = new RainWarn(username, password, city);
			break;
		case UrlLib.WATER_WARNING:
			mUpload = new WaterWarn(username, password, city);
			break;
		case UrlLib.IMPORT_RIVER:
			mUpload = new ImportRiver(username, password, city);
			break;
		case UrlLib.RESERVIOR:
			mUpload = new Reservior(username, password, city);
			break;
		case UrlLib.TOTAL_RAIN:
			String startdate = intent.getStringExtra("startdate");
			String enddate = intent.getStringExtra("enddate");
			mUpload = new TotalRain(username, password, city, startdate,
					enddate);
		case UrlLib.RAIN_DETAIL:
			String hour = intent.getStringExtra("hour");
			String duration = intent.getStringExtra("duration");
			mUpload = new RainDetail(username, password, city, hour, duration);
		default:
			break;
		}
		new SyncService(MListActivity.this, mUpload).execute();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (mBroadcastReceiver != null) {
			unregisterReceiver(mBroadcastReceiver);
		}
	}
	
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		switch (intent.getFlags()) {
		case UrlLib.RAIN_DETAIL:
			mIntent = new Intent();
			mIntent.addFlags(UrlLib.RAIN_DETAIL);
			L.info(TAG, "siteid="+list6.get(arg2));
			mIntent.putExtra("siteid", list6.get(arg2).getSiteid());
			mIntent.putExtra("sitename", list6.get(arg2).getSitename());
			mIntent.putExtra("hour", intent.getStringExtra("hour"));
			mIntent.putExtra("duration", intent.getStringExtra("duration"));
			mIntent.setClass(MListActivity.this, NListActivity.class);
			startActivity(mIntent);
			break;
		case UrlLib.IMPORT_RIVER:
			L.info(TAG, "siteid="+list3.get(arg2).getSiteid());
			mIntent = new Intent();
			mIntent.addFlags(UrlLib.IMPORT_RIVER);
			mIntent.putExtra("sitename", list3.get(arg2).getSitename());
			mIntent.putExtra("siteid", list3.get(arg2).getSiteid());
			mIntent.setClass(MListActivity.this, NListActivity.class);
			startActivity(mIntent);
			break;
		case UrlLib.RESERVIOR:
			L.info(TAG, "siteid="+list4.get(arg2));
			mIntent = new Intent();
			mIntent.addFlags(UrlLib.RESERVIOR);
			mIntent.putExtra("sitename", list4.get(arg2).getSitename());
			mIntent.putExtra("siteid", list4.get(arg2).getSiteid());
			mIntent.setClass(MListActivity.this, NListActivity.class);
			startActivity(mIntent);
			break;

		default:
			break;
		}

	}
}
