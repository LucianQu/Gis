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
		
		 String countyName ="";	//��������
		 String rsnm="";       //ˮ������
		 String valleySq=""  ; //�������
		 String irrigwatlev ="" ; //����ˮλ
		 String efst ="" ;	//��������
		 String floodlv ="" ;	//Ѵ��ˮλ
		 String floodst ="" ;  //Ѵ�޿���
		 String warnlv ="" ; //����ˮλ
		 String warnst ="" ; //�������
		 String ckflz ="" ;	//У��ˮλ
		 String chkst ="" ; //У�˿���
		 String spillForm="";//����    �� ʽ
		 String rz="";			//ʵʱˮλ
		 String ratioLv="";		//��Ѵ��ˮλ(����ʱ�䲻ͬ����Ϊ�ȳ���ˮλ����Ѵĩˮλ)
		 String w="";			//ʵʱ��ˮ��
		 String ratioSt="";		//��Ѵ�޿���(����ʱ�䲻ͬ����Ϊ�ȳ�����ݡ���Ѵĩ����)
		 String otqHole="";		//��ˮ����������
		 String otqRode="";		//������������
		
		
		
		
		 String fldControlLv="";//Ѵ��ˮλ(����ʱ�䲻ͬ����Ϊ������ˮλ��Ѵĩˮλ)
		 String fldControlSt="";//Ѵ�޿���(����ʱ�䲻ͬ����Ϊ��������ݡ�Ѵĩ����)
		 String stcd="";
		 String dataTime="";
		 String style = "";		//����ʵʱ������ʾ��ʽ	1--��ʾ��ɫ��2--��˸
				
		
		 String supStoLv="";	//������ˮλ
		 String supStoSt="";	//���������
		 String floodEndLv="";	//Ѵĩ��ˮλ
		 String floodEndSt="";	//Ѵĩ����
		
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
			fImportRiverEx.setCountyName("ˮ  ��");
	        //fImportRiverEx.setRsnm("�����ˮ��");
			fImportRiverEx.setRsnm("����ˮλ��Ӧ����");
	        fImportRiverEx.setValleySq("ʵʱ��ˮ��");
	        fImportRiverEx.setIrrigwatlev("ȥ��ͬ��");
	        fImportRiverEx.setEfst("ȥ��ͬ�ڱȽ�");
	        //fImportRiverEx.setFloodlv("����ͬ��");
	        //fImportRiverEx.setFloodst("����ͬ�ڱȽ�");
	        
	        fImportRiverEx.setFldControlLv("ȥ��ͬ�ڱ���");
	        fImportRiverEx.setFldControlSt("����ͬ��");
	        fImportRiverEx.setWarnlv("����ͬ�ڱȽ�");
	        fImportRiverEx.setWarnst("����ͬ�ڱ���");
	        
			
			
			
	        nlist.add(fImportRiverEx);
			
			for(int j =0;j<ajsonArray.length();j++){
				
			  JSONObject mJsonObject = ajsonArray.getJSONObject(j);
			
	          ImportRiverBeanEx mImportRiverEx = new ImportRiverBeanEx();	
	          countyName = mJsonObject.getString("rsType");//����/����/������
	          rsnm = mJsonObject.getString("desst");//�����ˮ����Ϊ��������֮��
	          valleySq = mJsonObject.getString("realst");//ʵʱ��ˮ����Ϊʵʱ��ˮ��֮��
		      irrigwatlev =mJsonObject.getString("lastst");//ȥ��ͬ����ˮ�� 
		      efst = mJsonObject.getString("campLastst");//��ȥ��ͬ�ڱȽ�
		      fldControlLv = mJsonObject.getString("ratioLastst");//��ȥ��ͬ�ڲ�ֵ����
		      fldControlSt =mJsonObject.getString("overst");//����ͬ����ˮ�� 
		      warnlv = mJsonObject.getString("campOverst");//������ͬ�ڱȽ�
		      warnst = mJsonObject.getString("ratioOverst");//������ͬ�ڲ�ֵ����
		      
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
