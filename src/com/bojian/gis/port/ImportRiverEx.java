package com.bojian.gis.port;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.bojian.gis.debug.L;

import com.bojian.gis.entity.ImportRiverBeanEx;
import com.bojian.gis.json.JSONArray;
import com.bojian.gis.json.JSONObject;
import com.bojian.gis.util.UrlLib;

public class ImportRiverEx extends Upload {
   private String mstarttime;
   private ArrayList<ImportRiverBeanEx> list;
	
   public ImportRiverEx(String username,String password,int city,String starttime)
   {
	   this.username =username;
	   this.password =password;
	   this.city =city;
	   this.mstarttime =starttime;
   }
	
	@Override
	public void getDataBase(InputStreamReader isr) throws Exception {
		// TODO Auto-generated method stub

		ArrayList<ImportRiverBeanEx> nlist = new ArrayList<ImportRiverBeanEx>();
		StringBuilder builder = new StringBuilder();
		BufferedReader br = new BufferedReader(isr);
		for (String s = br.readLine(); s != null; s = br.readLine())
		{
			builder.append(s);
		}
		L.info("ImportRiverBeanEx", "builder = "+builder.toString());
		JSONObject jsonObject = new JSONObject(builder.toString().substring(1, builder.toString().length()-1));
		int code = jsonObject.getInt("code");
		L.info("ImportRiverBeanEx", "code="+code);
		if(code!=0)
			return;
		
		JSONArray jsonArray  = jsonObject.getJSONArray("data");
		
		 String countyName ="";	//区县名称
		 String rsnm="";       //水库名称
		 String valleySq=""  ; //流域面积
		 String irrigwatlev ="" ; //兴利水位
		 String efst ="" ;	//兴利库容
		 String floodlv ="" ;	//汛限水位
		 String floodst ="" ;  //汛限库容
		 String warnlv ="" ; //警戒水位
		 String warnst ="" ; //警戒库容
		 String ckflz ="" ;	//校核水位
		 String chkst ="" ; //校核库容
		 String spillForm="";//溢洪道    型 式
		 String rz="";			//实时水位
		 String ratioLv="";		//比汛限水位(根据时间不同表现为比超蓄水位、比汛末水位)
		 String w="";			//实时蓄水量
		 String ratioSt="";		//比汛限库容(根据时间不同表现为比超蓄库容、比汛末库容)
		 String otqHole="";		//输水洞出库流量
		 String otqRode="";		//溢洪道出库流量
		
		
		
		
		 String fldControlLv="";//汛限水位(根据时间不同表现为允许超蓄水位、汛末水位)
		 String fldControlSt="";//汛限库容(根据时间不同表现为允许超蓄库容、汛末库容)
		 String stcd="";
		 String dataTime="";
		 String style = "";		//控制实时数据显示形式	1--显示红色，2--闪烁
				
		
		 String supStoLv="";	//允许超蓄水位
		 String supStoSt="";	//允许超蓄库容
		 String floodEndLv="";	//汛末蓄水位
		 String floodEndSt="";	//汛末库容
		
		for(int i =0;i<jsonArray.length();i++){
			JSONArray ajsonArray=jsonArray.getJSONArray(i);
			if (i==0)
			{	
			  for(int j =0;j<ajsonArray.length();j++){
				JSONArray bjsonArray=ajsonArray.getJSONArray(j);
			    for(int k =0;k<bjsonArray.length();k++){
			     if (i==0)//
			     {
			        JSONObject mJsonObject = bjsonArray.getJSONObject(k);
			        ImportRiverBeanEx mImportRiverEx = new ImportRiverBeanEx();
			        countyName = mJsonObject.getString("countyName");
			        rsnm = mJsonObject.getString("rsnm");
			        valleySq = mJsonObject.getString("valleySq");
			        irrigwatlev = mJsonObject.getString("irrigwatlev");
			        efst = mJsonObject.getString("efst");
			        floodlv = mJsonObject.getString("floodlv");
			        floodst = mJsonObject.getString("floodst");
			        warnlv = mJsonObject.getString("warnlv");
			        warnst = mJsonObject.getString("warnst");
			        ckflz = mJsonObject.getString("ckflz");
			        chkst = mJsonObject.getString("chkst");
			        spillForm = mJsonObject.getString("spillForm");
			        rz = mJsonObject.getString("rz");
			        ratioLv = mJsonObject.getString("ratioLv");
			        w = mJsonObject.getString("w");
			        ratioSt = mJsonObject.getString("ratioSt");
			        otqHole = mJsonObject.getString("otqHole");
			        otqRode = mJsonObject.getString("otqRode");
			        fldControlLv = mJsonObject.getString("fldControlLv");
			        fldControlSt = mJsonObject.getString("fldControlSt");
			        stcd = mJsonObject.getString("stcd");
			        dataTime = mJsonObject.getString("dataTime");
			        style = mJsonObject.getString("style");
			        supStoLv = mJsonObject.getString("supStoLv");
			        supStoSt = mJsonObject.getString("supStoSt");
			        floodEndLv = mJsonObject.getString("floodEndLv");
			        floodEndSt = mJsonObject.getString("floodEndSt");
			        
			        mImportRiverEx.setCountyName(countyName);
			        mImportRiverEx.setRsnm(rsnm);
			        mImportRiverEx.setValleySq(valleySq);
			        mImportRiverEx.setIrrigwatlev(irrigwatlev);
			        mImportRiverEx.setEfst(efst);
			        mImportRiverEx.setFloodlv(floodlv);
			        mImportRiverEx.setFloodst(floodst);
			        mImportRiverEx.setWarnlv(warnlv);
			        mImportRiverEx.setWarnst(warnst);
			        mImportRiverEx.setCkflz(ckflz);
			        mImportRiverEx.setChkst(chkst);
			        mImportRiverEx.setSpillForm(spillForm);
			        mImportRiverEx.setRz(rz);
			        mImportRiverEx.setRatioLv(ratioLv);
			        mImportRiverEx.setW(w);
			        mImportRiverEx.setRatioSt(ratioSt);
			        mImportRiverEx.setOtqHole(otqHole);
			        mImportRiverEx.setOtqRode(otqRode);
			        mImportRiverEx.setFldControlLv(fldControlLv);
			        mImportRiverEx.setFldControlSt(fldControlSt);
			        mImportRiverEx.setStcd(stcd);
			        mImportRiverEx.setDataTime(dataTime);
			        mImportRiverEx.setStyle(style);
			        mImportRiverEx.setSupStoLv(supStoLv);
			        mImportRiverEx.setSupStoSt(supStoSt);
			        mImportRiverEx.setFloodEndLv(floodEndLv);
			        mImportRiverEx.setFloodEndSt(floodEndSt);
			        			        
			        nlist.add(mImportRiverEx);
			      
			     }
			
			
			   }
			}
	     } 
		else if (i==1)
		  {
			ImportRiverBeanEx fImportRiverEx = new ImportRiverBeanEx();	
			fImportRiverEx.setCountyName("水  库");
	        //fImportRiverEx.setRsnm("设计蓄水量");
			fImportRiverEx.setRsnm("兴利水位对应库容");
	        fImportRiverEx.setValleySq("实时蓄水量");
	        fImportRiverEx.setIrrigwatlev("去年同期");
	        fImportRiverEx.setEfst("去年同期比较");
	        //fImportRiverEx.setFloodlv("历年同期");
	        //fImportRiverEx.setFloodst("历年同期比较");
	        
	        fImportRiverEx.setFldControlLv("去年同期比例");
	        fImportRiverEx.setFldControlSt("历年同期");
	        fImportRiverEx.setWarnlv("历年同期比较");
	        fImportRiverEx.setWarnst("历年同期比例");
	        
			
			
			
	        nlist.add(fImportRiverEx);
			
			for(int j =0;j<ajsonArray.length();j++){
				
			  JSONObject mJsonObject = ajsonArray.getJSONObject(j);
			
	          ImportRiverBeanEx mImportRiverEx = new ImportRiverBeanEx();	
	          countyName = mJsonObject.getString("rsType");//大型/中型/大中型
	          rsnm = mJsonObject.getString("desst");//设计蓄水量，为兴利库容之和
	          valleySq = mJsonObject.getString("realst");//实时蓄水量，为实时蓄水量之和
		      irrigwatlev =mJsonObject.getString("lastst");//去年同期蓄水量 
		      efst = mJsonObject.getString("campLastst");//与去年同期比较
		      fldControlLv = mJsonObject.getString("ratioLastst");//与去年同期差值比例
		      fldControlSt =mJsonObject.getString("overst");//历年同期蓄水量 
		      warnlv = mJsonObject.getString("campOverst");//与历年同期比较
		      warnst = mJsonObject.getString("ratioOverst");//与历年同期差值比例
		      
		        mImportRiverEx.setCountyName(countyName);
		        mImportRiverEx.setRsnm(rsnm);
		        mImportRiverEx.setValleySq(valleySq);
		        mImportRiverEx.setIrrigwatlev(irrigwatlev);
		        mImportRiverEx.setEfst(efst);
		        mImportRiverEx.setFldControlLv(fldControlLv);
		        mImportRiverEx.setFldControlSt(fldControlSt);
		        mImportRiverEx.setWarnlv(warnlv);
		        mImportRiverEx.setWarnst(warnst);
		        
		        nlist.add(mImportRiverEx);
			}
		
		  }
			
		}
		this.setList(nlist);
	}

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return UrlLib.getImportRaiverExUrl(username, password, city,mstarttime);
	}

	public void setList(ArrayList<ImportRiverBeanEx> list) {
		this.list = list;
	}

	public ArrayList<ImportRiverBeanEx> getList() {
		return list;
	}

}
