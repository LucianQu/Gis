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
import com.bojian.gis.entity.ImportRiverBean;
import com.bojian.gis.ui.BaseActivity;

public class ImportRiverAdapter extends BaseAdapter{
	private static final String TAG = "ImportRiverAdapter";
	private List<ImportRiverBean> list;
	private BaseActivity baseActivity;
	private LayoutInflater mLayoutInflater;
	ViewHolder holder;
	
	public ImportRiverAdapter(BaseActivity b, List<ImportRiverBean> list) {
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
					R.layout.head_of_import_river_list_adapter, null);
			holder.sitename = (TextView) convertView.findViewById(R.id.import_river_site_name);
			holder.siteadd = (TextView) convertView.findViewById(R.id.import_river_site_address);
			holder.time = (TextView) convertView.findViewById(R.id.import_river_time);
			holder.waterlevel = (TextView) convertView.findViewById(R.id.import_river_water_level);
			holder.warninglevel = (TextView) convertView.findViewById(R.id.import_river_warning_level);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		ImportRiverBean mImportRiverBean = list.get(position);
		L.info(TAG, "BEAN="+mImportRiverBean);
		if(mImportRiverBean!=null){
			holder.sitename.setText(mImportRiverBean.getSitename());
			holder.siteadd.setText(mImportRiverBean.getSiteadd());
			holder.time.setText(mImportRiverBean.getTime().substring(5, 13)+"ʱ");
			holder.waterlevel.setText(mImportRiverBean.getWaterlevel());
			holder.warninglevel.setText(mImportRiverBean.getWarninglevel());
		}
		return convertView;
	}

	
	static class ViewHolder {
		TextView sitename;
		TextView siteadd;
		TextView time;
		TextView waterlevel;
		TextView warninglevel;
	}
	
}
