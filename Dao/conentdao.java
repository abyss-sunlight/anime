package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;

import javaBean.spbean;

public class conentdao {
	Connection conn = null;// �������� ȫ��
	PreparedStatement pre = null;
	public List<spbean> body(String[] id){
		List<spbean> list=new ArrayList<spbean>();
try {
			
		    conn=dbConnection.getConnection();
			String sql="select  ����,ͼƬ,����  from zykml where id between 1 and 30";
			pre=conn.prepareStatement(sql);
			ResultSet rs=pre.executeQuery();
			spbean stu;
			while(rs.next()){
				//�����������ݼ���ʹ������һ�У�����
				//��һ��������Ϣ��װ��stulnfo����
				stu=new spbean();
				stu.setName(rs.getString("����"));
				stu.setTp(rs.getString("ͼƬ"));
				stu.setJis(rs.getString("����"));
				list.add(stu);
			}
		}
		catch (Exception g) {
			g.printStackTrace();
			
		}
		return list;
	}
	//lx��ѯ
public List<spbean> lxcx(String lx){
		
		List<spbean> list=new ArrayList<spbean>();
		try {
			 conn=dbConnection.getConnection();
			 String sql2="";
			 if(lx.equals("ȫ��")){
				 sql2="select id , ����,ͼƬ,����   from zykml ";
			 }else{
			sql2="select  id , ����,ͼƬ,����   from zykml where ����1='"+lx+"' or ����2='"+lx+"' or ����3='"+lx+"'";
			 }
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
 