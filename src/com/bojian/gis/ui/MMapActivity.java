package com.bojian.gis.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.ItemizedOverlay;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.Projection;
import com.bojian.gis.R;
import com.bojian.gis.R.color;
import com.bojian.gis.debug.L;
import com.bojian.gis.entity.ImportRiverBean;
import com.bojian.gis.entity.RainDetailBean;
import com.bojian.gis.entity.TotalRainBean;
import com.bojian.gis.entity.WaterWarnBean;
import com.bojian.gis.port.RainAfterEight;
import com.bojian.gis.util.Common;
import com.bojian.gis.util.CustomOverlayItem;
import com.bojian.gis.util.UrlLib;

public class MMapActivity extends MapActivity {
	private static final String TAG = "MMapActivity";
	private BMapManager mBMapMan;
	private MapController mapController;  
	static MapView mMapView;
	static View mPopView;
	static TextView textView;
	OverItemT overitem = null;
	private Intent intent;
	private ArrayList<WaterWarnBean> list1;
	private ArrayList<WaterWarnBean> list2;
	private ArrayList<ImportRiverBean> list3;
	private ArrayList<ImportRiverBean> list4;
	private ArrayList<TotalRainBean> list5;
	private ArrayList<RainDetailBean> list6;
	private ArrayList<RainDetailBean> list7;//当日雨量列表
	
	private Drawable marker[] = new Drawable[6];
	private Drawable Temp;
	/*************** 默认数据 **************/
	private ArrayList<CustomOverlayItem> mOverlayItems;
	private ArrayList<CustomOverlayItem> level_one_items = new ArrayList<CustomOverlayItem>();
	private ArrayList<CustomOverlayItem> level_two_items = new ArrayList<CustomOverlayItem>();
	private ArrayList<CustomOverlayItem> level_three_items = new ArrayList<CustomOverlayItem>();
	private ArrayList<CustomOverlayItem> level_other_items = new ArrayList<CustomOverlayItem>();
	
	private GeoPoint point;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.water_condition_map);
		init_map_man();
		init_pic();
		init_mapView();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onResume() {
		mBMapMan.start();
        L.info(TAG, "startLoad");
		startLoad();
		super.onResume();
	}

	@Override
	protected void onPause() {
		if (mBMapMan != null)
			mBMapMan.stop();
		if (MMapActivity.mPopView != null)
			MMapActivity.mPopView.setVisibility(View.GONE);
		super.onPause();
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	private void init_map_man() {
		// TODO Auto-generated method stub
		if (mBMapMan == null)
			mBMapMan = new BMapManager(getApplication());
		boolean isthrough = mBMapMan.init(UrlLib.key, null);
		L.info(TAG, "isthrough=" + isthrough);
		mBMapMan.start();
		super.initMapActivity(mBMapMan);
	}

	private void init_pic() {
		// TODO Auto-generated method stub
		int ids[] = { R.drawable.map_mark_circle_red,
				R.drawable.map_mark_circle_orange,
				R.drawable.map_mark_circle_blue,
				R.drawable.map_mark_triangle_red,
				R.drawable.map_mark_triangle_orange,
				R.drawable.map_mark_triangle_blue };

		for (int i = 0; i < ids.length; i++) {
			marker[i] = getResources().getDrawable(ids[i]);
			marker[i].setBounds(0, 0, marker[i].getIntrinsicWidth(),
					marker[i].getIntrinsicHeight());
		}

	}

	private void startLoad() {
		if (mMapView != null && mMapView.getOverlays() != null
				&& mMapView.getOverlays().size() > 0) {
			mMapView.getOverlays().clear();
		}
		if(level_one_items.size()>0)
			level_one_items.removeAll(level_one_items);
		if(level_two_items.size()>0)
			level_two_items.removeAll(level_two_items);
		if(level_three_items.size()>0)
			level_three_items.removeAll(level_three_items);
		if(level_other_items.size()>0)
			level_other_items.removeAll(level_other_items);

		if (init_data() == null) {
			overitem = new OverItemT(marker[0], this, mOverlayItems);
			mMapView.getOverlays().add(overitem);
		} else {
			ArrayList<CustomOverlayItem> list = init_data();
			CustomOverlayItem item = list.get(0);
			if (item.getFlag() == 1) {
				/*********雨情************/
				for (CustomOverlayItem customOverlayItem : list) {
					switch (Integer.parseInt(customOverlayItem.getLevel())) {
					case 1:
						level_one_items.add(customOverlayItem);
						break;
					case 2:
						level_two_items.add(customOverlayItem);
						break;
					case 3:
						level_three_items.add(customOverlayItem);
						break;
					default:
						level_other_items.add(customOverlayItem);
						break;
					}
				}
				if(level_one_items!=null&&level_one_items.size()>0)
					mMapView.getOverlays().add(new OverItemT(marker[0], this, level_one_items));
				if(level_two_items!=null&&level_two_items.size()>0)
					mMapView.getOverlays().add(new OverItemT(marker[1], this, level_two_items));
				if(level_three_items!=null&&level_three_items.size()>0)
					mMapView.getOverlays().add(new OverItemT(marker[2], this, level_three_items));
				if(level_other_items!=null&&level_other_items.size()>0)
					mMapView.getOverlays().add(new OverItemT(marker[2], this, level_other_items));
			} else {
				/*************水情*************/
				for (CustomOverlayItem customOverlayItem : list) {
					switch (Integer.parseInt(customOverlayItem.getLevel())) {
					case 1:
						level_one_items.add(customOverlayItem);
						break;
					case 2:
						level_two_items.add(customOverlayItem);
						break;
					case 3:
						level_three_items.add(customOverlayItem);
						break;
					default:
						level_other_items.add(customOverlayItem);
						break;
					}
				}
				if(level_one_items!=null&&level_one_items.size()>0)
					mMapView.getOverlays().add(new OverItemT(marker[3], this, level_one_items));
				if(level_two_items!=null&&level_two_items.size()>0)
					mMapView.getOverlays().add(new OverItemT(marker[4], this, level_two_items));
				if(level_three_items!=null&&level_three_items.size()>0)
					mMapView.getOverlays().add(new OverItemT(marker[5], this, level_three_items));
				if(level_other_items!=null&&level_other_items.size()>0)
					mMapView.getOverlays().add(new OverItemT(marker[5], this, level_other_items));
			}

		}

	}

	private void init_mapView() {
		// TODO Auto-generated method stub
		mMapView = (MapView) findViewById(R.id.water_condition_mapView);
		mMapView.setBuiltInZoomControls(true);
		mMapView.setDrawOverlayWhenZooming(true);
		point = new GeoPoint((int) (37.52 * 1e6), (int) (121.39 * 1e6));
		//GeoPoint point = new GeoPoint((int) (36.98 * 1e6), (int) (120.7 * 1e6));
		mapController =mMapView.getController();
		mapController.setZoom(9); 
		mMapView.getController().setCenter(point);
		
		
		
		// mapController.setCenter(point);  

		mPopView = super.getLayoutInflater().inflate(R.layout.popview, null);
		textView = (TextView) mPopView.findViewById(R.id.image);
		mMapView.addView(mPopView, new MapView.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, null,
				MapView.LayoutParams.TOP_LEFT));
		mPopView.setVisibility(View.GONE);
		/****** 测试数据 *****/
		mOverlayItems = new ArrayList<CustomOverlayItem>();
//		GeoPoint g = new GeoPoint((int) (37.52 * 1e6), (int) (121.39 * 1e6));
//		GeoPoint h = new GeoPoint((int) (37.51 * 1e6), (int) (121.39 * 1e6));
//		GeoPoint i = new GeoPoint((int) (37.53 * 1e6), (int) (121.39 * 1e6));
//		CustomOverlayItem o = new CustomOverlayItem(g, "p1", "p1");
//		CustomOverlayItem p = new CustomOverlayItem(h, "p2", "p2");
//		CustomOverlayItem q = new CustomOverlayItem(i, "p3", "p3");
//		mOverlayItems.add(o);
//		mOverlayItems.add(p);
//		mOverlayItems.add(q);
	}

	@SuppressWarnings({ "unchecked", "static-access" })
	private ArrayList<CustomOverlayItem> init_data() {
		intent = getIntent();
		if (MActivity.list == null || MActivity.list.size() == 0)
		{
			L.info(TAG, "MActivity.list.size= 0 ") ;	
			return null;
		}
		L.info(TAG, "GisConfig.list=" + MActivity.list);
		ArrayList<CustomOverlayItem> mOverlayItems = new ArrayList<CustomOverlayItem>();
		GeoPoint g;
		CustomOverlayItem o;
		L.info(TAG, "getFlags = "+intent.getFlags());
		mapController.setZoom(9); 
		mMapView.getController().setCenter(point);	
		
		switch (intent.getFlags()) {
		case UrlLib.RAIN_WARNING:
			list1 = (ArrayList<WaterWarnBean>) MActivity.list;
			
			L.info(TAG,"list1count="+String.valueOf(list1.size()));
			for (WaterWarnBean bean : list1) {
				double lon=0;
				double lat=0;
				if (!(bean.getLon().equals("")&&bean.getLon()!=null))
				{
				 lon = Double.parseDouble(bean.getLon());
				 lat = Double.parseDouble(bean.getLat());
				}
				g = new GeoPoint((int) (lat * 1E6), (int) (lon * 1E6));
				o = new CustomOverlayItem(g, bean.getSitename(), "\n"+"所在地:"+bean.getSiteadd()+"\n"+"预警级别:"+bean.getLevel()+"\n"+"雨量:"
						+ bean.getWaterlevel()+"\n"+"预警值:"+bean.getEarlywarningvalue(), bean.getLevel(), 1,bean.getWaterlevel());
				mOverlayItems.add(o);
				//mMapView.getController().setCenter(g);	
			}
			break;
		case UrlLib.WATER_WARNING:
			list2 = (ArrayList<WaterWarnBean>) MActivity.list;
			for (WaterWarnBean bean : list2) {
				double lon=0;
				double lat=0;
				if (!(bean.getLon().equals("")&&bean.getLon()!=null))
				{
				 lon = Double.parseDouble(bean.getLon());
				 lat = Double.parseDouble(bean.getLat());
				}
				g = new GeoPoint((int) (lat * 1E6), (int) (lon * 1E6));
				o = new CustomOverlayItem(g, bean.getSitename(), "\n"+"所在地:"+bean.getSiteadd()+"\n"+"预警级别:"+bean.getLevel()+"\n"+"水位:"
						+ bean.getWaterlevel()+"\n"+"预警值:"+bean.getEarlywarningvalue(), bean.getLevel(), 2,bean.getWaterlevel());
				mOverlayItems.add(o);
				//mMapView.getController().setCenter(g);	
			}
			break;
		case UrlLib.RAIN_DETAIL:
			list6 = (ArrayList<RainDetailBean>) MActivity.list;
			for (RainDetailBean bean : list6) {
				double lon=0;
				double lat=0;
				if (!(bean.getLon().equals("")&&bean.getLon()!=null))
				{
				 lon = Double.parseDouble(bean.getLon());
				 lat = Double.parseDouble(bean.getLat());
				}
				g = new GeoPoint((int) (lat * 1E6), (int) (lon * 1E6));
				o = new CustomOverlayItem(g, bean.getSitename(),"\n"+"所在地:"+bean.getSiteadd()+ "\n"+"雨量:"
						+ bean.getRainfall(), "3", 1,bean.getRainfall());
				mOverlayItems.add(o);
				//mMapView.getController().setCenter(g);	
			}
			break;
		case UrlLib.IMPORT_RIVER:
			list3 = (ArrayList<ImportRiverBean>) MActivity.list;
			for (ImportRiverBean bean : list3) {
				double lon=0;
				double lat=0;
				if (!(bean.getLon().equals("")&&bean.getLon()!=null))
				{
				 lon = Double.parseDouble(bean.getLon());
				 lat = Double.parseDouble(bean.getLat());
				}
				g = new GeoPoint((int) (lat * 1E6), (int) (lon * 1E6));

				o = new CustomOverlayItem(g, bean.getSitename(), "\n"+"所在地:"+bean.getSiteadd()+"\n"+"时间:"+bean.getTime()+"\n"+"水位:"
						+ bean.getWaterlevel()+"\n"+"超警:"+bean.getWarninglevel(), "3", 2,bean.getWaterlevel());
				mOverlayItems.add(o);
				//mMapView.getController().setCenter(g);	
			}
			break;
		case UrlLib.RESERVIOR:
			list4 = (ArrayList<ImportRiverBean>) MActivity.list;
			for (ImportRiverBean bean : list4) {
				double lon=0;
				double lat=0;
				if (!(bean.getLon().equals("")&&bean.getLon()!=null))
				{
				 lon = Double.parseDouble(bean.getLon());
				 lat = Double.parseDouble(bean.getLat());
				}
				g = new GeoPoint((int) (lat * 1E6), (int) (lon * 1E6));
				o = new CustomOverlayItem(g, bean.getSitename(), "\n"+"所在地:"+bean.getSiteadd()+"\n"+"时间:"+bean.getTime()+"\n"+"水位:"
						+ bean.getWaterlevel()+"\n"+"超警:"+bean.getWarninglevel(), "3", 2,bean.getWaterlevel());
				mOverlayItems.add(o);
				//mMapView.getController().setCenter(g);	
				
			}
			
			break;
		case UrlLib.afterEight:  //当日雨量列表
		list7 = (ArrayList<RainDetailBean>) MActivity.list;
		L.info(TAG,"list7count="+String.valueOf(list7.size()));
			for (RainDetailBean bean : list7) {
				double lon=0;
				double lat=0;
				if (!(bean.getLon().equals("")&&bean.getLon()!=null))
				{
				 lon = Double.parseDouble(bean.getLon());
				 lat = Double.parseDouble(bean.getLat());
				}
				
				L.info(TAG, " = "+bean.getSitename()+"\n"+"所在地12:"+bean.getSiteadd()+ "\n"+"当日雨量:"
						+ bean.getRainfall());
				g = new GeoPoint((int) (lat * 1E6), (int) (lon * 1E6));
				o = new CustomOverlayItem(g, bean.getSitename(),"\n"+"所在地:"+bean.getSiteadd()+ "\n"+"当日雨量:"
						+ bean.getRainfall(), "3", 1,bean.getRainfall());
				mOverlayItems.add(o);
				//mMapView.getController().setCenter(g);	
			}
			break;
			
		default:
			break;
		}
		
			
		return mOverlayItems;
	}

	class OverItemT extends ItemizedOverlay<CustomOverlayItem> {

		public ArrayList<CustomOverlayItem> mGeoList = new ArrayList<CustomOverlayItem>();
		private Drawable marker;
		private Context mContext;
		private List<CustomOverlayItem> list;

		public OverItemT(Drawable marker, Context context,
				ArrayList<CustomOverlayItem> list) {
			super(boundCenterBottom(marker));
			this.marker = marker;
			this.mContext = context;
			this.list = list;
			for (int i = 0; i < list.size(); i++) {
				mGeoList.add(list.get(i));
			}
			populate();
			// createItem(int)方法构造item。一旦有了数据，在调用其它方法前，首先调用这个方法
		}

		public void updateOverlay() {
			populate();
		}

		@Override
		public void draw(Canvas canvas, MapView mapView, boolean shadow) {

			// Projection接口用于屏幕像素坐标和经纬度坐标之间的变换
			Projection projection = mapView.getProjection();
			for (int index = size() - 1; index >= 0; index--) { // 遍历mGeoList
				CustomOverlayItem overLayItem = getItem(index); // 得到给定索引的item
				String title = overLayItem.getTitle();
				// 把经纬度变换到相对于MapView左上角的屏幕像素坐标
				Point point = projection.toPixels(overLayItem.getPoint(), null);
			
				// 可在此处添加您的绘制代码
				Paint paintText = new Paint();
				paintText.setColor(Color.BLUE);
				paintText.setTextSize(24);
				paintText.setFakeBoldText(true);
				//paintText.setTextSkewX(-0.5f);
				paintText.setStyle(Paint.Style.FILL);
				//paintText.setSubpixelText(true);
				paintText.setUnderlineText(true);
				paintText.setTypeface(Typeface.DEFAULT_BOLD);
				
				title =title+"("+overLayItem.getValue()+")";				
				
				
				
				canvas.drawText(title, point.x-25 , point.y+15, paintText); // 绘制文本
			}
			super.draw(canvas, mapView, shadow);
			boundCenterBottom(marker);
			// 调整一个drawable边界，使得（0，0）是这个drawable底部最后一行中心的一个像素

		}

		@Override
		protected CustomOverlayItem createItem(int i) {
			// TODO Auto-generated method stub
			return mGeoList.get(i);
		}

		@Override
		public int size() {
			// TODO Auto-generated method stub
			return mGeoList.size();
		}

		@Override
		// 处理当点击事件
		protected boolean onTap(int i) {
			setFocus(mGeoList.get(i));
			// 更新气泡位置,并使之显示
			GeoPoint pt = mGeoList.get(i).getPoint();

			MMapActivity.mMapView.updateViewLayout(MMapActivity.mPopView,
					new MapView.LayoutParams(LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT, pt,
							MapView.LayoutParams.BOTTOM_CENTER ));// 
//			"站名:" + list.get(i).getTitle() + "\n"
			String str = "站名:" + list.get(i).getTitle()+list.get(i).getSnippet();
			MMapActivity.textView.setTextSize(10);
			/*Common.stringFilter(Common.ToDBC(str
					.toString()))*/
			MMapActivity.textView.setText(str);
			MMapActivity.mPopView.setVisibility(View.VISIBLE);
			return true;
		}

		@Override
		public boolean onTap(GeoPoint arg0, MapView arg1) {
			// TODO Auto-generated method stub
			// 消去弹出的气泡
			MMapActivity.mPopView.setVisibility(View.GONE);
			return super.onTap(arg0, arg1);
		}
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		L.info(TAG, "onWindowFocusChanged");
		//Toast.makeText(MMapActivity.this, "已经切换地图",
		//		Toast.LENGTH_SHORT).show();
	}

}
