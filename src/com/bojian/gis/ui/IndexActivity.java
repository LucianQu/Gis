package com.bojian.gis.ui;

import com.bojian.gis.R;
import com.bojian.gis.util.SharePreUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//关于页面 
public class IndexActivity extends Activity implements OnClickListener{
	private Button backBtn;
	private TextView titleTv;
	private Button otherBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.index);
		init_titlebar();
	}

	private void init_titlebar() {
		// TODO Auto-generated method stub
		backBtn = (Button) findViewById(R.id.titlebar_back);
		titleTv = (TextView) findViewById(R.id.titlebar_title);
		otherBtn = (Button) findViewById(R.id.titlebar_other);
		
		backBtn.setOnClickListener(this);
		titleTv.setText("关于");
		//otherBtn.setVisibility(View.GONE);
		otherBtn.setOnClickListener(this);
		otherBtn.setText("注 销");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==backBtn)
			finish();
			else if (v==otherBtn)
			{
				SharePreUtil sp = new SharePreUtil(IndexActivity.this); 
				boolean auto_login = sp.getBooleanValueByKey("auto_login");
				if (auto_login) {
					sp.removeSpByKey("auto_login");
					Toast.makeText(IndexActivity.this, "成功注销自动登录！",
							Toast.LENGTH_SHORT).show();
				}
				else{
				Toast.makeText(IndexActivity.this, "已注销自动登录！",
						Toast.LENGTH_SHORT).show();
				}
				
			}
	}	
	
}
