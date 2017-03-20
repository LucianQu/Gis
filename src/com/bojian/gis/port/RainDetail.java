package com.bojian.gis.port;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.bojian.gis.debug.L;
import com.bojian.gis.entity.RainDetailBean;
import com.bojian.gis.json.JSONArray;
import com.bojian.gis.json.JSONObject;
import com.bojian.gis.util.UrlLib;

public class RainDetail extends Upload{
	private static final String TAG = "RainDetail";
	private String hour;
	private String duration;
	private RainDetailBean mRainDetailBean;
	private ArrayList<RainDetailBean> list;
	
	
	/**
	 * @return the list
	 */
	public ArrayList<RainDetailBean> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(ArrayList<RainDetailBean> list) {
		this.list = list;
	}

	public RainDetail(String username,String password,int city,String hour,String duration){
		this.username = username;
		this.password = password;
		this.city = city;
		this.hour = hour;
		this.duration = duration;
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
		L.info(TAG, "builder = "+builder.toString());
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
			rainfall = mJsonObject.getString("totalrainfall");
			lon = mJsonObject.getString("lon");
			lat = mJsonObject.getString("lat");
			
			mRainDetailBean.setSitename(sitename);
			mRainDetailBean.setSiteid(siteid);
			mRainDetailBean.setSiteadd(siteadd);
			mRainDetailBean.setRainfall(rainfall);
			mRainDetailBean.setLat(lat);
			mRainDetailBean.setLon(lon);
			
			nlist.add(mRainDetailBean);
		}
		this.list = nlist;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return UrlLib.getRainDetailUrl(username, password, city, hour, duration);
	}

}
