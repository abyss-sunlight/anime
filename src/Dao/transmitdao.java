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
	Connection conn = null;// �������� ȫ��
	PreparedStatement pre = null;
	//�ֻ��Ų�ѯjcxx
	public List<jcxxbean> sjcx(String mobile){
		List<jcxxbean> list=new ArrayList<jcxxbean>();
		try {
			
		    conn=dbConnection.getConnection();
			String sql="select * from jcxx where �ֻ�='"+mobile+"'";
			pre=conn.prepareStatement(sql);
			ResultSet rs=pre.executeQuery();
			jcxxbean stu;
			while(rs.next()){
				//�����������ݼ���ʹ������һ�У�����
				//��һ��������Ϣ��װ��stulnfo����
				stu=new jcxxbean();
				
				stu.setZhmc(rs.getString("�˺�����"));
				stu.setTp(rs.getString("ͷ��"));
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
			String sql2="select ���� from zykml where id='"+id+"'";
			pre=conn.prepareStatement(sql2);
			ResultSet rs=pre.executeQuery();
			spbean sp;
			while(rs.next()){
				jis=rs.getString("����");
			}
			
			String sql="select * from zyk where id='"+id+"' and ����='1'";
			pre=conn.prepareStatement(sql);
			rs=pre.executeQuery();
			while(rs.next()){
				//�����������ݼ���ʹ������һ�У�����
				//��һ��������Ϣ��װ��stulnfo����
				sp=new spbean();
				sp.setDq(rs.getString("����"));
				sp.setJtjs(rs.getString("�������"));
				sp.setName(rs.getString("����"));
				Date date = simpleDateFormat.parse(rs.getString("���"));
				sp.setSjian(date);
				sp.setSp(rs.getString("��Ƶ"));
				sp.setTp(rs.getString("ͼƬ"));
				sp.setLx1(rs.getString("����1"));
				sp.setJis(jis);
				if(rs.getString("����2")!=null){
				if(rs.getString("����3")==null){
					sp.setLx2(rs.getString("����2"));
				}else{
					sp.setLx3(rs.getString("����3"));
					sp.setLx2(rs.getString("����2"));
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
