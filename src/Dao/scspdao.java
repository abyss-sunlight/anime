package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javaBean.spbean;

import dbConnection.dbConnection;

public class scspdao {
	Connection conn = null;// �������� ȫ��
	PreparedStatement pre = null;
	//��ѯ�ö����Ƿ����
	public int spmlcx(String name,spbean sb){
		int a=0;
		try{
			conn=dbConnection.getConnection();
			String sql="select id from zykml where ����='"+name+"'";
			pre=conn.prepareStatement(sql);
			ResultSet rs=pre.executeQuery();
			if(rs.next()==false){
				sb.setCz(0);
				String sql2="select max(id) from zykml";
				pre=conn.prepareStatement(sql2);
				rs=pre.executeQuery();
					if(rs.next()){
						a=rs.getInt(1)+1;
						System.out.println("2:"+a);
				}
				
			}else{
				sb.setCz(1);
				String sql3="select id from zykml  where ����='"+name+"'";
				pre=conn.prepareStatement(sql3);
				rs=pre.executeQuery();
				while(rs.next()){
					a=rs.getInt("id");
					System.out.println("3:"+a);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("4:"+a);
		return a;
	}
	//�����µ���Դ��Ŀ¼��Ϣ
	public int cjzykml(int id ,spbean sb){
		java.sql.Date sqlDate = new java.sql.Date(sb.getSjian().getTime());
		int b=0;
		try{
		if(sb.getCz()==0){
			conn = dbConnection.getConnection();
				String sql = "insert into zykml (ͼƬ,�������,����,����,����1,����2,����3,���,����)values(?,?,?,?,?,?,?,?,?)";
				
				 pre = conn.prepareStatement(sql);
				 pre.setString(1,sb.getTp());
		         pre.setString(2, sb.getJtjs());
		         pre.setString(3, "1");
		         pre.setString(4, sb.getName());
		         pre.setString(5, sb.getLx1());
		         pre.setString(6, sb.getLx2());
		         pre.setString(7, sb.getLx3());
		         pre.setDate(8, sqlDate);
		         pre.setString(9, sb.getDq());
		         int i = pre.executeUpdate();
		         if(i>0){
		        	 String sql2 = "insert into zyk (��Ƶ,ͼƬ,id,�������,����,����,����1,����2,����3,���,����)values(?,?,?,?,?,?,?,?,?,?,?)";
						
					 pre = conn.prepareStatement(sql2);
					 pre.setString(1,sb.getSp());
					 pre.setString(2,sb.getTp());
					 pre.setInt(3, id);
			         pre.setString(4, sb.getJtjs());
			         pre.setString(5, sb.getJis());
			         pre.setString(6, sb.getName());
			         pre.setString(7, sb.getLx1());
			         pre.setString(8, sb.getLx2());
			         pre.setString(9, sb.getLx3());
			         pre.setDate(10, sqlDate);
			         pre.setString(11, sb.getDq());
			         int s = pre.executeUpdate();
			         if(s>0){
			        	 b=1;
			         }
		         }
		}else{
			String js=null;
			String sql5="select ���� from zykml where id='"+id+"'";
			String sql4="select ���� from zyk where id='"+id+"'";
			pre = conn.prepareStatement(sql4);
			ResultSet rs=pre.executeQuery();
			while(rs.next()){
				if(sb.getJis().equals(rs.getString("����"))){
					b=2;
					return b;
				}
			}
			pre = conn.prepareStatement(sql5);
			rs=pre.executeQuery();
			while(rs.next()){
				js=rs.getString("����");
				int j=Integer.parseInt(js)+1;
				js=Integer.toString(j) ;
			}
			String sql6="update zykml set ����='"+js+"' where id='"+id+"'";
			String sql3 = "insert into zyk (��Ƶ,ͼƬ,id,�������,����,����,����1,����2,����3,���,����)values(?,?,?,?,?,?,?,?,?,?,?)";
			
			 pre = conn.prepareStatement(sql3);
			 pre.setString(1,sb.getSp());
			 pre.setString(2,sb.getTp());
			 pre.setInt(3, id);
	         pre.setString(4, sb.getJtjs());
	         pre.setString(5, sb.getJis());
	         pre.setString(6, sb.getName());
	         pre.setString(7, sb.getLx1());
	         pre.setString(8, sb.getLx2());
	         pre.setString(9, sb.getLx3());
	         pre.setDate(10, sqlDate);
	         pre.setString(11, sb.getDq());
	         int s = pre.executeUpdate();
	         if(s>0){
	        	 pre = conn.prepareStatement(sql6);
	        	 int y = pre.executeUpdate();
	        	 if(y>0){
	        		b=1; 
	        	 }
	         }
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return b;
	}
}
