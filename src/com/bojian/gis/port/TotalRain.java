package com.bojian.gis.port;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.bojian.gis.debug.L;
import com.bojian.gis.entity.TotalRainBean;
import com.bojian.gis.json.JSONArray;
import com.bojian.gis.json.JSONObject;
import com.bojian.gis.util.UrlLib;

public class TotalRain extends Upload{
	private static final String TAG="TotalRain";
	private String startdate;
	private String enddate;
	private TotalRainBean totalRainBean;
	private ArrayList<TotalRainBean> list;
	
	

	/**
	 * @return the list
	 */
	public ArrayList<TotalRainBean> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(ArrayList<TotalRainBean> list) {
		this.list = list;
	}

	public TotalRain(String username,String password,int city,String startdate,String enddate){
		this.username = username;
		this.password = password;
		this.city = city;
		this.startdate = startdate;
		this.enddate = enddate;
	}
	
	@Override
	public void getDataBase(InputStreamReader isr) throws Exception {
		// TODO Auto-generated method stub
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
		
		
		String name="";
		String rainlast="";
		String rainyear="";
		String rainafteryear="";
		String rainallyear="";
		String type="";
		
		JSONArray mJsonArray = jsonObject.getJSONArray("data");
		ArrayList<TotalRainBean> nlist = new ArrayList<TotalRainBean>();
		for(int i =0;i<mJsonArray.length();i++){
			
			totalRainBean = new TotalRainBean();
			JSONObject jsonObject2 = mJsonArray.getJSONObject(i);
			name = jsonObject2.getString("name");
			rainlast = jsonObject2.getString("rainlast");
			rainyear = jsonObject2.getString("rainyear");
			rainafteryear = jsonObject2.getString("rainafteryear");
			rainallyear = jsonObject2.getString("rainallyear");
			type = jsonObject2.getString("type");
			
			totalRainBean.setName(name);
			totalRainBean.setTotalrain_last(rainlast);
			totalRainBean.setTotalrain_year(rainyear);
			totalRainBean.setTotalrain_afteryear(rainafteryear);
			totalRainBean.setTotalrain_allyear(rainallyear);
			totalRainBean.setType(type);
			
			nlist.add(totalRainBean);
		}
		this.list = nlist;
		
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return UrlLib.getTotalRainfallUrl(username, password, city, startdate, enddate);
	}

}
