package com.bojian.gis.port;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.bojian.gis.debug.L;
import com.bojian.gis.entity.ReChargeBean;
import com.bojian.gis.json.JSONArray;
import com.bojian.gis.json.JSONObject;
import com.bojian.gis.util.UrlLib;

public class ReCharge extends Upload {
	private static final String TAG = "ReCharge";
	private String startdate;
	private String enddate;
	private String wellId;
	private ReChargeBean mReChargeBean;
	private List<ReChargeBean> list;

	/**
	 * @return the list
	 */
	public List<ReChargeBean> getList() {
		return list;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<ReChargeBean> list) {
		this.list = list;
	}

	public ReCharge(String username, String password, String startdate,
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
		List<ReChargeBean> nlist = new ArrayList<ReChargeBean>();
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
		String dealtime = "";
		String dealingprice = "";
		String dealingprice_E = "";
		String remainPrice = "";
		String remainPrice_E = "";

		JSONArray mJonArray = jsonObject.getJSONArray("data");
		for (int i = 0; i < mJonArray.length(); i++) {
			mReChargeBean = new ReChargeBean();
			JSONObject mJsonObject = mJonArray.getJSONObject(i);

			villiage = mJsonObject.getString("villiage");
			name = mJsonObject.getString("name");
			dealtime = mJsonObject.getString("dealtime");
			dealingprice = mJsonObject.getString("dealingprice");
			dealingprice_E = mJsonObject.getString("dealingprice_E");
			remainPrice = mJsonObject.getString("remainPrice");
			remainPrice_E = mJsonObject.getString("remainPrice_E");

			mReChargeBean.setVilliage(villiage);
			mReChargeBean.setName(name);
			mReChargeBean.setDealtime(dealtime);
			mReChargeBean.setDealingprice(dealingprice);
			mReChargeBean.setDealingprice_E(dealingprice_E);
			mReChargeBean.setRemainPrice(remainPrice);
			mReChargeBean.setRemainPrice_E(remainPrice_E);

			nlist.add(mReChargeBean);
		}
		this.list = nlist;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return UrlLib.getRechargeUrl(username, password, startdate,
				enddate, wellId);
	}
}
