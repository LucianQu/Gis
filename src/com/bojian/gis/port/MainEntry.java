package com.bojian.gis.port;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.bojian.gis.debug.L;
import com.bojian.gis.entity.ArealBean;
import com.bojian.gis.json.JSONArray;
import com.bojian.gis.json.JSONObject;
import com.bojian.gis.util.UrlLib;

public class MainEntry extends Upload {
	private static final String TAG = "MainEntry";
	private ArrayList<ArealBean> mlist;

	/**
	 * @return the mlist
	 */
	public ArrayList<ArealBean> getMlist() {
		return mlist;
	}

	/**
	 * @param mlist
	 *            the mlist to set
	 */
	public void setMlist(ArrayList<ArealBean> mlist) {
		this.mlist = mlist;
	}

	@Override
	public void getDataBase(InputStreamReader isr) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<ArealBean> nlist = new ArrayList<ArealBean>();
		StringBuilder builder = new StringBuilder();
		BufferedReader br = new BufferedReader(isr);
		for (String s = br.readLine(); s != null; s = br.readLine())
		{
			builder.append(s);
		}
		L.info(TAG, "builder = "+builder.toString());
		String test = builder.toString();
		test = test.substring(1, test.length()-1);
		L.info(TAG, "test = "+test.toString());
		JSONObject jsonObject = new JSONObject(test.toString());
		int code = jsonObject.getInt("code");
		L.info(TAG, "code="+code);
		if(code!=0)
			return;
		JSONArray jsonArray = jsonObject.getJSONArray("data");
		String address = "";
		String id = "";
		ArealBean areal =null;
		nlist.add(new ArealBean(0, "ÑÌÌ¨ÊÐ"));
		for(int i =0;i<jsonArray.length();i++){
			areal = new ArealBean();
			JSONObject jsonObject1 = jsonArray.optJSONObject(i);
			id = jsonObject1.getString("id");
			address= jsonObject1.getString("address");
			areal.setId(Integer.parseInt(id));
			areal.setName(address);
			nlist.add(areal);
		}
		L.info(TAG, "nlist="+nlist);
		this.mlist = nlist;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return UrlLib.getMainEntryUrl();
	}

}
