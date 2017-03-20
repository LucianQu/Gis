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
		TextView sssq;//����
		TextView skmc;//ˮ������
		TextView lymj; //�������
		TextView xlsw;//����ˮλ
		TextView xlkr;//��������
		TextView xhsw;//У��ˮλ
		TextView xhkr;//У�˿���
		TextView jjsw;//����ˮλ
		TextView jjkr;//�������
		TextView yhxs;//�����ʽ
		TextView xxsw;//Ѵ��ˮλ
		TextView xxkr;//Ѵ�޿���
		TextView bxxsw;//��Ѵ��ˮλ
		
		TextView sssw;//ʵʱˮλ
		TextView xsl;//��ˮ��
		TextView bxxkr;//��Ѵ�޿���
		TextView ssd;//��ˮ��
		TextView yhd;//����
		
		HorizontalScrollView scrollView;
	}

}
