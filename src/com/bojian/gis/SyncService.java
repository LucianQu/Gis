package com.bojian.gis;

import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.view.KeyEvent;
import android.widget.Toast;
import com.bojian.gis.debug.L;
import com.bojian.gis.json.JSONException;
import com.bojian.gis.net.ExceptionInfo;
import com.bojian.gis.net.NotfoundException;
import com.bojian.gis.port.Upload;
import com.bojian.gis.ui.BaseActivity;
import com.bojian.gis.util.Common;
import com.bojian.gis.util.DialogUtil;

public class SyncService extends
		AsyncTask<Integer, ExceptionInfo, ExceptionInfo> {

	private static final String TAG = "SyncService";
	/** ���� */
	public static final int REQUEST_OK = 0;
	/** �������� */
	public static final int ERROR_CODE_PARSE = 2;
	/** ���粻���� */
	public static final int ERROR_CODE_NET_UNAVAILABLE = 3;
	/** ������ʱ */
	public static final int ERROR_CODE_NET_TIMEOUT = 4;
	/** ������ 404 */
	public static final int ERROR_CODE_NET_NOTFOUND = 5;
	/** URL ���� */
	public static final int ERROR_CODE_URL = 6;
	/** ���� */
	public static final int ERROR_CODE_OTHER = 7;

	/** δ�����쳣 */
	public static final int ERROR_CODE_UNCATCH = 8;

	/** �������ݺ��Ƿ�ص� {@link BaseActivity#onDataRequestSucceed()} **/
	public boolean isLoadResult = true;
	
	public boolean isShowLoadDialog = true;

	private Activity activity;
	private Upload mUpload;
	private ConnectivityManager mConnectivityManager;
	private SyncServiceListener nSyncServiceListener;
	private DialogUtil mDialogUtil;

	public SyncServiceListener getnSyncServiceListener() {
		return nSyncServiceListener;
	}

	public void setnSyncServiceListener(SyncServiceListener nSyncServiceListener) {
		this.nSyncServiceListener = nSyncServiceListener;
	}

	public SyncService(Activity mActivity, Upload upload) {
		this.activity = mActivity;
		this.mUpload = upload;
		mDialogUtil = new DialogUtil(mActivity);
	}

	private boolean isNetWorkAvailable() {
		if (mConnectivityManager == null) {
			mConnectivityManager = (ConnectivityManager) activity
					.getSystemService(Context.CONNECTIVITY_SERVICE);
		}
		if (mConnectivityManager != null) {
			NetworkInfo networkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (networkInfo != null && networkInfo.isAvailable()) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		if(isShowLoadDialog){
			mDialogUtil.showLoadingDialog().setOnKeyListener(listener);
		}
	}

	/**
	 * loadingDialog ��ȡ����ť�¼�
	 */
	private OnKeyListener listener = new OnKeyListener() {
		@Override
		public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
			if(keyCode==KeyEvent.KEYCODE_BACK)
			     cancel(true);
			return false;
		}
	};
	
	@Override
	protected ExceptionInfo doInBackground(Integer... params) {
		// TODO Auto-generated method stub
		L.info(TAG, "Network = "+isNetWorkAvailable());
		if (!isNetWorkAvailable()) {
			return new ExceptionInfo(ERROR_CODE_NET_UNAVAILABLE, null);
		}
		boolean useProxy = Common.isUsingCmwap(activity);
		L.info(TAG, "userProxy = "+useProxy);
		try {
			mUpload.getUrlConnection(useProxy);
		}catch(JSONException e){
			e.printStackTrace();
			return new ExceptionInfo(ERROR_CODE_PARSE, e);
		} 
		catch (SocketTimeoutException e) {
			e.printStackTrace();
			return new ExceptionInfo(ERROR_CODE_NET_TIMEOUT,e);
		} catch (NotfoundException e) {
			e.printStackTrace();
			return new ExceptionInfo(ERROR_CODE_NET_NOTFOUND,e);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return new ExceptionInfo(ERROR_CODE_URL,e);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return new ExceptionInfo(REQUEST_OK, null);
	}

	@Override
	protected void onPostExecute(ExceptionInfo result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if (nSyncServiceListener != null) {
			if (isLoadResult) {
				if (REQUEST_OK == result.state) {
					nSyncServiceListener.onDataRequestSuccess();
				} else {
					nSyncServiceListener.onDataRequestFail(result);
				}
			}
		} else if (activity instanceof BaseActivity) {
			if(isLoadResult){
				if(REQUEST_OK == result.state){
					((BaseActivity)activity).onDataRequestSuccess();
				}else{
					/*******Test Code*****/
//					((BaseActivity)activity).onDataRequestSuccess();
					onDataRequestError(result);
				}
			}
		}
		mDialogUtil.dismissLoadingDialog();
	}

	
	/**
	 * �Դ����ͳһ����
	 * 
	 * @Description �Դ�������������� ��������������SyncServices.onDataRequestError(errorState),
	 *              ��ֹ��������������
	 * @param errorInfo
	 *            ����״̬
	 * 
	 */
	public void onDataRequestError(ExceptionInfo errorInfo) {
		//�ύ��������
		String errState = "";
		//String ��  �����û���ʾ
		int errStrint=R.string.other_error;
		switch (errorInfo.state) {
		case SyncService.ERROR_CODE_PARSE:
			errState = "���ݽ�������";
			errStrint=R.string.parser_error;
			break;
		case SyncService.ERROR_CODE_NET_UNAVAILABLE:
//			errState = "���粻����";
			new DialogUtil(activity).showSettingDialog();
			break;
		case SyncService.ERROR_CODE_NET_TIMEOUT:
//			errState = "���ݼ��س�ʱ";
			errorInfo.exception=null;
			new DialogUtil(activity).showOvertimeDialog(new SyncService(activity, mUpload));
			break;
		case SyncService.ERROR_CODE_NET_NOTFOUND:
			errState = "404";
			errStrint=R.string.nofound_error;
			break;
		case SyncService.ERROR_CODE_URL:
			errState = "URL ��ַ����";
			errStrint=R.string.url_error;
			break;
		case SyncService.ERROR_CODE_OTHER:
			errState = "ϵͳ�ڲ�����";
			errStrint=R.string.other_error;
			break;
		}
		if (!"".equals(errState))
			Toast.makeText(activity, errStrint, Toast.LENGTH_LONG).show();
	}
	
	
	
	
	
	public interface SyncServiceListener {

		void onDataRequestSuccess();

		void onDataRequestFail(ExceptionInfo result);
	}

}
