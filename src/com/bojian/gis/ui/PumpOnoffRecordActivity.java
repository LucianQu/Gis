package com.bojian.gis.ui;

import java.util.List;

import android.R.integer;
import android.app.AlertDialog;
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
import com.bojian.gis.adapter.PumpOnoffRecordAdapter;
import com.bojian.gis.adapter.PumpOnoffRecordAdapter.MyClickListener;
import com.bojian.gis.debug.L;
import com.bojian.gis.entity.PumponoffRecordBean;
import com.bojian.gis.net.ExceptionInfo;
import com.bojian.gis.port.PumpOnoff;
import com.bojian.gis.port.PumponoffRecord;
import com.bojian.gis.util.SharePreUtil;
import com.bojian.gis.util.UrlLib;

public class PumpOnoffRecordActivity extends BaseActivity implements
		OnClickListener {
	private static final String TAG = "PumpOnoffRecordActivity";
	private Button backBtn;
	private TextView titleTv;
	private Button otherBtn;
	private PumponoffRecord mPumponoffRecord;
	private SyncService ss;
	private SyncService sNew;
	private SharePreUtil sp;
	private String username;
	private String password;
	private String startdate = "";
	private String enddate = "";
	private String wellId = "";
	private ListView listView;
	private PumpOnoffRecordAdapter adapter;
	private List<PumponoffRecordBean> Mlist;
	private boolean res;//是否返回主界面

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pumponoff_record);
		init();
		init_titlebar();
		startLoad();
		
	}

	private void init() {
		// TODO Auto-generated method stub
		sp = new SharePreUtil(PumpOnoffRecordActivity.this);
		username = sp.getStringValueByKey("username");
		password = sp.getStringValueByKey("password");
		listView = (ListView) findViewById(R.id.pumponoff_listview);
		//实例化Adapter类，并传入实现类
		listView.setAdapter(adapter);
	}

	private void startLoad() {
		// TODO Auto-generated method stub
		mPumponoffRecord = new PumponoffRecord(username, password, startdate,
				enddate, wellId);                          //此处的id需要修改为对应的list id
		ss = new SyncService(PumpOnoffRecordActivity.this, mPumponoffRecord);
		ss.isShowLoadDialog = true;
		ss.execute();
		res=true;
	}

	private void init_titlebar() {
		backBtn = (Button) findViewById(R.id.titlebar_back);
		
		titleTv = (TextView) findViewById(R.id.titlebar_title);
		otherBtn = (Button) findViewById(R.id.titlebar_other);
		backBtn.setOnClickListener(this);
		titleTv.setText(R.string.pumponoff_record);
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
				wellId="";
		    	 startLoad();
			}
		} else if (v == otherBtn) {
			
			startIntent();
		}
	}

	private void startIntent() {
		Intent intent = new Intent();
		intent.addFlags(UrlLib.PUMPONOFFRECORD);
		intent.setClass(PumpOnoffRecordActivity.this, TimeQueryActivity.class);
		startActivityForResult(intent, 2); //按code进行函数回调
	}

	@Override
	public void onDataRequestSuccess() {
		// TODO Auto-generated method stub
		super.onDataRequestSuccess();
		if(Mlist!=null&&Mlist.size()>0&&adapter!=null){
			Mlist.removeAll(Mlist);
			adapter.notifyDataSetChanged();
		}
		Mlist = mPumponoffRecord.getList();
		if (Mlist != null && Mlist.size() > 0) {
			new Thread(update).run();
		} else {
			Toast.makeText(PumpOnoffRecordActivity.this, "无数据！",
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
					+ data.getStringExtra("enddate").toString()+"&wellId="+wellId);
			
			startdate = data.getStringExtra("startdate");
			enddate = data.getStringExtra("enddate");
			wellId = data.getStringExtra("wellId");
			mPumponoffRecord = new PumponoffRecord(username, password, startdate, enddate, wellId);
			sNew = new SyncService(PumpOnoffRecordActivity.this, mPumponoffRecord);
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
			adapter = new PumpOnoffRecordAdapter(PumpOnoffRecordActivity.this, Mlist,mListener);
			if (UrlLib.mvalue == null || UrlLib.mvalue.size() == 0) {
				for (int i = 0; i < Mlist.size(); i++) {
					UrlLib.mvalue.add(Mlist.get(i).getWellno());
					UrlLib.mkey.add(Mlist.get(i).getWellname());
				}
			}
			listView.setAdapter(adapter);
		}
	};
	
	
	/*
	 * 实现类，响应按钮点击事件
	 * 
	 * */
	private MyClickListener mListener = new MyClickListener() {
		@Override
		public void myOnClick(int position,View v){
			 
			mPumponoffRecord = new PumponoffRecord(username, password, startdate,
     				enddate, wellId);
			mPumponoffRecord.setList(Mlist);
			Toast.makeText(
					PumpOnoffRecordActivity.this,
					"水井编号："+Mlist.get(position).getWellno()+"水井名称："+Mlist.get(position).getWellname()+"水井地址："+Mlist.get(position).getWelladdr(), position)
					.show();
				}
	};

	

}
