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
import com.bojian.gis.entity.ReChargeBean;
import com.bojian.gis.ui.BaseActivity;
import com.bojian.gis.util.UrlLib;

public class ReChargeAdapter extends BaseAdapter{
	private static final String TAG="";
	private List<ReChargeBean> list;
	private BaseActivity baseActivity;
	private LayoutInflater mLayoutInflater;
	ViewHolder holder;

	public ReChargeAdapter(BaseActivity b, List<ReChargeBean> list) {
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
					R.layout.recharge_list_adapter, null);
			holder.name = (TextView) convertView.findViewById(R.id.recharge_name);
			holder.dealtime = (TextView) convertView.findViewById(R.id.recharge_dealtime);
			holder.dealingprice = (TextView) convertView.findViewById(R.id.recharge_dealingprice);
			holder.dealingprice_E = (TextView) convertView.findViewById(R.id.recharge_dealingprice_E);
			holder.remainPrice = (TextView) convertView.findViewById(R.id.recharge_remainPrice);
			holder.remainPrice_E = (TextView) convertView.findViewById(R.id.recharge_remainPrice_E);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		ReChargeBean mImportReChargeBean = list.get(position);
		L.info(TAG, "BEAN="+mImportReChargeBean);
		if(mImportReChargeBean!=null){
			holder.name.setText(mImportReChargeBean.getName());
			holder.dealtime.setText(mImportReChargeBean.getDealtime());
			holder.dealingprice.setText(mImportReChargeBean.getDealingprice());
			holder.dealingprice_E.setText(mImportReChargeBean.getDealingprice_E());
			holder.remainPrice.setText(mImportReChargeBean.getRemainPrice());
			holder.remainPrice_E.setText(mImportReChargeBean.getRemainPrice_E());
		}
		
		return convertView;
	}
	
	static class ViewHolder {
		TextView name;
		TextView dealtime;
		TextView dealingprice;
		TextView dealingprice_E;
		TextView remainPrice;
		TextView remainPrice_E;
		
	}
	
	
	


}
