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
	Connection conn = null;// 塞在外面 全局
	PreparedStatement pre = null;
	 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	Format formatter=new SimpleDateFormat("yyyy-MM-dd"); 
	//获取信息
	public List<jcxxbean> grxx(String mobile){
		List<jcxxbean> list=new ArrayList<jcxxbean>();
		try {
			
		    conn=dbConnection.getConnection();
			String sql="select * from jcxx where 手机='"+mobile+"'";
			pre=conn.prepareStatement(sql);
			ResultSet rs=pre.executeQuery();
			jcxxbean stu;
			while(rs.next()){
				//遍历整个数据集假使遍历第一行，秦勇
				//第一行秦勇信息封装到stulnfo对象
				stu=new jcxxbean();
				
				stu.setSj(rs.getString("手机"));
				stu.setXb(rs.getString("性别"));
				stu.setMm(rs.getString("密码"));
				stu.setGxqm(rs.getString("个性签名"));
				java.util.Date  utildate= new java.util.Date(rs.getDate("生日").getTime());
				String s=formatter.format(utildate);
				stu.setSsr(s);
				stu.setZhmc(rs.getString("账号名称"));
				stu.setTp(rs.getString("头像"));
				list.add(stu);
			}
		}
		catch (Exception g) {
			g.printStackTrace();
			
		}
		return list;
	}
	//更改信息
	public int ggxx(jcxxbean j){
		int a=0;
		try{
			java.util.Date udate = format.parse(j.getSsr());
	        java.sql.Date sdate = new java.sql.Date(udate.getTime());
			conn=dbConnection.getConnection();
			String sql="update jcxx set 密码='"+j.getMm()+"' , 生日='"+sdate+"' ,  性别='"+j.getXb()+"' ,  账号名称='"+j.getZhmc()+"' where 手机='"+j.getSj()+"'";
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
	//更改手机
	public int ggsj(String mobile, String mobileg){
		int a=0;
		try{
			conn=dbConnection.getConnection();
			String sql="update jcxx set 手机='"+mobileg+"' where 手机='"+mobile+"'";
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
	//更改签名
	public int ggqm(String gxqm ,String mobile){
		int a=0;
		try{
			conn=dbConnection.getConnection();
			String sql="update jcxx set 个性签名='"+gxqm+"' where 手机='"+mobile+"'";
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
			String sql="update jcxx set 头像='"+file+"' where 手机='"+mobile+"'";
			
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