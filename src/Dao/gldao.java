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
	Connection conn = null;// 塞在外面 全局
	PreparedStatement pre = null;
	//判断
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
	// 注册
		public int insert(jcxxbean u) throws SQLException {
			int insert=0;
			
			
			try {
				
			
			conn = dbConnection.getConnection();
			String sql2="select * from gljcxx where 手机='"+u.getSj()+"'";
			pre=conn.prepareStatement(sql2);
			ResultSet rs=pre.executeQuery();
			if(rs.next()==false){
				String sql = "insert into gljcxx (手机,密码)values(?,?)";
				
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
		
		
		//登录
		public int login(jcxxbean u){
			int yzmr=0;
			try {
			conn = dbConnection.getConnection();
			String sql1="select * from gljcxx where 手机='"+u.getSj()+"'";
			pre=conn.prepareStatement(sql1);
			ResultSet rs=pre.executeQuery();
				//密码判断
				if(rs.next()==false){//判断数据库有没有手机号
					yzmr=3;
				}else{//判断是手机号错误还是密码错误
					if(!u.getMm().equals(rs.getString("密码"))){
						yzmr=4;
					}else{//返回手机号和密码都正确的值
					yzmr=5;
					}
				}
			
			
			
				
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return yzmr;
		}
}
