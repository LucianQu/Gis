package com.bojian.gis.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bojian.gis.R;
import com.bojian.gis.debug.L;
import com.bojian.gis.entity.WaterWarnBean;
import com.bojian.gis.ui.BaseActivity;

public class RainDetailMxAdapter extends BaseAdapter{
	
	private static final String TAG = "RainDetailMxAdapter";
	private List<WaterWarnBean> list;
	private BaseActivity baseActivity;
	private LayoutInflater mLayoutInflater;
	ViewHolder holder;

	public RainDetailMxAdapter(BaseActivity b, List<WaterWarnBean> list) {
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
					R.layout.rain_detail_mx_adapter, null);
			holder.sitename = (TextView)convertView.findViewById(R.id.water_warn_sitename);
			holder.siteadd = (TextView)convertView.findViewById(R.id.water_warn_siteadd);
			holder.time = (TextView)convertView.findViewById(R.id.water_warn_level);
			holder.rainfall =(TextView)convertView.findViewById(R.id.water_warn_waterlevel);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		WaterWarnBean mWaterWarnBean = list.get(position);
		L.info(TAG, "BEAN="+mWaterWarnBean);
		if(mWaterWarnBean!=null){
			holder.sitename.setText(mWaterWarnBean.getSitename());
			holder.siteadd.setText(mWaterWarnBean.getSiteadd());
			holder.time.setText(mWaterWarnBean.getLevel().substring(5, 13)+"ʱ");
			holder.rainfall.setText(mWaterWarnBean.getWaterlevel());
		}
		return convertView;
	}


	static class ViewHolder {
		TextView sitename;
		TextView siteadd;
		TextView time;
		TextView rainfall;
	}

	
	
}
