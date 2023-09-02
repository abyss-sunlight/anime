package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dbConnection.dbConnection;
import javaBean.jcxxbean;
import javaBean.spbean;

public class transmitdao {
	Connection conn = null;// 塞在外面 全局
	PreparedStatement pre = null;
	//手机号查询jcxx
	public List<jcxxbean> sjcx(String mobile){
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
	public List<spbean> spcx(int id){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		List<spbean> list=new ArrayList<spbean>();
		try {
			String jis=null;
			 conn=dbConnection.getConnection();
			String sql2="select 集数 from zykml where id='"+id+"'";
			pre=conn.prepareStatement(sql2);
			ResultSet rs=pre.executeQuery();
			spbean sp;
			while(rs.next()){
				jis=rs.getString("集数");
			}
			
			String sql="select * from zyk where id='"+id+"' and 集数='1'";
			pre=conn.prepareStatement(sql);
			rs=pre.executeQuery();
			while(rs.next()){
				//遍历整个数据集假使遍历第一行，秦勇
				//第一行秦勇信息封装到stulnfo对象
				sp=new spbean();
				sp.setDq(rs.getString("地区"));
				sp.setJtjs(rs.getString("具体介绍"));
				sp.setName(rs.getString("番名"));
				Date date = simpleDateFormat.parse(rs.getString("年代"));
				sp.setSjian(date);
				sp.setSp(rs.getString("视频"));
				sp.setTp(rs.getString("图片"));
				sp.setLx1(rs.getString("类型1"));
				sp.setJis(jis);
				if(rs.getString("类型2")!=null){
				if(rs.getString("类型3")==null){
					sp.setLx2(rs.getString("类型2"));
				}else{
					sp.setLx3(rs.getString("类型3"));
					sp.setLx2(rs.getString("类型2"));
				}
				}
				list.add(sp);
			}
		}
		catch (Exception g) {
			g.printStackTrace();
			
		}
		return list;
	}
}
