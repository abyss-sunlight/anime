package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javaBean.jcxxbean;
import javaBean.spbean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import Dao.transmitdao;

public class transmit extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public transmit() {
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
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd");
		String mobile=request.getParameter("mobile");
		int id=Integer.parseInt(request.getParameter("id"));
		transmitdao tt=new transmitdao();
		List<jcxxbean> list1=tt.sjcx(mobile);
		List<spbean> list2=tt.spcx(id);
		JSONObject obj = new JSONObject();
			jcxxbean jcb=list1.get(0);
			spbean jcb2=list2.get(0);
	        obj.put("zhmc", jcb.getZhmc());
	        obj.put("tx",jcb.getTp());
	        obj.put("tp",jcb2.getTp());
	        obj.put("sp",jcb2.getSp());
	        obj.put("sjian",simpleDateFormat.format(jcb2.getSjian()));
	        obj.put("jtjs",jcb2.getJtjs());
	        obj.put("jis",jcb2.getJis());
	        obj.put("dq",jcb2.getDq());
	        obj.put("name",jcb2.getName());
	       String lx1=jcb2.getLx1();
	       String lx=lx1;
	        if(jcb2.getLx2()!=null){
				if(jcb2.getLx3()==null){
					String lx2=jcb2.getLx2();
					lx=lx1+","+lx2;
				}else{
					String lx2=jcb2.getLx2();
					String lx3=jcb2.getLx3();
					 lx=lx1+","+lx2+","+lx3;
				}
				}
	      
	        obj.put("lx",lx);
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
