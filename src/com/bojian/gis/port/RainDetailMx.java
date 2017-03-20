package com.bojian.gis.port;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.bojian.gis.debug.L;
import com.bojian.gis.entity.WaterWarnBean;
import com.bojian.gis.json.JSONArray;
import com.bojian.gis.json.JSONObject;
import com.bojian.gis.util.UrlLib;

public class RainDetailMx extends Upload {
	private static final String TAG = "RainDetailMx";
	private String siteid;
	private String hour;
	private String duration;
	private WaterWarnBean mWaterWarnBean;
	private ArrayList<WaterWarnBean> list;
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

	public RainDetailMx(String siteid, String hour, String duration,
			String username, String password) {
		this.siteid = siteid;
		this.hour = hour;
		this.duration = duration;
		this.username = username;
		this.password = password;
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
		String siteadd="";
		String time="";
		String rainfall = "";
		for(int i =0;i<jsonArray.length();i++){
			JSONObject mJsonObject = jsonArray.getJSONObject(i);
			mWaterWarnBean = new WaterWarnBean();
			
			sitename = mJsonObject.getString("sitename");
			siteadd = mJsonObject.getString("siteadd");
			time = mJsonObject.getString("time");
			rainfall = mJsonObject.getString("rainfall");
			
			mWaterWarnBean.setSitename(sitename);
			mWaterWarnBean.setSiteadd(siteadd);
			mWaterWarnBean.setLevel(time);
			mWaterWarnBean.setWaterlevel(rainfall);
			
			nlist.add(mWaterWarnBean);
		}
		this.list = nlist;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return UrlLib.getRainDetailMx(siteid, hour, duration, username,
				password);
	}

}
