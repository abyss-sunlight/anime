package Dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import dbConnection.dbConnection;
import javaBean.jcxxbean;

public class grxxdao {
	Connection conn = null;// �������� ȫ��
	PreparedStatement pre = null;
	 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	Format formatter=new SimpleDateFormat("yyyy-MM-dd"); 
	//��ȡ��Ϣ
	public List<jcxxbean> grxx(String mobile){
		List<jcxxbean> list=new ArrayList<jcxxbean>();
		try {
			
		    conn=dbConnection.getConnection();
			String sql="select * from jcxx where �ֻ�='"+mobile+"'";
			pre=conn.prepareStatement(sql);
			ResultSet rs=pre.executeQuery();
			jcxxbean stu;
			while(rs.next()){
				//�����������ݼ���ʹ������һ�У�����
				//��һ��������Ϣ��װ��stulnfo����
				stu=new jcxxbean();
				
				stu.setSj(rs.getString("�ֻ�"));
				stu.setXb(rs.getString("�Ա�"));
				stu.setMm(rs.getString("����"));
				stu.setGxqm(rs.getString("����ǩ��"));
				java.util.Date  utildate= new java.util.Date(rs.getDate("����").getTime());
				String s=formatter.format(utildate);
				stu.setSsr(s);
				stu.setZhmc(rs.getString("�˺�����"));
				stu.setTp(rs.getString("ͷ��"));
				list.add(stu);
			}
		}
		catch (Exception g) {
			g.printStackTrace();
			
		}
		return list;
	}
	//������Ϣ
	public int ggxx(jcxxbean j){
		int a=0;
		try{
			java.util.Date udate = format.parse(j.getSsr());
	        java.sql.Date sdate = new java.sql.Date(udate.getTime());
			conn=dbConnection.getConnection();
			String sql="update jcxx set ����='"+j.getMm()+"' , ����='"+sdate+"' ,  �Ա�='"+j.getXb()+"' ,  �˺�����='"+j.getZhmc()+"' where �ֻ�='"+j.getSj()+"'";
			pre=conn.prepareStatement(sql);
			 int i = pre.executeUpdate();
			if(i>0){
	        	 a=1;
	         }
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return a;
	}
	//�����ֻ�
	public int ggsj(String mobile, String mobileg){
		int a=0;
		try{
			conn=dbConnection.getConnection();
			String sql="update jcxx set �ֻ�='"+mobileg+"' where �ֻ�='"+mobile+"'";
			pre=conn.prepareStatement(sql);
			 int i = pre.executeUpdate();
			if(i>0){
	        	 a=1;
	         }
		}catch(Exception e){
			e.printStackTrace();
		}
		return a;
	}
	//����ǩ��
	public int ggqm(String gxqm ,String mobile){
		int a=0;
		try{
			conn=dbConnection.getConnection();
			String sql="update jcxx set ����ǩ��='"+gxqm+"' where �ֻ�='"+mobile+"'";
			pre=conn.prepareStatement(sql);
			 int i = pre.executeUpdate();
			if(i>0){
	        	 a=1;
	         }
		}catch(Exception e){
			e.printStackTrace();
		}
		return a;
	}
	public int ggtx(String mobile,byte[] file){
		
		int a=0;
		try{
			System.out.print(file);
			 
			conn=dbConnection.getConnection();
			String sql="update jcxx set ͷ��='"+file+"' where �ֻ�='"+mobile+"'";
			
			pre=conn.prepareStatement(sql);
			 int i = pre.executeUpdate();
			if(i>0){
	        	 a=1;
	         }
		}catch(Exception e){
			e.printStackTrace();
		}
		return a;
	}
}