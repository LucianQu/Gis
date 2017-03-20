package com.bojian.gis.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.NumberPicker.OnValueChangeListener;

import com.bojian.gis.R;
import com.bojian.gis.SyncService;
import com.bojian.gis.debug.L;
import com.bojian.gis.net.ExceptionInfo;
import com.bojian.gis.port.WaterForcast;
import com.bojian.gis.util.Common;

public class Resservior_DatetimeActivity extends BaseActivity implements
		OnClickListener {
	private NumberPicker myear;
	private NumberPicker mmonth;
	private NumberPicker mday;
	private NumberPicker mhour;
	private TextView tvdate;
	private Button btnok;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);

		setContentView(R.layout.resservior_datetime);
		myear = (NumberPicker) findViewById(R.id.npkyear);
		mmonth = (NumberPicker) findViewById(R.id.npkmonth);
		mday = (NumberPicker) findViewById(R.id.npkday);
		mhour = (NumberPicker) findViewById(R.id.npkhour);
		btnok = (Button) findViewById(R.id.btnok);
		tvdate = (TextView) findViewById(R.id.textView1);
		init();
	}

	private void init() {
		// 就是日期可选的为月份，日（1号/6号/11/16/21/26/31），小时是6点或8点

//		((EditText)myear.getChildAt(1)).setTextSize(20); 
//		((EditText)mmonth.getChildAt(1)).setTextSize(20); 
//		((EditText)mday.getChildAt(1)).setTextSize(20); 
//		((EditText)mhour.getChildAt(1)).setTextSize(20); 
		tvdate.setText("");
		btnok.setOnClickListener(this);

		// 年
		myear.setMinValue(2000);
		
		String syear = Common.getCurrDate("yyyy");
		myear.setMaxValue(Integer.parseInt(syear));
		myear.setValue(Integer.parseInt(syear));
		myear.setOnValueChangedListener(new OnValueChangeListener() {
			@Override
			public void onValueChange(NumberPicker picker, int oldVal,
					int newVal) {
				tvdate.setText("日期时间：" + getDateTime());
				if (mmonth.getValue() > 0) {
					int monthofday = Common.getDayofMonth(myear.getValue(),
							mmonth.getValue());
					
					if (mmonth.getValue() == 2)
						mday.setMaxValue(26);
					else
						mday.setMaxValue(monthofday);
				}

			}
		});

		// 月
		mmonth.setMinValue(1);
		String smm = Common.getCurrDate("MM");
		mmonth.setMaxValue(12);
		mmonth.setValue(Integer.parseInt(smm));
		mmonth.setOnValueChangedListener(new OnValueChangeListener() {
			@Override
			public void onValueChange(NumberPicker picker, int oldVal,
					int newVal) {
				tvdate.setText("日期时间：" + getDateTime());
				
				int monthofday = Common.getDayofMonth(myear.getValue(),
						mmonth.getValue());
				
				if (mmonth.getValue() == 2)
					mday.setMaxValue(26);
				else
					mday.setMaxValue(monthofday);
			}
		});

		// 日
		mday.setMinValue(1);
		int monthofday = Common.getDayofMonth(myear.getValue(),
				mmonth.getValue());

		if (mmonth.getValue() == 2)
			mday.setMaxValue(26);
		else
			mday.setMaxValue(monthofday);
		
		//String smd = Common.getCurrDate("dd");
		mday.setValue(1);
		mday.setOnValueChangedListener(new OnValueChangeListener() {
			@Override
			public void onValueChange(NumberPicker picker, int oldVal,
					int newVal) {

				if ((oldVal == picker.getMaxValue()) && (newVal == 1)) {
					mday.setValue(1);

				} else if ((oldVal == 1) && (newVal == picker.getMaxValue())) {
					if (mday.getMaxValue() == 31)
						mday.setValue(31);
					else
						mday.setValue(26);
				}

				else {
					if (newVal > oldVal)
						newVal = oldVal + 5;
					else
						newVal = oldVal - 5;

					mday.setValue(newVal);
				}
				tvdate.setText("日期时间：" + getDateTime());
			}
		});

		// 小时
		mhour.setMinValue(1);
		mhour.setMaxValue(8);
		mhour.setValue(6);
		mhour.setOnValueChangedListener(new OnValueChangeListener() {
			@Override
			public void onValueChange(NumberPicker picker, int oldVal,
					int newVal) {

				if (oldVal == 6)
					mhour.setValue(8);
				else if (oldVal == 8)
					mhour.setValue(6);
				tvdate.setText("日期时间：" + getDateTime());

			}
		});

		tvdate.setText("日期时间：" + getDateTime());

	}

	private String getDateTime() {
		return myear.getValue() + "年" + mmonth.getValue() + "月"
				+ mday.getValue() + "日" + mhour.getValue() + "时";
	}

	private String changetwo(int i) {
		if (i < 10)
			return "0" + i;
		else
			return "" + i;
	}

	@Override
	public void onDataRequestSuccess() {
		// TODO Auto-generated method stub
		super.onDataRequestSuccess();
	}

	@Override
	public void onDataRequestFail(ExceptionInfo error) {
		// TODO Auto-generated method stub
		super.onDataRequestFail(error);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == btnok) {
			Intent intent = new Intent();
			intent.setClass(Resservior_DatetimeActivity.this, ReserviorActivity.class);
			String datetime = myear.getValue()+"-"+changetwo(mmonth.getValue())+"-"+changetwo(mday.getValue())+"-"+changetwo(mhour.getValue());
			
			intent.putExtra("datetime", datetime);
			setResult(1, intent);
			finish();
		}

	}
	
	


}
