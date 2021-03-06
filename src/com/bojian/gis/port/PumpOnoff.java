package com.bojian.gis.port;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.bojian.gis.debug.L;
import com.bojian.gis.entity.PumpOnoffBean;
import com.bojian.gis.json.JSONArray;
import com.bojian.gis.json.JSONObject;
import com.bojian.gis.util.UrlLib;

public class PumpOnoff extends Upload {
	private static final String TAG = "Pumponoff";
	private String startdate;
	private String enddate;
	private String wellId;
	private String status;
	private PumpOnoffBean mPumponoffBean;
	private List<PumpOnoffBean> list;

	/**
	 * @return the list
	 */
	public List<PumpOnoffBean> getList() {
		return list;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<PumpOnoffBean> list) {
		this.list = list;
	}

	public PumpOnoff(String username, String password, String startdate,
			String enddate, String rvsectId,String status) {
		this.username = username;
		this.password = password;
		this.startdate = startdate;
		this.enddate = enddate;
		this.wellId = rvsectId;
		this.status = status;
	}

	@Override
	public void getDataBase(InputStreamReader isr) throws Exception {
		// TODO Auto-generated method stub
		List<PumpOnoffBean> nlist = new ArrayList<PumpOnoffBean>();
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

		String wellId = "";
		String wellno = "";
		String wellname = "";
		String welladdr = "";
		String pumpontime = "";
		String pumpofftime = "";
		String type = "";

		JSONArray mJonArray = jsonObject.getJSONArray("data");
		for (int i = 0; i < mJonArray.length(); i++) {
			mPumponoffBean = new PumpOnoffBean();
			JSONObject mJsonObject = mJonArray.getJSONObject(i);

			wellId = mJsonObject.getString("wellId");
			wellno = mJsonObject.getString("wellno");
			wellname = mJsonObject.getString("wellname");
			welladdr = mJsonObject.getString("welladdr");
			pumpontime = mJsonObject.getString("pumpontime");
			pumpofftime = mJsonObject.getString("pumpofftime");
			type = mJsonObject.getString("type");

			mPumponoffBean.setWellno(wellno);
			mPumponoffBean.setWellname(wellname);
			mPumponoffBean.setWelladdr(welladdr);
			mPumponoffBean.setPumpontime(pumpontime);
			mPumponoffBean.setPumpofftime(pumpofftime);
			mPumponoffBean.setType(type);

			nlist.add(mPumponoffBean);
		}
		this.list = nlist;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return UrlLib.getPumponoffUrl(username, password, startdate,
				enddate, wellId,status);
	}
}
