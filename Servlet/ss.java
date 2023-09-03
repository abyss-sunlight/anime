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

import Dao.ssdao;
import Dao.transmitdao;

public class ss extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ss() {
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

		String mobile=request.getParameter("mobile");
		String ss=request.getParameter("ss");
		transmitdao td=new transmitdao();
		ssdao sd=new ssdao();
		List<jcxxbean> list1=td.sjcx(mobile);
		List<spbean> list2=sd.spcx(ss);
		
		if(list2==null){
			JSONObject obj = new JSONObject();
			jcxxbean jb=list1.get(0);
			obj.put("zhmc", jb.getZhmc());
			obj.put("tx", jb.getTp());
			obj.put("jgsl", "查无结果");
			renderData(response,obj);
		}else{
		JSONObject obj = new JSONObject();
		jcxxbean jb=list1.get(0);
		obj.put("zhmc", jb.getZhmc());
		obj.put("tx", jb.getTp());
		obj.put("jgsl", list2.size());
		for(int a=0;a<list2.size();a++){
			spbean jcb2=list2.get(a);
			int b=a+1;
			obj.put("name"+b, jcb2.getName());
			obj.put("jis"+b, jcb2.getJis());
			obj.put("tp"+b, jcb2.getTp());
			obj.put("id"+b, jcb2.getLx());
			
		}
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
