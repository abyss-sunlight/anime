package Dao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.UUID;
import javaBean.jcxxbean;

import com.sun.*;

import dbConnection.dbConnection;


import sun.misc.BASE64Decoder;

public class sctpdao {
	Connection conn = null;// 塞在外面 全局
	PreparedStatement pre = null;

//改头像
public int ggtx(String mobile,String htmlpath){
	int a=0;
	try{
		conn=dbConnection.getConnection();
		String sql="update jcxx set 头像='"+htmlpath+"' where 手机='"+mobile+"'";;
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