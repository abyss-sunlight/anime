package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javaBean.jcxxbean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.jcxxdao;
import Dao.yzmdao;



public class loginservlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public loginservlet() {
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
		String verifyCode = request.getParameter("verifyCode");
		yzmdao ydo=new yzmdao();
			List<jcxxbean> pd=ydo.yzmpd(mobile);
			if(pd==null){
				renderData(response, "�ֻ��Ŵ���");
				return ;
			}
			jcxxbean jcbean=pd.get(0);
			
		
		jcbean.setMm(password);
		System.out.println("�ֻ��ţ�"+jcbean.getSj());
		System.out.println("��֤�룺"+jcbean.getCode());
		System.out.println("��֤�뽨��ʱ�䣺"+jcbean.getCreateTime());
		if(!jcbean.getSj().equals(mobile)){
			renderData(response, "�ֻ��Ŵ���");
			return ;
		}
		if(!jcbean.getCode().equals(verifyCode)){
			renderData(response, "��֤�����");
			return ;
		}
		if((System.currentTimeMillis() -jcbean.getCreateTime()) > 1000 * 60 * 10){
			renderData(response, "��֤���ѹ���");
			return ;
		}
		
		//����ҵ�����
		jcxxdao dao=new jcxxdao();
		
			int a=dao.insert(jcbean);
			if(a==1){
				int yzmpd=ydo.yzmdate(jcbean);
				if(yzmpd==1){
					renderData(response, mobile);
					}else{
						renderData(response,"�������");
					}
				}else{if(a==2){
					renderData(response,jcbean.getFankui() );
				}else{
					renderData(response, "ע��ʧ��");
				}
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
