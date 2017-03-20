package com.bojian.gis.port;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.bojian.gis.debug.L;
import com.bojian.gis.entity.WaterUsingBean;
import com.bojian.gis.json.JSONArray;
import com.bojian.gis.json.JSONObject;
import com.bojian.gis.util.UrlLib;

public class WaterUsing extends Upload {
	private static final String TAG = "WaterUsing";
	private String startdate;
	private String enddate;
	private String wellId;
	private WaterUsingBean mWaterUsingBean;
	private List<WaterUsingBean> list;

	/**
	 * @return the list
	 */
	public List<WaterUsingBean> getList() {
		return list;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<WaterUsingBean> list) {
		this.list = list;
	}

	public WaterUsing(String username, String password, String startdate,
			String enddate, String rvsectId) {
		this.username = username;
		this.password = password;
		this.startdate = startdate;
		this.enddate = enddate;
		this.wellId = wellId;
	}

	@Override
	public void getDataBase(InputStreamReader isr) throws Exception {
		// TODO Auto-generated method stub
		List<WaterUsingBean> nlist = new ArrayList<WaterUsingBean>();
		StringBuilder builder = new StringBuilder();
		BufferedReader br = new BufferedReader(isr);
		for (String s = br.readLine(); s != null; s = br.readLine()) {
			builder.append(s);
		}
		L.info(TAG, "builder = " + builder.toString());
		JSONObject jsonObject = new JSONObject(builder.toString().substring(1,
				builder.toString().length() - 1));
		int code = jsonObject.getInt("code");
		L.info(TAG, "code=" + code);
		if (code != 0)
			return;

		String villiage = "";
		String name = "";
		String waterconsumption = "";
		String startpumptime = "";
		String shutpumptime = "";
		String type = "";

		JSONArray mJonArray = jsonObject.getJSONArray("data");
		for (int i = 0; i < mJonArray.length(); i++) {
			mWaterUsingBean = new WaterUsingBean();
			JSONObject mJsonObject = mJonArray.getJSONObject(i);

			villiage = mJsonObject.getString("villiage");
			name = mJsonObject.getString("name");
			waterconsumption = mJsonObject.getString("waterconsumption");
			startpumptime = mJsonObject.getString("startpumptime");
			shutpumptime = mJsonObject.getString("shutpumptime");
			type = mJsonObject.getString("type");

			mWaterUsingBean.setVilliage(villiage);
			mWaterUsingBean.setName(name);
			mWaterUsingBean.setWaterconsumption(waterconsumption);
			mWaterUsingBean.setStartpumptime(startpumptime);
			mWaterUsingBean.setShutpumptime(shutpumptime);
			mWaterUsingBean.setType(type);

			nlist.add(mWaterUsingBean);
		}
		this.list = nlist;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return UrlLib.getWaterUsingUrl(username, password, startdate,
				enddate, wellId);
	}
}
