package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javaBean.jcxxbean;
import javaBean.spbean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import Dao.conentdao;

public class conent extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public conent() {
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

		String[] id=request.getParameterValues("data[]"); 
		conentdao cd=new conentdao();
		List<spbean> list=cd.body(id);
		JSONObject obj= new JSONObject();
		for(int i=0;i<list.size();i++){
			spbean jcb=list.get(i);
			int b=i+1;
			
			obj.put("tp"+b,jcb.getTp());
			obj.put("jis"+b,jcb.getJis());
			obj.put("name"+b,jcb.getName());
		}
		obj.put("jgsl",list.size());
		renderData(response,obj);
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
