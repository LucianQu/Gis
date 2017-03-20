package com.bojian.gis.ui;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import android.os.Vibrator;

import com.bojian.gis.R;
import com.bojian.gis.SyncService;
import com.bojian.gis.adapter.MainMenuAdapter;

import com.bojian.gis.debug.L;
import com.bojian.gis.entity.ItemBean;
import com.bojian.gis.entity.WaringChangeBean;
import com.bojian.gis.net.ExceptionInfo;
import com.bojian.gis.port.IsWarning;
import com.bojian.gis.port.RainWarn;
import com.bojian.gis.port.WarningChange;
import com.bojian.gis.port.WaterWarn;
import com.bojian.gis.util.Common;
import com.bojian.gis.util.SharePreUtil;
import com.bojian.gis.util.UrlLib;

public class MainMenuActivity extends BaseActivity {
	private static final String TAG = "MainMenuActivity";
	private GridView m_gridView;
	private List<ItemBean> m_itemBeanList;
	private ItemBean m_ItemBean;
	private Button m_backBtn;
	private TextView m_titleTv;
	private Button m_otherBtn;
	private static final int EXIT_APP = 0;
	private static final int DOWNLOAD = 1;
	
	private Intent m_intent;
	private Handler m_handler;
	
	private int clo = 0; 
	private MainMenuAdapter m_mainMenuAdapt; 
	private Vibrator m_vibrator=null;
	
	private Timer m_timer;
	private Timer m_tmrWaringChange;
	private IsWarning m_isWarn;
	
	private  WarningChange m_warnChange;
	
	private  ArrayList<WaringChangeBean> m_warnChangeBeanList; //已经报警的ID


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
		_initTitle();
		_init();
		_setaAdapter();
		//getwarning();
		getWarningChange();
		
	}
	
	private void getwarning()
	{
		SharePreUtil sp = new SharePreUtil(MainMenuActivity.this);
		String username = sp.getStringValueByKey("username");
		String password = sp.getStringValueByKey("password");
		int city;
		if (UrlLib.isAdmin) {
			city = 0;
		} else {
			city = sp.getIntegerValueByKey("city");
		}
	
		m_isWarn = new IsWarning(username, password, city);
		SyncService ss = new SyncService(MainMenuActivity.this, m_isWarn);
		ss.isShowLoadDialog = false;
		ss.execute();
	
	}

	private void _initTitle() {
		m_backBtn = (Button) findViewById(R.id.titlebar_back);
		m_titleTv = (TextView) findViewById(R.id.titlebar_title);
		m_otherBtn = (Button) findViewById(R.id.titlebar_other);
		m_backBtn.setVisibility(View.GONE);
		m_otherBtn.setVisibility(View.GONE);
		m_titleTv.setText(R.string.titlebar_title);
	}

	private void _init() {
		m_gridView = (GridView) findViewById(R.id.main_menu);
		m_itemBeanList = new ArrayList<ItemBean>();
		m_warnChangeBeanList = new ArrayList<WaringChangeBean>();
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
		
	}

	private void _setaAdapter() {
		int[] itemPicArr = {R.drawable.history,
				R.drawable.weather, R.drawable.reservior,
				R.drawable.satellitecloudimagery, R.drawable.index_icon};
		String[] itemStrArr = { "开关泵记录","天气查询", "用水记录", "充值记录", "关于" };// problem also excist
		for (int i = 0; i < itemPicArr.length; i++) {
			m_ItemBean = new ItemBean();
			m_ItemBean.setId(itemPicArr[i]);
			m_ItemBean.setName(itemStrArr[i]);
			m_itemBeanList.add(m_ItemBean);
		}
		m_mainMenuAdapt = new MainMenuAdapter(MainMenuActivity.this, m_itemBeanList);
		m_gridView.setAdapter(m_mainMenuAdapt);
		m_gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				stop();
				switch (position) {
//				case 0://雨情预警
//					L.info(TAG, "*******position = " + position);
//					intent = new Intent();
//					intent.addFlags(UrlLib.RAIN_WARNING);
//					intent.setClass(MainMenuActivity.this, MActivity.class);
//					//startActivity(intent);
//					startActivityForResult(intent, 1);
//					if (mma.getWarntag()==0)
//					timer.cancel();
//					else if (mma.getWarntag()>=2)
//						mma.setWarntag(1);
//					
//					break;
//				case 1://水情预警
//					L.info(TAG, "*******position = " + position);
//					intent = new Intent();
//					intent.addFlags(UrlLib.WATER_WARNING);
//					intent.setClass(MainMenuActivity.this, MActivity.class);
//					//startActivity(intent);
//					startActivityForResult(intent, 2);
//					if (mma.getWarntag()==1)
//						timer.cancel();
//						else if (mma.getWarntag()>=2)
//							mma.setWarntag(0);	
//					break;
//
//				case 2://雨情查询
//					intent = new Intent();
//					//intent.addFlags(UrlLib.afterEight);
//					//intent.setClass(MainMenuActivity.this, RainTabActivity.class);
//					intent.setClass(MainMenuActivity.this, TotalRainActivity.class);
//					startActivity(intent);
//					break;
//				case 3://重点河道
//					L.info(TAG, "*******position = " + position);
//					intent = new Intent();
//					intent.addFlags(UrlLib.IMPORT_RIVER);
//					intent.setClass(MainMenuActivity.this, MActivity.class);
//					startActivity(intent);
//					break;
//				case 4://水文预报
//					L.info(TAG, "*******position = " + position);
//					intent = new Intent();
//					intent.setClass(MainMenuActivity.this,
//							WaterForcastActivity.class);
//					startActivity(intent);
//					break;
//				case 5://大中型水库
//					intent = new Intent();
//					intent.addFlags(UrlLib.RESERVIOR);
//					//intent.setClass(MainMenuActivity.this, MActivity.class);ReserviorActivity
//					intent.setClass(MainMenuActivity.this, ReserviorActivity.class);
//					startActivity(intent);
////					intent = new Intent();
////					intent.setFlags(5);
////					intent.setClass(MainMenuActivity.this,
////							SatelliteCloudImageryActivity.class);
////					startActivity(intent);
//					
//					break;
				case 0://开关泵记录
					m_intent = new Intent();
					m_intent.setClass(MainMenuActivity.this, PumpOnoffRecordActivity.class);
					startActivity(m_intent);
					break;
				case 1://开关泵操作
					m_intent = new Intent();
					m_intent.setClass(MainMenuActivity.this, PumpOnoffActivity.class);
					startActivity(m_intent);
					break;
//				case 1://天气查询
//					m_intent = new Intent();
//					m_intent.setClass(MainMenuActivity.this,
//							WeatherForcastActivity.class);
//					startActivity(m_intent);
//					break;
				case 2://用水记录
					m_intent = new Intent();
					m_intent.setClass(MainMenuActivity.this, WaterUsingActivity.class);
					startActivity(m_intent);
					break;
				case 3://充值记录
					m_intent = new Intent();
					m_intent.setClass(MainMenuActivity.this, RechargeActivity.class);
					startActivity(m_intent);
					break;
					
//				case 3://台风路径
//					if (Common.check(MainMenuActivity.this)) {
//						intent = new Intent(Intent.ACTION_VIEW, Uri
//								.parse(UrlLib.TYPHOON));
//						intent.setClassName("com.android.browser",
//								"com.android.browser.BrowserActivity");
//						startActivity(intent);
//					} else {
//						showDialog(DOWNLOAD);
//					}
//					break;
//				case 4://卫星云图
//					intent = new Intent();
//					intent.setFlags(8);
//					intent.setClass(MainMenuActivity.this,
//							SatelliteCloudImageryActivity.class);
//					startActivity(intent);
//					break;
				case 4://关于
					m_intent = new Intent();
					m_intent.setClass(MainMenuActivity.this, IndexActivity.class);
					startActivity(m_intent);
					break;
//				case 5://注销
//					intent = new Intent();
//					intent.setClass(MainMenuActivity.this, MainMenuActivity.class);
//					startActivity(intent);
//					break;
				default:
					break;
				}
			}
		});
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			showDialog(EXIT_APP);
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		switch (id) {
		case EXIT_APP:
			return new AlertDialog.Builder(this).setTitle("提示")
					.setIcon(android.R.drawable.ic_dialog_info)
					.setMessage("确认退出吗？")
					.setPositiveButton("确认", new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Common.ExitApp(MainMenuActivity.this);
						}
					}).setNegativeButton("取消", new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

						}
					}).create();
		case DOWNLOAD:
			return new AlertDialog.Builder(this).setTitle("提示")
					.setIcon(android.R.drawable.ic_dialog_info)
					.setMessage("该功能需要FlashPlayer插件支持,是否安装？")
					.setPositiveButton("确认", new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							gomarket();
						}
					}).setNegativeButton("取消", new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

						}
					}).create();
		default:
			return null;
		}
	}

	private void gomarket() {
		if (m_handler == null)
			m_handler = new Handler();
		m_handler.post(new Runnable() {
			public void run() {
				Intent installIntent = new Intent("android.intent.action.VIEW");
				installIntent.setData(Uri
						.parse("market://details?id=com.adobe.flashplayer"));
				startActivity(installIntent);
			}
		});
	}

	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
		if (requestCode ==  1)
		{
			m_mainMenuAdapt.setmTextColor(Color.BLACK);
			m_mainMenuAdapt.setRewarntag(0);
			m_mainMenuAdapt.notifyDataSetChanged();
			
		}
		else if (requestCode ==  2)
		{
			m_mainMenuAdapt.setmTextColor(Color.BLACK);
			m_mainMenuAdapt.setRewarntag(1);
			m_mainMenuAdapt.notifyDataSetChanged();
			
		}
		else if (requestCode ==  123) {  
		    if (resultCode == RESULT_OK) {  
		      Bitmap bm = (Bitmap) data.getExtras().get("data");  
		      //imgPhoto.setImageBitmap(bm);  
		     
		      ByteArrayOutputStream baos = new ByteArrayOutputStream();
		      bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
              byte[] photoBytes = baos.toByteArray();
              String SD_CARD_TEMP_DIR = Environment.getExternalStorageDirectory() + File.separator + "IMG.jpg";
              File aFile = new File(getDatedFName(SD_CARD_TEMP_DIR));
              String photoPath = aFile.getAbsolutePath();
              
              boolean b;
              if (aFile.exists()) 
            	  b = aFile.delete();
              //f.mkdirs();
              try {
				aFile.createNewFile();
			
              
              FileOutputStream fos;
			try {
				fos = new FileOutputStream(aFile);
			
              fos.write(photoBytes);
              fos.close();
		      
              
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
              } catch (IOException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}  //need add permission to manifest    
		      
		          }  
		  }  
		}  	
	
	 public static String getDatedFName(String fname) {
         StringBuffer result = new StringBuffer();
         SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmmss");
         String dateSfx = "_" + df.format(new Date());
         int idx = fname.lastIndexOf('.');
         if (idx != -1) {
                 result.append(fname.substring(0, idx));
                 result.append(dateSfx);
                 result.append(fname.substring(idx));
         } else {
                 result.append(fname);
                 result.append(dateSfx);
         }
         return result.toString();
 }
	 
	 public  void spark() {  
		

	        m_timer = new Timer();  
	        TimerTask taskcc = new TimerTask() {  
	            public void run() {  
	                runOnUiThread(new Runnable() {  
	                    public void run() {  
	                        if (clo == 0) {  
	                            clo = 1;  
	                            m_mainMenuAdapt.setmTextColor(Color.BLACK);
	                            
	                        } else {  
	                            if (clo == 1) {  
	  
	                                clo = 0;  
	                                m_mainMenuAdapt.setmTextColor(Color.RED);  
	                            } 
	                        }
	                        
	                        m_mainMenuAdapt.notifyDataSetChanged();
	                       
	                    }  
	                });  
	            }  
	        };  
	        m_timer.schedule(taskcc, 3, 300);  
	      
	    }  
	 
	 //定时器调用新警情和升级警情
	 public  void getWarningChange() {  
			
		 SharePreUtil sp = new SharePreUtil(MainMenuActivity.this);
			final String username = sp.getStringValueByKey("username");
			final String password = sp.getStringValueByKey("password");
			final int city;
			if (UrlLib.isAdmin) {
				city = 0;
			} else {
				city = sp.getIntegerValueByKey("city");
			}
		 m_tmrWaringChange = new Timer();  
	        TimerTask taskcc = new TimerTask() {  
	            public void run() {  
	                runOnUiThread(new Runnable() {  
	                    public void run() {  
	                    	 m_warnChange=new WarningChange(MainMenuActivity.this,username,password,city);
	                    	  L.info(TAG, "1234");
	                    	  SyncService ss = new SyncService(MainMenuActivity.this, m_warnChange);
	                  		  ss.isShowLoadDialog = false;
	                  		  ss.isLoadResult = false;
	                  		  ss.execute();
	                       
	                    }  
	                });  
	            }  
	        };  
	        m_tmrWaringChange.schedule(taskcc, 3, 60000*5);  
	      
	    } 
	 
	//处理报警，加到缓存，防止重复报警 
	  public void doWarnginginfo()
	 {
		  boolean rain = false;
		  boolean water = false;
		  
		 ArrayList<WaringChangeBean> list =m_warnChange.getList();
		 for (WaringChangeBean aWaringChangeBean :list )
		 {
			 L.info(TAG, aWaringChangeBean.getId()); 
			 boolean isFind=false;
			 for (WaringChangeBean iWaringChangeBean :m_warnChangeBeanList ) 
			 {
				if  (aWaringChangeBean.getId().equals(iWaringChangeBean.getId()))
						{
					      isFind=true;
					      break;
						}
			 }
			 if (!isFind)
			 {
				 m_warnChangeBeanList.add(aWaringChangeBean); //加到已报警列表中
				 String type=aWaringChangeBean.getType();
				 if (type.equals("1"))
					 rain=true;
				 else if (type.equals("2"))
					 water=true;	 

			 }
			 
		 }
		 int re=-1;
		 if (rain)
		 {
			 re=0;
			if (water)
				re=2;
		 }
		 else if (water)
			 re=1;
		 
		 if (re>=0)
		 beginwarning(re);
	 }
	 

	 //启动震动
		public void start() {
			// TODO Auto-generated method stub
			if(m_vibrator==null){
				m_vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);
				long[] pattern={100,5000,100,5000};//OFF/ON/OFF/ON
				m_vibrator.vibrate(pattern, 0);
			}
		}

	 //停止震动	
	 public void stop() {
			// TODO Auto-generated method stub
			if(m_vibrator!=null){
				m_vibrator.cancel();
				m_vibrator=null;
			}
		}

	@Override
	public void onDataRequestSuccess() {
		// TODO Auto-generated method stub
		super.onDataRequestSuccess();
		beginwarning(m_isWarn.getWarningType());
		
	}

	private void beginwarning(int type) //震动闪烁
	{
		int re=-1;
		re =type;
		//re=0;	
		if (re>=0)
		{
			
		  m_mainMenuAdapt.setWarntag(re);
	    	start();	
		    spark();
		}
	}
	
	
	@Override
	public void onDataRequestFail(ExceptionInfo error) {
		// TODO Auto-generated method stub
		super.onDataRequestFail(error);
	}
	 
	 
}
