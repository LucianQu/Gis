package com.bojian.gis.adapter;

import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bojian.gis.R;
import com.bojian.gis.debug.L;
import com.bojian.gis.entity.PumpOnoffBean;
import com.bojian.gis.port.PumpOnoff;
import com.bojian.gis.ui.BaseActivity;
import com.bojian.gis.ui.PumpOnoffActivity;
import com.bojian.gis.util.UrlLib;

public class PumpOnoffAdapter extends BaseAdapter{
	private static final String TAG="";
	private List<PumpOnoffBean> list;
	private BaseActivity baseActivity;
	private LayoutInflater mLayoutInflater;
	private Context context;
	private MyClickListener mListener;
	ViewHolder holder;


	public PumpOnoffAdapter(BaseActivity b, List<PumpOnoffBean> list,Context context,MyClickListener listener) {
		super();
		this.list = list;
		this.baseActivity = b;
		this.context = context;
		mLayoutInflater = (LayoutInflater) context
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
					R.layout.pumponoff_list_adapter, null);
			holder.wellno = (TextView) convertView.findViewById(R.id.well_no);
			holder.wellname = (TextView) convertView.findViewById(R.id.well_name);
			holder.viewbtn_on = (Button)convertView.findViewById(R.id.view_btn_on); 
			holder.viewbtn_off = (Button)convertView.findViewById(R.id.view_btn_off); 
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		PumpOnoffBean mImportRiverBean = list.get(position);
		holder.viewbtn_on.setTag(position);  
		holder.viewbtn_on.setOnClickListener(mListener);
		holder.viewbtn_off.setTag(position);  
		holder.viewbtn_off.setOnClickListener(mListener);
		
		L.info(TAG, "BEAN="+mImportRiverBean);
		if(mImportRiverBean!=null){
			holder.wellno.setText(mImportRiverBean.getWellno());
			holder.wellname.setText(mImportRiverBean.getWellname());
		}
		return convertView;
	}

	static class ViewHolder {
		TextView wellno;
		TextView wellname;
		TextView welladdr;
		TextView pumpontime;
		TextView pumpofftime;
		TextView type;
	    Button viewbtn_on;
	    Button viewbtn_off;
		
		
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
            switch(v.getId()){
            case R.id.view_btn_on:
            	System.out.println("开泵");
            	break;
            case R.id.view_btn_off:
            	System.out.println("关泵");
            	break;
            }
            myOnClick((Integer) v.getTag(),v);
		}
		public abstract void myOnClick(int position,View v);
	}
	
	
	


}
