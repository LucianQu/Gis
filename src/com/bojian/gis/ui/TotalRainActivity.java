//累计雨量界面
package com.bojian.gis.ui;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bojian.gis.R;
import com.bojian.gis.SyncService;
import com.bojian.gis.adapter.TotalRainAdapter;
import com.bojian.gis.debug.L;
import com.bojian.gis.entity.TotalRainBean;
import com.bojian.gis.port.TotalRain;
import com.bojian.gis.util.Common;
import com.bojian.gis.util.SharePreUtil;
import com.bojian.gis.util.UrlLib;

public class TotalRainActivity extends BaseActivity implements
		OnClickListener, OnCheckedChangeListener {
	private static final String TAG = "TotalRainActivity2";

	private Button backBtn;
	private TextView titleTv;
	private Button otherBtn;
	private Button totalBtn;

	private RadioButton today;
	private RadioButton three_days;
	private RadioButton seven_days;
	private RadioButton one_month;

	private TotalRain mTotalRain;
	private TotalRain mTotalRainNew;

	private ListView mListView;
	
	private FrameLayout frmTimeView;
	private TextView tvTime;
	private RadioGroup rbTimeSelect;

	private SharePreUtil sp;
	private String username;
	private String password;
	private int city;
	private String startdate;
	private String enddate;

	private SyncService ss;
	private SyncService sNew;
	private List<TotalRainBean> Mlist;
	private TotalRainAdapter adapter;
	
	public static boolean isSave;
	public static String saveStartTime;
	public static String saveEndTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		isSave =false;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.total_rainfall);
		init_titlebar();
		init_buttom();
		init_listview();
		init_sp();
		startLoad();
	}

	private void init_sp() {
		// TODO Auto-generated method stub
		sp = new SharePreUtil(TotalRainActivity.this);
		username = sp.getStringValueByKey("username");
		password = sp.getStringValueByKey("password");
		if (UrlLib.isAdmin) {
			city = getIntent().getIntExtra("city", 0);
		} else {
			city = sp.getIntegerValueByKey("city");
		}
		String strtime=Common.getCurrDate("yyyy-MM-dd-HH");
		strtime=strtime.substring(11, 13);
		if (Integer.parseInt(strtime)>=8)
		startdate = Common.getCurrDate("yyyy-MM-dd")+"-08:00";
		else
			startdate =Common.getCurrDate(2)+"-08:00";	
		
		enddate = Common.getCurrDate("yyyy-MM-dd-HH")+":00";
	}

	private void init_listview() {
		// TODO Auto-generated method stub
		mListView = (ListView) findViewById(R.id.total_rainfall2_listview);
	}

	private void startLoad() {
		// TODO Auto-generated method stub
		mTotalRain = new TotalRain(username, password, city, startdate, enddate);
		//tvTime.setText("开始："+startdate+"  结束："+enddate);
		tvTime.setText("开始："+startdate.substring(0, 10)+" "+startdate.substring(11, 16)+"  结束："+enddate.substring(0, 10)+" "+enddate.substring(11, 16));
		ss = new SyncService(TotalRainActivity.this, mTotalRain);
		ss.execute();
	}

	private void init_buttom() {
		// TODO Auto-generated method stub
		rbTimeSelect=(RadioGroup) findViewById(R.id.content_radio_group);
		today = (RadioButton) findViewById(R.id.today_tv);
		three_days = (RadioButton) findViewById(R.id.three_days_tv);
		seven_days = (RadioButton) findViewById(R.id.seven_days_tv);
		one_month = (RadioButton) findViewById(R.id.one_month_tv);
		frmTimeView =(FrameLayout) findViewById(R.id.time_tv);
		tvTime = (TextView)findViewById(R.id.time_txt_tv);
		
		today.setOnCheckedChangeListener(this);
		three_days.setOnCheckedChangeListener(this);
		seven_days.setOnCheckedChangeListener(this);
		one_month.setOnCheckedChangeListener(this);
		//frmTimeView.setVisibility(View.GONE);
		
		
	}

	private void init_titlebar() {
		// TODO Auto-generated method stub
		backBtn = (Button) findViewById(R.id.titlebar_back);
		titleTv = (TextView) findViewById(R.id.titlebar_title);
		otherBtn = (Button) findViewById(R.id.titlebar_other);
		backBtn.setOnClickListener(this);
		titleTv.setText("累计雨量");
		otherBtn.setOnClickListener(this);
		otherBtn.setVisibility(View.VISIBLE);
		otherBtn.setText("时间选择");
		totalBtn = (Button) findViewById(R.id.total_rainfall);
		totalBtn.setText("测站统计");
		totalBtn.setVisibility(View.VISIBLE);
		totalBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == backBtn) {
			finish();
		} else if (v == otherBtn) {
			Intent intent = new Intent();
			intent.addFlags(UrlLib.FLAG1);
			intent.setClass(TotalRainActivity.this, TimeQueryActivity.class);
			startActivityForResult(intent, 1);
		}else if (v == totalBtn){
			
			Intent intent = new Intent();
			intent.addFlags(UrlLib.afterEight);
			intent.setClass(TotalRainActivity.this, RainTabActivity.class);
			startActivity(intent);
		}
	}

	@Override
	public void onDataRequestSuccess() {
		// TODO Auto-generated method stub
		super.onDataRequestSuccess();
		if (Mlist != null && Mlist.size() > 0 && adapter != null) {
			Mlist.removeAll(Mlist);
			adapter.notifyDataSetChanged();
		}
		Mlist = mTotalRain.getList();
		if (Mlist != null && Mlist.size() > 0) {
			new Thread(update).run();
		} else {
			Toast.makeText(TotalRainActivity.this, "无数据！", Toast.LENGTH_SHORT)
					.show();
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
			adapter = new TotalRainAdapter(TotalRainActivity.this, Mlist);
			mListView.setAdapter(adapter);
		}
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 1) {
			isSave =true;
			L.info(TAG, "startdate="
					+ data.getStringExtra("startdate").toString() + "&enddate="
					+ data.getStringExtra("enddate").toString());
			startdate = data.getStringExtra("startdate");
			enddate = data.getStringExtra("enddate");
			saveStartTime=startdate;
			saveEndTime =enddate;
			if (UrlLib.isAdmin)
			city=data.getIntExtra("city", 0);
			mTotalRain = new TotalRain(username, password, city, startdate,
					enddate);
			sNew = new SyncService(TotalRainActivity.this, mTotalRain);
			sNew.execute();
			//tvTime.setText("开始："+startdate+"  结束："+enddate);
			tvTime.setText("开始："+startdate.substring(0, 10)+" "+startdate.substring(11, 16)+"  结束："+enddate.substring(0, 10)+" "+enddate.substring(11, 16));
			rbTimeSelect.clearCheck();
			
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		if (isChecked) {
			
			switch (buttonView.getId()) {
			case R.id.today_tv:
				
				
				String strtime=Common.getCurrDate("yyyy-MM-dd-HH");
				strtime=strtime.substring(11, 13);
				if (Integer.parseInt(strtime)>=8)
				startdate = Common.getCurrDate("yyyy-MM-dd")+"-08:00";
				else
					startdate =Common.getCurrDate(2)+"-08:00";	
				
				enddate =  Common.getCurrDate("yyyy-MM-dd-HH")+":00";//Common.getCurrDate("yyyy-MM-dd")+"-08:00";
				
				mTotalRain = new TotalRain(username, password, city, startdate,
						enddate);
				new SyncService(TotalRainActivity.this, mTotalRain).execute();
				break;
			case R.id.three_days_tv:
				startdate = Common.getCurrDate(3)+"-08:00";
				enddate = Common.getCurrDate("yyyy-MM-dd")+"-08:00";
				mTotalRain = new TotalRain(username, password, city, startdate,
						enddate);
				new SyncService(TotalRainActivity.this, mTotalRain).execute();
				break;
			case R.id.seven_days_tv:
				startdate = Common.getCurrDate(7)+"-08:00";
				enddate = Common.getCurrDate("yyyy-MM-dd")+"-08:00";
				mTotalRain = new TotalRain(username, password, city, startdate,
						enddate);
				new SyncService(TotalRainActivity.this, mTotalRain).execute();
				break;
			case R.id.one_month_tv:
				startdate = Common.getCurrDate(30)+"-08:00";
				enddate = Common.getCurrDate("yyyy-MM-dd")+"-08:00";
				mTotalRain = new TotalRain(username, password, city, startdate,
						enddate);
				new SyncService(TotalRainActivity.this, mTotalRain).execute();
				break;

			default:
				break;
			}
			;
			tvTime.setText("开始："+startdate.substring(0, 10)+" "+startdate.substring(11, 16)+"  结束："+enddate.substring(0, 10)+" "+enddate.substring(11, 16));
		}
	}

}
