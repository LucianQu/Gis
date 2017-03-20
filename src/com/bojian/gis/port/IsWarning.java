package com.bojian.gis.port;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.bojian.gis.json.JSONArray;
import com.bojian.gis.json.JSONObject;
import com.bojian.gis.util.UrlLib;

public class IsWarning extends Upload {

	private int warningType=-1;
	
	public IsWarning(String username,String password, int city)
	{
		super.city=city;
		super.username =username;
		super.password =password;
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
		
		JSONObject jsonObject = new JSONObject(builder.toString().substring(1, builder.toString().length()-1));
		int code = jsonObject.getInt("code");
		
		if(code!=0)
			return;
		
		JSONArray jsonArray  = jsonObject.getJSONArray("data");
		JSONObject mJsonObject = jsonArray.getJSONObject(0);
		setWarningType(mJsonObject.getInt("warntype"));
		
		
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return UrlLib.getIsWaring(city, username, password);
	}

	public void setWarningType(int warningType) {
		this.warningType = warningType;
	}

	public int getWarningType() {
		return warningType;
	}

}
