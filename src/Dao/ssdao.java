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
	Connection conn = null;// 塞在外面 全局
	PreparedStatement pre = null;
	public List<spbean> spcx(String ss){
		
		List<spbean> list=new ArrayList<spbean>();
		try {
			 conn=dbConnection.getConnection();
			String sql2="select  id , 番名,图片,集数   from zykml where 番名 like '%"+ss+"%'";
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
