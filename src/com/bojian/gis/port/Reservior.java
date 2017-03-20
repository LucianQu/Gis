package com.bojian.gis.port;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.bojian.gis.debug.L;
import com.bojian.gis.entity.ImportRiverBean;
import com.bojian.gis.json.JSONArray;
import com.bojian.gis.json.JSONObject;
import com.bojian.gis.util.UrlLib;

public class Reservior extends Upload{
	private static final String TAG = "Reservior";
	private ImportRiverBean mImportRiver;
	private ArrayList<ImportRiverBean> list;
	

	/**
	 * @return the list
	 */
	public ArrayList<ImportRiverBean> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(ArrayList<ImportRiverBean> list) {
		this.list = list;
	}

	public Reservior(String username, String password, int city) {
		super(username, password, city);
		// TODO Auto-generated constructor stub
		this.username = username;
		this.password = password;
		this.city = city;
	}

	@Override
	public void getDataBase(InputStreamReader isr) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<ImportRiverBean> nlist = new ArrayList<ImportRiverBean>();
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
		String sitename="";
		String siteid = "";
		String siteadd="";
		String time="";
		String waterlevel="";
		String warninglevel="";
		String lon="";
		String lat="";
		
		for(int i =0;i<jsonArray.length();i++){
			JSONObject mJsonObject = jsonArray.getJSONObject(i);
			mImportRiver = new ImportRiverBean();
			sitename = mJsonObject.getString("sitename");
			siteid = mJsonObject.getString("siteid");
			siteadd = mJsonObject.getString("siteadd");
			time = mJsonObject.getString("time");
			waterlevel = mJsonObject.getString("waterlevel");
			warninglevel = mJsonObject.getString("warninglevel");
			lon = mJsonObject.getString("lon");
			lat = mJsonObject.getString("lat");
			
			mImportRiver.setSitename(sitename);
			mImportRiver.setSiteid(siteid);
			mImportRiver.setSiteadd(siteadd);
			mImportRiver.setTime(time);
			mImportRiver.setWaterlevel(waterlevel);
			mImportRiver.setWarninglevel(warninglevel);
			mImportRiver.setLon(lon);
			mImportRiver.setLat(lat);
			
			nlist.add(mImportRiver);
		}
		this.list = nlist;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return UrlLib.getReserviorUrl(username, password, city);
	}

}
