package com.bojian.gis.port;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.bojian.gis.debug.L;
import com.bojian.gis.entity.RainBean;
import com.bojian.gis.json.JSONArray;
import com.bojian.gis.json.JSONObject;
import com.bojian.gis.util.UrlLib;

public class Rain extends Upload{
	private static final String TAG = "Rain";
	private RainBean mRainBean;
	private List<RainBean> list;
	
	/**
	 * @return the list
	 */
	public List<RainBean> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<RainBean> list) {
		this.list = list;
	}

	public Rain(String username, String password, int city) {
		super(username, password, city);
		// TODO Auto-generated constructor stub
		this.username = username;
		this.password = password;
		this.city = city;
	}

	@Override
	public void getDataBase(InputStreamReader isr) throws Exception {
		// TODO Auto-generated method stub
		List<RainBean> nlist = new ArrayList<RainBean>();
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
		
		String hour1count = "";
		String hour2count = "";
		String hour3count = "";
		String hour6count = "";
		String hour12count = "";
		String hour24count = "";
		
		JSONArray jsonArray = jsonObject.getJSONArray("data");
		for(int i =0;i<jsonArray.length();i++){
			JSONObject mJsonObject = jsonArray.getJSONObject(i);
			mRainBean = new RainBean();
			
			hour1count = mJsonObject.getString("hour1Count");
			hour2count = mJsonObject.getString("hour2Count");
			hour3count = mJsonObject.getString("hour3Count");
			hour6count = mJsonObject.getString("hour6Count");
			hour12count = mJsonObject.getString("hour12Count");
			hour24count = mJsonObject.getString("hour24Count");
			
			mRainBean.setHour1count(hour1count);
			mRainBean.setHour2count(hour2count);
			mRainBean.setHour3count(hour3count);
			mRainBean.setHour6count(hour6count);
			mRainBean.setHour12count(hour12count);
			mRainBean.setHour24count(hour24count);
			L.info("Rain", "mRainBean="+mRainBean);
			nlist.add(mRainBean);
			
		}
		this.list = nlist;
		
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return UrlLib.getRainConditionUrl(username, password, city);
	}

}
