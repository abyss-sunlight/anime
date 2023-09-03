package Servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import Dao.grxxdao;

public class ggtx extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ggtx() {
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
	public void doPost(HttpServletRequest  request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String data=request.getParameter("data");
		System.out.print(data);
		/*String b1="";
		String b2="";
		String[] s = request.getParameterValues("data2");
		String mobile=s[0];
		System.out.println(mobile);
		int cd=Integer.parseInt(s[1]);
		System.out.println(cd);
		for(int i=2;i<cd;i++){
			 b1=s[i];
			b2=b2+b1;
			System.out.println("base"+b1);
		}
		grxxdao gd=new grxxdao();
		byte[] file= DatatypeConverter.parseBase64Binary(b2);
		try{
			
			int g=gd.ggtx(mobile, file);
			if(g==1){
				renderData(response,b2);
			}else{
				renderData(response,"¸ü¸ÄÊ§°Ü ");
			}
		}catch(Exception e){
			
		}*/
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
