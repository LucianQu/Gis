package com.bojian.gis.adapter;

import java.util.List;


import com.bojian.gis.R;
import com.bojian.gis.adapter.ImportRiverAdapter.ViewHolder;
import com.bojian.gis.debug.L;
import com.bojian.gis.entity.ImportRiverBean;
import com.bojian.gis.entity.ImportRiverBeanEx;
import com.bojian.gis.ui.BaseActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

public class ImportRiverExAdapter extends BaseAdapter {
	private static final String TAG = "ImportRiverAdapter";
	private List<ImportRiverBeanEx> list;
	private BaseActivity baseActivity;
	private LayoutInflater mLayoutInflater;
	ViewHolder holder;
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
		
		return convertView;
	}
	
	static class ViewHolder {
		TextView sssq;//市区
		TextView skmc;//水库名称
		TextView lymj; //流域面积
		TextView xlsw;//兴利水位
		TextView xlkr;//兴利库容
		TextView xhsw;//校核水位
		TextView xhkr;//校核库容
		TextView jjsw;//警戒水位
		TextView jjkr;//警戒库容
		TextView yhxs;//溢洪形式
		TextView xxsw;//汛限水位
		TextView xxkr;//汛限库容
		TextView bxxsw;//比汛限水位
		
		TextView sssw;//实时水位
		TextView xsl;//蓄水量
		TextView bxxkr;//比汛限库容
		TextView ssd;//输水洞
		TextView yhd;//溢洪道
		
		HorizontalScrollView scrollView;
	}

}
