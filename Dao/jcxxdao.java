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
	
	Connection conn = null;// 塞在外面 全局
	PreparedStatement pre = null;
	SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

	// 注册
	public int insert(jcxxbean u) throws SQLException {
		int insert=0;
		u.setXb("还未设置性别");
		u.setZhmc("LX" + u.getSj());
		u.setGxqm("该up主很懒，什么都没留下");
		u.setTp("./tx/head.jpeg");
		try {
			Date sr=dateformat.parse("2000-01-01");
			
		java.sql.Date sqlDate = new java.sql.Date(sr.getTime());
		
		conn = dbConnection.getConnection();
		String sql2="select * from jcxx where 手机='"+u.getSj()+"'";
		pre=conn.prepareStatement(sql2);
		ResultSet rs=pre.executeQuery();
		if(rs.next()==false){
			String sql = "insert into jcxx (密码,手机,生日,性别,账号名称,个性签名,头像)values(?,?,?,?,?,?,?)";
			
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
				 u.setFankui("该手机号已注册");
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
	
	
	//登录
	public int login(jcxxbean u,int pd){
		int yzmr=0;
		try {
		conn = dbConnection.getConnection();
		String sql1="select * from jcxx where 手机='"+u.getSj()+"'";
		pre=conn.prepareStatement(sql1);
		ResultSet rs=pre.executeQuery();
		if(pd==1){//判断是验证码登录还是密码登录
			//验证码判断
			if(rs.next()==false){//判断数据库有没有手机号
				yzmr=1;
			}else{
				yzmr=2;
			}
		}else{
			//密码判断
			if(rs.next()==false){//判断数据库有没有手机号
				yzmr=1;
			}else{//判断是手机号错误还是密码错误
				if(!u.getMm().equals(rs.getString("密码"))){
					yzmr=3;
				}else{//返回手机号和密码都正确的值
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
