package Dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javaBean.jcxxbean;

import dbConnection.dbConnection;

public class yzmdao {
	Connection conn = null;// 塞在外面 全局
	PreparedStatement pre = null;
	ResultSet rs=null;
	// 验证码判断
	public int insertyzm(jcxxbean u) throws SQLException {

		
		int insert=0;
		try {
		conn = dbConnection.getConnection();
		String sql2="select * from yzm where 手机='"+u.getSj()+"'";
		 pre = conn.prepareStatement(sql2);
		if(pre.executeQuery().next()){
			System.out.println(pre.executeQuery().next());
			 String sql3="update yzm set 验证码='"+u.getCode()+"' , 创建时间='"+u.getCreateTime()+"' where 手机='"+u.getSj()+"'";
			 pre = conn.prepareStatement(sql3);
			 int m=pre.executeUpdate();
			 System.out.println("zw2");
			 if(m>0){
				 System.out.println("zw3");
	        	 insert=1;
			 }
			 }else{
			System.out.println(pre.executeQuery().next());
				 String sql = "insert into yzm (验证码,手机,创建时间)values(?,?,?)";
					
				 pre = conn.prepareStatement(sql);
				 pre.setString(1,u.getCode());
		         pre.setString(2, u.getSj());
		         pre.setLong(3, u.getCreateTime());
		         int i = pre.executeUpdate();
		         System.out.println("zw4");
		         if(i>0){
		        	 System.out.println("zw5");
		        	 insert=1;
		         }
				 
				
			 }
		
		} catch (Exception e) {
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
	//验证码删除
	public int yzmdate(jcxxbean x) throws SQLException {
		int yzmdate=0;
		try {
			conn = dbConnection.getConnection();
			String sql="delete from yzm where 手机='"+x.getSj()+"'";
			pre = conn.prepareStatement(sql);
			 int m=pre.executeUpdate();
			 if(m>0){
	        	 yzmdate=1;
			 }
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
            try {
                dbConnection.dbClose(conn, pre, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
		return yzmdate;
	}
	//验证码获取
	public List<jcxxbean> yzmpd(String sjh){
		List<jcxxbean> list=new ArrayList<jcxxbean>();
		jcxxbean jcbn;
		try {
		    conn=dbConnection.getConnection();
			
			String sql="select * from yzm where 手机='"+sjh+"'";
			pre=conn.prepareStatement(sql);
			rs=pre.executeQuery();
			while(rs.next()){
				jcbn=new jcxxbean();
				jcbn.setSj(rs.getString("手机"));
				jcbn.setCode(rs.getString("验证码"));
				jcbn.setCreateTime(rs.getLong("创建时间"));
				list.add(jcbn);
				
			}
			
		
		}
		catch (Exception g) {
			g.printStackTrace();
			
		}
		finally {
            try {
                dbConnection.dbClose(conn, pre, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
		return list;
	}
	
	
	
}
