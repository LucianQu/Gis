//水文预报,累计雨量 自定义界面
package com.bojian.gis.ui;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bojian.gis.R;
import com.bojian.gis.debug.L;
import com.bojian.gis.util.Common;
import com.bojian.gis.util.CustomDatePickerDialog;
import com.bojian.gis.util.UrlLib;

public class TimeQueryActivity extends Activity implements OnClickListener,
		OnDateSetListener, OnItemSelectedListener {
	private static final String TAG = "TotalRainfallQuery";
	private Button backBtn;
	private TextView titleTv;
	private Button otherBtn;
	private EditText start_timeET;
	private EditText end_timeET;
	private DatePickerDialog start_time_dialog;
	private DatePickerDialog end_time_dialog;
	private String start_time;
	private String end_time;
	private String rvsectId;
	private int city;
	private Intent intent;
	private LinearLayout head_of_water_forcast_selector;
	private TextView duanmian;
	private Spinner water_forcast_spinner;
	private ArrayAdapter<String> mArrayAdapter;
//	private SharePreUtil sp;
	private String str ="";
	private boolean s;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_time);
		
		init_titlebar();
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
//		sp = new SharePreUtil(TimeQueryActivity.this);
		
		//水文预报自定义
		if (intent.getFlags() == UrlLib.FLAG2) {
			//head_of_water_forcast_selector.setVisibility(View.VISIBLE);
			duanmian.setText("预报断面");
			if (UrlLib.mvalue != null && UrlLib.mvalue.size() > 0) {
				mArrayAdapter = new ArrayAdapter<String>(TimeQueryActivity.this,
						android.R.layout.simple_spinner_item, UrlLib.mvalue);
				mArrayAdapter
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				water_forcast_spinner.setAdapter(mArrayAdapter);
				water_forcast_spinner.setSelection(0, true);
			}
//			start_time = sp.getStringValueByKey("FLAG2_START_TIME");
//			end_time = sp.getStringValueByKey("FLAG2_END_TIME");
			start_time = Common.getCurrDate(30);
			end_time = Common.getCurrDate("yyyy-MM-dd");
			if (start_time != null)
				start_timeET.setText(start_time);
			if (end_time != null)
				end_timeET.setText(end_time);
		}else {//累计雨量自定义
//			start_time = sp.getStringValueByKey("FLAG1_START_TIME");
//			end_time = sp.getStringValueByKey("FLAG1_END_TIME");
			if (UrlLib.isAdmin)
			{
			//head_of_water_forcast_selector.setVisibility(View.VISIBLE);
			//duanmian.setText("城市选择");
			if (UrlLib.cityName != null && UrlLib.cityName.size() > 0) {
				mArrayAdapter = new ArrayAdapter<String>(TimeQueryActivity.this,
						android.R.layout.simple_spinner_item, UrlLib.cityName);
				mArrayAdapter
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				water_forcast_spinner.setAdapter(mArrayAdapter);
				water_forcast_spinner.setSelection(0, true);
			}
			}
			
			if (TotalRainActivity.isSave)
			{
				start_time=TotalRainActivity.saveStartTime;
				end_time=TotalRainActivity.saveEndTime;
				if (start_time != null)
					start_timeET.setText(start_time);
				if (end_time != null)
					end_timeET.setText(end_time);
				
			}
			else
			{
			
				String strtime=Common.getCurrDate("yyyy-MM-dd-HH");
				strtime=strtime.substring(11, 13);
				if (Integer.parseInt(strtime)>=8)
					start_time = Common.getCurrDate("yyyy-MM-dd");
				else
					start_time =Common.getCurrDate(2);	
				
				end_time = Common.getCurrDate("yyyy-MM-dd");
				
				
				
			
			if (start_time != null)
				start_timeET.setText(start_time+"-08:00");
			if (end_time != null)
				end_timeET.setText(Common.getCurrDate("yyyy-MM-dd-HH")+":00");
			
			}
			
		}
	}

	private void init_titlebar() {
		// TODO Auto-generated method stub
		intent = getIntent();
		backBtn = (Button) findViewById(R.id.titlebar2_back);
		titleTv = (TextView) findViewById(R.id.titlebar2_title);
		otherBtn = (Button) findViewById(R.id.titlebar2_other);
		backBtn.setOnClickListener(this);
		otherBtn.setOnClickListener(this);
		titleTv.setText("时间选择");

		head_of_water_forcast_selector = (LinearLayout) findViewById(R.id.head_of_water_forcast_selector);
		start_timeET = (EditText) findViewById(R.id.start_time);
		end_timeET = (EditText) findViewById(R.id.end_time);
		water_forcast_spinner = (Spinner) findViewById(R.id.water_forcast_spinner2);
		water_forcast_spinner.setOnItemSelectedListener(this);
		start_timeET.setOnClickListener(this);
		end_timeET.setOnClickListener(this);
		duanmian =(TextView) findViewById(R.id.duanmian);
	
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == backBtn) {
			TimeQueryActivity.this.finish();
		} else if (v == otherBtn) {
			if (validate_time()) {
				if (intent.getFlags() == UrlLib.FLAG1) {
					L.info(TAG, "startdate="
							+ start_timeET.getText().toString() + "&enddate="
							+ end_timeET.getText().toString());
					Intent intent = new Intent(TimeQueryActivity.this,
							TotalRainActivity.class);
					intent.putExtra("startdate", start_timeET.getText()
							.toString());
					intent.putExtra("enddate", end_timeET.getText().toString());
					
					intent.putExtra("city", city);
//					sp.saveStringValueToSp("FLAG1_START_TIME", start_timeET
//							.getText().toString());
//					sp.saveStringValueToSp("FLAG1_END_TIME", end_timeET
//							.getText().toString());
					
					setResult(1, intent);
					finish();
				} else if (intent.getFlags() == UrlLib.FLAG2) {//水文预报自定义
					L.info(TAG, "startdate="
							+ start_timeET.getText().toString() + "&enddate="
							+ end_timeET.getText().toString() + "&rvsectId="
							+ rvsectId);
					Intent intent = new Intent(TimeQueryActivity.this,
							WaterForcastActivity.class);
					intent.putExtra("startdate", start_timeET.getText()
							.toString());
					intent.putExtra("enddate", end_timeET.getText().toString());
//					sp.saveStringValueToSp("FLAG2_START_TIME", start_timeET
//							.getText().toString());
//					sp.saveStringValueToSp("FLAG2_END_TIME", end_timeET
//							.getText().toString());
					intent.putExtra("rvsectId", rvsectId);
					setResult(2, intent);
					finish();
				}
			}
		} else if (v == start_timeET) {
			Calendar cal = Calendar.getInstance();
			String strdate=start_timeET.getText().toString();
			
			start_time_dialog = new CustomDatePickerDialog(TimeQueryActivity.this,
					this, Integer.parseInt(strdate.substring(0, 4)), Integer.parseInt(strdate.substring(5, 7))-1,
					Integer.parseInt(strdate.substring(8, 10)));
			start_time_dialog.setTitle("请选择开始日期");
			s=true;
			start_time_dialog.show();
		} else if (v == end_timeET) {
			Calendar cal = Calendar.getInstance();
			String strdate=end_timeET.getText().toString();
			end_time_dialog = new CustomDatePickerDialog(TimeQueryActivity.this,
					this, Integer.parseInt(strdate.substring(0, 4)), Integer.parseInt(strdate.substring(5, 7))-1,
					Integer.parseInt(strdate.substring(8, 10)));
			end_time_dialog.setTitle("请选择结束日期");
			s=true;
			end_time_dialog.show();
		}

	}
	private boolean validate_time(){
		start_time = start_timeET.getText().toString();
		end_time = end_timeET.getText().toString();
		if (start_time == null || start_time.equals("")) {
			showToast("请选择开始时间");
			return false;
		}
		if (end_time == null || end_time.equals("")) {
			showToast("请选择结束时间");
			return false;
		}
		if (start_time != null && end_time != null) {
			String[] a = start_time.split("-");
			String[] b = end_time.split("-");
			L.info(TAG, a[0] + "," + a[1] + "," + a[2] +"," + b[0] + ","
					+ b[1] + "," + b[2]);

			if (Integer.parseInt(a[0]) > Integer.parseInt(b[0])) {
				showToast("起始时间必须小于结束时间");
				return false;
			} else if (Integer.parseInt(a[0]) == Integer.parseInt(b[0])) {
				if (Integer.parseInt(a[1]) > Integer.parseInt(b[1])) {
					showToast("起始时间必须小于结束时间");
					return false;
				} else if (Integer.parseInt(a[1]) == Integer.parseInt(b[1])) {
					if (Integer.parseInt(a[2]) > Integer.parseInt(b[2])) {
						showToast("起始时间必须小于结束时间");
						return false;
					}
					else if (a.length>3&&b.length>3)//有小时
					{
					 if (Integer.parseInt(a[2]) == Integer.parseInt(b[2])) {
						if (Integer.parseInt(a[3].substring(0,2)) > Integer.parseInt(b[3].substring(0, 2))) {
							showToast("起始时间必须小于结束时间");
							return false;
						}
					}
					}	
				}
			}
		}
		return true;
	}
	
	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		// TODO Auto-generated method stub
		L.info(TAG, "start_time_dialog=" + start_time_dialog);
		L.info(TAG, "end_time_dialog=" + end_time_dialog);
		String month="";
		String day = "";
		if (start_time_dialog != null) {
			if((monthOfYear + 1)<10){
				month= "0"+(monthOfYear+1);
			}else{
				month = ""+(monthOfYear+1);
			}
			if(dayOfMonth<10){
				day  = "0"+dayOfMonth;
			}else{
				day = ""+dayOfMonth;
			}
			String tempDatetime;
			tempDatetime=start_timeET.getText().toString();
			start_timeET.setText("" + year + "-" +month + "-"	+ day);
			start_time_dialog.dismiss();
			start_time_dialog = null;
			
			
			//累计雨量时间处理
			if (intent.getFlags() != UrlLib.FLAG2) {
				
			
		
			str = + year + "-" +month + "-"	+ day;
			if (tempDatetime.length()>12)
			start_timeET.setText(str+"-"+tempDatetime.substring(11,13)+":00");
			else
				start_timeET.setText(str+"-08:00");	
			
			Calendar time = Calendar.getInstance();
			Dialog timeDialog = new TimePickerDialog(TimeQueryActivity.this,
					new TimePickerDialog.OnTimeSetListener() {
						@Override
						public void onTimeSet(TimePicker tp, int hourOfDay,
								int minute) {
							
							if (str.length()<14)//不知道为啥触发2次
							{	
							 if (hourOfDay<=9)
								 str = str+"-0"+hourOfDay + ":00" ;
							 else
							  str = str+"-"+hourOfDay + ":00" ;
							}
							if (str.length()>12)
							start_timeET.setText(str);
							
						}
					}
					// 设置初始时间
					, time.get(Calendar.HOUR_OF_DAY), 0
					// true表示采用24小时制
					, true);
			
			timeDialog.setTitle("请选择时间");
			//不知道为啥触发2次
			
			if (s)
			{
			timeDialog.show();
			s=false;
			}
			
			}
			
			
			
			
			
			
			
			
			
			
		}
		if (end_time_dialog != null) {
			if((monthOfYear + 1)<10){
				month= "0"+(monthOfYear+1);
			}else{
				month = ""+(monthOfYear+1);
			}
			if(dayOfMonth<10){
				day  = "0"+dayOfMonth;
			}else{
				day = ""+dayOfMonth;
			}
			String tempEDatetime;
			tempEDatetime=end_timeET.getText().toString();
			end_timeET.setText("" + year + "-" + month + "-"
					+ day);
			end_time_dialog.dismiss();
			end_time_dialog = null;
			
			//累计雨量时间处理
			if (intent.getFlags() != UrlLib.FLAG2) {
			
			str = + year + "-" +month + "-"	+ day;
			if (tempEDatetime.length()>12)
			end_timeET.setText(str+"-"+tempEDatetime.substring(11,13)+":00");
			else
				end_timeET.setText(str+"-08:00");	
			Calendar time = Calendar.getInstance();
			Dialog timeDialog = new TimePickerDialog(TimeQueryActivity.this,
					new TimePickerDialog.OnTimeSetListener() {
						@Override
						public void onTimeSet(TimePicker tp, int hourOfDay,
								int minute) {
							
							if (str.length()<14)//不知道为啥触发2次
							{
								 if (hourOfDay<=9)
									 str = str+"-0"+hourOfDay + ":00" ;
								 else
								  str = str+"-"+hourOfDay + ":00" ;
							}
							if (str.length()>12)
							end_timeET.setText(str);
							
						}
					}
					// 设置初始时间
					, time.get(Calendar.HOUR_OF_DAY), 0
					// true表示采用24小时制
					, true);
			
			timeDialog.setTitle("请选择时间");
			//不知道为啥触发2次
			
			if (s)
			{
			timeDialog.show();
			s=false;
			}
			
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
	}

	private void showToast(String msg) {
		Toast.makeText(TimeQueryActivity.this, msg, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		if (intent.getFlags() == UrlLib.FLAG2) {
		  if (UrlLib.mkey != null&&UrlLib.mkey.size()>0)
			rvsectId = UrlLib.mkey.get(position);}
		else if(intent.getFlags() == UrlLib.FLAG1) {
		  if(UrlLib.cityName !=null&&UrlLib.cityName.size()>0)
		   
		  city = UrlLib.areaBeanList.get(position).getId();
		}
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}

}
