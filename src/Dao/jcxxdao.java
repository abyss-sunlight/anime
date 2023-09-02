package Dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javaBean.jcxxbean;

import dbConnection.dbConnection;

public class jcxxdao {
	
	Connection conn = null;// �������� ȫ��
	PreparedStatement pre = null;
	SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

	// ע��
	public int insert(jcxxbean u) throws SQLException {
		int insert=0;
		u.setXb("��δ�����Ա�");
		u.setZhmc("LX" + u.getSj());
		u.setGxqm("��up��������ʲô��û����");
		u.setTp("./tx/head.jpeg");
		try {
			Date sr=dateformat.parse("2000-01-01");
			
		java.sql.Date sqlDate = new java.sql.Date(sr.getTime());
		
		conn = dbConnection.getConnection();
		String sql2="select * from jcxx where �ֻ�='"+u.getSj()+"'";
		pre=conn.prepareStatement(sql2);
		ResultSet rs=pre.executeQuery();
		if(rs.next()==false){
			String sql = "insert into jcxx (����,�ֻ�,����,�Ա�,�˺�����,����ǩ��,ͷ��)values(?,?,?,?,?,?,?)";
			
			 pre = conn.prepareStatement(sql);
			 pre.setString(1,u.getMm());
	         pre.setString(2, u.getSj());
	         pre.setDate(3, sqlDate);
	         pre.setString(4, u.getXb());
	         pre.setString(5, u.getZhmc());
	         pre.setString(6, u.getGxqm());
	         pre.setString(7, u.getTp());
	         int i = pre.executeUpdate();
	         if(i>0){
	        	 insert=1;
	         }
			 }else{
				 insert=2;
				 u.setFankui("���ֻ�����ע��");
			 }
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
            try {
                dbConnection.dbClose(conn, pre, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
		return insert;
	}
	
	
	//��¼
	public int login(jcxxbean u,int pd){
		int yzmr=0;
		try {
		conn = dbConnection.getConnection();
		String sql1="select * from jcxx where �ֻ�='"+u.getSj()+"'";
		pre=conn.prepareStatement(sql1);
		ResultSet rs=pre.executeQuery();
		if(pd==1){//�ж�����֤���¼���������¼
			//��֤���ж�
			if(rs.next()==false){//�ж����ݿ���û���ֻ���
				yzmr=1;
			}else{
				yzmr=2;
			}
		}else{
			//�����ж�
			if(rs.next()==false){//�ж����ݿ���û���ֻ���
				yzmr=1;
			}else{//�ж����ֻ��Ŵ������������
				if(!u.getMm().equals(rs.getString("����"))){
					yzmr=3;
				}else{//�����ֻ��ź����붼��ȷ��ֵ
				yzmr=2;
				}
			}
		}
		
		
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return yzmr;
	}
	

}
