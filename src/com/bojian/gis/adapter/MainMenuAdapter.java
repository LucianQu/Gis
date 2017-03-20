package com.bojian.gis.adapter;

import java.util.List;
import com.bojian.gis.R;
import com.bojian.gis.entity.ItemBean;
import com.bojian.gis.ui.BaseActivity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainMenuAdapter extends BaseAdapter {
	private BaseActivity baseActivity;
	private LayoutInflater mLayoutInflater;
	private List<ItemBean> mList;
	private int mTextColor=Color.BLACK;
	private int warntag=-1;
	private int rewarntag=-1;
	ViewHolder holder;

	public MainMenuAdapter(BaseActivity b, List<ItemBean> list) {
		this.baseActivity = b;
		mLayoutInflater = (LayoutInflater) baseActivity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		setList(list);
	}

	

	public void addList(List<ItemBean>nList) {
		mList.addAll(nList);
	}

	public void setList(List<ItemBean> list) {
		// TODO Auto-generated method stub
		this.mList = list;
	}

	public List<ItemBean> getList() {
		return mList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
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
			convertView = mLayoutInflater.inflate(R.layout.main_menu_adapter,
					null);
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.main_menu_adapter_imageview);
			holder.textView = (TextView) convertView
					.findViewById(R.id.main_menu_adapter_textview);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		ItemBean item = mList.get(position);
		if(item!=null){
			holder.textView.setText(item.getName());
			holder.imageView.setImageResource(item.getId());
			
			if (position==0||position==1)
			{
				holder.textView.setTextColor(Color.BLACK);
				
				rewarntag=-1;
			}
			
			
			//ÉÁË¸×ÖÌå
			if (warntag>1)
			{
			  if (position==0 ||position==1)
					  {
				        holder.textView.setTextColor(mTextColor);
					  }
			}else{
				if (position==warntag)
				{
					holder.textView.setTextColor(mTextColor);
					
				}
				else
				{
					
				}
				
			}
			
			
			
		}else{
			holder.textView.setText(R.string.item_default_text);
			holder.imageView.setImageResource(R.drawable.desktop_icon);
		}
		return convertView;
	}

	public void setmTextColor(int mTextColor) {
		this.mTextColor = mTextColor;
	}

	public int getmTextColor() {
		return mTextColor;
	}

	public void setWarntag(int warntag) {
		this.warntag = warntag;
	}

	public int getWarntag() {
		return this.warntag;
	}

	 public void setRewarntag(int rewarntag) {
		this.rewarntag = rewarntag;
	}



	public int getRewarntag() {
		return rewarntag;
	}

	static class ViewHolder {
		 ImageView imageView;
		 TextView textView;
	}
}
