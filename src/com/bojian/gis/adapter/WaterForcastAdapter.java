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
import com.bojian.gis.entity.WaterForcastBean;
import com.bojian.gis.ui.BaseActivity;
import com.bojian.gis.util.UrlLib;

public class WaterForcastAdapter extends BaseAdapter{
	private static final String TAG="";
	private List<WaterForcastBean> list;
	private BaseActivity baseActivity;
	private LayoutInflater mLayoutInflater;
	ViewHolder holder;

	public WaterForcastAdapter(BaseActivity b, List<WaterForcastBean> list) {
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
					R.layout.head_of_water_forcast_adapter, null);
			holder.sitename = (TextView) convertView.findViewById(R.id.water_forcast_sitename);
			holder.time = (TextView) convertView.findViewById(R.id.water_forcast_time);
			//holder.latestrainfall = (TextView) convertView.findViewById(R.id.water_forcast_latest_rainfall);
			holder.peakdischarge = (TextView) convertView.findViewById(R.id.water_forcast_peak_discharge);
			holder.floodvalue = (TextView) convertView.findViewById(R.id.water_forcast_floodvalue);
			//holder.duration = (TextView) convertView.findViewById(R.id.water_forcast_duration);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		WaterForcastBean mImportRiverBean = list.get(position);
		L.info(TAG, "BEAN="+mImportRiverBean);
		if(mImportRiverBean!=null){
			holder.sitename.setText(mImportRiverBean.getSitename());
			
			//holder.latestrainfall.setText(mImportRiverBean.getLatestrainfall());
			holder.peakdischarge.setText(mImportRiverBean.getPeakdischarge());
			holder.floodvalue.setText(mImportRiverBean.getFloodvalue());
			//holder.duration.setText(mImportRiverBean.getDuration());
			holder.time.setText(mImportRiverBean.getTime().substring(5, 13)+"ʱ");
		}
		
		return convertView;
	}
	
	static class ViewHolder {
		TextView sitename;
		
		//TextView latestrainfall;
		TextView peakdischarge;
		TextView floodvalue;
		//TextView duration;
		TextView time;
	}
	
	
	


}
