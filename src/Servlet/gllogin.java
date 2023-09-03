package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javaBean.jcxxbean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.gldao;
import Dao.jcxxdao;
import Dao.yzmdao;

public class gllogin extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public gllogin() {
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
		try {
			String password = request.getParameter("password");
			String mobile = request.getParameter("mobile");
			String pd=request.getParameter("pd");
			jcxxbean bean=new jcxxbean();
			bean.setMm(password);
			bean.setSj(mobile);
			gldao gd=new gldao();
			int a=gd.pd(bean, pd);
			
				if(a==0){
					renderData(response,"Ê§°Ü");
					return;
				}
				if(a==1){
					renderData(response,"register");
					return;
				}
				if(a==2){
					renderData(response,"¸ÃÊÖ»úÒÑ×¢²á");
					return;
				}
				if(a==3){
					renderData(response,"¸ÃÊÖ»úºÅÎ´×¢²á");
					return;
				}
				if(a==4){
					renderData(response,"ÃÜÂë´íÎó");
					return;
				}
				if(a==5){
					renderData(response,"login");
					return;
				}
			
			
			} catch (Exception e) {
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
