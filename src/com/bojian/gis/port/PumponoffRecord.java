package com.bojian.gis.port;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.bojian.gis.debug.L;
import com.bojian.gis.entity.PumponoffRecordBean;
import com.bojian.gis.json.JSONArray;
import com.bojian.gis.json.JSONObject;
import com.bojian.gis.util.UrlLib;

public class PumponoffRecord extends Upload {
	private static final String TAG = "PumponoffRecord";
	private String startdate;
	private String enddate;
	private String wellId;
	private PumponoffRecordBean mPumponoffRecordBean;
	private List<PumponoffRecordBean> list;

	/**
	 * @return the list
	 */
	public List<PumponoffRecordBean> getList() {
		return list;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<PumponoffRecordBean> list) {
		this.list = list;
	}

	public PumponoffRecord(String username, String password, String startdate,
			String enddate, String wellId) {
		this.username = username;
		this.password = password;
		this.startdate = startdate;
		this.enddate = enddate;
		this.wellId = wellId;
	}

	@Override
	public void getDataBase(InputStreamReader isr) throws Exception {
		// TODO Auto-generated method stub
		List<PumponoffRecordBean> nlist = new ArrayList<PumponoffRecordBean>();
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
			mPumponoffRecordBean = new PumponoffRecordBean();
			JSONObject mJsonObject = mJonArray.getJSONObject(i);

			wellId = mJsonObject.getString("wellId");
			wellno = mJsonObject.getString("wellno");
			wellname = mJsonObject.getString("wellname");
			welladdr = mJsonObject.getString("welladdr");
			pumpontime = mJsonObject.getString("pumpontime");
			pumpofftime = mJsonObject.getString("pumpofftime");
			type = mJsonObject.getString("type");

			mPumponoffRecordBean.setWellno(wellno);
			mPumponoffRecordBean.setWellname(wellname);
			mPumponoffRecordBean.setWelladdr(welladdr);
			mPumponoffRecordBean.setPumpontime(pumpontime);
			mPumponoffRecordBean.setPumpofftime(pumpofftime);
			mPumponoffRecordBean.setType(type);

			nlist.add(mPumponoffRecordBean);
		}
		this.list = nlist;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return UrlLib.getPumponoffRecordUrl(username, password, startdate,
				enddate, wellId);
	}
}
