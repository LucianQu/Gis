package com.bojian.gis.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;

import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import android.widget.TextView;

import com.bojian.gis.R;
import com.bojian.gis.SyncService;
import com.bojian.gis.entity.ImportRiverBeanEx;
import com.bojian.gis.net.ExceptionInfo;
import com.bojian.gis.port.ImportRiverEx;
import com.bojian.gis.util.Common;
import com.bojian.gis.util.MyHScrollView;
import com.bojian.gis.util.SharePreUtil;
import com.bojian.gis.util.UrlLib;
import com.bojian.gis.util.MyHScrollView.OnScrollChangedListener;

public class ReserviorActivity extends BaseActivity implements OnClickListener {
	private TextView fldControlLv;// /汛限水位(根据时间不同表现为允许超蓄水位、汛末水位)
	private TextView fldControlSt;// 汛限库容(根据时间不同表现为允许超蓄库容、汛末库容)

	private TextView ratioLv; // 比汛限水位(根据时间不同表现为比超蓄水位、比汛末水位)
	private TextView ratioSt; // 比汛限库容(根据时间不同表现为比超蓄库容、比汛末库容)

	private ListView mListView1;
	private AppLoginAdapter myAdapter;
	private LinearLayout mHead;
	private LinearLayout main;
	private TextView tvtitle;
	private TextView tvdatetime;
	private Button btnres;
	private Button btyquery;
	private ImportRiverEx upload;
	private Timer timer;
	private int clo = 0;
	
	private SharePreUtil sp ;
	private String username ;
	private String password ;
	private int city;
	private ArrayList<ImportRiverBeanEx> list;
	
	private boolean isStarttimer=false;
	
	private MyHScrollView slvheard;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resservior_list);
		tvtitle = (TextView) findViewById(R.id.titlebar_title);
		btnres = (Button) findViewById(R.id.titlebar_back);
		btyquery = (Button) findViewById(R.id.titlebar_other);
		
		tvtitle.setText("大中型水库");
		btnres.setOnClickListener(this);
		btyquery.setOnClickListener(this);
		mHead = (LinearLayout) findViewById(R.id.listview_head);
		mHead.setFocusable(true);
		mHead.setClickable(true);
		mHead.setBackgroundColor(Color.parseColor("#00CCFF"));

		mHead.setOnTouchListener(new ListViewAndHeadViewTouchLinstener());

		mListView1 = (ListView) findViewById(R.id.listView1);
		mListView1.setOnTouchListener(new ListViewAndHeadViewTouchLinstener());
		fldControlLv = (TextView) findViewById(R.id.floodlv);
		fldControlSt = (TextView) findViewById(R.id.floodst);
		ratioLv = (TextView) findViewById(R.id.ratioLv);
		ratioSt = (TextView) findViewById(R.id.ratioSt);
		slvheard= (MyHScrollView) findViewById(R.id.horizontalScrollView1);
		tvdatetime = (TextView) findViewById(R.id.datetime_txt_tv);
		init();
	}

	private void init() {
		String curDate = Common.getCurrDate("MMdd");
		setMoveHeardName(curDate);
		
		 sp = new SharePreUtil(ReserviorActivity.this);
		 username = sp.getStringValueByKey("username");
		 password = sp.getStringValueByKey("password");
		
		if (UrlLib.isAdmin) {
			city = getIntent().getIntExtra("city", 0);
		} else {
			city = sp.getIntegerValueByKey("city");
		}
		String datetime = Common.getCurrDate("yyyy-MM-dd") + "-08";
		tvdatetime.setText("时间："+Common.getCurrDate("yyyy-MM-dd") + " 08时");
		upload = new ImportRiverEx(username, password, city, datetime);

		SyncService ss = new SyncService(ReserviorActivity.this, upload);
		ss.execute();
	}

	private void setMoveHeardName(String mmdd)
	{
		if ((Integer.parseInt(mmdd) >= Integer.parseInt("0601"))
				&& (Integer.parseInt(mmdd) <= Integer.parseInt("0731"))) {
			fldControlLv.setText("汛限水位");
			fldControlSt.setText("汛限库容");
			ratioLv.setText("比汛限水位");
			ratioSt.setText("比汛限库容");
		}
		else if ((Integer.parseInt(mmdd) >= Integer.parseInt("0801"))
				&& (Integer.parseInt(mmdd) <= Integer.parseInt("0815"))) {
			fldControlLv.setText("超蓄水位");
			fldControlSt.setText("超蓄库容");
			ratioLv.setText("比超蓄水位");
			ratioSt.setText("比超蓄库容");
		} else  {
			fldControlLv.setText("汛末水位");
			fldControlSt.setText("汛末库容");
			ratioLv.setText("比汛末水位");
			ratioSt.setText("比汛末库容");
		}
	}
	
	class ListViewAndHeadViewTouchLinstener implements View.OnTouchListener {

		public boolean onTouch(View arg0, MotionEvent arg1) {
			// 当在列头 和 listView控件上touch时，将这个touch的事件分发给 ScrollView
			HorizontalScrollView headSrcrollView = (HorizontalScrollView) mHead
					.findViewById(R.id.horizontalScrollView1);
			headSrcrollView.onTouchEvent(arg1);
			return false;
		}
	}

	public class AppLoginAdapter extends BaseAdapter {
		// public List<ViewHolder> mHolderList = new ArrayList<ViewHolder>();
		LayoutInflater mInflater;
		private List<ImportRiverBeanEx> list = upload.getList();
		private int mTextColor;

		public AppLoginAdapter(Context context) {
			super();
			mInflater = LayoutInflater.from(context);

		}

		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return list.get(arg0);
		}

		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		public View getView(int position, View convertView, ViewGroup parentView) {
			ViewHolder holder = null;
			if (convertView == null) {
				synchronized (ReserviorActivity.this) {
					convertView = mInflater.inflate(
							R.layout.head_of_reserviorex, null);
					holder = new ViewHolder();

					MyHScrollView scrollView1 = (MyHScrollView) convertView
							.findViewById(R.id.horizontalScrollView1);
					
					

					holder.scrollView = scrollView1;
					holder.countyName = (TextView) convertView
							.findViewById(R.id.countyName);
					holder.rsnm = (TextView) convertView
							.findViewById(R.id.rsnm);
					holder.valleySq = (TextView) convertView
							.findViewById(R.id.valleySq);
					holder.irrigwatlev = (TextView) convertView
							.findViewById(R.id.irrigwatlev);
					holder.efst = (TextView) convertView
							.findViewById(R.id.efst);
					holder.floodlv = (TextView) convertView
							.findViewById(R.id.floodlv);
					holder.floodst = (TextView) convertView
							.findViewById(R.id.floodst);
					holder.warnlv = (TextView) convertView
							.findViewById(R.id.warnlv);
					holder.warnst = (TextView) convertView
							.findViewById(R.id.warnst);
					holder.ckflz = (TextView) convertView
							.findViewById(R.id.ckflz);
					holder.chkst = (TextView) convertView
							.findViewById(R.id.chkst);
					holder.spillForm = (TextView) convertView
							.findViewById(R.id.spillForm);
					holder.rz = (TextView) convertView.findViewById(R.id.rz);
					holder.ratioLv = (TextView) convertView
							.findViewById(R.id.ratioLv);
					holder.w = (TextView) convertView.findViewById(R.id.w);
					holder.ratioSt = (TextView) convertView
							.findViewById(R.id.ratioSt);
					holder.otqHole = (TextView) convertView
							.findViewById(R.id.otqHole);
					holder.otqRode = (TextView) convertView
							.findViewById(R.id.otqRode);

					MyHScrollView headSrcrollView = (MyHScrollView) mHead
							.findViewById(R.id.horizontalScrollView1);
					headSrcrollView
							.AddOnScrollChangedListener(new OnScrollChangedListenerImp(
									scrollView1));

					convertView.setTag(holder);
					// mHolderList.add(holder);
				}
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.countyName.setBackgroundResource(R.drawable.all_adapter_bg);
			holder.rsnm.setBackgroundResource(R.drawable.all_adapter_bg);
			holder.valleySq.setBackgroundResource(R.drawable.all_adapter_bg);
			holder.irrigwatlev.setBackgroundResource(R.drawable.all_adapter_bg);
			holder.efst.setBackgroundResource(R.drawable.all_adapter_bg);
			holder.floodlv.setBackgroundResource(R.drawable.all_adapter_bg);
			holder.floodst.setBackgroundResource(R.drawable.all_adapter_bg);
			holder.warnlv.setBackgroundResource(R.drawable.all_adapter_bg);
			holder.warnst.setBackgroundResource(R.drawable.all_adapter_bg);
			holder.ckflz.setBackgroundResource(R.drawable.all_adapter_bg);
			holder.chkst.setBackgroundResource(R.drawable.all_adapter_bg);
			holder.spillForm.setBackgroundResource(R.drawable.all_adapter_bg);
			holder.rz.setBackgroundResource(R.drawable.all_adapter_bg);
			holder.ratioLv.setBackgroundResource(R.drawable.all_adapter_bg);
			holder.w.setBackgroundResource(R.drawable.all_adapter_bg);
			holder.ratioSt.setBackgroundResource(R.drawable.all_adapter_bg);
			holder.otqHole.setBackgroundResource(R.drawable.all_adapter_bg);
			holder.otqRode.setBackgroundResource(R.drawable.all_adapter_bg);

			ImportRiverBeanEx mImportRiverBean = list.get(position);
			if (mImportRiverBean.getStyle() != null) {
				if (mImportRiverBean.getStyle().equals("1")) {
					holder.rz.setTextColor(Color.RED);
					holder.ratioLv.setTextColor(Color.RED);
					holder.w.setTextColor(Color.RED);
					holder.ratioSt.setTextColor(Color.RED);
					holder.otqHole.setTextColor(Color.RED);
					holder.otqRode.setTextColor(Color.RED);
				}
				else if (mImportRiverBean.getStyle().equals("2")) {
					holder.rz.setTextColor(mTextColor);
					holder.ratioLv.setTextColor(mTextColor);
					holder.w.setTextColor(mTextColor);
					holder.ratioSt.setTextColor(mTextColor);
					holder.otqHole.setTextColor(mTextColor);
					holder.otqRode.setTextColor(mTextColor);
				} else {
					holder.rz.setTextColor(Color.BLACK);
					holder.ratioLv.setTextColor(Color.BLACK);
					holder.w.setTextColor(Color.BLACK);
					holder.ratioSt.setTextColor(Color.BLACK);
					holder.otqHole.setTextColor(Color.BLACK);
					holder.otqRode.setTextColor(Color.BLACK);
				}

			}
			holder.countyName.setText(mImportRiverBean.getCountyName());
			holder.rsnm.setText(mImportRiverBean.getRsnm());
			holder.valleySq.setText(mImportRiverBean.getValleySq());
			holder.irrigwatlev.setText(mImportRiverBean.getIrrigwatlev());
			holder.efst.setText(mImportRiverBean.getEfst());
			holder.floodlv.setText(mImportRiverBean.getFldControlLv());
			holder.floodst.setText(mImportRiverBean.getFldControlSt());
			holder.warnlv.setText(mImportRiverBean.getWarnlv());
			holder.warnst.setText(mImportRiverBean.getWarnst());
			
			holder.ckflz.setText(mImportRiverBean.getCkflz());
			holder.chkst.setText(mImportRiverBean.getChkst());
			holder.spillForm.setText(mImportRiverBean.getSpillForm());
			holder.rz.setText(mImportRiverBean.getRz());
			holder.ratioLv.setText(mImportRiverBean.getRatioLv());
			holder.w.setText(mImportRiverBean.getW());
			holder.ratioSt.setText(mImportRiverBean.getRatioSt());
			holder.otqHole.setText(mImportRiverBean.getOtqHole());
			holder.otqRode.setText(mImportRiverBean.getOtqRode());

			// mHead.setBackgroundResource(R.drawable.all_adapter_bg);

			return convertView;
		}

		public void setmTextColor(int mTextColor) {
			this.mTextColor = mTextColor;
		}

		public int getmTextColor() {
			return mTextColor;
		}

		class OnScrollChangedListenerImp implements OnScrollChangedListener {
			MyHScrollView mScrollViewArg;

			public OnScrollChangedListenerImp(MyHScrollView scrollViewar) {
				mScrollViewArg = scrollViewar;
			}

			public void onScrollChanged(int l, int t, int oldl, int oldt) {
				mScrollViewArg.smoothScrollTo(l, t);
			}
		};

		class ViewHolder {

			TextView countyName; // 区县名称
			TextView rsnm; // 水库名称
			TextView valleySq; // 流域面积
			TextView irrigwatlev; // 兴利水位
			TextView efst; // 兴利库容
			TextView floodlv; // 汛限水位
			TextView floodst; // 汛限库容
			TextView warnlv; // 警戒水位
			TextView warnst; // 警戒库容
			TextView ckflz; // 校核水位
			TextView chkst; // 校核库容
			TextView spillForm;// 溢洪道 型 式
			TextView rz; // 实时水位
			TextView ratioLv; // 比汛限水位(根据时间不同表现为比超蓄水位、比汛末水位)
			TextView w; // 实时蓄水量
			TextView ratioSt; // 比汛限库容(根据时间不同表现为比超蓄库容、比汛末库容)
			TextView otqHole; // 输水洞出库流量
			TextView otqRode; // 溢洪道出库流量
			TextView wellno;
			TextView wellname;
			TextView welladdr;
			Button viewBtn;

			HorizontalScrollView scrollView;
		}
	}// end class my

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == btnres) {
			finish();
		}
		else if (v ==btyquery)
		{
		  Intent intent =new Intent();	
		  intent.setClass(ReserviorActivity.this, Resservior_DatetimeActivity.class);
		  startActivityForResult(intent, 1);
		 
		}
	}

	@Override
	public void onDataRequestSuccess() {
		// TODO Auto-generated method stub
		super.onDataRequestSuccess();
		slvheard.scrollTo(0,0);
		if (list != null && myAdapter != null) {
			list.removeAll(list);
			myAdapter.notifyDataSetChanged();
		}
		list = upload.getList();
		if (list != null && list.size() > 0) {
		myAdapter = new AppLoginAdapter(this);
		mListView1.setAdapter(myAdapter);
		}
		if (!isStarttimer)
		{
		spark();
		isStarttimer=true;
		}
	}

	@Override
	public void onDataRequestFail(ExceptionInfo error) {
		// TODO Auto-generated method stub
		super.onDataRequestFail(error);
	}

	public void spark() {

		timer = new Timer();

		TimerTask taskcc = new TimerTask() {
			public void run() {
				
				runOnUiThread(new Runnable() {
					

					public void run() {
						if (clo == 0) {
							clo = 1;
							myAdapter.setmTextColor(Color.BLACK);

						} else {
							if (clo == 1) {

								clo = 0;
								myAdapter.setmTextColor(Color.RED);
							}
						}

						myAdapter.notifyDataSetChanged();

					}
				});
			}
		};
		timer.schedule(taskcc, 2000, 1000);

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		timer.cancel();
		super.onDestroy();

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 1) {
		   
		   String datetime=data.getStringExtra("datetime");
		   
		   tvdatetime.setText("时间："+datetime.substring(0,10)+" "+datetime.substring(11,13) + "时");
		   String mmdd=datetime.substring(5, 7)+datetime.substring(8,10);
		   setMoveHeardName(mmdd);
		   upload = new ImportRiverEx(username, password, city, datetime);

			SyncService ss = new SyncService(ReserviorActivity.this, upload);
			ss.execute();
		   
		}
	}
}
