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
import com.bojian.gis.entity.RainDetailBean;
import com.bojian.gis.ui.BaseActivity;

public class RainDetailAdapter extends BaseAdapter{
	private static final String TAG = "RainDetailAdapter";
	private List<RainDetailBean> list;
	private BaseActivity baseActivity;
	private LayoutInflater mLayoutInflater;
	ViewHolder holder;
	
	public RainDetailAdapter(BaseActivity b, List<RainDetailBean> list) {
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
					R.layout.total_rainfallmx_list_adapter, null);
			holder.sitename = (TextView) convertView.findViewById(R.id.total_rainfall_sitename);
			holder.siteadd = (TextView) convertView.findViewById(R.id.total_rainfall_siteadd);
			holder.rainfall = (TextView) convertView.findViewById(R.id.total_rainfall_totalrainfall);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		RainDetailBean mImportRiverBean = list.get(position);
		L.info(TAG, "BEAN="+mImportRiverBean);
		if(mImportRiverBean!=null){
			holder.sitename.setText(mImportRiverBean.getSitename());
			holder.siteadd.setText(mImportRiverBean.getSiteadd());
			holder.rainfall.setText(mImportRiverBean.getRainfall());
		}
		return convertView;
	}
	
	static class ViewHolder {
		TextView sitename;
		TextView siteadd;
		TextView rainfall;
	}

}
