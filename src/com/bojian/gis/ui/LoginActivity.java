package com.bojian.gis.ui;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bojian.gis.R;
import com.bojian.gis.SyncService;
import com.bojian.gis.SyncService.SyncServiceListener;
import com.bojian.gis.debug.L;
import com.bojian.gis.entity.ArealBean;
import com.bojian.gis.net.ExceptionInfo;
import com.bojian.gis.port.UserLogin;
import com.bojian.gis.util.SharePreUtil;
import com.bojian.gis.util.UrlLib;
//登录页面
public class LoginActivity extends Activity implements OnClickListener,
		OnCheckedChangeListener, OnItemSelectedListener {
	private static final String TAG = "LoginActivity";
	private EditText m_userNameET;
	private EditText m_passWordET;
	private Button m_backBtn;
	private TextView m_titleTv;
	private Button m_otherBtn;
	private CheckBox m_savePwdCb;
	private CheckBox m_autoLoginCb;
	private Button m_loginBtn;//登录按钮
	private Spinner spinner;//下拉列表
	/*** Test Code **/
	private ArrayList<ArealBean> m_areaBeanList;
	private ArrayAdapter<String> strAdapter;
	private SharePreUtil m_sharePreUtil;
	private int m_city = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
//		init_title();	
		init();
		isAutoLogin();
	}

	private void init_title() {
		m_backBtn = (Button) findViewById(R.id.titlebar_back);
		m_titleTv = (TextView) findViewById(R.id.titlebar_title);
		m_otherBtn = (Button) findViewById(R.id.titlebar_other);
		m_backBtn.setVisibility(View.GONE);
		m_otherBtn.setVisibility(View.GONE);
		m_titleTv.setText(R.string.user_login);
	}

	private void init() {
		m_userNameET = (EditText) findViewById(R.id.acount);
		m_passWordET = (EditText) findViewById(R.id.password);
		//spinner = (Spinner) findViewById(R.id.server);
		m_savePwdCb = (CheckBox) findViewById(R.id.save_password);
		m_savePwdCb.setOnCheckedChangeListener(this);//记住密码复选框监听器
		m_autoLoginCb = (CheckBox) findViewById(R.id.auto_login);
		m_autoLoginCb.setOnCheckedChangeListener(this);//自动登录复选框监听器
		m_loginBtn = (Button) findViewById(R.id.login);
		m_areaBeanList = (ArrayList<ArealBean>) UrlLib.areaBeanList;
		L.info(TAG, "areaBeanList=" + m_areaBeanList);
//		adapter = new ArrayAdapter<String>(this,
//				android.R.layout.simple_spinner_item, UrlLib.cityName);
//		
//		//android.R.layout.simple_spinner_dropdown_item
//		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		spinner.setAdapter(adapter);
//		spinner.setOnItemSelectedListener(this);
	}

	private boolean checkBeforeLogin() {
		String userName = m_userNameET.getText().toString();
		String passWord = m_passWordET.getText().toString();
		if (null == userName || userName.equals("")) {
			showToast("账号不能为空");
			return false;
		}
		if (null == passWord || passWord.equals("")) {
			showToast("密码不能为空");
			return false;
		}
		return true;
	}

	private void showToast(String msg) {
		Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
	}

	private void setListener() {
		m_loginBtn.setOnClickListener(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
		setListener();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		if (v == m_loginBtn) {
			if (checkBeforeLogin()) {
				String userName = m_userNameET.getText().toString();
				String passWord = m_passWordET.getText().toString();
				L.info(TAG, "passWord=" + passWord);
				m_city = new SharePreUtil(LoginActivity.this)
						.getIntegerValueByKey("city");
				final UserLogin userLogin = new UserLogin(userName, passWord, m_city);
				SyncService syncService = new SyncService(LoginActivity.this, userLogin);
				syncService.isShowLoadDialog = true;
				syncService.setnSyncServiceListener(new SyncServiceListener() {
					@Override
					public void onDataRequestSuccess() {
						// TODO Auto-generated method stub
						switch (userLogin.getStatus()) {
						case 0:
							if (m_autoLoginCb.isChecked()) {
								m_sharePreUtil.saveBooleanValueToSp("save_password", true);
								m_sharePreUtil.saveBooleanValueToSp("auto_login", true);
							} else {
								if (m_savePwdCb.isChecked()) {
									m_sharePreUtil.saveBooleanValueToSp("save_password",
											true);
								}
								m_sharePreUtil.saveBooleanValueToSp("auto_login", false);
							}
							if(m_city==0)
								UrlLib.isAdmin=true;
							m_sharePreUtil.saveStringValueToSp("username", m_userNameET
									.getText().toString());
							m_sharePreUtil.saveStringValueToSp("password", m_passWordET
									.getText().toString());
							Intent intent = new Intent();
							intent.setClass(LoginActivity.this,
									MainMenuActivity.class);
							startActivity(intent);
							finish();
							break;
						case 1:
							showToast("登陆失败！");
							break;
						case 2:
							showToast("密码错误！");
							break;
						case 3:
							showToast("该用户已禁用！");
							break;
						case 4:
							showToast("请选择对应的服务器！");
						default:
							break;
						}
					}

					@Override
					public void onDataRequestFail(ExceptionInfo result) {
						// TODO Auto-generated method stub
						L.info(TAG, "MSG = " + "onDataRequestFail");
						showToast("登陆失败");
					}
				});
				syncService.execute();
			}
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		if (buttonView == m_savePwdCb) {

		} else if (buttonView == m_autoLoginCb) {
			if (isChecked) {
				m_savePwdCb.setChecked(true);
			}
		}
		L.info(TAG, "is Checked = " + isChecked);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		if (m_areaBeanList != null && m_areaBeanList.size() > 0) {
			new SharePreUtil(LoginActivity.this).saveIntValueToSp("city", m_areaBeanList
					.get(position).getId());
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		L.info(TAG, "nothing selected ");
		//		new SharePreUtil(LoginActivity.this).saveIntValueToSp("city", -1);
	}

	private void isAutoLogin() {
		// TODO Auto-generated method stub
		m_sharePreUtil = new SharePreUtil(LoginActivity.this);
		boolean auto_login = m_sharePreUtil.getBooleanValueByKey("auto_login");
		boolean save_password = m_sharePreUtil.getBooleanValueByKey("save_password");
		final int city = m_sharePreUtil.getIntegerValueByKey("city");
		L.info(TAG, "city=" + city);
		if (!auto_login) {
			if (save_password) {
				m_savePwdCb.setChecked(true);
				m_userNameET.setText(m_sharePreUtil.getStringValueByKey("username"));
				m_passWordET.setText(m_sharePreUtil.getStringValueByKey("password"));
			}
		} else {
			final UserLogin ul = new UserLogin(
					m_sharePreUtil.getStringValueByKey("username"),
					m_sharePreUtil.getStringValueByKey("password"), city);
			SyncService ss = new SyncService(LoginActivity.this, ul);
			ss.isShowLoadDialog = true;
			ss.setnSyncServiceListener(new SyncServiceListener() {
				@Override
				public void onDataRequestSuccess() {
					// TODO Auto-generated method stub
					switch (ul.getStatus()) {
					case 0:
						if(city==0)
							UrlLib.isAdmin = true;
						Intent intent = new Intent();
						intent.setClass(LoginActivity.this,
								MainMenuActivity.class);
						startActivity(intent);
						finish();
						break;
					case 1:
						showToast("登陆失败！");
						break;
					case 2:
						showToast("密码错误！");
						break;
					case 3:
						showToast("该用户已禁用！");
						break;
					case 4:
						showToast("请选择对应的服务器！");
					default:
						break;
					}
				}
				public void onDataRequestFail(ExceptionInfo result) {
					// TODO Auto-generated method stub
					showToast("登陆失败");
				}
			});
			ss.execute();
		}
	}
}
