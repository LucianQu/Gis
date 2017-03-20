package com.bojian.gis.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.bojian.gis.debug.L;

public class Common {

	public static boolean isUsingCmwap(Context context) {
		boolean result = false;
		try {
			ConnectivityManager cwjManager = (ConnectivityManager) context
					.getSystemService("connectivity");
			NetworkInfo info = cwjManager.getActiveNetworkInfo();
			if ((info.getTypeName().equalsIgnoreCase("MOBILE"))
					&& ((info.getExtraInfo().contains("cmwap")) || (info
							.getExtraInfo().contains("CMWAP"))))
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			L.error("gis",
					"When get the info if using internet,occur exception e:"
							+ e.toString());
		}

		return result;
	}

	/**
	 * 
	 * @Title getWapUrl
	 * @Description ����wap ��ƴ�Ӻ��url���� http://www.baidu.com/img/baidu_logo.gif
	 *              ƴ�Ӻ�http://10.0.0.172/img/baidu_logo.gif
	 * @param url
	 * @return String ��������
	 * @throws
	 * @author aceway-liwei
	 * @date 2012-6-5 ����11:04:03
	 */
	public static String getWapUrl(String url) {
		if ((url == null) || (url.equals(""))) {
			return null;
		}
		url = url.replaceFirst("http://", "");
		url = url.substring(url.indexOf("/"), url.length());
		return "http://10.0.0.172" + url;
	}

	/**
	 * 
	 * @Title getHost
	 * @Description �õ� ��ַ�� host �� http://www.baidu.com/img/baidu_logo.gif �õ�
	 *              www.baidu.com
	 * @param url
	 * @return ����
	 * @return String ��������
	 * @throws
	 * @author aceway-liwei
	 * @date 2012-6-5 ����12:01:16
	 */
	public static String getHost(String url) {
		if ((url == null) || (url.equals("")))
			return null;
		url = url.replaceFirst("http://", "");
		return url.substring(0, url.indexOf("/"));
	}

	/**
	 * �˳�����
	 * 
	 * @param ctx
	 */
	public static void ExitApp(Context ctx) {
		NotificationManager nm = (NotificationManager) ctx
				.getSystemService(ctx.NOTIFICATION_SERVICE);
		nm.cancelAll();

		Intent startMain = new Intent(Intent.ACTION_MAIN);
		startMain.addCategory(Intent.CATEGORY_HOME);
		startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		ctx.startActivity(startMain);

		android.os.Process.killProcess(android.os.Process.myPid());

		System.exit(0);
	}

	/**
	 * ��ȡ��ǰʱ�䲢��ʽ�����
	 * 
	 * @param format
	 * @return
	 */
	public static String getCurrDate(String format) {
		String str_format = (format == null || "".equals(format)) ? "yyyy-MM-dd HH:mm:ss"
				: format;
		Date date = new Date();
		SimpleDateFormat from = new SimpleDateFormat(str_format);
		String times = from.format(date);

		return times;
	}

	/**
	 * ��ȡ��ǰ���ڼ���ǰ������
	 * 
	 * @param day
	 * @return
	 */

	public static String getCurrDate(int day) {
		String times = getCurrDate("yyyy-MM-dd");
		String months = null;
		String dates = null;
		int year = 0;
		int month = 0;
		int date = 0;

		year = Integer.parseInt(times.split("-")[0]);
		month = Integer.parseInt(times.split("-")[1]);
		date = Integer.parseInt(times.split("-")[2]);

		if (day <= date) {
			date = date - day + 1;
		} else {
			date = date + 30 - day;
			month = month - 1;
			if (month == 0) {
				month = 12;
				year = year - 1;
			}
		}
		if (month < 10) {
			months = "0" + month;
		} else {
			months = "" + month;
		}

		if (date < 10) {
			dates = "0" + date;
		} else {
			dates = "" + date;
		}

		return year + "-" + months + "-" + dates;
	}
	
	
	
//  ����һ���������ַ��������ؼӼ�n�����������ַ���  
    public  static String  nDaysAfterOneDateString(String   basicDate,int   n)   {  
        SimpleDateFormat   df   =   new   SimpleDateFormat("yyyy-MM-dd");  
        Date   tmpDate   =   null;  
        try   {  
            tmpDate   =   df.parse(basicDate);  
        }  
        catch(Exception   e){  
            //   �������ַ�����ʽ����  
        }  
        long   nDay=(tmpDate.getTime()/(24*60*60*1000)+1+n)*(24*60*60*1000);  
        tmpDate.setTime(nDay);  
 
        return   df.format(tmpDate);  
    }  
	
//�õ�ĳ��ĳ���ж�����
    
    public static int getDayofMonth(int year,int month)
    {
    	Calendar cal = Calendar.getInstance();
    	cal.set(Calendar.YEAR,year);
    	cal.set(Calendar.MONTH, month - 1);//Java�·ݲ�0��ʼ��
    	int dateOfMonth = cal.getActualMaximum(Calendar.DATE);
    	return dateOfMonth;
    	
    }
	
	
	

	/***
	 * ���ת��Ϊȫ��
	 * 
	 * @param input
	 * @return
	 */
	public static String ToDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}

	/**
	 * * ȥ�������ַ����������ı���滻ΪӢ�ı��
	 * 
	 * @param str
	 * @return
	 */
	public static String stringFilter(String str) {
		str = str.replaceAll("��", "[").replaceAll("��", "]")
				.replaceAll("��", "!").replaceAll("��", ":");// �滻���ı��
		String regEx = "[����]"; // ����������ַ�
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	/**
	 * Drawable---->Bitmap
	 * 
	 * @param drawable
	 * @return
	 */
	public static Bitmap drawableToBitmap(Drawable drawable) {

		Bitmap bitmap = Bitmap.createBitmap(

		drawable.getIntrinsicWidth(),

		drawable.getIntrinsicHeight(),

		drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888

		: Bitmap.Config.RGB_565);

		Canvas canvas = new Canvas(bitmap);

		// canvas.setBitmap(bitmap);

		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
				drawable.getIntrinsicHeight());

		drawable.draw(canvas);

		return bitmap;

	}
	
	public static  boolean check(Context ctx) {  
        PackageManager pm = ctx.getPackageManager();  
        List<PackageInfo> infoList = pm  
                .getInstalledPackages(PackageManager.GET_SERVICES);  
        for (PackageInfo info : infoList) {  
            if ("com.adobe.flashplayer".equals(info.packageName)) {  
                return true;  
            }  
        }  
        return false;  
}  
	public static String gbEncoding(final String gbString) {
		  char[] utfBytes = gbString.toCharArray();
		  StringBuffer buffer = new StringBuffer();
		  for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
		   String hexB = Integer.toHexString(utfBytes[byteIndex]);
		   if (hexB.length() <= 2) {
		    hexB = "00" + hexB;
		   }
		   buffer.append("" + hexB);
		  }
		  return buffer.substring(0);
		 }
	
}
