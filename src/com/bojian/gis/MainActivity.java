package com.bojian.gis;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.bojian.gis.net.ExceptionInfo;
import com.bojian.gis.port.MainEntry;
import com.bojian.gis.ui.BaseActivity;
import com.bojian.gis.ui.LoginActivity;
import com.bojian.gis.util.CrashHandler;
import com.bojian.gis.util.UrlLib;

public class MainActivity extends BaseActivity {

	private Intent intent;
	private ProgressBar pb;
	private MainEntry me;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	/**
	 * 全局不可捕获的异常处理类，调试时也请将此代码注释掉
	 */
	private void init() {
		// TODO Auto-generated method stub
//		CrashHandler.getInstance().init(MainActivity.this);
	}

	private void startLoad() {
		// TODO Auto-generated method stub
		me = new MainEntry();
		SyncService ss = new SyncService(this, me);
		ss.isShowLoadDialog = false;
		ss.execute();	
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		startLoad();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void startApp() {
		intent = new Intent();
		intent.setClass(MainActivity.this, LoginActivity.class);
		startActivity(intent);
		finish();
	}

	public void onDataRequestSuccess() {
		if(me.getMlist()!=null&&me.getMlist().size()>0){
			UrlLib.areaBeanList = me.getMlist();
			for(int i=0;i<UrlLib.areaBeanList.size();i++){
				UrlLib.cityName.add(UrlLib.areaBeanList.get(i).getName());
			}
			new Thread(runnable).run();
		}else{
			Toast.makeText(MainActivity.this, "Sorry，暂无数据！!", Toast.LENGTH_SHORT).show();
		}
	};
	
	@Override
	public void onDataRequestFail(ExceptionInfo error) {
		// TODO Auto-generated method stub
		super.onDataRequestFail(error);
	}
	
	Runnable runnable = new  Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			startApp();
		}
	};
}
