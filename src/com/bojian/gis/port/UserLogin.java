package com.bojian.gis.port;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.bojian.gis.debug.L;
import com.bojian.gis.json.JSONObject;
import com.bojian.gis.util.UrlLib;


public class UserLogin extends Upload {
	private static final String TAG = "UserLogin";
	private String username;
	private String password;
	private int city;
	
	private int loginStatus;
	
	
	/**
	 * @return the code
	 */
	public int getStatus() {
		return loginStatus;
	}

	/**
	 * @param status the code to set
	 */
	public void setStatus(int status) {
		this.loginStatus = status;             
	}

	public UserLogin(String username, String password,int city) {
		super();
		this.username = username;
		this.password = password;
		this.city = city;
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
		L.info(TAG, "builder = "+builder.toString());
		JSONObject jsonObject = new JSONObject(builder.toString().substring(1, builder.toString().length()-1));
		int code = jsonObject.getInt("code");
		this.loginStatus = code;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return UrlLib.getLoginUrl(username, password,city);
	}

}
