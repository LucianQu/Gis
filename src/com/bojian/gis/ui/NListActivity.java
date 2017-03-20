package com.bojian.gis.ui;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bojian.gis.R;
import com.bojian.gis.SyncService;
import com.bojian.gis.adapter.ImportRiverAdapter;
import com.bojian.gis.adapter.RainDetailMxAdapter;
import com.bojian.gis.debug.L;
import com.bojian.gis.entity.ImportRiverBean;
import com.bojian.gis.entity.WaterWarnBean;
import com.bojian.gis.net.ExceptionInfo;
import com.bojian.gis.port.ImportRiverMx;
import com.bojian.gis.port.RainDetailMx;
import com.bojian.gis.port.ReserviorMx;
import com.bojian.gis.port.Upload;
import com.bojian.gis.util.SharePreUtil;
import com.bojian.gis.util.UrlLib;

public class NListActivity extends BaseActivity implements OnClickListener {
	private static final String TAG = "NListActivity";
	private Button backBtn;
	private TextView titleTv;
	private Button otherBtn;
	private ListView mListView;
	private Intent intent;
	private SyncService ss;
	private Upload mUpload;
	private SharePreUtil sp;
	private String username;
	private String password;
	private String siteid;
	private String hour;
	private String duration;
	private BaseAdapter adapter;
	private LayoutInflater mLayoutInflater;

	private ArrayList<WaterWarnBean> mList;
	private ArrayList<ImportRiverBean> nList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nlist);
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
//				WindowManager.LayoutParams.FLAG_FULLSCREEN); 
		init_titlebar();
		init_listview();
		startLoad();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	private void init_listview() {
		mListView = (ListView) findViewById(R.id.water_condition_listview);
		sp = new SharePreUtil(NListActivity.this);
		username = sp.getStringValueByKey("username");
		password = sp.getStringValueByKey("password");
		mLayoutInflater = (LayoutInflater) NListActivity.this
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		intent = getIntent();
	}

	private void init_titlebar() {
		backBtn = (Button) findViewById(R.id.titlebar_back);
		titleTv = (TextView) findViewById(R.id.titlebar_title);
		titleTv.setText("");
		otherBtn = (Button) findViewById(R.id.titlebar_other);
		backBtn.setOnClickListener(this);
		otherBtn.setVisibility(View.GONE);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == backBtn)
			finish();
	}

	private void startLoad() {
		L.info(TAG, "intent.getflag = "+intent.getFlags());
		if (intent.getFlags() == 0) {
			return;
		} else {
			switch (intent.getFlags()) {
			case UrlLib.RAIN_DETAIL:
				siteid = intent.getStringExtra("siteid");
				L.info(TAG, "siteid="+intent.getStringExtra("siteid"));
				hour = intent.getStringExtra("hour");
				duration = intent.getStringExtra("duration");
				if (siteid != null && !siteid.equals("") && hour != null
						&& !hour.equals("") && duration != null
						&& !duration.equals(null)) {
					mUpload = new RainDetailMx(siteid, hour, duration,
							username, password);
					ss = new SyncService(NListActivity.this, mUpload);
					ss.execute();
				}
				break;
			case UrlLib.IMPORT_RIVER:
				siteid = intent.getStringExtra("siteid");
				L.info(TAG, "siteid="+intent.getStringExtra("siteid"));
				if(siteid!=null&&!siteid.equals("")){
					mUpload = new ImportRiverMx(siteid, username, password);
					ss = new SyncService(NListActivity.this, mUpload);
					ss.execute();
				}
				break;
			case UrlLib.RESERVIOR:
				siteid = intent.getStringExtra("siteid");
				L.info(TAG, "siteid="+intent.getStringExtra("siteid"));
				if(siteid!=null&&!siteid.equals("")){
					mUpload = new ReserviorMx(siteid, username, password);
					ss = new SyncService(NListActivity.this, mUpload);
					ss.execute();
				}
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void onDataRequestSuccess() {
		// TODO Auto-generated method stub
		super.onDataRequestSuccess();
		switch (intent.getFlags()) {
		case UrlLib.RAIN_DETAIL:
			if (mList != null && adapter != null) {
				mList.removeAll(mList);
				adapter.notifyDataSetChanged();
			}
			mList = ((RainDetailMx)mUpload).getList();
			if (mList != null && mList.size() > 0) {
				adapter = new RainDetailMxAdapter(NListActivity.this, mList);
				View v = mLayoutInflater.inflate(R.layout.head_of_rain_detail_mx_list_adapter, null);
				mListView.addHeaderView(v);
				mListView.setAdapter(adapter);
			}else{
				showToast("数据为空！");
			}
			break;
		case UrlLib.IMPORT_RIVER:
			if (nList != null && adapter != null) {
				nList.removeAll(nList);
				adapter.notifyDataSetChanged();
			}
			nList = ((ImportRiverMx)mUpload).getList();
			if (nList != null && nList.size() > 0) {
				adapter = new ImportRiverAdapter(NListActivity.this, nList);
				View v = mLayoutInflater.inflate(R.layout.head_of_import_river, null);
				mListView.addHeaderView(v);
				mListView.setAdapter(adapter);
			}else{
				showToast("数据为空！");
			}
			break;
		case UrlLib.RESERVIOR:
			if (nList != null && adapter != null) {
				nList.removeAll(nList);
				adapter.notifyDataSetChanged();
			}
			nList = ((ReserviorMx)mUpload).getList();
			if (nList != null && nList.size() > 0) {
				adapter = new ImportRiverAdapter(NListActivity.this, nList);
				View v = mLayoutInflater.inflate(R.layout.head_of_import_river, null);
				mListView.addHeaderView(v);
				mListView.setAdapter(adapter);
			}else{
				showToast("数据为空！");
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void onDataRequestFail(ExceptionInfo error) {
		// TODO Auto-generated method stub
		super.onDataRequestFail(error);
		showToast("连接失败！");
	}

	
	private void showToast(String msg) {
		// TODO Auto-generated method stub
		Toast.makeText(NListActivity.this, msg, Toast.LENGTH_SHORT).show();
	}

}
