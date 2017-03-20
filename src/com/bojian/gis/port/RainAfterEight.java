package com.bojian.gis.port;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.bojian.gis.debug.L;
import com.bojian.gis.entity.RainDetailBean;
import com.bojian.gis.json.JSONArray;
import com.bojian.gis.json.JSONObject;
import com.bojian.gis.ui.MActivity;
import com.bojian.gis.util.UrlLib;

public class RainAfterEight extends Upload {
	
	private static final String TAG = "RainAfterEight";
	private RainDetailBean mRainDetailBean;
	private  ArrayList<RainDetailBean> list;

	public  ArrayList<RainDetailBean> getList() {
		return list;
	}

	public void setList(ArrayList<RainDetailBean> list) {
		this.list = list;
	}

	public RainAfterEight(String username,String password,int city){
		this.username = username;
		this.password = password;
		this.city = city;
		
	}	
	
	@Override
	public void getDataBase(InputStreamReader isr) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<RainDetailBean> nlist = new ArrayList<RainDetailBean>();
		StringBuilder builder = new StringBuilder();
		BufferedReader br = new BufferedReader(isr);
		for (String s = br.readLine(); s != null; s = br.readLine())
		{
			builder.append(s);
		}
		L.info(TAG, "RainAfterEight = "+builder.toString());
		JSONObject jsonObject = new JSONObject(builder.toString().substring(1, builder.toString().length()-1));
		int code = jsonObject.getInt("code");
		L.info(TAG, "code="+code);
		if(code!=0)
			return;
		JSONArray jsonArray  = jsonObject.getJSONArray("data");
		String sitename = "";
		String siteid = "";
		String siteadd="";
		String rainfall = "";
		String lon="";
		String lat = "";
		for(int i =0;i<jsonArray.length();i++){
			JSONObject mJsonObject = jsonArray.getJSONObject(i);
			mRainDetailBean = new RainDetailBean();
			
			sitename = mJsonObject.getString("sitename");
			siteid = mJsonObject.getString("siteid");
			siteadd = mJsonObject.getString("siteadd");
			rainfall = mJsonObject.getString("rainfall");
			lon = mJsonObject.getString("lon");
			lat = mJsonObject.getString("lat");
			L.info(TAG, "sitename = "+sitename);
			L.info(TAG, "siteid = "+siteid);
			L.info(TAG, "siteadd = "+siteadd);
			L.info(TAG, "rainfall = "+rainfall);
			L.info(TAG, "lon = "+lon);
			L.info(TAG, "lat = "+lat);
			
			mRainDetailBean.setSitename(sitename);
			mRainDetailBean.setSiteid(siteid);
			mRainDetailBean.setSiteadd(siteadd);
			mRainDetailBean.setRainfall(rainfall);
			mRainDetailBean.setLat(lat);
			mRainDetailBean.setLon(lon);
			
			nlist.add(mRainDetailBean);
		}
		this.list = nlist;
		
		MActivity.list = nlist;
	
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return UrlLib.getAfterEight(username, password, city);
	}

}
