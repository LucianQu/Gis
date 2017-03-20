package com.bojian.gis.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bojian.gis.R;
import com.bojian.gis.debug.L;
import com.bojian.gis.entity.WaterWarnBean;
import com.bojian.gis.ui.BaseActivity;

public class WaterWarnAdapter extends BaseAdapter{

	
	private static final String TAG = "WaterWarnAdapter";
	private List<WaterWarnBean> list;
	private BaseActivity baseActivity;
	private LayoutInflater mLayoutInflater;
	ViewHolder holder;
	
	public WaterWarnAdapter(BaseActivity b, List<WaterWarnBean> list) {
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
					R.layout.head_of_water_warn_list_adapter, null);
			holder.sitename = (TextView) convertView.findViewById(R.id.water_warn_sitename);
			holder.siteadd = (TextView) convertView.findViewById(R.id.water_warn_siteadd);
			holder.level = (TextView) convertView.findViewById(R.id.water_warn_level);
			holder.waterlevel = (TextView) convertView.findViewById(R.id.water_warn_waterlevel);
			holder.earlywarningvalue = (TextView) convertView.findViewById(R.id.water_warn_earlywarningvalue);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		WaterWarnBean mWaterWarnBean = list.get(position);
		L.info(TAG, "BEAN="+mWaterWarnBean);
		if(mWaterWarnBean!=null){
			int color = Color.BLACK;
			if (mWaterWarnBean.getLevel().equals("1"))
			color=Color.RED;
			else if (mWaterWarnBean.getLevel().equals("2"))
				color=0xFFFFA500;
			else if (mWaterWarnBean.getLevel().equals("3"))
				color=Color.BLUE;
				
			holder.sitename.setTextColor(color);
			holder.siteadd.setTextColor(color);
			holder.level.setTextColor(color);
			holder.waterlevel.setTextColor(color);
			holder.earlywarningvalue.setTextColor(color);
			
			holder.sitename.setText(mWaterWarnBean.getSitename());
			holder.siteadd.setText(mWaterWarnBean.getSiteadd());
			holder.level.setText(mWaterWarnBean.getLevel());
			holder.waterlevel.setText(mWaterWarnBean.getWaterlevel());
			holder.earlywarningvalue.setText(mWaterWarnBean.getEarlywarningvalue());
		}
		return convertView;
	}
	
	
	static class ViewHolder {
		TextView sitename;
		TextView siteadd;
		TextView level;
		TextView waterlevel;
		TextView earlywarningvalue;
	}

}
