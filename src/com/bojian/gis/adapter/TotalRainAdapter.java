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
import com.bojian.gis.entity.TotalRainBean;
import com.bojian.gis.ui.BaseActivity;

public class TotalRainAdapter extends BaseAdapter{

	private static final String TAG = "TotalRainAdapter";
	private List<TotalRainBean> list;
	private BaseActivity baseActivity;
	private LayoutInflater mLayoutInflater;
	ViewHolder holder;
	
	public TotalRainAdapter(BaseActivity b, List<TotalRainBean> list) {
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
					R.layout.total_rainfall_list_adapter, null);
			holder.name = (TextView) convertView.findViewById(R.id.total_rainfall_name);
			holder.lastrain = (TextView) convertView.findViewById(R.id.total_rainfall_last);
			holder.yearrain = (TextView) convertView.findViewById(R.id.total_rainfall_year);
			holder.afteryearrain = (TextView) convertView.findViewById(R.id.total_rainfall_afteryear);
			holder.allyearrain = (TextView) convertView.findViewById(R.id.total_rainfall_allyear);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		TotalRainBean mImportRiverBean = list.get(position);
		L.info(TAG, "BEAN="+mImportRiverBean);
		if(mImportRiverBean!=null){
			int color ;
			if (mImportRiverBean.getType().equals("3"))
				color =Color.BLACK;		
				else	
					color =Color.RED;
			holder.name.setTextColor(color);
			holder.lastrain.setTextColor(color);
			holder.yearrain.setTextColor(color);
			holder.afteryearrain.setTextColor(color);
			holder.allyearrain.setTextColor(color);
			
			holder.name.setText(mImportRiverBean.getName());
			holder.lastrain.setText(mImportRiverBean.getTotalrain_last());
			if (mImportRiverBean.getType().equals("3"))
			holder.yearrain.setText("");
			else
				holder.yearrain.setText(mImportRiverBean.getTotalrain_year());	
			if (mImportRiverBean.getType().equals("3"))
				holder.afteryearrain.setText("");
			else
			holder.afteryearrain.setText(mImportRiverBean.getTotalrain_afteryear());
			
			if (mImportRiverBean.getType().equals("3"))
				holder.allyearrain.setText("");
			else
			holder.allyearrain.setText(mImportRiverBean.getTotalrain_allyear());
		}
		return convertView;
	}
	
	static class ViewHolder {
		TextView name;
		TextView lastrain;
		TextView yearrain;
		TextView afteryearrain;
		TextView allyearrain;
		
	}
}
