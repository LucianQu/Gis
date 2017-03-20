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
import com.bojian.gis.entity.WaterUsingBean;
import com.bojian.gis.ui.BaseActivity;
import com.bojian.gis.util.UrlLib;

public class WaterusingAdapter extends BaseAdapter{
	private static final String TAG="";
	private List<WaterUsingBean> list;
	private BaseActivity baseActivity;
	private LayoutInflater mLayoutInflater;
	ViewHolder holder;

	public WaterusingAdapter(BaseActivity b, List<WaterUsingBean> list) {
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
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mLayoutInflater.inflate(
					R.layout.water_using_list_adapter, null);
			holder.name = (TextView) convertView.findViewById(R.id.water_using_name);
			holder.waterconsumption = (TextView) convertView.findViewById(R.id.water_using_water);
			holder.startpumptime = (TextView) convertView.findViewById(R.id.water_using_starttm);
			holder.shutpumptime = (TextView) convertView.findViewById(R.id.water_using_offtm);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		WaterUsingBean mImportWaterUsingBean = list.get(position);
		L.info(TAG, "BEAN="+mImportWaterUsingBean);
		if(mImportWaterUsingBean!=null){
			holder.name.setText(mImportWaterUsingBean.getName());
			holder.waterconsumption.setText(mImportWaterUsingBean.getWaterconsumption());
			
			holder.startpumptime.setText(mImportWaterUsingBean.getStartpumptime());
			holder.shutpumptime.setText(mImportWaterUsingBean.getShutpumptime());
		}
		
		return convertView;
	}
	
	static class ViewHolder {
		TextView name;
		TextView waterconsumption;
		TextView startpumptime;
		TextView shutpumptime;
		TextView type;
		
	}
	
	
	


}
