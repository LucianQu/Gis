package com.bojian.gis.port;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.bojian.gis.debug.L;
import com.bojian.gis.entity.ImportRiverBean;
import com.bojian.gis.json.JSONArray;
import com.bojian.gis.json.JSONObject;
import com.bojian.gis.util.UrlLib;

public class ReserviorMx extends Upload{
	private static final String TAG = "ImportRiverMx";
	private ImportRiverBean mImportRiver;
	private ArrayList<ImportRiverBean> list;
	private String siteid;
	
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

	public ReserviorMx(String siteid,String username, String password) {
		// TODO Auto-generated constructor stub
		this.siteid = siteid;
		this.username = username;
		this.password = password;
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
		
		String sitename = "";
		String siteadd = "";
		String time = "";
		String waterlevel= "";
		String warninglevel = "";
		
		for(int i =0;i<jsonArray.length();i++){
			JSONObject mJsonObject = jsonArray.getJSONObject(i);
			mImportRiver = new ImportRiverBean();
			sitename = mJsonObject.getString("sitename");
			siteadd = mJsonObject.getString("siteadd");
			time = mJsonObject.getString("time");
			waterlevel = mJsonObject.getString("waterlevel");
			warninglevel = mJsonObject.getString("warninglevel");
			
			mImportRiver.setSitename(sitename);
			mImportRiver.setSiteadd(siteadd);
			mImportRiver.setTime(time);
			mImportRiver.setWaterlevel(waterlevel);
			mImportRiver.setWarninglevel(warninglevel);
			
			nlist.add(mImportRiver);
		}
		this.list = nlist;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return UrlLib.getReserviorTailMx(siteid, username, password);
	}

}
