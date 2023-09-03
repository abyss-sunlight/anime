package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javaBean.jcxxbean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import Dao.grxxdao;

public class grxx extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public grxx() {
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
		grxxdao gd=new grxxdao();
		List<jcxxbean> list=gd.grxx(mobile);
		for(int i=0;i<list.size();i++){
			jcxxbean jcb=list.get(i);
			JSONObject obj = new JSONObject();
			obj.put("mobile", jcb.getSj());
	        obj.put("password", jcb.getMm());
	        obj.put("zhmc", jcb.getZhmc());
	        obj.put("gxqm",jcb.getGxqm());
	        obj.put("sex",jcb.getXb());
	        obj.put("sr",jcb.getSsr());
	        obj.put("tx",jcb.getTp());
	        renderData(response,obj);
	        
		}
	}
	protected void renderData(HttpServletResponse response,JSONObject obj){
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(obj.toString());
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
