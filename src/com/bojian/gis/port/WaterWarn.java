package com.bojian.gis.port;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.bojian.gis.debug.L;
import com.bojian.gis.entity.WaterWarnBean;
import com.bojian.gis.json.JSONArray;
import com.bojian.gis.json.JSONObject;
import com.bojian.gis.util.UrlLib;

public class WaterWarn extends Upload{
	private static final String TAG = "WaterWarn";
	private WaterWarnBean mWaterWarnBean;
	private ArrayList<WaterWarnBean>list;
	
	


	/**
	 * @return the list
	 */
	public ArrayList<WaterWarnBean> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(ArrayList<WaterWarnBean> list) {
		this.list = list;
	}

	public WaterWarn(String username, String password, int city) {
		super(username, password, city);
		// TODO Auto-generated constructor stub
		this.username = username;
		this.password = password;
		this.city = city;
	}

	@Override
	public void getDataBase(InputStreamReader isr) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<WaterWarnBean> nlist = new ArrayList<WaterWarnBean>();
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
		String siteadd = "";
		String level = "";
		String waterlevel="";
		String earlywarningvalue = "";
		
		for(int i =0;i<jsonArray.length();i++){
			JSONObject mJsonObject = jsonArray.getJSONObject(i);
			mWaterWarnBean = new WaterWarnBean();
			mWaterWarnBean.setSitename(mJsonObject.getString("sitename"));
			mWaterWarnBean.setSiteadd(mJsonObject.getString("siteadd"));
			mWaterWarnBean.setLevel(mJsonObject.getString("level"));
			mWaterWarnBean.setWaterlevel(mJsonObject.getString("waterlevel"));
			mWaterWarnBean.setEarlywarningvalue(mJsonObject.getString("earlywarningvalue"));
			mWaterWarnBean.setLon(mJsonObject.getString("lon"));
			mWaterWarnBean.setLat(mJsonObject.getString("lat"));
			nlist.add(mWaterWarnBean);			
		}
		this.list = nlist;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return UrlLib.getWaterWarningUrl(username, password, city);
	}

}
