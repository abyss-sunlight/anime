package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javaBean.spbean;

import dbConnection.dbConnection;

public class ssdao {
	Connection conn = null;// �������� ȫ��
	PreparedStatement pre = null;
	public List<spbean> spcx(String ss){
		
		List<spbean> list=new ArrayList<spbean>();
		try {
			 conn=dbConnection.getConnection();
			String sql2="select  id , ����,ͼƬ,����   from zykml where ���� like '%"+ss+"%'";
			pre=conn.prepareStatement(sql2);
			ResultSet rs=pre.executeQuery();
			spbean sp;
			if(rs.next()==false){
				list=null;
			}else{
				rs=pre.executeQuery();
			while(rs.next()){
				//�����������ݼ���ʹ������һ�У�����
				//��һ��������Ϣ��װ��stulnfo����
				sp=new spbean();
				sp.setName(rs.getString("����"));
				sp.setTp(rs.getString("ͼƬ"));
				sp.setJis(rs.getString("����"));
				sp.setLx(rs.getInt("id"));
				list.add(sp);
			}
		}}
		catch (Exception g) {
			g.printStackTrace();
			
		}
		return list;
	}
}
