package com.bojian.gis.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask.Status;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import com.bojian.gis.R;
import com.bojian.gis.SyncService;

public class DialogUtil {
	private AlertDialog mLoadingDialog;
	private AlertDialog settingDialog;
	private AlertDialog overtimeDialog;
	private Activity mContext;

	public DialogUtil(Activity mActivity) {
		this.mContext = mActivity;
	}
	
	public Dialog showLoadingDialog(){
		return showLoadingDialog(mContext.getString(R.string.data_loading));
	}
	
	
	
	public Dialog showLoadingDialog(String msg){
		return showLoadingDialog(msg, true);
	}
	public Dialog showLoadingDialog(String message, boolean cancelAble) {
		if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
			mLoadingDialog.cancel();
		}
		View loadingView = (View) mContext.getLayoutInflater().inflate(
				R.layout.loading_view, null);
		TextView mMessageView = (TextView) loadingView
				.findViewById(R.id.loading_message);
		mMessageView.setText(message);
		mLoadingDialog = new AlertDialog.Builder(getRootActivity()).create();

		if (mContext.isFinishing()) {
			return mLoadingDialog;
		}
		mLoadingDialog.setCancelable(cancelAble);
		mLoadingDialog.show();
		mLoadingDialog.getWindow().setContentView(loadingView);
		return mLoadingDialog;
	}

	public void dismissLoadingDialog(){
		if(mLoadingDialog!=null)
		mLoadingDialog.dismiss();
	}
	
	public Dialog showSettingDialog(){
		if(settingDialog!=null&&settingDialog.isShowing()){	
			return settingDialog;}
		settingDialog= new AlertDialog.Builder(getRootActivity())
		.setIcon(android.R.drawable.ic_dialog_alert)
		.setTitle(R.string.app_name)
		.setMessage(R.string.no_network)
		.setPositiveButton(R.string.dialog_btn_setting,
				new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog,
							int whichButton)
					{
						// 联网失败，setting net
						openSetting();
					}
				})
		.setNegativeButton(R.string.dialog_btn_cancel, null)
		.create();
		settingDialog.show();
		return settingDialog;
	}
	
	public Dialog showOvertimeDialog(final SyncService syncServices){
		if(overtimeDialog!=null&&overtimeDialog.isShowing()){
			return overtimeDialog;
		}
		overtimeDialog= new AlertDialog.Builder(getRootActivity())
		.setIcon(android.R.drawable.ic_dialog_alert)
		.setTitle(R.string.app_name)
		.setMessage(R.string.net_overtime)
		.setPositiveButton(R.string.dialog_btn_again,
				new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog,
							int whichButton)
					{
						// 联网超时，重新联网
						if(syncServices.getStatus()!=Status.RUNNING)
						syncServices.execute();
					}
				})
		.setNegativeButton(R.string.dialog_btn_cancel, null)
		.create();
		overtimeDialog.show();
		return overtimeDialog;
	}
	
	private void openSetting()
	{
		Intent intent = new Intent(Intent.ACTION_MAIN);
		  intent.setClassName("com.android.settings", "com.android.phone.Settings");
		  mContext.startActivity(new Intent(Settings.ACTION_SETTINGS));
	}
	/**
	 * 
	 * @Title getRootActivity
	 * @Description 得到 最上层的Activity,确保dialog的稳定
	 * @return 参数
	 * @return Activity 返回类型
	 * @throws
	 * @date 2012-6-6 上午10:41:07
	 */
	public Activity getRootActivity() {
		Activity context = mContext.getParent();
		if (context == null) {
			context = mContext;
		} else {
			if (context.getParent() != null) {
				context = context.getParent();
			}
		}
		return context;
	}
}
