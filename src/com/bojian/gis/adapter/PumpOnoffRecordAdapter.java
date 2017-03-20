package com.bojian.gis.adapter;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bojian.gis.R;
import com.bojian.gis.adapter.PumpOnoffAdapter.MyClickListener;
import com.bojian.gis.debug.L;
import com.bojian.gis.entity.PumponoffRecordBean;
import com.bojian.gis.ui.BaseActivity;
import com.bojian.gis.ui.PumpOnoffRecordActivity;
import com.bojian.gis.util.UrlLib;
import com.bojian.gis.adapter.PumpOnoffAdapter.MyClickListener;

public class PumpOnoffRecordAdapter extends BaseAdapter{
	private static final String TAG="";
	private List<PumponoffRecordBean> list;
	private BaseActivity baseActivity;
	private LayoutInflater mLayoutInflater;
	private MyClickListener mListener;
	ViewHolder holder;

	public PumpOnoffRecordAdapter(BaseActivity b, List<PumponoffRecordBean> list,MyClickListener listener) {
		super();
		this.list = list;
		this.baseActivity = b;
		mLayoutInflater = (LayoutInflater) baseActivity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mListener = listener;
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mLayoutInflater.inflate(
					R.layout.pumponoff_record_list_adapter, null);
			holder.wellno = (TextView) convertView.findViewById(R.id.well_no);
			holder.wellname = (TextView) convertView.findViewById(R.id.well_name);
			holder.welladdr = (TextView) convertView.findViewById(R.id.welladdr);
			holder.viewbtn = (Button)convertView.findViewById(R.id.view_btn); 
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		PumponoffRecordBean mImportRiverBean = list.get(position);
		holder.viewbtn.setTag(position);  
		holder.viewbtn.setOnClickListener(mListener);
		
		L.info(TAG, "BEAN="+mImportRiverBean);
		if(mImportRiverBean!=null){
			holder.wellno.setText(mImportRiverBean.getWellno());
			
			holder.wellname.setText(mImportRiverBean.getWellname());
			holder.welladdr.setText(mImportRiverBean.getWelladdr());
		}
		
		return convertView;
	}
	
	public void showInfo(int position){  
        
    }  
	
	static class ViewHolder {
		TextView wellno;
		TextView wellname;
		TextView welladdr;
		TextView pumpontime;
		TextView pumpofftime;
		TextView type;
	    Button viewbtn;
		
		
	}
	
	/**
	 * 用于回调的抽象类
	 * @author YanZhao Gao
	 * 2017-2-2
	 * */
	
	public static abstract class MyClickListener implements OnClickListener {
		/*
		 * 基类的onClick方法
		 * */
		@Override
		public void onClick(View v){
            myOnClick((Integer) v.getTag(),v);
		}
		public abstract void myOnClick(int position,View v);
	}
	
	
	


}
