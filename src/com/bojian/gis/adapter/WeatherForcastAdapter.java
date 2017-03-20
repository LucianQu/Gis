package com.bojian.gis.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bojian.gis.R;
import com.bojian.gis.entity.WeatherBean;
import com.bojian.gis.ui.BaseActivity;

public class WeatherForcastAdapter extends BaseAdapter {

	private List<WeatherBean> list;
	private BaseActivity baseActivity;
	private LayoutInflater mLayoutInflater;
	ViewHolder holder;

	public WeatherForcastAdapter(BaseActivity b, List<WeatherBean> list) {
		super();
		this.list = list;
		this.baseActivity = b;
		mLayoutInflater = (LayoutInflater) baseActivity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mLayoutInflater.inflate(
					R.layout.weather_forcast_lis_adapter, null);
			holder.week = (TextView) convertView.findViewById(R.id.weather_week);
			holder.weather = (TextView)convertView.findViewById(R.id.weather_title);
			holder.temp =(TextView) convertView.findViewById(R.id.weather_temp);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		WeatherBean mWeather = list.get(position);
		if(mWeather!=null){
			holder.week.setText(mWeather.getDate_y());
			holder.weather.setText(mWeather.getWeather());
			holder.temp.setText(mWeather.getTemp());
		}
		return convertView;
	}

	static class ViewHolder {
		TextView week;
		TextView weather;
		TextView temp;
	}

}
