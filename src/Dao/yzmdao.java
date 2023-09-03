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
	Connection conn = null;// �������� ȫ��
	PreparedStatement pre = null;
	ResultSet rs=null;
	// ��֤���ж�
	public int insertyzm(jcxxbean u) throws SQLException {

		
		int insert=0;
		try {
		conn = dbConnection.getConnection();
		String sql2="select * from yzm where �ֻ�='"+u.getSj()+"'";
		 pre = conn.prepareStatement(sql2);
		if(pre.executeQuery().next()){
			System.out.println(pre.executeQuery().next());
			 String sql3="update yzm set ��֤��='"+u.getCode()+"' , ����ʱ��='"+u.getCreateTime()+"' where �ֻ�='"+u.getSj()+"'";
			 pre = conn.prepareStatement(sql3);
			 int m=pre.executeUpdate();
			 System.out.println("zw2");
			 if(m>0){
				 System.out.println("zw3");
	        	 insert=1;
			 }
			 }else{
			System.out.println(pre.executeQuery().next());
				 String sql = "insert into yzm (��֤��,�ֻ�,����ʱ��)values(?,?,?)";
					
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
	//��֤��ɾ��
	public int yzmdate(jcxxbean x) throws SQLException {
		int yzmdate=0;
		try {
			conn = dbConnection.getConnection();
			String sql="delete from yzm where �ֻ�='"+x.getSj()+"'";
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
	//��֤���ȡ
	public List<jcxxbean> yzmpd(String sjh){
		List<jcxxbean> list=new ArrayList<jcxxbean>();
		jcxxbean jcbn;
		try {
		    conn=dbConnection.getConnection();
			
			String sql="select * from yzm where �ֻ�='"+sjh+"'";
			pre=conn.prepareStatement(sql);
			rs=pre.executeQuery();
			while(rs.next()){
				jcbn=new jcxxbean();
				jcbn.setSj(rs.getString("�ֻ�"));
				jcbn.setCode(rs.getString("��֤��"));
				jcbn.setCreateTime(rs.getLong("����ʱ��"));
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
