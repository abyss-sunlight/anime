package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javaBean.jcxxbean;

import dbConnection.dbConnection;

public class loginpddao {
	Connection conn = null;// �������� ȫ��
	PreparedStatement pre = null;
	public List<jcxxbean> loginpd(String sj) throws SQLException {
		
		List<jcxxbean> list=new ArrayList<jcxxbean>();
		jcxxbean jcb;
	conn = dbConnection.getConnection();
	String sql2="select * from jcxx where �ֻ�='"+sj+"'";
	pre=conn.prepareStatement(sql2);
	ResultSet rs=pre.executeQuery();
	while(rs.next()){
		jcb=new jcxxbean();
		jcb.setSj(sj);
		jcb.setZhmc(rs.getString("�˺�����"));
		jcb.setTp(rs.getString("ͷ��"));
		list.add(jcb);
	}
	
	return list;
	}
}
