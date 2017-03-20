package com.bojian.gis.ui;

import java.util.List;

import android.R.integer;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bojian.gis.R;
import com.bojian.gis.SyncService;
import com.bojian.gis.adapter.ReChargeAdapter;
import com.bojian.gis.debug.L;
import com.bojian.gis.entity.ReChargeBean;
import com.bojian.gis.net.ExceptionInfo;
import com.bojian.gis.port.ReCharge;
import com.bojian.gis.util.SharePreUtil;
import com.bojian.gis.util.UrlLib;

public class RechargeActivity extends BaseActivity implements
		OnClickListener {
	private static final String TAG = "RechargeActivity";
	private Button backBtn;
	private TextView titleTv;
	private Button otherBtn;
	private ReCharge mReCharge;
	private SyncService ss;
	private SyncService sNew;
	private SharePreUtil sp;
	private String username;
	private String password;
	private String startdate = "";
	private String enddate = "";
	private String rvsectId = "";
	private ListView listView;
	private ReChargeAdapter adapter;
	private List<ReChargeBean> Mlist;
	private boolean res;//是否返回主界面

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recharge);
		init();
		init_titlebar();
		startLoad();
		
	}

	private void init() {
		// TODO Auto-generated method stub
		sp = new SharePreUtil(RechargeActivity.this);
		username = sp.getStringValueByKey("username");
		password = sp.getStringValueByKey("password");
		listView = (ListView) findViewById(R.id.recharge_listview);
	}

	private void startLoad() {
		// TODO Auto-generated method stub
		mReCharge = new ReCharge(username, password, startdate,
				enddate, rvsectId);
		ss = new SyncService(RechargeActivity.this, mReCharge);
		ss.isShowLoadDialog = true;
		ss.execute();
		res=true;
	}

	private void init_titlebar() {
		backBtn = (Button) findViewById(R.id.titlebar_back);
		
		titleTv = (TextView) findViewById(R.id.titlebar_title);
		otherBtn = (Button) findViewById(R.id.titlebar_other);
		backBtn.setOnClickListener(this);
		titleTv.setText(R.string.recharge_info);
		otherBtn.setOnClickListener(this);
		otherBtn.setVisibility(View.VISIBLE);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if (v == backBtn) {
			if(res)
			finish();
			else
			{
				startdate="";
				enddate="";
				rvsectId="";
		    	 startLoad();
			}
		} else if (v == otherBtn) {
			
			startIntent();
		}
	}

	private void startIntent() {
		Intent intent = new Intent();
		intent.addFlags(UrlLib.FLAG2);
		intent.setClass(RechargeActivity.this, TimeQueryActivity.class);
		startActivityForResult(intent, 2);
	}

	@Override
	public void onDataRequestSuccess() {
		// TODO Auto-generated method stub
		super.onDataRequestSuccess();
		if(Mlist!=null&&Mlist.size()>0&&adapter!=null){
			Mlist.removeAll(Mlist);
			adapter.notifyDataSetChanged();
		}
		Mlist = mReCharge.getList();
		if (Mlist != null && Mlist.size() > 0) {
			new Thread(update).run();
		} else {
			Toast.makeText(RechargeActivity.this, "无数据！",
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onDataRequestFail(ExceptionInfo error) {
		// TODO Auto-generated method stub
		super.onDataRequestFail(error);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 2) {
			L.info(TAG, "startdate="
					+ data.getStringExtra("startdate").toString() + "&enddate="
					+ data.getStringExtra("enddate").toString()+"&rvsectId="+rvsectId);
			
			startdate = data.getStringExtra("startdate");
			enddate = data.getStringExtra("enddate");
			rvsectId = data.getStringExtra("rvsectId");
			mReCharge = new ReCharge(username, password, startdate, enddate, rvsectId);
			sNew = new SyncService(RechargeActivity.this, mReCharge);
			sNew.isShowLoadDialog = true;
			sNew.execute();
			res =false;
		}
	}

	Runnable update = new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			updateHandler.sendEmptyMessage(0);
		}
	};
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
			adapter = new ReChargeAdapter(RechargeActivity.this, Mlist);
			if (UrlLib.mvalue == null || UrlLib.mvalue.size() == 0) {
				for (int i = 0; i < Mlist.size(); i++) {
					UrlLib.mvalue.add(Mlist.get(i).getName());
					UrlLib.mkey.add(Mlist.get(i).getDealtime());
				}
			}
			listView.setAdapter(adapter);
		}
	};

	

}
