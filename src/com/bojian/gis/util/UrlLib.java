package com.bojian.gis.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.bojian.gis.entity.ArealBean;

public class UrlLib {
	public static final int RAIN_WARNING =1; //雨情预警
	public static final int WATER_WARNING = 2;//水情预警
	public static final int IMPORT_RIVER = 4; //重点河道
	public static final int RESERVIOR =5;    //大中型水库
	public static final int TOTAL_RAIN=6;
	public static final int RAIN_DETAIL = 9;
	public static final int afterEight=3; //当日雨量
	
	/*本次添加irri部分内容*/
	public static final int WATER_USING=11;//用水量   
	public static final int RECHARGE=12;//充值记录   
	public static final int PUMPONOFFRECORD=13;
	

	
	public static final int FLAG1=7;
	public static final int FLAG2 =8;
	public static final int FLAG3 = 10;
	
	public static List<String> mvalue = new ArrayList<String>();//断面
	public static List<String> mkey = new ArrayList<String>();
	public static List<ArealBean> areaBeanList = new ArrayList<ArealBean>();
	public static List<String> cityName = new ArrayList<String>();//城市
	public static boolean isAdmin=false;
	
	
	
	/**百度map key**/
	public static final String key = "F276A3FB159A657749DFA0DCD095B0D0C1E17EBF";
	/**各个版本Http地址**/
		
//	public static final String HTTP="http://202.85.210.70:9772:8080/mvcLogin/";
	public static final String HTTP="http://202.85.210.70:9772/mvcLogin/";
//	public static final String HTTP="http://192.168.0.105:8080/mvcLogin/";
//	public static final String HTTP="http://192.168.1.26:8080/mvcLogin/";
	/***************卫星云图url*****************/
	//public static final String CLI = "http://www.cma.gov.cn/tqyb/v2/product/nephogram_video.php";
	public static final String CLI = "http://www.weather.com.cn/html/module/satellite.shtml";
	
	//大中型水库
	public static final String CLI5 = "http://218.201.131.178/yantai_sq_web";
	
	/**台风flash Url**/
	public static final String TYPHOON = "http://flash.weather.com.cn/typhoon/taifeng.swf";
	
	public static String getMainEntryUrl(){
		return HTTP+"mvcinit";
	}
	
	/****************************此处添加irri部分内容***********************************/

	public static String getWaterUsingUrl(String username,String password,String startdate,String enddate,String wellId){
		return HTTP+"waterusing?"+UrlProcessing(username, password,startdate,enddate);
	}
	
	public static String getRechargeUrl(String username,String password,String startdate,String enddate,String wellId){
		return HTTP+"recharge?"+UrlProcessing(username, password,startdate,enddate);
	}
//	public static String getRechargeUrl(String username,String password,int city,String startdate ,String enddate) {
//		return HTTP+"recharge?"+UrlProcessing(username,password,city)+"&startdate="+startdate+"&enddate="+enddate;
//	}
	
//	
	public static String getPumponoffRecordUrl(String username,String password,String startdate,String enddate,String wellId){
		return HTTP+"pumponoffrecord?"+UrlProcessing(username, password,startdate,enddate);
	}
	
	public static String getPumponoffUrl(String username,String password,String startdate,String enddate,String wellId,String status){
		return HTTP+"pumponoff?"+UrlProcessing(username, password,startdate,enddate);
	}
	public static String getPumponoffOp(String username,String password,String wellno,String status){
		return HTTP + "pumponoffop?"+UrlProcessing(username,password,wellno,status);
	}
//	public static String getPumponoffUrl(String username,String password,int city,String startdate ,String enddate){
//		return HTTP+"pumponoff?"+UrlProcessing(username,password,city)+"&startdate="+startdate+"&enddate="+enddate;
//	}
	
	/*****************************************************************************/
	
	public static String getLoginUrl(String username, String password,int city) {
		return HTTP+"mvcLogin?"+UrlProcessing(username,password,city);
	}

	public static String getRainWarningUrl(String username,String password,int city) {
		return HTTP+"rainfallwarning?"+UrlProcessing(username,password,city);
	}

	public static String getWaterWarningUrl(String username,String password,int city){
		return HTTP+"waterwarning?"+UrlProcessing(username,password,city);
	}
	
	public static String getRainConditionUrl(String username,String password,int city){
		return HTTP+"raincondition?"+UrlProcessing(username,password,city);
	}
	
	
	public static String getRainDetailUrl(String username,String password,int city,String hour,String duration){
		return HTTP+"raindetail?"+UrlProcessing(username,password,city)+"&hour="+hour+"&duration="+duration;
	}
	
	public static String getTotalRainfallUrl(String username,String password,int city,String startdate ,String enddate){
		return HTTP+"totalrainfalla?"+UrlProcessing(username,password,city)+"&startdate="+startdate+"&enddate="+enddate;
	}
	
	public static String getImportRaiverUrl(String username,String password,int city){
		return HTTP+"importraiver?"+UrlProcessing(username,password,city);
	}
	public static String getImportRaiverExUrl(String username,String password,int city,String datetime){
		return HTTP+"reserviorer?"+UrlProcessing(username,password,city)+"&datetime="+datetime;
	}
	
	public static String getWaterForcastUrl(String username,String password,String startdate,String enddate,String rvsectId){
		return HTTP+"waterforcast?"+UrlProcessing(username, password,startdate,enddate);
	}
	
	public static String getReserviorUrl(String username,String password,int city){
		return HTTP+"reservior?"+UrlProcessing(username, password,city);
	}
	
	public static String getAfterEight(String username,String password,int city){
		return HTTP+"afterEight?"+UrlProcessing(username, password,city);
	}

	public static String getWeatherForcast(String cityCode) {
		// "http://m.weather.com.cn/data/101050101.html"
		return "http://www.weather.com.cn/data/cityinfo/" + cityCode + ".html";
	}
	public static String getRainDetailMx(String siteid,String hour,String duration,String username,String password){
		return HTTP+"raindetailmx?"+UrlProcessing(siteid, username, password)+"&hour="+hour+"&duration="+duration;
	}
	
	public static String getImportRaiverDetailMx(String siteid,String username,String password){
		return HTTP+"importraiverdetailmx?"+UrlProcessing(siteid, username, password);
	}
	
	public static String getReserviorTailMx(String siteid,String username,String password){
		return HTTP+"reserviortailmx?"+UrlProcessing(siteid, username, password);
	}
	
	public static String getIsWaring(int city,String username,String password){
		return HTTP+"iswarning?"+UrlProcessing( username, password,city);
	}
	
	public static String getWaringChange(int city,String username,String password)
	{
		return HTTP+"warningchange?"+UrlProcessing( username, password,city);
	}
	private static String UrlProcessing(String username,String password,int city){
		String citycode;
		if(city==0||city==-1){
			citycode="";
		}else {
			citycode=""+city;
		}
		return "username="+username+"&password="+Md5Util.encode(password.getBytes())+"&city="+citycode;
	}
	@SuppressWarnings("finally")
	private static String UrlProcessing(String siteid,String username,String password){
		return "siteid="+siteid+ "&username="+username+"&password="+Md5Util.encode(password.getBytes());
		
	}
	
	private static String UrlProcessing(String username,String password,String startdate ,String enddate){
		return "username="+username+"&password="+Md5Util.encode(password.getBytes())+"&startdate="+startdate+"&enddate="+enddate;
	}

	
}
