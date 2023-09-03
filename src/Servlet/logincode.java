package Servlet;

import java.io.IOException;
import java.util.Random;
import javaBean.jcxxbean;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import Dao.yzmdao;

public class logincode extends HttpServlet {
	
	//短信平台相关参数
	
       
    public logincode() {
        super();
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		try {
			String mobile = request.getParameter("mobile");
			//生成6位验证码
			String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
			//发送短信
			HttpClient client = new HttpClient();
			 PostMethod post = new PostMethod("https://utf8api.smschinese.cn/"); 
			 post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
			 NameValuePair[] data =new NameValuePair("smsMob",mobile),new NameValuePair("smsText","[灵星二次元动漫]您的验证码为:" + verifyCode + "，注:该码有效期为10分钟，该码只能使用一次且上一次发送的验证码失效!")};//前面两个参数已删除
		        post.setRequestBody(data);
		        client.executeMethod(post);
		        Header[] headers = post.getResponseHeaders();
		        int statusCode = post.getStatusCode();
		        System.out.println("statusCode:"+statusCode); //HTTP状态码
		        for(Header h : headers){
		            System.out.println(h.toString());
		        }
		        String result = new String(post.getResponseBodyAsString().getBytes("utf-8")); 
		        System.out.println(result);
		       int result2=Integer.parseInt(result);
		        post.releaseConnection();
			if(result2 <= 0){//发送短信失败
				
				renderData(response, "短信发送错误:"+result);
				  
				return; 
			}
			//封装
			jcxxbean jcxxbean=new jcxxbean();
			jcxxbean.setCode(verifyCode);
			jcxxbean.setCreateTime( System.currentTimeMillis());
			jcxxbean.setSj( mobile);
			yzmdao yzmdao=new yzmdao();
			int insertyzm=yzmdao.insertyzm(jcxxbean);
			if(insertyzm==1){
				renderData(response, mobile);
				return ;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		renderData(response, "fail2");
	}
	
	protected void renderData(HttpServletResponse response, String data){
		try {
			response.setContentType("text/plain;charset=UTF-8");
			response.getWriter().write(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
