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
import com.bojian.gis.adapter.PumpOnoffAdapter;
import com.bojian.gis.adapter.PumpOnoffAdapter.MyClickListener;
import com.bojian.gis.debug.L;
import com.bojian.gis.entity.PumpOnoffBean;
import com.bojian.gis.net.ExceptionInfo;
import com.bojian.gis.port.PumpOnoff;
import com.bojian.gis.port.PumponoffOp;
import com.bojian.gis.util.SharePreUtil;
import com.bojian.gis.util.UrlLib;

public class PumpOnoffActivity extends BaseActivity implements
		OnClickListener {
	private static final String TAG = "PumpOnoffActivity";
	private Button m_backBtn;
	private TextView m_titleTv;
	private Button m_otherBtn;
	private Button m_viewbtn_on;
	private Button m_viewbtn_off;
	private PumpOnoff m_pumponoff;
	private PumponoffOp m_pumponoffOp;
	private SyncService m_syncService;
	private SyncService m_syncServiceNew;
	private SharePreUtil m_sharePreUtil;
	private String m_userName;
	private String m_passWord;
	private String m_startDate = "";
	private String m_endDate = "";
	private String m_rvsectId = "";
	private String m_status = "";//_on && _off分别代表开关泵指令
	private ListView m_listView;
	private PumpOnoffAdapter m_pumpOnoffAdapt;
	private List<PumpOnoffBean> m_pumpOnoffBeanList;
	private boolean m_isBack;//是否返回主界面

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pumponoff);
		_init();
		_init_titlebar();
		_startLoad();
		
	}

	private void _init() {
		m_sharePreUtil = new SharePreUtil(PumpOnoffActivity.this);
		m_userName = m_sharePreUtil.getStringValueByKey("username");
		m_passWord = m_sharePreUtil.getStringValueByKey("password");
		m_listView = (ListView) findViewById(R.id.pumponoff_listview);
		//实例化Adapter类，并传入实现类
		m_listView.setAdapter(m_pumpOnoffAdapt);
	}

	private void _startLoad() {
		// TODO Auto-generated method stub
		m_pumponoff = new PumpOnoff(m_userName, m_passWord, m_startDate,
				m_endDate, m_rvsectId,m_status);
		m_syncService = new SyncService(PumpOnoffActivity.this, m_pumponoff);
		m_syncService.isShowLoadDialog = true;
		m_syncService.execute();
		m_isBack=true;
	}

	private void _init_titlebar() {
		m_backBtn = (Button) findViewById(R.id.titlebar_back);
		
		m_titleTv = (TextView) findViewById(R.id.titlebar_title);
		m_otherBtn = (Button) findViewById(R.id.titlebar_other);
		m_backBtn.setOnClickListener(this);
		m_titleTv.setText(R.string.pumponoff_option);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if (v == m_backBtn) {
			if(m_isBack)
			finish();
			else
			{
				m_startDate="";
				m_endDate="";
				m_rvsectId="";
				m_status ="";
		    	_startLoad();
			}
		}
	}

	private void startIntent() {
		Intent intent = new Intent();
		intent.addFlags(UrlLib.FLAG2);
		intent.setClass(PumpOnoffActivity.this, TimeQueryActivity.class);
		startActivityForResult(intent, 2);
	}

	@Override
	public void onDataRequestSuccess() {
		// TODO Auto-generated method stub
		super.onDataRequestSuccess();
		if(m_pumpOnoffBeanList!=null&&m_pumpOnoffBeanList.size()>0&&m_pumpOnoffAdapt!=null){
			m_pumpOnoffBeanList.removeAll(m_pumpOnoffBeanList);
			m_pumpOnoffAdapt.notifyDataSetChanged();
		}
		m_pumpOnoffBeanList = m_pumponoff.getList();
		if (m_pumpOnoffBeanList != null && m_pumpOnoffBeanList.size() > 0) {
			new Thread(update).run();
		} else {
			Toast.makeText(PumpOnoffActivity.this, "无数据！",
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
					+ data.getStringExtra("enddate").toString()+"&rvsectId="+m_rvsectId);
			
			m_startDate = data.getStringExtra("startdate");
			m_endDate = data.getStringExtra("enddate");
			m_rvsectId = data.getStringExtra("rvsectId");
			m_pumponoff = new PumpOnoff(m_userName, m_passWord, m_startDate, m_endDate, m_rvsectId,m_status);
			m_syncServiceNew = new SyncService(PumpOnoffActivity.this, m_pumponoff);
			m_syncServiceNew.isShowLoadDialog = true;
			m_syncServiceNew.execute();
			m_isBack =false;
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
			m_pumpOnoffAdapt = new PumpOnoffAdapter(PumpOnoffActivity.this, m_pumpOnoffBeanList,getBaseContext(),mListener);
			if (UrlLib.mvalue == null || UrlLib.mvalue.size() == 0) {
				for (int i = 0; i < m_pumpOnoffBeanList.size(); i++) {
					UrlLib.mvalue.add(m_pumpOnoffBeanList.get(i).getWellno());
					UrlLib.mkey.add(m_pumpOnoffBeanList.get(i).getWellname());
				}
			}
			m_listView.setAdapter(m_pumpOnoffAdapt);
		}
	};

	/*
	 * 实现类，响应按钮点击事件
	 * 
	 * */
	private MyClickListener mListener = new MyClickListener() {
		@Override
		public void myOnClick(int position,View v){
			 switch(v.getId()){
	            case R.id.view_btn_on:
	            	System.out.println("开泵");
	            	m_pumpOnoffBeanList.get(position).setStatus("_on");
	            	break;
	            case R.id.view_btn_off:
	            	System.out.println("关泵");
	            	m_pumpOnoffBeanList.get(position).setStatus("_off");
	            	break;
	            }
			 
         	m_pumponoff = new PumpOnoff(m_userName, m_passWord, m_startDate,
     				m_endDate, m_rvsectId,m_status);
         	m_pumponoff.setList(m_pumpOnoffBeanList);
         	System.out.println(m_pumponoff.getList().toString());
     		m_syncService = new SyncService(PumpOnoffActivity.this, m_pumponoff);
     		m_syncService.execute();
			Toast.makeText(
					PumpOnoffActivity.this,
					"机井："+m_pumpOnoffBeanList.get(position).getWellno().toString()+"状态"+m_pumpOnoffBeanList.get(position).getStatus().toString()
					,Toast.LENGTH_SHORT)
					.show();
				}
	};
}
