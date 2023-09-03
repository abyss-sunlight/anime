package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;

import javaBean.spbean;

public class conentdao {
	Connection conn = null;// 塞在外面 全局
	PreparedStatement pre = null;
	public List<spbean> body(String[] id){
		List<spbean> list=new ArrayList<spbean>();
try {
			
		    conn=dbConnection.getConnection();
			String sql="select  番名,图片,集数  from zykml where id between 1 and 30";
			pre=conn.prepareStatement(sql);
			ResultSet rs=pre.executeQuery();
			spbean stu;
			while(rs.next()){
				//遍历整个数据集假使遍历第一行，秦勇
				//第一行秦勇信息封装到stulnfo对象
				stu=new spbean();
				stu.setName(rs.getString("番名"));
				stu.setTp(rs.getString("图片"));
				stu.setJis(rs.getString("集数"));
				list.add(stu);
			}
		}
		catch (Exception g) {
			g.printStackTrace();
			
		}
		return list;
	}
	//lx查询
public List<spbean> lxcx(String lx){
		
		List<spbean> list=new ArrayList<spbean>();
		try {
			 conn=dbConnection.getConnection();
			 String sql2="";
			 if(lx.equals("全部")){
				 sql2="select id , 番名,图片,集数   from zykml ";
			 }else{
			sql2="select  id , 番名,图片,集数   from zykml where 类型1='"+lx+"' or 类型2='"+lx+"' or 类型3='"+lx+"'";
			 }
			pre=conn.prepareStatement(sql2);
			ResultSet rs=pre.executeQuery();
			spbean sp;
			if(rs.next()==false){
				list=null;
			}else{
				rs=pre.executeQuery();
			while(rs.next()){
				//遍历整个数据集假使遍历第一行，秦勇
				//第一行秦勇信息封装到stulnfo对象
				sp=new spbean();
				sp.setName(rs.getString("番名"));
				sp.setTp(rs.getString("图片"));
				sp.setJis(rs.getString("集数"));
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
 