package com.bojian.gis.port;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


import com.bojian.gis.entity.WaringChangeBean;
import com.bojian.gis.json.JSONArray;
import com.bojian.gis.json.JSONObject;
import com.bojian.gis.ui.BaseActivity;
import com.bojian.gis.util.UrlLib;
import com.bojian.gis.ui.MainMenuActivity;
public class WarningChange extends Upload {
	private ArrayList<WaringChangeBean> list;
	private BaseActivity mActivity;
	public WarningChange(BaseActivity activity,String username, String password, int city) {
		super(username, password, city);
		// TODO Auto-generated constructor stub
		this.username = username;
		this.password = password;
		this.city = city;
		this.mActivity = activity; 
	}
	
	
	@Override
	public void getDataBase(InputStreamReader isr) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<WaringChangeBean> nlist = new ArrayList<WaringChangeBean>();
		StringBuilder builder = new StringBuilder();
		BufferedReader br = new BufferedReader(isr);
		for (String s = br.readLine(); s != null; s = br.readLine())
		{
			builder.append(s);
		}
		
		JSONObject jsonObject = new JSONObject(builder.toString().substring(1, builder.toString().length()-1));
		int code = jsonObject.getInt("code");
		
		if(code!=0)
			return;
		JSONArray jsonArray  = jsonObject.getJSONArray("data");
		String id="";
		String type="";
		String stationID="";
		
		for(int i =0;i<jsonArray.length();i++){
			JSONObject mJsonObject = jsonArray.getJSONObject(i);
			WaringChangeBean mWaringChangeBean = new WaringChangeBean();
			id = mJsonObject.getString("id");
			type = mJsonObject.getString("type");
			stationID = mJsonObject.getString("stationId");
			
			
			mWaringChangeBean.setId(id);
			mWaringChangeBean.setType(type);
			mWaringChangeBean.setStationId(stationID);
			
			nlist.add(mWaringChangeBean);
		}
		this.list = nlist;
		((MainMenuActivity)mActivity).doWarnginginfo();
		
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return UrlLib.getWaringChange(city, username, password);
	}

	public void setList(ArrayList<WaringChangeBean> list) {
		this.list = list;
	}

	public ArrayList<WaringChangeBean> getList() {
		return list;
	}

}
