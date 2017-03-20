package com.bojian.gis.port;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.bojian.gis.debug.L;
import com.bojian.gis.net.NotfoundException;
import com.bojian.gis.util.Common;

public abstract class Upload {
	private static final String TAG = "Upload";
	private HttpURLConnection httpURLConnection;
	private final static int CONNECTION_TIMEOUT = 20000;
	String username;
	String password;
	 int city;
	// private final static String USER_AGENT =
	// "HANMING/269013000720 Platform/AndroidV1.5/480x800/MIDP-2.0 Configuration/CLDC-1.0";
	 
	public Upload(String username,String password,int city){};
	
	public Upload(){};
	
	/**
	 * ���android�����쳣java.net.SocketException: Bad address family ���ߣ�lenomon
	 * ������2012-03-11 16:52 ���ࣺAndroid, Java �Ķ���1533 1������
	 * 
	 * ��Androidϵͳ�н������翪��ʱ�� ���ʹ����java.nio����ذ����п��������쳣java.net.SocketException:
	 * Bad address family�� ԭ����Android2.2bug��������IPV6Э�顣
	 */
	static {
		java.lang.System.setProperty("java.net.preferIPv4Stack", "true");
		java.lang.System.setProperty("java.net.preferIPv6Addresses", "false");
	}

	public void getUrlConnection(boolean usePorxy) throws Exception,NotfoundException{
		URL url = null;
		String urlStr = getUrl();
		L.info(TAG, "url=" + urlStr);
		if (urlStr == null)
			return;
		if (usePorxy) {
			url = new URL(Common.getWapUrl(urlStr));
			httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestProperty("X-Online-Host", Common.getHost(urlStr));
		} else {
			url = new URL(urlStr);
			httpURLConnection = (HttpURLConnection) url.openConnection();
		}
//		httpURLConnection.setDoOutput(true);
//		httpURLConnection.setDoInput(true);
//		httpURLConnection.setRequestMethod("GET");
//		httpURLConnection.setRequestProperty("Charset", "UTF-8");
//		httpURLConnection.setRequestProperty("Content-Type", "text/xml");
		httpURLConnection.setReadTimeout(CONNECTION_TIMEOUT);
		httpURLConnection.setConnectTimeout(CONNECTION_TIMEOUT);
		httpURLConnection.connect();

		InputStream is = null;
		InputStreamReader isr = null;
		L.info(TAG, "httpURLConnection.getResponseCode = "+httpURLConnection.getResponseCode());
		if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			try {
				is = httpURLConnection.getInputStream();
				isr = new InputStreamReader(is);
				getDataBase(isr);
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				try {
					if (isr != null) {
						isr.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					isr = null;
				}

				try {
					if (is != null) {
						is.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					is = null;
				}
				try {
					if (httpURLConnection != null) {
						httpURLConnection.disconnect();
						httpURLConnection = null;
					}
				} catch (Exception e) {
				} finally {
					httpURLConnection = null;
				}
			}

		} else {
			L.error(TAG, "NotfoundException"+getUrl());
			throw new NotfoundException(getUrl());
		}
	}

	public abstract void getDataBase(InputStreamReader isr) throws Exception;

	protected abstract String getUrl();
	
}
