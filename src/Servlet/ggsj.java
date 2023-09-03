package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javaBean.jcxxbean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.grxxdao;
import Dao.yzmdao;

public class ggsj extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ggsj() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String mobile=request.getParameter("mobile");
		String code=request.getParameter("code");
			String mobileg=request.getParameter("mobileg");
		yzmdao ydo=new yzmdao();
try{
			List<jcxxbean> pd=ydo.yzmpd(mobileg);
			if(pd==null){
				renderData(response, "手机号错误");
				return ;
			}
			//获取验证码数值
			jcxxbean jcbean=pd.get(0);
			
		if(!jcbean.getSj().equals(mobileg)){
			renderData(response, "手机号错误");
			return ;
		}
		if(!jcbean.getCode().equals(code)){
			renderData(response, "验证码错误");
			return ;
		}
		if((System.currentTimeMillis() -jcbean.getCreateTime()) > 1000 * 60 * 10){
			renderData(response, "验证码已过期");
			return ;
		}
		grxxdao gd=new grxxdao();
		int g=gd.ggsj(mobile, mobileg);
		if(g==1){
			ydo.yzmdate(jcbean);
			renderData(response,mobileg);
		}else{
			renderData(response,"更改失败 ");
		}
}catch(Exception e){
	e.printStackTrace();
}

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
