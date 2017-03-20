package com.bojian.gis.ui;

import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bojian.gis.R;
import com.bojian.gis.SyncService;
import com.bojian.gis.adapter.WeatherForcastAdapter;
import com.bojian.gis.debug.L;
import com.bojian.gis.entity.WeatherBean;
import com.bojian.gis.net.ExceptionInfo;
import com.bojian.gis.port.WeatherInfo;
import com.bojian.gis.util.Convertor;
import com.bojian.gis.util.LocationUtil;
import com.bojian.gis.util.UrlLib;

public class WeatherForcastActivity extends BaseActivity implements
		OnClickListener {
	private static final String TAG = "WeatherForcastActivity";
	private Button backBtn;
	private TextView titleTv;
	private Button otherBtn;
	private SyncService ss;
	private WeatherInfo wf;
	private ListView listview;
	private List<WeatherBean> list;
	private WeatherForcastAdapter adapter;
	private TextView weather_location;
	private TextView update_time;
	private TextView temp;
	private WeatherBean head;
	private ImageView weather_pic;
	private LocationUtil locationUtil;
	private Location location;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weather_forcast);
		init_titleber();
		init();
		startLoad();
	}

	private void init() {
		// TODO Auto-generated method stub
		listview = (ListView) findViewById(R.id.weather_forcast_listview);
		weather_location = (TextView) findViewById(R.id.weather_location);
		update_time = (TextView) findViewById(R.id.weather_updatetime);
		temp = (TextView) findViewById(R.id.weather_temp);
		weather_pic = (ImageView) findViewById(R.id.weather_pic);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	private void startLoad() {
		wf = new WeatherInfo("101090908");
		ss = new SyncService(this, wf);
		ss.execute();
	}

	private void init_titleber() {
		// TODO Auto-generated method stub
		backBtn = (Button) findViewById(R.id.titlebar_back);
		backBtn.setOnClickListener(this);
		titleTv = (TextView) findViewById(R.id.titlebar_title);
		titleTv.setText(R.string.weather_forcast);
		otherBtn = (Button) findViewById(R.id.titlebar_other);
		otherBtn.setBackgroundResource(R.drawable.titlebar_customer_bg);
		otherBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == backBtn) {
			finish();
		} else if (v == otherBtn) {
			Intent intent = new Intent();
			intent.addFlags(UrlLib.FLAG3);
			intent.setClass(WeatherForcastActivity.this,
					CityQueryActivity.class);
			startActivityForResult(intent, 3);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		String cityName = "";
		String cityCode = "";
		if (resultCode == 3) {
			cityName = data.getStringExtra("city");
			if (cityName != null)
				cityCode = Convertor.getCityCode(cityName);
			if (cityCode != null) {
				wf = new WeatherInfo(cityCode);
				new SyncService(WeatherForcastActivity.this, wf).execute();
			} else {
				Toast.makeText(WeatherForcastActivity.this, "Œ¥’“µΩ∏√µÿ«¯",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	@Override
	public void onDataRequestSuccess() {
		// TODO Auto-generated method stub
		super.onDataRequestSuccess();
		setList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bojian.gis.ui.BaseActivity#onDataRequestFail(com.bojian.gis.net.
	 * ExceptionInfo)
	 */
	@Override
	public void onDataRequestFail(ExceptionInfo error) {
		// TODO Auto-generated method stub
		super.onDataRequestFail(error);
	}

	private void setList() {
		// TODO Auto-generated method stub
		list = wf.getnList();
		head = wf.getHead();
		L.info(TAG, "head = " + head + "&list=" + list);
		if (head != null && list != null && list.size() > 0) {
			weather_location.setText(head.getCity());
			update_time.setText("" + head.getDate_y()+"    "+head.getWeather());
			weather_pic.setImageResource(getWeatherDrawable(head.getWeather()));
			temp.setText(head.getTemp());
			adapter = new WeatherForcastAdapter(this, list);
			listview.setAdapter(adapter);
		} else {
			Toast.makeText(WeatherForcastActivity.this, "«Î«Û ˝æ› ß∞‹",
					Toast.LENGTH_SHORT).show();
		}
	}

	private int getWeatherDrawable(String str) {
		int ids[] = { R.drawable.weather_sunny, R.drawable.weather_rain,
				R.drawable.weather_cloudy, R.drawable.weather_thunder_rain,
				R.drawable.weather_snowy };
		if (str == null)
			return ids[0];
		if (str.equals("«Á")) {
			return ids[0];
		} else if (str.equals("∂‡‘∆")||str.contains("∂‡‘∆")) {
			return ids[2];
		}else if(str.equals("¿◊’Û”Í")||str.contains("¿◊’Û”Í")){
			return ids[3];
		}else if(str.equals("’Û”Í")||str.contains("’Û”Í")){
			return ids[1];
		}else if(str.contains("—©")){
			return ids[4];
		}else if(str.contains("”Í")){
			return ids[1];	
		}else{
			return ids[0];
		}
	}

}
