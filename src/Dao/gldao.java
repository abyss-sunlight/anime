package Dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javaBean.jcxxbean;

import dbConnection.dbConnection;

public class gldao {
	Connection conn = null;// �������� ȫ��
	PreparedStatement pre = null;
	//�ж�
	public int pd(jcxxbean u, String pd){
		int a=0;
		try{
		if(pd.equals("1")){
			a=insert(u);
			
		}
		if(pd.equals("2")){
			a=login(u);
			
		}
		}catch(Exception e) {
				e.printStackTrace();
			}
		return a;
	}
	// ע��
		public int insert(jcxxbean u) throws SQLException {
			int insert=0;
			
			
			try {
				
			
			conn = dbConnection.getConnection();
			String sql2="select * from gljcxx where �ֻ�='"+u.getSj()+"'";
			pre=conn.prepareStatement(sql2);
			ResultSet rs=pre.executeQuery();
			if(rs.next()==false){
				String sql = "insert into gljcxx (�ֻ�,����)values(?,?)";
				
				 pre = conn.prepareStatement(sql);
				 pre.setString(1,u.getSj());
		         pre.setString(2, u.getMm());
		         int i = pre.executeUpdate();
		         if(i>0){
		        	 insert=1;
		         }
				 }else{
					 insert=2;
				 }
			
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			finally {
	           
	                dbConnection.dbClose(conn, pre, null);
	            
	        }
			return insert;
		}
		
		
		//��¼
		public int login(jcxxbean u){
			int yzmr=0;
			try {
			conn = dbConnection.getConnection();
			String sql1="select * from gljcxx where �ֻ�='"+u.getSj()+"'";
			pre=conn.prepareStatement(sql1);
			ResultSet rs=pre.executeQuery();
				//�����ж�
				if(rs.next()==false){//�ж����ݿ���û���ֻ���
					yzmr=3;
				}else{//�ж����ֻ��Ŵ������������
					if(!u.getMm().equals(rs.getString("����"))){
						yzmr=4;
					}else{//�����ֻ��ź����붼��ȷ��ֵ
					yzmr=5;
					}
				}
			
			
			
				
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return yzmr;
		}
}
