package com.bojian.gis.port;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.bojian.gis.debug.L;
import com.bojian.gis.entity.WaterForcastBean;
import com.bojian.gis.json.JSONArray;
import com.bojian.gis.json.JSONObject;
import com.bojian.gis.util.UrlLib;

public class WaterForcast extends Upload {
	private static final String TAG = "WaterForcast";
	private String startdate;
	private String enddate;
	private String rvsectId;
	private WaterForcastBean mWaterForcastBean;
	private List<WaterForcastBean> list;

	/**
	 * @return the list
	 */
	public List<WaterForcastBean> getList() {
		return list;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<WaterForcastBean> list) {
		this.list = list;
	}

	public WaterForcast(String username, String password, String startdate,
			String enddate, String rvsectId) {
		this.username = username;
		this.password = password;
		this.startdate = startdate;
		this.enddate = enddate;
		this.rvsectId = rvsectId;
	}

	@Override
	public void getDataBase(InputStreamReader isr) throws Exception {
		// TODO Auto-generated method stub
		List<WaterForcastBean> nlist = new ArrayList<WaterForcastBean>();
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

		String rvsectId = "";
		String sitename = "";
		String time = "";
		String latestrainfall = "";
		String peakdischarge = "";
		String floodvalue = "";
		String duration = "";
		String peaktime = "";

		JSONArray mJonArray = jsonObject.getJSONArray("data");
		for (int i = 0; i < mJonArray.length(); i++) {
			mWaterForcastBean = new WaterForcastBean();
			JSONObject mJsonObject = mJonArray.getJSONObject(i);

			rvsectId = mJsonObject.getString("rvsectId");
			sitename = mJsonObject.getString("sitename");
			time = mJsonObject.getString("time");
			latestrainfall = mJsonObject.getString("latestrainfall");
			peakdischarge = mJsonObject.getString("peakdischarge");
			floodvalue = mJsonObject.getString("floodvalue");
			duration = mJsonObject.getString("duration");
			peaktime = mJsonObject.getString("peaktime");

			mWaterForcastBean.setRvsectId(rvsectId);
			mWaterForcastBean.setSitename(sitename);
			mWaterForcastBean.setTime(time);
			mWaterForcastBean.setLatestrainfall(latestrainfall);
			mWaterForcastBean.setPeakdischarge(peakdischarge);
			mWaterForcastBean.setFloodvalue(floodvalue);
			mWaterForcastBean.setDuration(duration);
			mWaterForcastBean.setPeaktime(peaktime);

			nlist.add(mWaterForcastBean);
		}
		this.list = nlist;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return UrlLib.getWaterForcastUrl(username, password, startdate,
				enddate, rvsectId);
	}
}
