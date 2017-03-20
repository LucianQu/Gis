package com.bojian.gis.ui;

import com.bojian.gis.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
//������ѯ------>�Զ���------>����ѡ��
public class CityQueryActivity extends Activity implements OnClickListener{
	private static final String TAG = "CityQueryActivity";
	private Button backBtn;
	private TextView titleTv;
	private Button otherBtn;
	private EditText cityET;
	private String city;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_city);
		init_titlebar();
	}


	private void init_titlebar() {
		// TODO Auto-generated method stub
		backBtn = (Button) findViewById(R.id.titlebar2_back);
		titleTv = (TextView) findViewById(R.id.titlebar2_title);
		otherBtn = (Button) findViewById(R.id.titlebar2_other);
		backBtn.setOnClickListener(this);
		otherBtn.setOnClickListener(this);
		titleTv.setText("����ѡ��");
		
		cityET = (EditText)findViewById(R.id.city);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == backBtn) {
			CityQueryActivity.this.finish();
		}else{
			if(validate()){
				Intent intent = new Intent(CityQueryActivity.this,WeatherForcastActivity.class);
				intent.putExtra("city",city);
				setResult(3, intent);
				this.finish();
			}
		}
	}
	
	private boolean validate(){
		city = cityET.getText().toString();
		if(city==null||city.equals("")){
			showToast("�������������!");
			return false;
		}
		return true;
	}
	
	private void showToast(String msg) {
		Toast.makeText(CityQueryActivity.this, msg, Toast.LENGTH_SHORT).show();
	}

}
