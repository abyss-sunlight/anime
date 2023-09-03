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

import net.sf.json.JSONObject;

public class ggxx extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ggxx() {
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
		String password=request.getParameter("password");
		String zhmc=request.getParameter("zhmc");
		String sex=request.getParameter("sex");
		String sr=request.getParameter("sr");
		jcxxbean j=new jcxxbean();
		j.setSj(mobile);
		j.setMm(password);
		j.setSsr(sr);
		j.setZhmc(zhmc);
		j.setXb(sex);
		grxxdao gd=new grxxdao();
		try{
		int g=gd.ggxx(j);
		if(g==1){
			renderData(response,"yes");
		}else{
			renderData(response,"no");
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	protected void renderData(HttpServletResponse response,String data){
		try {
			response.setContentType("text/html;charset=UTF-8");
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
