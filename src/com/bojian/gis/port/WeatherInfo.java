package com.bojian.gis.port;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.bojian.gis.debug.L;
import com.bojian.gis.entity.WeatherBean;
import com.bojian.gis.json.JSONObject;
import com.bojian.gis.util.Common;
import com.bojian.gis.util.UrlLib;

public class WeatherInfo extends Upload{

	private static final String TAG= "WeatherInfo";
	private String cityCode;
	private List<WeatherBean> nList;

	private WeatherBean head;
	
	/**
	 * @return the head
	 */
	public WeatherBean getHead() {
		return head;
	}

	/**
	 * @param head the head to set
	 */
	public void setHead(WeatherBean head) {
		this.head = head;
	}

	/**
	 * @return the nList
	 */
	public List<WeatherBean> getnList() {
		return nList;
	}

	/**
	 * @param nList the nList to set
	 */
	public void setnList(List<WeatherBean> nList) {
		this.nList = nList;
	}

	public WeatherInfo(String cityCode) {
		super();
		this.cityCode = cityCode;
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
		
		if(builder.toString().length()==0)
			return ;
		JSONObject jsonObject = new JSONObject(builder.toString()).getJSONObject("weatherinfo");
		List<WeatherBean> mList = new ArrayList<WeatherBean>();
		head = new WeatherBean();
		WeatherBean weather2 = new WeatherBean();
		WeatherBean weather3 = new WeatherBean();
		WeatherBean weather4 = new WeatherBean();
		WeatherBean weather5 = new WeatherBean();
		WeatherBean weather6 = new WeatherBean();
		
		String city="";
		String week="";
		String date_y="";
		String temp1="";
		String temp2="";
		String temp3="";
		String temp4="";
		String temp5="";
		String temp6="";
		String weather_1="";
		String weather_2="";
		String weather_3="";
		String weather_4="";
		String weather_5="";
		String weather_6="";
		String week2 = "";
		String week3 = "";
		String week4="";
		String week5 = "";
		String week6 = "";
		
		city = jsonObject.getString("city");
		week = jsonObject.getString("week");
		if(week!=null){
			if(week.equals("星期一")){
				week2 = "星期二";
				week3="星期三";
				week4="星期四";
				week5="星期五";
				week6="星期六";
			}else if(week.equals("星期二")){
				week2 = "星期三";
				week3="星期四";
				week4="星期五";
				week5="星期六";
				week6="星期日";
			}else if(week.equals("星期三")){
				week2 = "星期四";
				week3="星期五";
				week4="星期六";
				week5="星期日";
				week6="星期一";
			}else if(week.equals("星期四")){
				week2 = "星期五";
				week3="星期六";
				week4="星期日";
				week5="星期一";
				week6="星期二";
			}else if(week.equals("星期五")){
				week2 = "星期六";
				week3="星期日";
				week4="星期一";
				week5="星期二";
				week6="星期三";
			}else if(week.equals("星期六")){
				week2 = "星期日";
				week3="星期一";
				week4="星期二";
				week5="星期三";
				week6="星期四";
			}else if(week.equals("星期日")){
				week2 = "星期一";
				week3="星期二";
				week4="星期三";
				week5="星期四";
				week6="星期五";
			}else{
				week2 = "星期一";
				week3="星期二";
				week4="星期三";
				week5="星期四";
				week6="星期五";
			}
		}
		
		date_y = jsonObject.getString("date_y");
		temp1 = jsonObject.getString("temp1");
		temp2 = jsonObject.getString("temp2");
		temp3 = jsonObject.getString("temp3");
		temp4 = jsonObject.getString("temp4");
		temp5 = jsonObject.getString("temp5");
		temp6 = jsonObject.getString("temp6");
		weather_1 = jsonObject.getString("weather");
		weather_2 = jsonObject.getString("weather2");
		weather_3 = jsonObject.getString("weather3");
		weather_4 = jsonObject.getString("weather4");
		weather_5 = jsonObject.getString("weather5");
		weather_6 = jsonObject.getString("weather6");
		
		head.setCity(city);
		head.setWeek(week);
		head.setDate_y(date_y);
		head.setTemp(temp1);
		head.setWeather(weather_1);
		
		String curDate=Common.getCurrDate("yyyy-MM-dd");
		weather2.setCity(city);
		weather2.setWeek(week2);
		weather2.setDate_y(Common.nDaysAfterOneDateString(curDate,1));
		weather2.setTemp(temp2);
		weather2.setWeather(weather_2);
		mList.add(weather2);
		
		weather3.setCity(city);
		weather3.setWeek(week3);
		weather3.setDate_y(Common.nDaysAfterOneDateString(curDate,2));
		weather3.setTemp(temp3);
		weather3.setWeather(weather_3);
		mList.add(weather3);
		
		weather4.setCity(city);
		weather4.setWeek(week4);
		weather4.setDate_y(Common.nDaysAfterOneDateString(curDate,3));
		weather4.setTemp(temp4);
		weather4.setWeather(weather_4);
		mList.add(weather4);
		
		weather5.setCity(city);
		weather5.setWeek(week5);
		weather5.setDate_y(Common.nDaysAfterOneDateString(curDate,4));
		weather5.setTemp(temp5);
		weather5.setWeather(weather_5);
		mList.add(weather5);
		
		weather6.setCity(city);
		weather6.setWeek(week6);
		weather6.setDate_y(Common.nDaysAfterOneDateString(curDate,5));
		weather6.setTemp(temp6);
		weather6.setWeather(weather_6);
		mList.add(weather6);
		this.nList = mList;
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return UrlLib.getWeatherForcast(cityCode);
	}

}
