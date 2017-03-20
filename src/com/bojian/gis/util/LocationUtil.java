package com.bojian.gis.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;

import com.bojian.gis.debug.L;
import com.bojian.gis.entity.BaseStationBean;
import com.bojian.gis.json.JSONArray;
import com.bojian.gis.json.JSONException;
import com.bojian.gis.json.JSONObject;

public class LocationUtil {
	private static final String TAG = "LocationUtil";
	private LocationManager mLocationManager;
	private CdmaCellLocation location = null;
	private Context context;

	public LocationUtil(Context context) {
		super();
		this.context = context;
	}

	public String getLocalCityNameByGps() {

		String cityName = null;

		if (mLocationManager == null) {
			mLocationManager = (LocationManager) context
					.getSystemService(Context.LOCATION_SERVICE);
		}
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		criteria.setCostAllowed(true);
		criteria.setPowerRequirement(Criteria.POWER_LOW);
		String provider = mLocationManager.getBestProvider(criteria, true);
		if (provider == null) {
			L.info(TAG, "Provider is " + provider);
			return null;
		}
		Location location = mLocationManager.getLastKnownLocation(provider);
		if (location == null) {
			L.info(TAG, "Location is " + location);
			return null;
		}
		double lat = location.getLatitude();
		double lng = location.getLongitude();
		Geocoder gc = new Geocoder(context, Locale.getDefault());
		// 取得地址相关的一些信息\经度、纬度
		List<Address> addresses = null;
		try {
			addresses = gc.getFromLocation(lat, lng, 1);
			StringBuilder sb = new StringBuilder();
			if (addresses.size() > 0) {
				Address address = addresses.get(0);
				sb.append(address.getLocality()).append("\n");
				cityName = sb.toString();
			} else {
				cityName = null;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			L.error(TAG, "get location failed");
			cityName = null;
		}
		return cityName;
	}

	public String getCityNameByLocation(Location location) {
		String resultString = "";
		if (location == null)
			return null;

		String urlString = 
				"http://maps.google.cn/maps/api/geocode/json?latlng="+
				location.getLatitude()+","+location.getLongitude()+"&language=zh-CN&sensor=true";
		L.info(TAG, "URL=" + urlString);
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(urlString);

		try {
			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			BufferedReader buffReader = new BufferedReader(
					new InputStreamReader(entity.getContent()));
			StringBuffer strBuff = new StringBuffer();
			String result = null;
			while ((result = buffReader.readLine()) != null) {
				strBuff.append(result);
			}
			resultString = strBuff.toString();
			L.info(TAG, result);
			if (resultString != null && resultString.length() > 0) {
				JSONObject jsonobject = new JSONObject(resultString);
				resultString="";
				JSONArray jsonArray = jsonobject.getJSONArray("results");
				JSONObject mJsonObject =jsonArray.getJSONObject(0);
				JSONArray  mJsonArray = mJsonObject.getJSONArray("address_components");
				JSONObject nJsonObject = mJsonArray.getJSONObject(3);
				resultString = nJsonObject.getString("long_name");
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultString;
	}

	public List<BaseStationBean> getBaseStations() {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		List<BaseStationBean> bslist = new ArrayList<BaseStationBean>();
		int type = tm.getNetworkType();
		L.info(TAG, "type="+type);
		// 在中国，移动的2G是EGDE，联通的2G为GPRS，电信的2G为CDMA，电信的3G为EVDO
		// 中国电信为CTC
		// NETWORK_TYPE_EVDO_A是中国电信3G的getNetworkType
		// NETWORK_TYPE_CDMA电信2G是CDMA
		Location loc = null;

		if (type == TelephonyManager.NETWORK_TYPE_EVDO_A
				|| type == TelephonyManager.NETWORK_TYPE_CDMA
				|| type == TelephonyManager.NETWORK_TYPE_1xRTT) {
			location = (CdmaCellLocation) tm.getCellLocation();
			int cellIDs = location.getBaseStationId();
			int networkID = location.getNetworkId();
			StringBuilder nsb = new StringBuilder();
			nsb.append(location.getSystemId());
			BaseStationBean bs = new BaseStationBean();
			bs.setCellid(cellIDs);
			bs.setLocationAreaCode(networkID);
			bs.setMobileCountryCode(tm.getNetworkOperator().substring(0, 3));
			bs.setMobileNetworkCode(nsb.toString());
			bs.setRadioType("cdma");
			bslist.add(bs);
		} else if (type == TelephonyManager.NETWORK_TYPE_EDGE) {
			// 移动2G卡 + CMCC + 2
			// type = NETWORK_TYPE_EDGE
			GsmCellLocation location = (GsmCellLocation) tm.getCellLocation();
			int cellIDs = location.getCid();
			int lac = location.getLac();
			BaseStationBean bs = new BaseStationBean();
			bs.setCellid(cellIDs);
			bs.setLocationAreaCode(lac);
			bs.setMobileCountryCode(tm.getNetworkOperator().substring(0, 3));
			bs.setMobileNetworkCode(tm.getNetworkOperator().substring(3, 5));
			bs.setRadioType("gsm");
			bslist.add(bs);
		} else if (type == TelephonyManager.NETWORK_TYPE_GPRS) {
			// 联通的2G经过测试 China Unicom 1 NETWORK_TYPE_GPRS
			GsmCellLocation location = (GsmCellLocation) tm.getCellLocation();
			int cellIDs = location.getCid();
			int lac = location.getLac();
			BaseStationBean bs = new BaseStationBean();
			bs.setCellid(cellIDs);
			bs.setLocationAreaCode(lac);
			bs.setMobileCountryCode(tm.getNetworkOperator().substring(0, 3));
			bs.setMobileNetworkCode(tm.getNetworkOperator().substring(3, 5));
			bs.setRadioType("gsm");
			L.info(TAG, "cellIDs="+cellIDs+"&lac="+lac+"&countrycode="+tm.getNetworkOperator().substring(0, 3)+
			"&networkcode="+tm.getNetworkOperator().subSequence(3, 5));
			bslist.add(bs);
		} else {
			L.error(TAG, "THE TYPE IS NOT SURRPORTED");
		}
		return bslist;
	}

	public Location getLocationByCells(List<BaseStationBean> list) {

		if (list.isEmpty()) {
			return null;
		}
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://www.google.com/loc/json");
		JSONObject holder = new JSONObject();

		try {
			holder.put("version", "1.1.0");
			holder.put("host", "maps.google.com");
			holder.put("home_mobile_country_code", list.get(0)
					.getMobileCountryCode());
			holder.put("home_mobile_network_code", list.get(0)
					.getMobileNetworkCode());
			holder.put("radio_type", list.get(0).getRadioType());
			holder.put("request_address", true);
			if ("460".equals(list.get(0).getMobileCountryCode()))
				holder.put("address_language", "zh_CN");
			else
				holder.put("address_language", "en_US");

			JSONObject data, current_data;
			JSONArray array = new JSONArray();
			current_data = new JSONObject();
			current_data.put("cell_id", list.get(0).getCellid());
			current_data.put("location_area_code", list.get(0)
					.getLocationAreaCode());
			current_data.put("mobile_country_code", list.get(0)
					.getMobileCountryCode());
			current_data.put("mobile_network_code", list.get(0)
					.getMobileNetworkCode());
			current_data.put("age", 0);
			array.put(current_data);

			if (list.size() > 2) {
				for (int i = 1; i < list.size(); i++) {
					data = new JSONObject();
					data.put("cell_id", list.get(i).getCellid());
					data.put("location_area_code", list.get(i)
							.getLocationAreaCode());
					data.put("mobile_country_code", list.get(i)
							.getMobileCountryCode());
					data.put("mobile_network_code", list.get(i)
							.getMobileNetworkCode());
					data.put("age", 0);
					array.put(data);
				}
			} else if (list.size() == 1) {
				data = new JSONObject();
				data.put("cell_id", list.get(0).getCellid());
				data.put("location_area_code", list.get(0)
						.getLocationAreaCode());
				data.put("mobile_country_code", list.get(0)
						.getMobileCountryCode());
				data.put("mobile_network_code", list.get(0)
						.getMobileNetworkCode());
				data.put("age", 0);
				array.put(data);
			}
			holder.put("cell_towers", array);
			StringEntity se = new StringEntity(holder.toString());
			post.setEntity(se);
			HttpResponse resp = client.execute(post);
			HttpEntity entity = resp.getEntity();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					entity.getContent()));
			StringBuffer sb = new StringBuffer();
			String result = br.readLine();
			while (result != null) {
				sb.append(result);
				result = br.readLine();
			}
			if (sb.length() <= 1)
				return null;
			L.info(TAG, "location="+sb.toString());
			data = new JSONObject(sb.toString());
			data = (JSONObject) data.get("location");
			Location loc = new Location(LocationManager.NETWORK_PROVIDER);
			loc.setLatitude((Double) data.get("latitude"));
			loc.setLongitude((Double) data.get("longitude"));
			loc.setAccuracy(Float.parseFloat(data.get("accuracy").toString()));
			loc.setTime(GetUTCTime());
			return loc;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private long GetUTCTime() {
		Calendar cal = Calendar.getInstance(Locale.CHINA);
		int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);
		int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);
		cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
		return cal.getTimeInMillis();
	}

}
